package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    List<Resume> storageList = new ArrayList<>();

    protected int getIndex(String uuid) {
        for (int i = 0; i < storageList.size(); i++) {
            if (storageList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void updateStorage(Resume resume, int index) {
        storageList.set(index, resume);
    }

    public void saveStorage(Resume resume, int index) {
        storageList.add(resume);
    }

    public Resume getStorage(String uuid, int index) {
        return storageList.get(index);
    }

    @Override
    public void deleteStorage(String uuid, int index) {
        storageList.remove(index);
    }

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    public Resume[] getAll() {
        return storageList.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storageList.size();
    }
}
