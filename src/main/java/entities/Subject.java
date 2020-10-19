package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;

@Entity
@Table(
        name="SUBJECTS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NAME", "COURSE_CODE", "SCHOLAR_YEAR"})
)
@NamedQueries({
        @NamedQuery(
                name = "getAllSubjects",
                query = "SELECT s FROM Subject s ORDER BY s.course.name, s.scholarYear DESC, s.courseYear, s.name" // JPQL
        ),

})
public class Subject implements Serializable {
    @Id
    @Column(name="CODE")
    private int code;
    @NotNull
    private String name;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "COURSE_CODE")
    private Course course;
    @NotNull
    @Column(name="COURSE_YEAR")
    private int courseYear;
    @NotNull
    @Column(name="SCHOLAR_YEAR")
    private String scholarYear;
    @ManyToMany
    @JoinTable(name = "SUBJECTS_STUDENTS",
            joinColumns = @JoinColumn(name = "SUBJECT_CODE", referencedColumnName = "CODE"),
            inverseJoinColumns = @JoinColumn(name = "STUDENT_USERNAME", referencedColumnName =
                    "USERNAME"))
    private final Collection<Student> students;
    @ManyToMany
    @JoinTable(name = "SUBJECTS_TEACHERS",
            joinColumns = @JoinColumn(name = "SUBJECT_CODE", referencedColumnName = "CODE"),
            inverseJoinColumns = @JoinColumn(name = "TEACHER_USERNAME", referencedColumnName =
                    "USERNAME"))
    private final Collection<Teacher> teachers;

    public Subject() { // Mandatory a default constructor
        System.out.println("Default constructor Subject initialized!");
        this.students = new LinkedHashSet<>();
        this.teachers = new LinkedHashSet<>();
    }

    public Subject(int code, @NotNull String name, @NotNull Course course, @NotNull int courseYear, @NotNull String scholarYear) {
        this.code = code;
        this.name = name;
        this.course = course;
        this.courseYear = courseYear;
        this.scholarYear = scholarYear;
        this.students = new LinkedHashSet<>();
        this.teachers = new LinkedHashSet<>();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }

    public String getScholarYear() {
        return scholarYear;
    }

    public void setScholarYear(String scholarYear) {
        this.scholarYear = scholarYear;
    }

    public LinkedHashSet<Student> getStudents() {
        return new LinkedHashSet<>(students);
    }

    public boolean addStudent(Student student) {
        return this.students.add(student);
    }

    public boolean removeStudent(Student student) {
        return this.students.remove(student);
    }

    public LinkedHashSet<Teacher> getTeachers() {
        return new LinkedHashSet<>(teachers);
    }

    public boolean addTeacher(Teacher teacher) {
        return this.teachers.add(teacher);
    }

    public boolean removeTeacher(Teacher teacher) {
        return this.teachers.remove(teacher);
    }
}
