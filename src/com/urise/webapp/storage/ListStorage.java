package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storageList = new ArrayList<>();

    protected Integer getSearchKey(String uuid) {
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

    public void saveToStorage(Resume resume, int index) {
        storageList.add(resume);
    }

    public Resume getFromStorage(String uuid, int index) {
        return storageList.get(index);
    }

    @Override
    public void deleteFromStorage(String uuid, int index) {
        storageList.remove(index);
    }

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    List<Resume> getListResumes() {
        return storageList;
    }

    @Override
    public int size() {
        return storageList.size();
    }
}
