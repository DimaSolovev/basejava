package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private Resume[] storage = new Resume[10_000];
    private int size;

    public Resume check(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                return r;
            }
        }
        return null;
    }

    public void update(Resume r) {
        if (check(r) != null) {
            System.out.println("ERROR " + r.getUuid());
        }
    }

    public void save(Resume r) {
        if (size == storage.length || check(r) != null) {
            System.out.println("ERROR " + r.getUuid());
            return;
        }
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        if (check(resume) == null) {
            System.out.println("ERROR " + uuid);
            return null;
        }
        return resume;
    }

    public void delete(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        if (check(resume) == null) {
            System.out.println("ERROR " + uuid);
            return;
        }
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
            }
        }
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
