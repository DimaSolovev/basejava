package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {

    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
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
        Assert.assertEquals(new Resume(UUID_1), storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void update() {
        Resume test = new Resume("test");
        storage.save(test);
        storage.update(test);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("test"));
    }

    @Test
    public void save() {
        Resume resume = new Resume("uuid4");
        storage.save(resume);
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistException() throws Exception {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void saveStorageOverflow() throws Exception {
        try {
            for (int i = 0; i < 9998; i++) {
                storage.save(new Resume("uuidd" + i));
            }
        } catch (Exception e) {
            //Assert.assertNotEquals("", e.getMessage());
            Assert.fail("storage is overflowed to early");
        }
        storage.save(new Resume("test"));
    }

    @Test
    public void delete() {
        storage.delete(UUID_3);
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("test");
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] actualResumes = storage.getAll();
        Resume[] expectedResumes = new Resume[]{new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        Assert.assertArrayEquals(expectedResumes, actualResumes);

    }


}