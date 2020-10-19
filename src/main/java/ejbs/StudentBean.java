package ejbs;

import entities.Course;
import entities.Student;
import entities.Subject;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class StudentBean {

    @PersistenceContext
    EntityManager em;

    public void create (String username, String password, String name, String email, int courseId) {
        try {
            Course course = em.find(Course.class, courseId);
            Student student = new Student(username, password, name, email, course);
            em.persist(student);
            course.addStudent(student);
        } catch (Exception e) {
            System.out.println("ERROR! ERROR! ERROR!");
            System.out.println(e.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        return em.createNamedQuery("getAllStudents", Student.class).getResultList(); //extra fix unchecked cast
    }

    public Student findStudent(String username) {
        return em.find(Student.class, username);
    }

    public boolean enrollStudentInSubject(String studentUsername, int subjectCode) {
        Student student = em.find(Student.class, studentUsername);
        Subject subject = em.find(Subject.class, subjectCode);

        if (student != null && subject != null) {
            if (student.getCourse().equals(subject.getCourse())) {
                return subject.addStudent(student) && student.addSubject(subject);
            }
        }
        return false;
    }
}
