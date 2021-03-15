package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    static final Comparator<Resume> COMPARATOR = Comparator.comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    @Override
    public void update(Resume resume) {
        updateStorage(resume, getSearchKeyIfExist(resume.getUuid()));
    }

    abstract void updateStorage(Resume resume, int searchKey);

    @Override
    public void save(Resume resume) {
        int searchKey = getSearchKey(resume.getUuid());
        if (searchKey >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveToStorage(resume, searchKey);
    }

    abstract void saveToStorage(Resume resume, int searchKey);

    @Override
    public Resume get(String uuid) {
        return getFromStorage(uuid, getSearchKeyIfExist(uuid));
    }

    abstract Resume getFromStorage(String uuid, int searchKey);

    @Override
    public void delete(String uuid) {
        deleteFromStorage(uuid, getSearchKeyIfExist(uuid));
    }

    abstract void deleteFromStorage(String uuid, int searchKey);

    abstract Integer getSearchKey(String uuid);

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
