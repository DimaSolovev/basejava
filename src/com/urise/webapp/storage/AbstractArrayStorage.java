package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;


    public int size() {
        return size;
    }

    public Resume getFromStorage(String uuid, int index) {
        return storage[index];
    }

    public void updateStorage(Resume resume, int index) {
        storage[index] = resume;
    }

    public void saveToStorage(Resume resume, int index) {
        if (size == storage.length) {
            throw new ExistStorageException(resume.getUuid());
        }
        insertElement(resume, index);
        size++;
    }

    public void deleteFromStorage(String uuid, int index) {
        fillDeletedElement(uuid, index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public List<Resume> getAllSorted() {
        Resume[] resumes = new Resume[size];
        if (size >= 0) System.arraycopy(storage, 0, resumes, 0, size);
        List<Resume> list = Arrays.asList(resumes);
        Collections.sort(list,COMPARATOR);
        return list;
    }

    abstract void insertElement(Resume resume, int index);

    abstract void fillDeletedElement(String uuid, int index);

    abstract Integer getSearchKey(String uuid);
}
