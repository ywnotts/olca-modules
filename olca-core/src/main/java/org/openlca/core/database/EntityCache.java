package org.openlca.core.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.openlca.core.model.AbstractEntity;
import org.openlca.core.model.descriptors.ActorDescriptor;
import org.openlca.core.model.descriptors.BaseDescriptor;
import org.openlca.core.model.descriptors.FlowDescriptor;
import org.openlca.core.model.descriptors.FlowPropertyDescriptor;
import org.openlca.core.model.descriptors.ImpactCategoryDescriptor;
import org.openlca.core.model.descriptors.ImpactMethodDescriptor;
import org.openlca.core.model.descriptors.ProcessDescriptor;
import org.openlca.core.model.descriptors.ProductSystemDescriptor;
import org.openlca.core.model.descriptors.ProjectDescriptor;
import org.openlca.core.model.descriptors.SourceDescriptor;
import org.openlca.core.model.descriptors.UnitGroupDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * A loading cache for entities and descriptors. This cache is intended to be
 * used for caching entities that are used very often (like unit groups or flow
 * properties) and descriptors.
 */
public class EntityCache {

	private Logger log = LoggerFactory.getLogger(getClass());
	private LoadingCache<Key, Object> cache;

	public static EntityCache create(IDatabase database) {
		return new EntityCache(database);
	}

	private EntityCache(IDatabase database) {
		cache = CacheBuilder.newBuilder().build(new Loader(database));
	}

	public <T> T get(Class<T> clazz, long id) {
		try {
			Object obj = cache.get(Key.get(clazz, id));
			if (obj instanceof Optional)
				return null;
			return clazz.cast(obj);
		} catch (Exception e) {
			log.error("failed to get from cache " + clazz + " with id " + id, e);
			return null;
		}
	}

	public <T> Map<Long, T> getAll(Class<T> clazz, Collection<Long> ids) {
		List<Key> keys = new ArrayList<>(ids.size());
		for (long id : ids) {
			keys.add(Key.get(clazz, id));
		}
		try {
			Map<Key, Object> values = cache.getAll(keys);
			Map<Long, T> result = new HashMap<>(values.size());
			for (Key key : values.keySet()) {
				Object obj = values.get(key);
				if (obj instanceof Optional)
					continue;
				result.put(key.id, clazz.cast(obj));
			}
			return result;
		} catch (Exception e) {
			log.error("failed to get entities from cache: " + clazz, e);
			return Collections.emptyMap();
		}
	}

	public void invalidate(Class<?> clazz, long id) {
		cache.invalidate(Key.get(clazz, id));
	}

	public void refresh(Class<?> clazz, long id) {
		cache.refresh(Key.get(clazz, id));
	}

	public void invalidateAll() {
		cache.invalidateAll();
	}

	public void invalidateAll(Class<?> clazz, Collection<Long> ids) {
		List<Key> keys = new ArrayList<>(ids.size());
		for (long id : ids) {
			keys.add(Key.get(clazz, id));
		}
		cache.invalidateAll(keys);
	}

	private static class Key {

		private final Class<?> clazz;
		private final long id;

		private static Key get(Class<?> clazz, long id) {
			return new Key(clazz, id);
		}

		private Key(Class<?> clazz, long id) {
			this.clazz = clazz;
			this.id = id;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Key))
				return false;
			Key other = (Key) obj;
			return this.id == other.id
					&& Objects.equals(this.clazz, other.clazz);
		}

		@Override
		public int hashCode() {
			return Objects.hash(id, clazz);
		}
	}

	private static class Loader extends CacheLoader<Key, Object> {

		private Logger log = LoggerFactory.getLogger(getClass());
		private final IDatabase database;
		private final HashMap<Class<?>, BaseDao<?>> daos = new HashMap<>();
		private final HashMap<Class<?>, RootEntityDao<?, ?>> descriptorDaos = new HashMap<>();

		public Loader(IDatabase database) {
			this.database = database;
			registerDescriptorDaos(database);
		}

		/** Registers the DAOs for the descriptor types. */
		private void registerDescriptorDaos(IDatabase database) {
			log.trace("register descriptor DAOs");
			descriptorDaos.put(ActorDescriptor.class, new ActorDao(database));
			descriptorDaos.put(SourceDescriptor.class, new SourceDao(database));
			descriptorDaos.put(UnitGroupDescriptor.class, new UnitGroupDao(
					database));
			descriptorDaos.put(FlowPropertyDescriptor.class,
					new FlowPropertyDao(database));
			descriptorDaos.put(FlowDescriptor.class, new FlowDao(database));
			descriptorDaos.put(ProcessDescriptor.class,
					new ProcessDao(database));
			descriptorDaos.put(ProductSystemDescriptor.class,
					new ProductSystemDao(database));
			descriptorDaos.put(ImpactMethodDescriptor.class,
					new ImpactMethodDao(database));
			descriptorDaos.put(ProjectDescriptor.class,
					new ProjectDao(database));
			descriptorDaos.put(ImpactCategoryDescriptor.class,
					new ImpactCategoryDao(database));
		}

		@Override
		public Map<Key, Object> loadAll(Iterable<? extends Key> keys)
				throws Exception {
			Multimap<Class<?>, Long> idMap = ArrayListMultimap.create();
			for (Key key : keys)
				idMap.put(key.clazz, key.id);
			Set<Class<?>> classes = idMap.keySet();
			HashMap<Key, Object> result = new HashMap<>();
			for (Class<?> clazz : classes) {
				Collection<Long> ids = idMap.get(clazz);
				if (ids.isEmpty())
					continue;
				if (BaseDescriptor.class.isAssignableFrom(clazz))
					loadDescriptors(clazz, ids, result);
				else
					loadFullEntities(clazz, ids, result);
			}
			for (Key key : keys) {
				if (!result.containsKey(key))
					result.put(key, Optional.absent());
			}
			return result;
		}

		private void loadFullEntities(Class<?> clazz, Collection<Long> ids,
				HashMap<Key, Object> result) {
			BaseDao<?> dao = getDao(clazz);
			List<?> entities = dao.getForIds(new HashSet<>(ids));
			for (Object obj : entities) {
				AbstractEntity entity = (AbstractEntity) obj;
				result.put(Key.get(clazz, entity.getId()), entity);
			}
		}

		private void loadDescriptors(Class<?> clazz, Collection<Long> ids,
				HashMap<Key, Object> result) {
			RootEntityDao<?, ?> dao = descriptorDaos.get(clazz);
			if (dao == null) {
				log.error("unknown descriptor class {}, returning null", clazz);
				return;
			}
			List<? extends BaseDescriptor> descriptors = dao
					.getDescriptors(new HashSet<>(ids));
			for (BaseDescriptor descriptor : descriptors)
				result.put(Key.get(clazz, descriptor.getId()), descriptor);
		}

		@Override
		public Object load(Key key) throws Exception {
			if (key == null || key.clazz == null)
				return null;
			Object obj = null;
			if (BaseDescriptor.class.isAssignableFrom(key.clazz))
				obj = loadDescriptor(key);
			else
				obj = loadFull(key);
			return obj != null ? obj : Optional.absent();
		}

		private Object loadDescriptor(Key key) {
			RootEntityDao<?, ?> dao = descriptorDaos.get(key.clazz);
			if (dao == null) {
				log.error("unknown descriptor class {}, returning null",
						key.clazz);
				return null;
			}
			return dao.getDescriptor(key.id);
		}

		private Object loadFull(Key key) {
			BaseDao<?> dao = getDao(key.clazz);
			return dao.getForId(key.id);
		}

		private BaseDao<?> getDao(Class<?> clazz) {
			BaseDao<?> dao = daos.get(clazz);
			if (dao == null) {
				log.trace("register class {}", clazz);
				dao = new BaseDao<>(clazz, database);
				daos.put(clazz, dao);
			}
			return dao;
		}
	}

}
