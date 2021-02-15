import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public boolean checkResume(Resume e) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void update(Resume r) {
        if (checkResume(r)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].equals(r)) {
                    storage[i] = r;
                }
            }
        } else {
            System.out.println("ERROR");
        }
    }

    public void save(Resume r) {
        if (storage.length > 10000) {
            System.out.println("ERROR");
            return;
        }
        if (!checkResume(r)) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("ERROR");
        }
    }

    public Resume get(String uuid) {
        if (checkUuid(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid))
                    return storage[i];
            }
        } else {
            System.out.println("ERROR");
        }
        return null;
    }

    public void delete(String uuid) {
        if (checkUuid(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                }
            }
        } else {
            System.out.println("ERROR");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] storageAll = new Resume[size];
        if (size >= 0) System.arraycopy(storage, 0, storageAll, 0, size);
        return storageAll;
    }

    public int size() {
        return size;
    }
}
