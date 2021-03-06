package org.openlca.core.matrix.cache;

import gnu.trove.map.hash.TLongObjectHashMap;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openlca.core.database.IDatabase;
import org.openlca.core.model.FlowType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class FlowTypeTable {

	private Logger log = LoggerFactory.getLogger(getClass());
	private IDatabase database;
	private final TLongObjectHashMap<FlowType> map = new TLongObjectHashMap<FlowType>();

	public static FlowTypeTable create(IDatabase database) {
		return new FlowTypeTable(database);
	}

	private FlowTypeTable(IDatabase database) {
		this.database = database;
		init();
	}

	public void reload() {
		map.clear();
	}

	private void init() {
		log.trace("initialize flow type index");
		try (Connection con = database.createConnection()) {
			String query = "select id, flow_type from tbl_flows";
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);
			while (result.next())
				fetchFlowType(result);
			result.close();
			statement.close();
			log.trace("{} flow types fetched", map.size());
		} catch (Exception e) {
			log.error("failed to initialize flow type index", e);
		}
	}

	private void fetchFlowType(ResultSet result) throws Exception {
		long id = result.getLong("id");
		String typeString = result.getString("flow_type");
		if (typeString == null)
			return;
		FlowType type = FlowType.valueOf(typeString);
		map.put(id, type);
	}

	public FlowType getType(long flowId) {
		return map.get(flowId);
	}

}
