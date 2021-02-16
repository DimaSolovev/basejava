package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private Resume[] storage = new Resume[10_000];
    private int size;

    public void update(Resume resume) {
        int index = getIndex (resume.getUuid());
        if (index == -1) {
            System.out.println("ERROR, storage doesn't contain resume " + resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("ERROR, storage is full");
            return;
        }
        if (getIndex (resume.getUuid()) != -1) {
            System.out.println("ERROR, storage already contains resume " + resume.getUuid());
            return;
        }
        storage[size] = resume;
        size++;
    }

    public Resume get(String uuid) {
        int index = getIndex (uuid);
        if (index == -1) {
            System.out.println("ERROR, storage doesn't contain resume " + uuid);
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex (uuid);
        if (index == -1) {
            System.out.println("ERROR, storage doesn't contain resume  " + uuid);
            return;
        }
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    private int getIndex (String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        Arrays.fill(storage,0,size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        if (size >= 0) System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    public int size() {
        return size;
    }
}
