package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
//        int searchKey = getSearchKey(resume.getUuid());
//        if (searchKey < 0) {
//            throw new NotExistStorageException(resume.getUuid());
//        } else {
//            updateStorage(resume, searchKey);
//        }
        updateStorage(resume, checkNotExistException(resume.getUuid()));
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
//        int searchKey = getSearchKey(uuid);
//        if (searchKey < 0) {
//            throw new NotExistStorageException(uuid);
//        }
        return getFromStorage(uuid, checkNotExistException(uuid));
    }

    abstract Resume getFromStorage(String uuid, int searchKey);

    @Override
    public void delete(String uuid) {
//        int searchKey = getSearchKey(uuid);
//        if (searchKey < 0) {
//            throw new NotExistStorageException(uuid);
//        }
        //deleteFromStorage(uuid, searchKey);
        deleteFromStorage(uuid, checkNotExistException(uuid));
    }

    abstract void deleteFromStorage(String uuid, int searchKey);

    abstract int getSearchKey(String uuid);

    private int checkNotExistException(String uuid) {
        int searchKey = getSearchKey(uuid);
        if (searchKey < 0) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}
