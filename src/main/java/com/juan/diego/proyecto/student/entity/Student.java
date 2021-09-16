package com.juan.diego.proyecto.student.entity;


import com.juan.diego.proyecto.student.enums.Branch;
import com.juan.diego.proyecto.student.generators.StringPrefixedSequenceIdGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudiantes_seq")
    @GenericGenerator(
            name = "estudiantes_seq",
            strategy = "com.juan.diego.proyecto.student.generators.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "EST"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            })
    private String idStudent;
    @NotNull
    private String name;
    @NotNull
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

    private String comments;
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Branch branch;
    @NotNull
    private boolean active;
    @NotNull
    private Date createdDate;

    private Date terminationDate;

}