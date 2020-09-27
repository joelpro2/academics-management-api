package ejbs;

import entities.Student;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton(name = "ConfigEJB")
@Startup
public class ConfigBean {

    @EJB
    StudentBean studentBean;

    @PostConstruct
    void populateDB(){
        System.out.println("Hello Java EE!");
        studentBean.create("2191999", "123456", "joel", "2191999@my.ipleiria.pt");
        studentBean.create("219xxxx", "xxxxxx", "marc-dennis", "219xxxx@my.ipleiria.pt");
    }
}