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
        if (size == storage.length) {
            System.out.println("ERROR, storage is full");
            return;
        }
        if (getIndex(resume.getUuid()) > 0) {
            System.out.println("ERROR, storage already contains resume " + resume.getUuid());
            return;
        }
        storage[size+1] = resume;
        size++;
    }
}
