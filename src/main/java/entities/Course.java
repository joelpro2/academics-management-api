package entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;

@Entity
@Table(
        name="COURSES",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NAME"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllCourses",
                query = "SELECT c FROM Course c ORDER BY c.name" // JPQL
        ),

})
public class Course implements Serializable {
    @Id
    private int id;
    @NotNull
    private String name;
    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private final Collection<Student> students;
    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private final Collection<Subject> subjects;

    public Course() {
        students = new LinkedHashSet<>();
        subjects = new LinkedHashSet<>();
        System.out.println("Default constructor Course initialized!");
    }

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
        this.students = new LinkedHashSet<>();
        this.subjects = new LinkedHashSet<>();

        System.out.println("Course Created!");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedHashSet<Student> getStudents() {
        return new LinkedHashSet<>(students);
    }

    public boolean addStudent(Student student) {
        return students.add(student);
    }

    public boolean removeStudent(Student student) {
        return students.remove(student);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return getId() == course.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
