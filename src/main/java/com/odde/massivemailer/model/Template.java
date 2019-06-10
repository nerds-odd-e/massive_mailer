package com.odde.massivemailer.model;


import com.odde.massivemailer.model.base.Entity;
import com.odde.massivemailer.model.base.Repository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

import static com.odde.massivemailer.model.base.Repository.repo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Template extends Entity<Template> {
    private String templateName;
    private String subject;
    private String content;

    public static List<Template> findByTemplateName(String templateName) {
        return repo(Template.class).findBy("templateName", templateName);
    }

    public List<Mail> getPopulatedEmailTemplate(Course course, List<ContactPerson> courseParticipants) {
        List<Mail> mails = new ArrayList<>();

        String content;
        String contactEmailId;

        for (ContactPerson contactPerson : courseParticipants) {
            content = getEmailContentFromTemplate(contactPerson, course);
            contactEmailId = contactPerson.getEmail();

            Mail mail = Mail.createUpcomingCoursesEmail(content, contactEmailId);
            mail.setSubject(getEmailSubjectFromTemplate(contactPerson));

            mails.add(mail);
        }

        return mails;
    }


    private String getEmailContentFromTemplate(ContactPerson contactPerson, Course course) {

        String populatedContent = null;
        if (contactPerson != null) {
            populatedContent = this.content;

            populatedContent = populatedContent.replace("{FirstName}", (contactPerson.getFirstName() != null ? contactPerson.getFirstName() : "{FirstName}"));
            populatedContent = populatedContent.replace("{LastName}", (contactPerson.getLastName() != null ? contactPerson.getLastName() : "{LastName}"));
            populatedContent = populatedContent.replace("{CourseName}", (course.getCourseName() != null ? course.getCourseName() : "{CourseName}"));
            populatedContent = populatedContent.replace("{Instructor}", (course.getInstructor() != null ? course.getInstructor() : "{Instructor}"));
            populatedContent = populatedContent.replace("{Location}", (course.location() != null ? course.location() : "{Location}"));
        }

        return populatedContent;
    }

    private String getEmailSubjectFromTemplate(ContactPerson contactPerson) {
        String populatedSubject = this.subject;
        populatedSubject = populatedSubject.replace("{FirstName}", (contactPerson.getFirstName() != null ? contactPerson.getFirstName() : "{FirstName}"));

        return populatedSubject;
    }

    public void saveTemplate(String subject, String content) {
        setSubject(subject);
        setContent(content);
        saveIt();
    }

    @Override
    public void onBeforeSave() {
    }

}
