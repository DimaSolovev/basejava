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

//            List<SectionType> types = new ArrayList<>(sections.keySet());
//            dos.writeInt(types.size());
//            for (SectionType type : types) {
//                dos.writeUTF(type.name());
//            }

            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                switch (entry.getKey().name()) {
                    case ("OBJECTIVE"):
                    case ("PERSONAL"):
                        dos.writeUTF(entry.getKey().name());
                        dos.writeUTF(String.valueOf(entry.getValue()));
                        break;
                    case ("ACHIEVEMENT"):
                    case ("QUALIFICATIONS"):
                        ListSection listSection = (ListSection) entry.getValue();
                        List<String> list = listSection.getItems();
                        dos.writeUTF(entry.getKey().name());
                        dos.writeInt(list.size());
                        for (String s : list) {
                            dos.writeUTF(s);
                        }
                        break;
                    case ("EXPERIENCE"):
                        OrganizationSection organizationSection = (OrganizationSection) entry.getValue();
                        List<Organization> listOrg = organizationSection.getOrganizations();//список организаций
                        dos.writeInt(listOrg.size());//размер списка организаций
                        for (Organization org : listOrg){ //проходим по списку организаций
                            List<Organization.Position> listPos = org.getPositions();//получаем список позиций в каждой организации
                            dos.writeInt(listPos.size());//размер списка позиций
                            for (Organization.Position op : listPos) {//проходим по списку позиций

                            }
                        }
                        break;
                    case ("EDUCATION"):
                        break;
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
            String section = null;
            while (dis.available()>0){
                switch ( section = dis.readUTF()) {
                    case ("OBJECTIVE"):
                    case ("PERSONAL"):
                        resume.addSection(SectionType.valueOf(section), new TextSection(dis.readUTF()));
                        break;
                    case ("ACHIEVEMENT"):
                    case ("QUALIFICATIONS"):
                        int listSize = dis.readInt();
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < listSize; i++) {
                            list.add(dis.readUTF());
                        }
                        resume.addSection(SectionType.valueOf(section), new ListSection(list));
                        break;
                    case ("EXPERIENCE"):
                    case ("EDUCATION"):
                        break;
                }
            }
            // TODO implements sections
            return resume;
        }
    }
}