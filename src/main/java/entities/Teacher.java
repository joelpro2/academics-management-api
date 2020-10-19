package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.LinkedHashSet;

@Entity
public class Teacher extends User{

    @NotNull
    private String office;
    @ManyToMany(mappedBy = "teachers")
    private final Collection<Subject> subjects;

    public Teacher() {
        this.subjects = new LinkedHashSet<>();
    }

    public Teacher(String username, String password, String name, String email, String office) {
        super(username, password, name, email);
        this.office = office;
        this.subjects = new LinkedHashSet<>();
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public LinkedHashSet<Subject> getSubjects() {
        return new LinkedHashSet<>(subjects);
    }

    public boolean addSubject(Subject subject) {
        return this.subjects.add(subject);
    }

    public boolean removeSubject(Subject subject) {
        return this.subjects.remove(subject);
    }
}
