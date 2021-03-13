package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class MapResumeStorage extends AbstractStorage{
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
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
