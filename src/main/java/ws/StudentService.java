package ws;



import dtos.StudentDTO;
import dtos.SubjectDTO;
import ejbs.StudentBean;
import entities.Student;
import entities.Subject;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/students") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”

public class StudentService {
    @EJB
    private StudentBean studentBean;

    // Converts an entity Student to a DTO Student class
    private StudentDTO toDTO(Student student) {
        return new StudentDTO(
                student.getUsername(),
                student.getPassword(),
                student.getName(),
                student.getEmail(),
                student.getCourse().getName(),
                student.getCourse().getId()
        );
    }

    // converts an entire list of entities into a list of DTOs
    private List<StudentDTO> toDTOs(List<Student> students) {
        return students.stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Converts an entity Subject to a DTO Subject class
    private SubjectDTO subjectsToDTO(Subject subject) {
        return new SubjectDTO(
                subject.getCode(),
                subject.getName(),
                subject.getCourse().getId(),
                subject.getCourse().getName(),
                subject.getCourseYear(),
                subject.getScholarYear()
        );
    }

    // converts an entire list of entities into a list of DTOs
    private List<SubjectDTO> subjectsToDTOs(List<Subject> subjects) {
        return subjects.stream().map(this::subjectsToDTO).collect(Collectors.toList());
    }

    @GET // means: to call this endpoint, we need to use the HTTP GET method
    @Path("/all") // means: the relative url path is “/api/students/all”
    public List<StudentDTO> getAllStudentsWS() {
        return toDTOs(studentBean.getAllStudents());
    }

    @POST
    @Path("/")
    public Response createNewStudent (StudentDTO studentDTO) {
        System.out.println("Creating a new student");
        // Verify if Student already exists
        Student student = studentBean.findStudent(studentDTO.getUsername());
        System.out.println(student);
        if(student != null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        studentBean.create(studentDTO.getUsername(),
                studentDTO.getPassword(),
                studentDTO.getName(),
                studentDTO.getEmail(),
                studentDTO.getCourseCode());
        // Verifies if Student has been created
        Student newStudent = studentBean.findStudent(studentDTO.getUsername());
        if(newStudent == null)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.status(Response.Status.CREATED)
                .entity(toDTO(newStudent))
                .build();
    }

    @GET
    @Path("{username}")
    public Response getStudentDetails(@PathParam("username") String username) {
        Student student = studentBean.findStudent(username);
        if (student != null) {
            return Response.status(Response.Status.OK)
                    .entity(toDTO(student))
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("ERROR_FINDING_STUDENT")
                    .build();
        }

    }

    @GET
    @Path("{username}/subjects")
    public Response getStudentSubjects(@PathParam("username") String username) {
        Student student = studentBean.findStudent(username);
        if (student != null) {
            GenericEntity<List<SubjectDTO>> entity
                    = new
                    GenericEntity<List<SubjectDTO>>(subjectsToDTOs(new ArrayList<>(student.getSubjects()))) {
                    };
            return Response.status(Response.Status.OK)
                    .entity(entity)
                    .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("ERROR_FINDING_STUDENT")
                .build();
    }

}
