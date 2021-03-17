package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected void doUpdate(Resume resume, Resume uuid) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doSave(Resume resume, Resume searchKey) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Resume resume) {
        return  resume;
    }

    @Override
    protected void doDelete(Resume resume) {
        mapStorage.remove(( resume).getUuid());
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return mapStorage.get(uuid);
    }

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    public List<Resume> doCopyAll() {
        return new ArrayList<>(mapStorage.values());
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
