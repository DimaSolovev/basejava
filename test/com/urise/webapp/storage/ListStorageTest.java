package com.urise.webapp.storage;

import com.urise.webapp.exeption.StorageException;
import org.junit.Ignore;
import org.junit.Test;

public class ListStorageTest extends AbstractArrayStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Ignore
    @Test(expected = StorageException.class)
    public void saveStorageOverflow() throws Exception {
    }
}