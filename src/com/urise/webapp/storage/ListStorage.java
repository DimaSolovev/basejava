package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage <Integer>{

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
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    public void doUpdate(Resume resume, Integer index) {
        storageList.set( index, resume);
    }

    @Override
    public void doSave(Resume resume, Integer index) {
        storageList.add(resume);
    }

    @Override
    public Resume doGet(Integer index) {
        return storageList.get( index);
    }

    @Override
    public void doDelete(Integer index) {
        storageList.remove(( index).intValue());
    }

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(storageList);
    }

    @Override
    public int size() {
        return storageList.size();
    }
}
