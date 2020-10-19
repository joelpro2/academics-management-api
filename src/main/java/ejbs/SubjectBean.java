package ejbs;

import entities.Course;
import entities.Student;
import entities.Subject;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class SubjectBean {

    @PersistenceContext
    EntityManager em;

    public void create (int code, String name, int courseId, int courseYear, String scholarYear) {
        try {
            Course course = em.find(Course.class, courseId);
            Subject subject = new Subject(code, name, course, courseYear, scholarYear);
            em.persist(subject);
            course.addSubject(subject);
        } catch (Exception e) {
            System.out.println("ERROR! ERROR! ERROR!");
            System.out.println(e.getMessage());
        }
    }

    public List<Subject> getAllSubjects() {
        return em.createNamedQuery("getAllSubjects", Subject.class).getResultList();
    }
}
