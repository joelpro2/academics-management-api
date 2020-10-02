package ejbs;

import entities.Course;
import entities.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CourseBean {

    @PersistenceContext
    EntityManager em;

    public void create (int id, String name) {
        try {
            Course course = new Course(id, name);
            em.persist(course);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Course> getAllCourses() {
        return em.createNamedQuery("getAllCourses", Course.class).getResultList();
    }
}
