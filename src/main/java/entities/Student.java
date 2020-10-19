package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;

import entities.User;

@Entity
@Table(name="STUDENTS")
@NamedQueries({
        @NamedQuery(
                name = "getAllStudents",
                query = "SELECT s FROM Student s ORDER BY s.name" // JPQL
        ),

})
public class Student extends User implements Serializable {

    @ManyToOne
    @JoinColumn(name = "COURSE_CODE")
    @NotNull
    private Course course;
    @ManyToMany(mappedBy = "students")
    private final Collection<Subject> subjects;

    public Student() { // Mandatory a default constructor
        System.out.println("Default constructor Student initialized!");
        this.subjects = new LinkedHashSet<>();
    }

    public Student(String username, String password, String name, String email, Course course) {
        super(username, password, name, email);
        this.course = course;
        this.subjects = new LinkedHashSet<>();

        System.out.println("Student Created!");
    }

    public Course getCourse() {
        return course;
    }

    public LinkedHashSet<Subject> getSubjects() {
        return new LinkedHashSet<>(subjects);
    }

    public boolean addSubject(Subject subject) {
        return this.subjects.add(subject);
    }

    public Subject removeSubject(Subject subject) {
        this.subjects.remove(subject);
        return subject;
    }

    @Override
    public String toString() {
        return "Student{" +
                "username='" + getUsername() + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", course=" + course +
                '}';
    }
}
