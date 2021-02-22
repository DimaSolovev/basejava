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

    public void saveStorage(Resume resume) {
        int index = getIndex(resume.getUuid());
        index = -(index + 1);
        storage[index] = resume;
    }

    public void deleteStorage(String uuid) {
        for (int i = getIndex(uuid); i < size; i++) {
            storage[i] = storage[i + 1];
            storage[i + 1] = null;
        }
    }
}