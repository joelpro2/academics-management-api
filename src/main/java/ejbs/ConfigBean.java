package ejbs;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton(name = "ConfigEJB")
@Startup
public class ConfigBean {

    @EJB
    AdministratorBean administratorBean;
    @EJB
    StudentBean studentBean;
    @EJB
    CourseBean courseBean;
    @EJB
    SubjectBean subjectBean;
    @EJB
    TeacherBean teacherBean;

    @PostConstruct
    void populateDB(){
        System.out.println("Hello Java EE!");
        administratorBean.create("admin", "admin", "Administrator", "admin@academic-management.com");
        courseBean.create(1, "Engenharia Informática");
        courseBean.create(2, "Engenharia e Gestão Industrial");
        courseBean.create(3, "Nutrição e Dietética");
        subjectBean.create(1, "Anatomia", 3, 2012, "2020/2021");
        subjectBean.create(2, "Programação 1", 1, 2000, "2020/2021");
        subjectBean.create(3, "Desenvolvimento de Aplicações Empresariais", 1, 2000, "2020/2021");
        studentBean.create("2191999", "123456", "joel", "2191999@my.ipleiria.pt", 1);
        studentBean.create("219xxxx", "xxxxxx", "marc-dennis", "219xxxx@my.ipleiria.pt", 1);
        studentBean.enrollStudentInSubject("2191999", 3);
        teacherBean.create("carlos.grilo", "123456", "Carlos Grilo", "carlos.grilo@my.ipleiria.pt", "A.S0.1");
        teacherBean.enrollTeacherInSubject("carlos.grilo", 3);
    }
}