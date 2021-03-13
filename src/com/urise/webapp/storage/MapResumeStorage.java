package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage{

    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    void updateStorage(Resume resume, int searchKey) {

    }

    @Override
    void saveToStorage(Resume resume, int searchKey) {

    }

    @Override
    Resume getFromStorage(String uuid, int searchKey) {
        return null;
    }

    @Override
    void deleteFromStorage(String uuid, int searchKey) {

    }

    @Override
    Integer getSearchKey(String uuid) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public List<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
