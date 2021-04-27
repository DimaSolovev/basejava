package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
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
                if (entry.getKey().name().equals("OBJECTIVE") || entry.getKey().name().equals("PERSONAL")) {
                    dos.writeUTF(entry.getKey().name());
                    dos.writeUTF(String.valueOf(entry.getValue()));
                } else if (entry.getKey().name().equals("ACHIEVEMENT") || entry.getKey().name().equals("QUALIFICATIONS")) {
                    ListSection listSection = (ListSection) entry.getValue();
                    List<String> list = listSection.getItems();
                    int i = list.size();
                    dos.writeInt(i);
                    dos.writeUTF(entry.getKey().name());
                    for (String s : list) {
                        dos.writeUTF(s);
                    }
                } else if (entry.getKey().name().equals("EXPERIENCE") || entry.getKey().name().equals("EDUCATION")) {

                }
            }


            // TODO implements sections
        }
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

            for (int i = 0; i < 2; i++) {
                resume.addSection(SectionType.valueOf(dis.readUTF()), new TextSection(dis.readUTF()));
            }

            for (int i = 0; i < 2; i++) {
                int listSize = dis.readInt();
                String nameSection = dis.readUTF();
                List<String> list = new ArrayList<>();
                for (int j = 0; j < listSize; j++) {
                    String str = dis.readUTF();
                    list.add(str);
                }
                resume.addSection(SectionType.valueOf(nameSection), new ListSection(list));
            }

            // TODO implements sections
            return resume;
        }
    }
}