package com.juan.diego.proyecto.student.controller;

import com.juan.diego.proyecto.student.dto.EstudianteOutputDto;
import com.juan.diego.proyecto.student.dto.EstudianteSearchInputDto;
import com.juan.diego.proyecto.student.exception.StudentNotFoundException;
import com.juan.diego.proyecto.student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class ControladorGetStudent {

    @Autowired
    IStudentService service;

    @GetMapping
    public List<EstudianteOutputDto> getStudents(){
        return service.getAll();
    }

    @GetMapping("{id}")
    public EstudianteOutputDto getStudentById(@PathVariable String id) throws StudentNotFoundException {
        return service.getById(id);
    }

    @GetMapping("/withRequirements")
    public List<EstudianteOutputDto> getStudentMeetRequirements(@RequestBody EstudianteSearchInputDto estudiante){
        return service.getData(estudiante);
    }
}