package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.TypedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllStudents",
                query = "SELECT s FROM Student s ORDER BY s.name" // JPQL
        ),

})
public class Student implements Serializable {
    @Id
    private String username; // mandatory an ID
    private String password;
    private String name;
    private String email;

    public Student() { // Mandatory a default constructor
        System.out.println("Default constructor Student initialized!");
    }

    public Student(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;

        System.out.println("Student Created!");
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
}
