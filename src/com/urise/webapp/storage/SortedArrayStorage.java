package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        index = -(index + 1);
        storage[index] = resume;
        size++;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        for (int i = index; i < size; i++) {
            storage[i] = storage[i + 1];
            storage[i + 1] = null;
        }
        size--;
    }
}