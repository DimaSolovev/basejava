package com.urise.webapp.exeption;

import com.urise.webapp.model.*;
import com.urise.webapp.util.DateUtil;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

public class ResumeTestData {
    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.contacts.put(ContactType.PHONE, uuid + "123456789");
        resume.contacts.put(ContactType.SKYPE, fullName + "skype");
        resume.contacts.put(ContactType.EMAIL, fullName + "@mail.ru");

        resume.sections.put(SectionType.PERSONAL, new TextSection(fullName));
        resume.sections.put(SectionType.OBJECTIVE, new TextSection(fullName));
        resume.sections.put(SectionType.ACHIEVEMENT, new ListSection(new ArrayList<>(Arrays.asList("достижения"))));
        resume.sections.put(SectionType.QUALIFICATIONS, new ListSection(new ArrayList<>(Arrays.asList("квалификация"))));
        resume.sections.put(SectionType.EXPERIENCE, new OrganizationSection(new ArrayList<>(Arrays.asList(new Organization("Organization","URL",
                new ArrayList(Arrays.asList(DateUtil.of(2010, Month.JANUARY),DateUtil.of(2011,Month.JUNE),"title","description")))))));
        resume.sections.put(SectionType.EDUCATION, new OrganizationSection(new ArrayList<>(Arrays.asList(new Organization("Organization","URL",
                new ArrayList(Arrays.asList(DateUtil.of(2010, Month.JANUARY),DateUtil.of(2011,Month.JUNE),"title","description")))))));

        return resume;
    }

    public static void main(String[] args) {

    }
}
