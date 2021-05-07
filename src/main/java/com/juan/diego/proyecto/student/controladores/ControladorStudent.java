package com.juan.diego.proyecto.student.controladores;

import com.juan.diego.proyecto.student.dto.EstudianteInputDto;
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

    @PostMapping
    //"Hola" es para comprobar el nombre, ya que estudianteInputDto no contiene nombre
    //Esto lo solucionaremos mas adelante
    public void addStudent(@RequestBody EstudianteInputDto estudianteInputDto) throws Exception {
        if (!studentRepository.existsStudentByNameAndSurname("Hola",estudianteInputDto.getSurname())) {
            Student student = estudianteInputDto.getStudent();
            studentRepository.save(student);
            return;
        }
        throw new Exception("Ya existe un usuario con ese nombre y apellidos");

    }

    @PutMapping("{id}")
    public void updateStudent(@PathVariable String id, @RequestBody EstudianteInputDto estudianteInputDto) throws Exception {
        if (studentRepository.existsById(id)){
            if (!studentRepository.existsStudentByNameAndSurname("Hola",estudianteInputDto.getSurname())) {
                Student student = estudianteInputDto.getStudent();
                studentRepository.save(student);
                return;
            }
            throw new Exception("Ya existe un usuario con ese nombre y apellidos");
        }
        throw new Exception("No existe un usuario con ese id");
    }
}