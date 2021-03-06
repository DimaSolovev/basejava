package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage  extends AbstractStorage{

    private Map<String,Resume> mapStorage = new HashMap<>();

    @Override
    public void save(Resume resume){
        mapStorage.put(resume.getUuid(),resume);
    }

    @Override
    public void delete(String uuid) {
        mapStorage.remove(uuid);
    }

    @Override
    public Resume get(String uuid) {
        return mapStorage.get(uuid);
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public Resume[] getAll() {
        return mapStorage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return mapStorage.size();
    }

    @Override
    public void update(Resume resume) {
        mapStorage.put(resume.getUuid(),resume);
    }

    @Override
    void updateStorage(Resume resume, int index) {

    }

    @Override
    void saveToStorage(Resume resume, int index) {

    }

    @Override
    Resume getFromStorage(String uuid, int index) {
        return null;
    }

    @Override
    void deleteFromStorage(String uuid, int index) {

    }

    @Override
    int getIndex(String uuid) {
        return 0;
    }
}
