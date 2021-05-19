package com.juan.diego.proyecto.student.repository;

import com.juan.diego.proyecto.student.dto.EstudianteOutputDto;
import com.juan.diego.proyecto.student.dto.EstudianteSearchInputDto;
import com.juan.diego.proyecto.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
    public boolean existsStudentByNameAndSurname(String name, String surname);
}
