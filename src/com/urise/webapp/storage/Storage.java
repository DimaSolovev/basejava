package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.util.List;

public interface Storage {

    void update(Resume resume);

    void save(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    void clear();

    List<Resume> getAllSorted() throws IOException;

    int size();

}

