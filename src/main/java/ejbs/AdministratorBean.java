package ejbs;


import entities.Administrator;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AdministratorBean {

    @PersistenceContext
    EntityManager em;

    public void create (String username, String password, String name, String email) {
        try {
            Administrator administrator = new Administrator(username, password, name, email);
            em.persist(administrator);
        } catch (Exception e) {
            System.out.println("ERROR! ERROR! ERROR!");
            System.out.println(e.getMessage());
        }
    }
}
