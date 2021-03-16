package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    static final Comparator<Resume> COMPARATOR = Comparator.comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    abstract void doUpdate(Resume resume, int searchKey);

    abstract void doSave(Resume resume, int searchKey);

    abstract Resume doGet(String uuid, int searchKey);

    abstract void doDelete(String uuid, int searchKey);

    abstract Integer getSearchKey(String uuid);

    @Override
    public void update(Resume resume) {
        doUpdate(resume, getSearchKeyIfExist(resume.getUuid()));
    }

    @Override
    public void save(Resume resume) {
        int searchKey = getSearchKey(resume.getUuid());
        if (searchKey >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        doSave(resume, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        return doGet(uuid, getSearchKeyIfExist(uuid));
    }

    @Override
    public void delete(String uuid) {
        doDelete(uuid, getSearchKeyIfExist(uuid));
    }

    private int getSearchKeyIfExist(String uuid) {
        int searchKey = getSearchKey(uuid);
        if (searchKey < 0) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public List<Resume> getAllSorted(){
        List<Resume> listResumes = getListResumes();
        listResumes.sort(COMPARATOR);
        return listResumes;
    }
    abstract List<Resume> getListResumes();
}
