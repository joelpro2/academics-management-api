package ejbs;

import entities.Subject;
import entities.Teacher;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TeacherBean {

    @PersistenceContext
    EntityManager em;

    public void create (String username, String password, String name, String email, String office) {
        try {
            Teacher teacher = new Teacher(username, password, name, email, office);
            em.persist(teacher);
        } catch (Exception e) {
            System.out.println("ERROR! ERROR! ERROR!");
            System.out.println(e.getMessage());
        }
    }

    public boolean enrollTeacherInSubject(String teacherUsername, int subjectCode) {
        Teacher teacher = em.find(Teacher.class, teacherUsername);
        Subject subject = em.find(Subject.class, subjectCode);

        if (teacher != null && subject != null) {
            return subject.addTeacher(teacher) && teacher.addSubject(subject);
        }
        return false;
    }
}
