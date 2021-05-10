package com.juan.diego.proyecto.student.dto;

import com.juan.diego.proyecto.student.entity.Student;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class EstudianteSearchInputDto {
    private String name;
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

    public static EstudianteSearchInputDto getEstudianteSearchInput(Student student){
        return new EstudianteSearchInputDto(student.getName(), student.getSurname(),student.getCompanyEmail(), student.getPersonalEmail(),
                student.getCity(), student.getNumHoursWeek(), student.getComents(), student.getBranch(),
                student.isActive(), student.getCreatedDate(),student.getTerminationDate());
    }
}
