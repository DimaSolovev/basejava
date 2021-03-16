package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storageList = new ArrayList<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storageList.size(); i++) {
            if (storageList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    public void doUpdate(Resume resume, Object index) {
        storageList.set((Integer) index, resume);
    }

    @Override
    public void doSave(Resume resume, Object index) {
        storageList.add(resume);
    }

    @Override
    public Resume doGet(Object index) {
        return storageList.get((Integer) index);
    }

    @Override
    public void doDelete(Object index) {
        storageList.remove(((Integer) index).intValue());
    }

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    List<Resume> getListResumes() {
        return new ArrayList<>(storageList);
    }

    @Override
    public int size() {
        return storageList.size();
    }
}
