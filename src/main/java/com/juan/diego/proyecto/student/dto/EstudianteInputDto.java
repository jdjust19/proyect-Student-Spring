package com.juan.diego.proyecto.student.dto;

import com.juan.diego.proyecto.student.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@Getter
public class EstudianteInputDto {
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Integer numhoursWeek;
    private String comments;
    private String branch;
    private Boolean active;
    private Date createdDate;
    private Date terminationDate;

    private final boolean isStudentDateValid= (terminationDate == null || createdDate.before(terminationDate));

    public Student getStudent() throws Exception {
        if (isStudentDateValid) {
            Student student = new Student();
            student.setName("Hola");
            student.setSurname(surname);
            student.setCompanyEmail(company_email);
            student.setPersonalEmail(personal_email);
            student.setCity(city);
            student.setNumHoursWeek(numhoursWeek);
            student.setComents(comments);
            student.setBranch(branch);
            student.setActive(active);
            student.setCreatedDate(createdDate);
            student.setTerminationDate(terminationDate);
            return student;
        }
        throw new Exception("El estudiante no es valido");
    }

    public static EstudianteInputDto getEstudianteInput(Student student){
        return new EstudianteInputDto(student.getSurname(),student.getCompanyEmail(), student.getPersonalEmail(),
                    student.getCity(), student.getNumHoursWeek(), student.getComents(), student.getBranch(),
                    student.isActive(), student.getCreatedDate(),student.getTerminationDate());
    }
}
