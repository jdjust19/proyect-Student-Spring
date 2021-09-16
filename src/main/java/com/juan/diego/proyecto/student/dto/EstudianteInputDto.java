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
}
