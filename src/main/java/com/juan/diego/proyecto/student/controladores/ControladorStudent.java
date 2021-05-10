package com.juan.diego.proyecto.student.controladores;

import com.juan.diego.proyecto.student.dto.EstudianteInputDto;
import com.juan.diego.proyecto.student.dto.EstudianteOutputDto;
import com.juan.diego.proyecto.student.entity.Student;
import com.juan.diego.proyecto.student.exception.StudentNotFoundException;
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

    @PostMapping
    public void addStudent(@RequestBody EstudianteInputDto estudianteInputDto) throws Exception {
        if (!studentRepository.existsStudentByNameAndSurname(estudianteInputDto.getName(),estudianteInputDto.getSurname())) {
            Student student = estudianteInputDto.getStudent();
            studentRepository.save(student);
            return;
        }
        throw new Exception("Ya existe un usuario con ese nombre y apellidos");
    }

    @PutMapping("{id}")
    public void updateStudent(@PathVariable String id, @RequestBody EstudianteInputDto estudianteInputDto) throws Exception {
        if (studentRepository.existsById(id)){
            if (!studentRepository.existsStudentByNameAndSurname(estudianteInputDto.getName(), estudianteInputDto.getSurname())) {
                Student student = estudianteInputDto.getStudent();
                studentRepository.save(student);
                return;
            }
            throw new Exception("Ya existe un usuario con ese nombre y apellidos");
        }
        throw new StudentNotFoundException();
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable String id) {
        if (studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return;
        }
        throw new StudentNotFoundException();
    }
}