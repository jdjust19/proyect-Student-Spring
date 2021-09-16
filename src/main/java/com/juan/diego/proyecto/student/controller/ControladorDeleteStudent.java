package com.juan.diego.proyecto.student.controller;

import com.juan.diego.proyecto.student.exception.StudentNotFoundException;
import com.juan.diego.proyecto.student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class ControladorDeleteStudent {

    @Autowired
    IStudentService service;

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable String id) throws StudentNotFoundException {
        service.delete(id);
    }
}