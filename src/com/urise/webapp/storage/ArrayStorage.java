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

    public void save(Resume resume) {
        super.save(resume);
        if (getIndex(resume.getUuid()) != -1) {
            System.out.println("ERROR, storage already contains resume " + resume.getUuid());
            return;
        }
        storage[size] = resume;
        size++;
    }


    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR, storage doesn't contain resume  " + uuid);
            return;
        }
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }
}
