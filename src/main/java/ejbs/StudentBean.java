package ejbs;

import entities.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class StudentBean {

    @PersistenceContext
    EntityManager em;

    public void create (String username, String password, String name, String email) {
        Student student = new Student(username, password, name, email);
        em.persist(student);
    }

    public List<Student> getAllStudents() {
        // remember, maps to: “SELECT s FROM Student s ORDER BY s.name”
        //return (List<Student>) em.createNamedQuery("getAllStudents").getResultList();
        return em.createNamedQuery("getAllStudents", Student.class).getResultList(); //extra fix unchecked cast
    }

}
