package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    static final Comparator<Resume> COMPARATOR = Comparator.comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    protected abstract void doUpdate(Resume resume, int searchKey);

    protected abstract void doSave(Resume resume, int searchKey);

    protected abstract Resume doGet(int searchKey);

    protected abstract void doDelete(int searchKey);

    protected abstract Integer getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

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
        return doGet(getSearchKeyIfExist(uuid));
    }

    @Override
    public void delete(String uuid) {
        doDelete(getSearchKeyIfExist(uuid));
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
