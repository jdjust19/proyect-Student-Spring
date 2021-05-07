package com.juan.diego.proyecto.student.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class EstudianteInputDto {
    private String surname;
    @NotNull
    private String company_email;
    @NotNull
    private String personal_email;
    @NotNull
    private String city;
    @NotNull
    private String numhoursWeek;
    private String comments;
    @NotNull
    private String branch;
    @NotNull
    private Boolean active;
    @NotNull
    private Date createdDate;
    
    private Date terminationDate;
}
