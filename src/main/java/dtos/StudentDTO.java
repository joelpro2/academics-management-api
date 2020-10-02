package dtos;

import java.io.Serializable;

public class StudentDTO implements Serializable {

    private String username;
    private String password;
    private String name;
    private String email;
    private String courseName;
    private int courseCode;

    public StudentDTO() {}

    public StudentDTO(String username, String password, String name, String email, String courseName, int courseCode) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }
}
