package com.urise.webapp.storage;

import com.urise.webapp.storage.strategy.ObjectStreamStrategy;

public class ObjectStreamStorageTest extends AbstractStorageTest{
    public ObjectStreamStorageTest() {
        super(new FileStorage(STORAGE_DIR,new ObjectStreamStrategy()));
    }
//    public ObjectStreamStorageTest() {
//        super(new ObjectStreamStorage(STORAGE_DIR));
//    }
}