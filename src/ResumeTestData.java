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
        achievement.getText().add("С 2013 года: разработка проектов ");

        AchievementQualification qualification = new AchievementQualification();
        qualification.getText().add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");

        Organization experience = new Organization();
        experience.getExperienceList().add(new Experience(LocalDate.of(1997, Month.SEPTEMBER,01)
                ,LocalDate.of(2005, Month.JANUARY,01)
                ,"Инженер по аппаратному и программному тестированию","Alcatel"));

        Organization education = new Organization();
        education.getExperienceList().add(new Experience(LocalDate.of(1984, Month.SEPTEMBER,01)
                ,LocalDate.of(1987, Month.JUNE,01)
                ,"Закончил с отличием","Заочная физико-техническая школа при МФТИ"));

        resume.sections.put(SectionType.PERSONAL,personal);
        resume.sections.put(SectionType.OBJECTIVE,objective);
        resume.sections.put(SectionType.ACHIEVEMENT,achievement);
        resume.sections.put(SectionType.QUALIFICATIONS,qualification);
        resume.sections.put(SectionType.EXPERIENCE,experience);
        resume.sections.put(SectionType.EDUCATION,education);

        System.out.println(resume.toString());
    }
}
