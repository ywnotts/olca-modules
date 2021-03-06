package org.openlca.geo;

import org.openlca.core.database.IDatabase;
import org.openlca.core.database.NativeSql;
import org.openlca.core.matrix.LongPair;
import org.openlca.core.matrix.ProductIndex;
import org.openlca.util.BinUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Loads the KML features for a given set of process products from a database.
 */
class KmlLoader {

	private Logger log = LoggerFactory.getLogger(getClass());

	private final IDatabase database;
	private final ProductIndex index;

	private HashMap<Long, byte[]> processKmz = new HashMap<>();
	private HashMap<Long, Long> processLocations = new HashMap<>();
	private HashMap<Long, byte[]> locationKmz = new HashMap<>();
	private HashMap<Long, KmlFeature> locationFeatures = new HashMap<>();

	private KmlLoader(IDatabase database, ProductIndex index) {
		this.database = database;
		this.index = index;
	}

	static Map<LongPair, KmlFeature> load(IDatabase database, ProductIndex index) {
		return new KmlLoader(database, index).load();
	}

	private Map<LongPair, KmlFeature> load() {
		try {
			Set<Long> processIds = index.getProcessIds();
			queryProcessTable(processIds);
			queryLocationTable();
			Map<LongPair, KmlFeature> features = new HashMap<>();
			for (int i = 0; i < index.size(); i++) {
				LongPair processProduct = index.getProductAt(i);
				KmlFeature feature = getFeature(processProduct);
				if (feature != null)
					features.put(processProduct, feature);
			}
			return features;
		} catch (Exception e) {
			log.error("failed to get KML data from database", e);
			return Collections.emptyMap();
		}
	}

	private void queryProcessTable(final Set<Long> processIds) throws Exception {
		String query = "select id, f_location, kmz from tbl_processes";
		NativeSql.on(database).query(query, new NativeSql.QueryResultHandler() {
			@Override
			public boolean nextResult(ResultSet resultSet) throws SQLException {
				registerProcessRow(resultSet, processIds);
				return true;
			}
		});
	}

	private void registerProcessRow(ResultSet resultSet, Set<Long> processIds)
			throws SQLException {
		long id = resultSet.getLong("id");
		if (!processIds.contains(id))
			return;
		byte[] kmz = resultSet.getBytes("kmz");
		if (kmz != null)
			processKmz.put(id, kmz);
		else {
			long locationId = resultSet.getLong("f_location");
			if (resultSet.wasNull())
				return;
			processLocations.put(id, locationId);
		}
	}

	private void queryLocationTable() throws Exception {
		String query = "select id, kmz from tbl_locations";
		NativeSql.on(database).query(query, new NativeSql.QueryResultHandler() {
			@Override
			public boolean nextResult(ResultSet resultSet) throws SQLException {
				long id = resultSet.getLong("id");
				if (!processLocations.containsValue(id))
					return true;
				byte[] kmz = resultSet.getBytes("kmz");
				if (kmz != null)
					locationKmz.put(id, kmz);
				return true;
			}
		});
	}

	private KmlFeature getFeature(LongPair processProduct) {
		if (processProduct == null)
			return null;
		long processId = processProduct.getFirst();
		byte[] procKmz = processKmz.get(processId);
		if (procKmz != null)
			return createFeature(procKmz);
		Long locationId = processLocations.get(processId);
		if (locationId == null)
			return null;
		KmlFeature feature = locationFeatures.get(locationId);
		if (feature != null)
			return feature;
		byte[] locKmz = locationKmz.get(locationId);
		if (locKmz == null)
			return null;
		feature = createFeature(locKmz);
		locationFeatures.put(locationId, feature);
		return feature;
	}

	private KmlFeature createFeature(byte[] kmz) {
		if (kmz == null)
			return null;
		try {
			byte[] kmlBytes = BinUtils.unzip(kmz);
			String kml = new String(kmlBytes, "utf-8");
			return KmlFeature.parse(kml);
		} catch (Exception e) {
			log.error("failed to parse KMZ", e);
			return null;
		}
	}
}
