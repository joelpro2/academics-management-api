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
    @EJB
    CourseBean courseBean;

    @PostConstruct
    void populateDB(){
        System.out.println("Hello Java EE!");
        courseBean.create(1, "Engenharia Informática");
        courseBean.create(2, "Engenharia e Gestão Industrial");
        courseBean.create(3, "Nutrição e Dietética");
        studentBean.create("2191999", "123456", "joel", "2191999@my.ipleiria.pt", 1);
        studentBean.create("219xxxx", "xxxxxx", "marc-dennis", "219xxxx@my.ipleiria.pt", 1);
    }
}