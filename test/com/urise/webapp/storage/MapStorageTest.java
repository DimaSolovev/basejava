package com.urise.webapp.storage;

import com.urise.webapp.exeption.StorageException;
import org.junit.Ignore;
import org.junit.Test;

public class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapUuidStorage());
    }

    @Ignore
    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {

    }
}