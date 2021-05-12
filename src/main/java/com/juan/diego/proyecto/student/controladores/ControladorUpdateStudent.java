package com.juan.diego.proyecto.student.controladores;

import com.juan.diego.proyecto.student.dto.EstudianteInputDto;
import com.juan.diego.proyecto.student.dto.EstudianteOutputDto;
import com.juan.diego.proyecto.student.exception.StudentNotFoundException;
import com.juan.diego.proyecto.student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class ControladorUpdateStudent {

    @Autowired
    IStudentService service;

    @PutMapping("{id}")
    public void updateStudent(@PathVariable String id, @RequestBody EstudianteInputDto estudianteInputDto) throws Exception {
        service.update(id, estudianteInputDto);
    }
}