package com.urise.webapp.storage;

import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public int size() {
        return size;
    }

    @Override
    public Resume doGet(Integer index) {
        return storage[ index];
    }

    @Override
    public void doUpdate(Resume resume, Integer index) {
        storage[ index] = resume;
    }

    public void doSave(Resume resume, Integer index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertElement(resume,  index);
            size++;
        }
    }

    @Override
    public void doDelete(Integer index) {
        fillDeletedElement( index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public List<Resume> doCopyAll() {
        Resume[] resumes = new Resume[size];
        if (size >= 0) System.arraycopy(storage, 0, resumes, 0, size);
        return Arrays.asList(resumes);
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);

    protected abstract Integer getSearchKey(String uuid);

    @Override
    protected boolean isExist(Integer index) {
        return  index >= 0;
    }
}
