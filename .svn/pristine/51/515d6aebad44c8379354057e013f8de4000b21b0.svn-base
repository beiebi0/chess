package org.alexanderoskotsky.hw10;

import com.google.gwt.storage.client.Storage;

public class StorageHelper {
	private Storage storage;

	public StorageHelper() {
		try {
			storage = Storage.getLocalStorageIfSupported();
		} catch (Throwable e) {

		}
	}

	public void saveInStorage(String key, String value) {
		if (storage != null) {
			storage.setItem(key, value);
		}
	}

	public String getFromStorage(String key) {
		if (storage != null) {
			return storage.getItem(key);
		}
		return null;
	}

	public void remove(String key) {
		if (storage != null) {
			storage.removeItem(key);
		}
	}
}
