package com.urise.webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    private final String uuid;
    private final String fullName;
    public Map<ContactType, String> contacts = new EnumMap<ContactType, String>(ContactType.class);
    public Map<SectionType, AbstractSection> typeMap = new EnumMap<SectionType, AbstractSection>(SectionType.class);

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "full name mustn't be null");
        Objects.requireNonNull(fullName, "full name mustn't be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public static void printResume(Resume resume) {
        System.out.println(resume.fullName);
        for (Map.Entry<ContactType, String> entry : resume.contacts.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
        for (Map.Entry<SectionType, AbstractSection> entry : resume.typeMap.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public String getFullName() {
        return fullName;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return uuid + " " + fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) && Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }
}
