package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR, storage doesn't contain resume " + uuid);
            return null;
        }
        return storage[index];
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("ERROR, storage doesn't contain resume " + resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size == storage.length) {
            System.out.println("ERROR, storage is full");
            return;
        } else if (index >= 0) {
            System.out.println("ERROR, storage already contains resume " + resume.getUuid());
            return;
        }
        saveStorage(resume, index);
        size++;
    }

    public abstract void saveStorage(Resume resume, int index);

    public abstract void deleteStorage(String uuid, int index);

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR, storage doesn't contain resume  " + uuid);
            return;
        }
        deleteStorage(uuid, index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        if (size >= 0) System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    protected abstract int getIndex(String uuid);
}
