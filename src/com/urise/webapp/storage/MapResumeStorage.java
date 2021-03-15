package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {

    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    void updateStorage(Resume resume, int searchKey) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    void saveToStorage(Resume resume, int searchKey) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    Resume getFromStorage(String uuid, int searchKey) {
        return mapStorage.get(uuid);
    }

    @Override
    void deleteFromStorage(String uuid, int searchKey) {
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

    public List<Resume> getListResumes() {
        return new ArrayList<>(mapStorage.values());
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
