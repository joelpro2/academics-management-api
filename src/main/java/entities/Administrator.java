package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Administrator extends User {

    public Administrator() {

    }

    public Administrator(String username, String password, String name, String email) {
        super(username, password, name, email);
    }

}
