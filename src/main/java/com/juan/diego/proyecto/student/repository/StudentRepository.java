package com.juan.diego.proyecto.student.repository;

import com.juan.diego.proyecto.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {
    public boolean existsStudentByNameAndSurname(String name, String surname);
}
