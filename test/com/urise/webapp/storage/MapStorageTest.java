package com.urise.webapp.storage;

import com.urise.webapp.exeption.StorageException;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Ignore
    @Test(expected = StorageException.class)
    public void saveStorageOverflow() throws Exception {

    }
}