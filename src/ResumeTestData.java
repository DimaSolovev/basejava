import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.time.Month;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("1","Grigorii");
        resume.contacts.put(ContactType.PHONE,"+7(921) 855-0482");
        resume.contacts.put(ContactType.SKYPE,"grigory.kislin");
        resume.contacts.put(ContactType.EMAIL,"gkislin@yandex.ru");
        resume.contacts.put(ContactType.LINKEDIN,"Профиль LinkedIn");
        resume.contacts.put(ContactType.GITHUB,"Профиль GitHub");
        resume.contacts.put(ContactType.STACKOVERFLOW,"Профиль Stackoverflow");

        PersonalObjective personal = new PersonalObjective();
        personal.setText("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");

        PersonalObjective objective = new PersonalObjective();
        objective.setText("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");

        AchievementQualification achievement = new AchievementQualification();
        achievement.text.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievement.text.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");

        AchievementQualification qualification = new AchievementQualification();
        qualification.text.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualification.text.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");

        Organization experience = new Organization();
        experience.experienceList.add(new Experience(LocalDate.of(1997, Month.SEPTEMBER,01)
                ,LocalDate.of(2005, Month.JANUARY,01)
                ,"Инженер по аппаратному и программному тестированию","Alcatel"));

        Organization education = new Organization();
        education.experienceList.add(new Experience(LocalDate.of(1984, Month.SEPTEMBER,01)
                ,LocalDate.of(1987, Month.JUNE,01)
                ,"Закончил с отличием","Заочная физико-техническая школа при МФТИ"));

        resume.typeMap.put(SectionType.PERSONAL,personal);
        resume.typeMap.put(SectionType.OBJECTIVE,objective);
        resume.typeMap.put(SectionType.ACHIEVEMENT,achievement);
        resume.typeMap.put(SectionType.QUALIFICATIONS,qualification);
        resume.typeMap.put(SectionType.EXPERIENCE,experience);
        resume.typeMap.put(SectionType.EDUCATION,education);

    }
}
