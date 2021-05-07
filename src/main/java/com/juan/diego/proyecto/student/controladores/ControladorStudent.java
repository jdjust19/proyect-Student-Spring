package com.juan.diego.proyecto.student.controladores;

import com.juan.diego.proyecto.student.dto.EstudianteOutputDto;
import com.juan.diego.proyecto.student.entity.Student;
import com.juan.diego.proyecto.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("student")
public class ControladorStudent {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public List<EstudianteOutputDto> getStudents(){
        List<Student> studentsDB = studentRepository.findAll();
        List<EstudianteOutputDto> estudianteOutputDtoList = studentsDB.stream()
                .map(s -> EstudianteOutputDto.getEstudianteOutput(s)).collect(Collectors.toList());
        return estudianteOutputDtoList;
    }

}
