package com.urise.webapp.storage;

import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }
        if(!directory.canRead() || !directory.canWrite()){
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not readeble/writable");
        }
        this.directory=directory;
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            doWrite(resume,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume,file);
        } catch (IOException e) {
            throw new StorageException("IO error",file.getName(),e);
        }
    }

    protected abstract void doWrite(Resume resume, File file) throws IOException;

    @Override
    protected Resume doGet(File file) {
        return doRead(file);
    }

    protected abstract Resume doRead(File file);

    @Override
    protected void doDelete(File file) {
        file.delete();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory,uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected List<Resume> doCopyAll() {
        List<Resume> resumeList = new ArrayList<>();
        File[]files = directory.listFiles();
        for (File f: files){
            resumeList.add(doGet(f));
        }
        return resumeList;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        for(File file: files){
            file.delete();
        }
    }

    @Override
    public int size() {
        return directory.listFiles().length;
    }
}
