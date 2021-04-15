package com.urise.webapp.exeption;

import com.urise.webapp.model.*;

import java.time.Month;

public class ResumeTestData {
    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.addContact(ContactType.MAIL, "mail1@ya.ru");
        resume.addContact(ContactType.PHONE, "11111");

        resume.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        resume.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));

        resume.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://Organization11.ru",
                               new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));

        resume.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));
        return resume;
    }

    public static void main(String[] args) {

    }
}
