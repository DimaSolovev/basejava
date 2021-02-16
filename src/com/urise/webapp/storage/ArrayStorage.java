package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private Resume[] storage = new Resume[10_000];
    private int size;

    public int check(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void update(Resume r) {
        int num = check(r.getUuid());
        if (num == -1) {
            System.out.println("ERROR, storage doesn't contain resume " + r.getUuid());
        } else {
            this.storage[num] = r;
        }
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("ERROR, storage is full");
            return;
        }
        if (check(r.getUuid()) != -1) {
            System.out.println("ERROR, storage already contains resume " + r.getUuid());
            return;
        }
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        int num = check(uuid);
        if (num == -1) {
            System.out.println("ERROR, storage doesn't contain resume " + uuid);
            return null;
        }
        return storage[num];
    }

    public void delete(String uuid) {
        int num = check(uuid);
        if (num == -1) {
            System.out.println("ERROR, storage doesn't contain resume  " + uuid);
            return;
        }
        storage[num] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    public void clear() {
        Arrays.fill(storage, null);
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
