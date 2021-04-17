package com.urise.webapp.storage;

public class ObjectStreamPathStorageTest extends AbstractStorageTest{
    public ObjectStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(),new ObjectStreamStrategy()));
    }
    //public ObjectStreamPathStorageTest() {
    //    super(new ObjectStreamPathStorage(STORAGE_DIR.getAbsolutePath()));
    //}
}