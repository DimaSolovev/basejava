package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void insertElement(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    public void fillDeletedElement(int index) {
        storage[index] = storage[size - 1];
    }
}