package com.juan.diego.proyecto.student.controller;

import com.juan.diego.proyecto.student.dto.EstudianteInputDto;
import com.juan.diego.proyecto.student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class ControladorCreateStudent {

    @Autowired
    IStudentService service;

    @PostMapping
    public void createStudent(@RequestBody EstudianteInputDto estudianteInputDto) throws Exception {
        service.add(estudianteInputDto);
    }

}