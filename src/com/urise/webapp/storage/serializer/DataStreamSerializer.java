package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
            dos.writeInt(sections.size());

            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                dos.writeUTF(entry.getKey().name());
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(((TextSection) entry.getValue()).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSection listSection = (ListSection) entry.getValue();
                        List<String> list = listSection.getItems();
                        writeWithEx(list, dos, dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        OrganizationSection organizationSection = (OrganizationSection) entry.getValue();//организация
                        List<Organization> listOrg = organizationSection.getOrganizations();//список организаций
                        writeWithEx(listOrg, dos, (org) -> {
                            dos.writeUTF(org.getHomePage().getName());
                            dos.writeUTF(org.getHomePage().getUrl());
                            writeWithEx(org.getPositions(), dos, (pos) -> {
                                writeDate(dos, pos.getStartDate());
                                writeDate(dos, pos.getEndDate());
                                dos.writeUTF(pos.getTitle());
                                dos.writeUTF(pos.getDescription());
                            });
                        });
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            int contactSize = dis.readInt();
            for (int i = 0; i < contactSize; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            SectionType type;
            int sectionSize = dis.readInt();
            for (int i = 0; i < sectionSize; i++) {
                type = SectionType.valueOf(dis.readUTF());
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.addSection(type, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        int listSize = dis.readInt();
                        List<String> list = new ArrayList<>();
                        for (int j = 0; j < listSize; j++) {
                            list.add(dis.readUTF());
                        }

                        resume.addSection(type, new ListSection(list));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        int orgSize = dis.readInt();
                        System.out.println(orgSize);
                        List<Organization> organizations = new ArrayList<>();

                        for (int x = 0; x < orgSize; x++) {
                            Link link = new Link(dis.readUTF(), dis.readUTF());
                            int positionSize = dis.readInt();
                            List<Organization.Position> positions = new ArrayList<>();

                            for (int j = 0; j < positionSize; j++) {
                                LocalDate startDate = readDate(dis);
                                LocalDate endDate = readDate(dis);
                                String title = dis.readUTF();
                                String description = dis.readUTF();
                                positions.add(new Organization.Position(startDate, endDate, title, description));
                            }
                            organizations.add(new Organization(link, positions));
                        }
                        resume.addSection(type, new OrganizationSection(organizations));
                        break;
                }
            }
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
}

interface Write<T> {
    public void writeElement(T t) throws IOException;
}