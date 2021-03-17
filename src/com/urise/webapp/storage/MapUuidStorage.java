package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {

    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected void doUpdate(Resume resume, String uuid) {
        mapStorage.put( uuid, resume);
    }

    @Override
    protected void doSave(Resume resume, String uuid) {
        mapStorage.put( uuid, resume);
    }

    @Override
    protected Resume doGet(String uuid) {
        return mapStorage.get(uuid);
    }

    @Override
    protected void doDelete(String uuid) {
        mapStorage.remove(uuid);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String uuid) {
        return mapStorage.containsKey(uuid);
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
