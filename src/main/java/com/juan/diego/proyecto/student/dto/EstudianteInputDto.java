package com.juan.diego.proyecto.student.dto;

import com.juan.diego.proyecto.student.entity.Student;
import com.juan.diego.proyecto.student.enums.Branch;
import com.juan.diego.proyecto.student.exception.StudentBadRequestException;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class EstudianteInputDto {
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Integer numhoursWeek;
    private String comments;
    private Branch branch;
    private boolean active;
    private Date createdDate;
    private Date terminationDate;

    private final boolean isStudentDateValid= (terminationDate == null || createdDate.before(terminationDate));

    public Student getStudent() throws Exception {
        if (isStudentDateValid) {
            Student student = new Student();
            student.setName(name);
            student.setSurname(surname);
            student.setCompanyEmail(company_email);
            student.setPersonalEmail(personal_email);
            student.setCity(city);
            student.setNumHoursWeek(numhoursWeek);
            student.setComments(comments);
            student.setBranch(branch);
            student.setActive(active);
            student.setCreatedDate(createdDate);
            student.setTerminationDate(terminationDate);
            return student;
        }
        throw new StudentBadRequestException();
    }

    public static EstudianteInputDto getEstudianteInput(Student student){
        return new EstudianteInputDto(student.getName(),student.getSurname(),student.getCompanyEmail(), student.getPersonalEmail(),
                    student.getCity(), student.getNumHoursWeek(), student.getComments(), student.getBranch(),
                    student.isActive(), student.getCreatedDate(),student.getTerminationDate());
    }
}
