package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.Resume;

import java.io.InputStream;
import java.io.OutputStream;

public interface Strategy {

    void doWrite(Resume resume, OutputStream os);

    Resume doRead(InputStream is);

}
