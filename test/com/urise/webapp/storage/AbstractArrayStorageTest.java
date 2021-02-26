package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbstractArrayStorageTest {

    private Storage storage = new ArrayStorage();
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    public AbstractArrayStorageTest() {
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume(UUID_1), storage.get("uuid1"));
    }

    @Test
    public void update() {
        Resume test = storage.get("uuid1");
        storage.update(storage.get("uuid1"));
        Assert.assertEquals(test, storage.get("uuid1"));
    }

    @Test
    public void save() {
        Resume resume = new Resume("uuid4");
        storage.save(resume);
        Assert.assertEquals(resume, storage.get("uuid4"));
    }

    @Test
    public void delete() {
        storage.delete("uuid3");
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] test = storage.getAll();
        Resume[] test2 = new Resume[]{storage.get("uuid1"), storage.get("uuid2"), storage.get("uuid3")};
        Assert.assertArrayEquals(test, test2);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = StorageException.class)
    public void getStorageExc() throws Exception {
        for (int i = 0; i < 9998; i++) {
            storage.save(new Resume("uuidd" + i));
        }
        try {
            storage.save(new Resume("test"));
            Assert.fail("storage is overflowed to early");
        } catch (Exception e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test(expected = ExistStorageException.class)
    public void getExistExc()throws Exception{
        storage.save(new Resume("uuid1"));
    }
}