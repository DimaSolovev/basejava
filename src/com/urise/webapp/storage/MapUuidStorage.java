package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {

    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected void doUpdate(Resume resume, int index) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doSave(Resume resume, int index) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(String uuid, int index) {
        return mapStorage.get(uuid);
    }

    @Override
    protected void doDelete(String uuid, int index) {
        mapStorage.remove(uuid);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        return mapStorage.containsKey(uuid) ? 0 : -1;
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    public List<Resume> getListResumes() {
        return new ArrayList<>(mapStorage.values());
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
