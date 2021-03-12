package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {

    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    void updateStorage(Resume resume, int index) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    void saveToStorage(Resume resume, int index) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    Resume getFromStorage(String uuid, int index) {
        return mapStorage.get(uuid);
    }

    @Override
    void deleteFromStorage(String uuid, int index) {
        mapStorage.remove(uuid);
    }

    @Override
    Integer getSearchKey(String uuid) {
        return mapStorage.containsKey(uuid) ? 0 : -1;
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] arr = mapStorage.values().toArray(new Resume[0]);
        Arrays.sort(arr);
        return arr;
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
