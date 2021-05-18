package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());

            Map<ContactType, String> contacts = r.getContacts();
            writeWithEx(contacts.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            Map<SectionType, Section> sections = r.getSections();
            writeWithEx(sections.entrySet(), dos, (entry) -> {
                //for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                    SectionType type = entry.getKey();
                    dos.writeUTF(type.name());
                    switch (type) {
                        case OBJECTIVE:
                        case PERSONAL:
                            dos.writeUTF(((TextSection) entry.getValue()).getContent());
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            writeWithEx(((ListSection) entry.getValue()).getItems(), dos, dos::writeUTF);
                            break;
                        case EXPERIENCE:
                        case EDUCATION:
                            writeWithEx(((OrganizationSection) entry.getValue()).getOrganizations(), dos, (org) -> {
                                Link homePage = org.getHomePage();
                                dos.writeUTF(homePage.getName());
                                dos.writeUTF(homePage.getUrl());
                                writeWithEx(org.getPositions(), dos, (pos) -> {
                                    writeDate(dos, pos.getStartDate());
                                    writeDate(dos, pos.getEndDate());
                                    dos.writeUTF(pos.getTitle());
                                    dos.writeUTF(pos.getDescription());
                                });
                            });
                            break;
                    }
                //}
            });
        }
    }

    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            readWithEx(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

            readWithEx(dis, () -> {
                SectionType type = SectionType.valueOf(dis.readUTF());
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.addSection(type, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = new ArrayList<>();
                        readWithEx(dis, () -> list.add(dis.readUTF()));
                        resume.addSection(type, new ListSection(list));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = new ArrayList<>();
                        readWithEx(dis, () -> {
                            Link link = new Link(dis.readUTF(), dis.readUTF());
                            List<Organization.Position> positions = new ArrayList<>();
                            readWithEx(dis, () -> {
                                LocalDate startDate = readDate(dis);
                                LocalDate endDate = readDate(dis);
                                String title = dis.readUTF();
                                String description = dis.readUTF();
                                positions.add(new Organization.Position(startDate, endDate, title, description));

                            });
                            organizations.add(new Organization(link, positions));
                        });
                        resume.addSection(type, new OrganizationSection(organizations));
                        break;
                }
            });

            return resume;
        }
    }

    private void writeDate(DataOutputStream dos, LocalDate startDate) throws IOException {
        dos.writeInt(startDate.getYear());
        dos.writeInt(startDate.getMonth().getValue());
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private <T> void writeWithEx(Collection<T> col, DataOutputStream dos, Write<T> writer) throws IOException {
        dos.writeInt(col.size());
        for (T t : col) {
            writer.writeElement(t);
        }
    }

    private void readWithEx(DataInputStream dis, Read reader) throws IOException {
        int positionSize = dis.readInt();
        for (int i = 0; i < positionSize; i++) {
            reader.readElement();
        }
    }
}

interface Write<T> {
    void writeElement(T t) throws IOException;
}

interface Read<T> {
    void readElement() throws IOException;
}