package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void insertElement(Resume resume, int index) {
        storage[size] = resume;
    }

    public void fillDeletedElement(String uuid, int index) {
        storage[index] = storage[size - 1];
    }
}