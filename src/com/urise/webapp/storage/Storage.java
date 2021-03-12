package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public interface Storage {

    void update(Resume resume);

    void save(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    void clear();

    Resume[] getAll();
    //List<Resume> getAllSorted();

    int size();

}

