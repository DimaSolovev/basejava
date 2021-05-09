package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());

            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> sections = r.getSections();

            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                String sectionName = entry.getKey().name();
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(sectionName);
                        dos.writeUTF(((TextSection) entry.getValue()).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSection listSection = (ListSection) entry.getValue();
                        List<String> list = listSection.getItems();
                        dos.writeUTF(sectionName);
                        dos.writeInt(list.size());
                        for (String s : list) {
                            dos.writeUTF(s);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        dos.writeUTF(sectionName);
                        OrganizationSection organizationSection = (OrganizationSection) entry.getValue();//организация
                        List<Organization> listOrg = organizationSection.getOrganizations();//список организаций
                        dos.writeInt(listOrg.size());//размер списка организаций
                        for (Organization org : listOrg) { //проходим по списку организаций
                            Link homePage = org.getHomePage();
                            dos.writeUTF(homePage.getName());
                            dos.writeUTF(homePage.getUrl());
                            List<Organization.Position> listPos = org.getPositions();//получаем список позиций в каждой организации
                            dos.writeInt(listPos.size());//размер списка позиций
                            for (Organization.Position op : listPos) {//проходим по списку позиций
                                writeDate(dos, op.getStartDate());
                                writeDate(dos, op.getEndDate());
                                dos.writeUTF(op.getTitle());
                                dos.writeUTF(op.getDescription());
                            }
                        }
                        break;
                }
            }
        }
    }


    private void writeDate(DataOutputStream dos, LocalDate startDate) throws IOException {
        dos.writeInt(startDate.getYear());
        dos.writeInt(startDate.getMonth().getValue());
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            SectionType type;
            while (dis.available() > 0) {
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
                        for (int i = 0; i < listSize; i++) {
                            list.add(dis.readUTF());
                        }

                        resume.addSection(type, new ListSection(list));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        int orgSize = dis.readInt();
                        System.out.println(orgSize);
                        List<Organization> organizations = new ArrayList<>();

                        for (int i = 0; i < orgSize; i++) {
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

    private LocalDate readDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }
}