package com.juan.diego.proyecto.student.dto;

import com.juan.diego.proyecto.student.entity.Student;
import com.juan.diego.proyecto.student.enums.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteOutputDto {
    private String id;
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
