package com.juan.diego.proyecto.student.entity;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GenericGenerator(
//            name = "ausencias_seq",
//            parameters = (name = StringPrefixed)
//    )
    private String idStudent;
    @NotNull
    @Column(unique = true)
    private String name;
    @NotNull
    @Column(unique = true)
    private String surname;
    @NotNull
    @Column(unique = true)
    private String companyEmail;
    @NotNull
    @Column(unique = true)
    private String personalEmail;
    @NotNull
    private String city;
    @NotNull
    private Integer numHoursWeek;

    private String coments;
    @NotNull
    private String branch;
    @NotNull
    private boolean active;
    @NotNull
    private Date createdDate;

    private Date terminationDate;

}
