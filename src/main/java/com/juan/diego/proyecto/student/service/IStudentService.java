package com.juan.diego.proyecto.student.service;

import com.juan.diego.proyecto.student.dto.EstudianteInputDto;
import com.juan.diego.proyecto.student.dto.EstudianteOutputDto;
import com.juan.diego.proyecto.student.dto.EstudianteSearchInputDto;
import com.juan.diego.proyecto.student.exception.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

public interface IStudentService {
    public List<EstudianteOutputDto> getAll();
    public EstudianteOutputDto getById(String id) throws StudentNotFoundException;
    public void add(EstudianteInputDto estudianteInputDto) throws Exception;
    public void update(String id, EstudianteInputDto estudianteInputDto) throws Exception;
    public void delete(String id) throws StudentNotFoundException;
    public List<EstudianteOutputDto> getData(EstudianteSearchInputDto estudianteSearchInputDto);
}
