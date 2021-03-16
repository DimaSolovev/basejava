package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;


    public int size() {
        return size;
    }

    public Resume doGet(String uuid, int index) {
        return storage[index];
    }

    public void doUpdate(Resume resume, int index) {
        storage[index] = resume;
    }

    public void doSave(Resume resume, int index) {
        if (size == storage.length) {
            throw new ExistStorageException(resume.getUuid());
        }
        insertElement(resume, index);
        size++;
    }

    public void doDelete(String uuid, int index) {
        fillDeletedElement(uuid, index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public List<Resume> getListResumes() {
        Resume[] resumes = new Resume[size];
        if (size >= 0) System.arraycopy(storage, 0, resumes, 0, size);
        return Arrays.asList(resumes);
    }

    abstract void insertElement(Resume resume, int index);

    abstract void fillDeletedElement(String uuid, int index);

    protected abstract Integer getSearchKey(String uuid);
}
