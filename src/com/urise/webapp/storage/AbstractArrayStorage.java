package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public int size() {
        return size;
    }

    public Resume getStorage(String uuid, int index) {
        return storage[index];
    }

    public void updateStorage(Resume resume, int index) {
        storage[index] = resume;
    }

    public void saveStorage(Resume resume, int index) {
        if (size == storage.length) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveIndex(resume, index);
        size++;
    }

    public void deleteStorage(String uuid, int index) {
        deleteIndex(uuid, index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        if (size >= 0) System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    abstract void saveIndex(Resume resume, int index);

    abstract void deleteIndex(String uuid, int index);

    abstract int getIndex(String uuid);
}
