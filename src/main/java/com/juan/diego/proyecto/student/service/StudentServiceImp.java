package com.juan.diego.proyecto.student.service;

import com.juan.diego.proyecto.student.dto.EstudianteInputDto;
import com.juan.diego.proyecto.student.dto.EstudianteOutputDto;
import com.juan.diego.proyecto.student.entity.Student;
import com.juan.diego.proyecto.student.exception.StudentBadRequestException;
import com.juan.diego.proyecto.student.exception.StudentNotFoundException;
import com.juan.diego.proyecto.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class StudentServiceImp implements IStudentService{

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<EstudianteOutputDto> getAll() {
        var estudiantes = studentRepository.findAll();
        return estudiantes.stream().map(s -> getEstudianteOutput(s)).collect(Collectors.toList());
    }

    @Override
    public EstudianteOutputDto getById(String id) throws StudentNotFoundException {
        Student student = studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException("No se ha encontrado un usuario con ese id"));
        return getEstudianteOutput(student);
    }

    @Override
    public void add(EstudianteInputDto estudianteInputDto) throws Exception {
        if (!studentRepository.existsStudentByNameAndSurname(estudianteInputDto.getName(),estudianteInputDto.getSurname())) {
            Student student = getStudentByEstudianteInputDto(estudianteInputDto);
            studentRepository.save(student);
            return;
        }
        throw new StudentBadRequestException("Los campos introducidos no son validos");
    }

    @Override
    public void update(String id, EstudianteInputDto estudianteInputDto) throws Exception {
        if (studentRepository.existsById(id)){
            Student studentOrigin = studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException("No se ha encontado un usuario con ese id"));
            if (!studentRepository.existsStudentByNameAndSurname(estudianteInputDto.getName(), estudianteInputDto.getSurname())) {
                if (estudianteInputDto.getName()==null)
                    estudianteInputDto.setName(studentOrigin.getName());
                if (estudianteInputDto.getSurname()==null)
                    estudianteInputDto.setSurname(studentOrigin.getSurname());
                if (estudianteInputDto.getCompany_email()==null)
                    estudianteInputDto.setCompany_email(studentOrigin.getCompanyEmail());
                if (estudianteInputDto.getPersonal_email()==null)
                    estudianteInputDto.setPersonal_email(studentOrigin.getPersonalEmail());
                if (estudianteInputDto.getCity()==null)
                    estudianteInputDto.setCity(studentOrigin.getCity());
                if (estudianteInputDto.getNumhoursWeek()==null)
                    estudianteInputDto.setNumhoursWeek(studentOrigin.getNumHoursWeek());
                if (estudianteInputDto.getComments()==null)
                    estudianteInputDto.setComments(studentOrigin.getComments());
                if (estudianteInputDto.getName()==null)
                    estudianteInputDto.setName(studentOrigin.getName());
                if (estudianteInputDto.getBranch()==null)
                    estudianteInputDto.setBranch(studentOrigin.getBranch());
                if (estudianteInputDto.getCreatedDate()==null)
                    estudianteInputDto.setCreatedDate(studentOrigin.getCreatedDate());
                Student student = getStudentByEstudianteInputDto(estudianteInputDto);
                studentRepository.save(student);
                return;
            }
            throw new StudentBadRequestException("Los campos introducidos no son validos");
        }
        throw new StudentNotFoundException("No se ha encontrado un usuario con ese id");
    }

    @Override
    public void delete(String id) throws StudentNotFoundException {
        if (studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return;
        }
        throw new StudentNotFoundException("No existe un usuario con ese id");
    }

    private static EstudianteOutputDto getEstudianteOutput(Student student){
        return new EstudianteOutputDto(student.getIdStudent(), student.getName(), student.getSurname(),student.getCompanyEmail(), student.getPersonalEmail(),
                student.getCity(), student.getNumHoursWeek(), student.getComments(), student.getBranch(),
                student.isActive(), student.getCreatedDate(),student.getTerminationDate());
    }

    private static Student getStudentByEstudianteInputDto(EstudianteInputDto estudianteInputDto) throws Exception {
        if ((estudianteInputDto.getTerminationDate() == null || estudianteInputDto.getCreatedDate()
                .before(estudianteInputDto.getTerminationDate()))) {
            Student student = new Student();
            student.setName(estudianteInputDto.getName());
            student.setSurname(estudianteInputDto.getSurname());
            student.setCompanyEmail(estudianteInputDto.getCompany_email());
            student.setPersonalEmail(estudianteInputDto.getPersonal_email());
            student.setCity(estudianteInputDto.getCity());
            student.setNumHoursWeek(estudianteInputDto.getNumhoursWeek());
            student.setComments(estudianteInputDto.getComments());
            student.setBranch(estudianteInputDto.getBranch());
            student.setActive(estudianteInputDto.isActive());
            student.setCreatedDate(estudianteInputDto.getCreatedDate());
            student.setTerminationDate(estudianteInputDto.getTerminationDate());
            return student;
        }
        throw new StudentBadRequestException("Los campos introducidos no son validos");
    }

}
