package org.openlca.ilcd.io;

import java.util.Iterator;

public interface DataStore {

	<T> T get(Class<T> type, String id) throws DataStoreException;

	void put(Object obj, String id) throws DataStoreException;

	<T> void delete(Class<T> type, String id) throws DataStoreException;

	<T> Iterator<T> iterator(Class<T> type) throws DataStoreException;

	<T> boolean contains(Class<T> type, String id) throws DataStoreException;

}