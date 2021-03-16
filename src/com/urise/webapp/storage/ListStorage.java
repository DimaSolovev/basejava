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

    @Override
    protected boolean isExist(Object searchKey) {
        return false;
    }

    public void doUpdate(Resume resume, int index) {
        storageList.set(index, resume);
    }

    public void doSave(Resume resume, int index) {
        storageList.add(resume);
    }

    public Resume doGet(int index) {
        return storageList.get(index);
    }

    @Override
    public void doDelete(int index) {
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
