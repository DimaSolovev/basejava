package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {

    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected void doUpdate(Resume resume, Object uuid) {
        mapStorage.put((String) uuid, resume);
    }

    @Override
    protected void doSave(Resume resume, Object uuid) {
        mapStorage.put((String) uuid, resume);
    }

    @Override
    protected Resume doGet(Object uuid) {
        return mapStorage.get((String)uuid);
    }

    @Override
    protected void doDelete(Object uuid) {
        mapStorage.remove(uuid);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object uuid) {
        return mapStorage.containsKey((String)uuid);
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    public List<Resume> doCopyAll() {
        return new ArrayList<>(mapStorage.values());
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
