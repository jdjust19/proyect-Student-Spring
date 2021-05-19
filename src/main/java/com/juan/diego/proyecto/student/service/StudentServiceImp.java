package com.juan.diego.proyecto.student.service;

import com.juan.diego.proyecto.student.dto.EstudianteInputDto;
import com.juan.diego.proyecto.student.dto.EstudianteOutputDto;
import com.juan.diego.proyecto.student.dto.EstudianteSearchInputDto;
import com.juan.diego.proyecto.student.entity.Student;
import com.juan.diego.proyecto.student.enums.Branch;
import com.juan.diego.proyecto.student.exception.StudentBadRequestException;
import com.juan.diego.proyecto.student.exception.StudentNotFoundException;
import com.juan.diego.proyecto.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class StudentServiceImp implements IStudentService{

    @PersistenceContext
    private EntityManager entityManager;

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
                student.setIdStudent(id);
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

    @Override
    public List<EstudianteOutputDto> getData(EstudianteSearchInputDto estudianteSearchInputDto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        HashMap<String,Object> conditions = new HashMap<>();

        if (estudianteSearchInputDto.getName()!=null)
            conditions.put("name",estudianteSearchInputDto.getName());
        if (estudianteSearchInputDto.getSurname()!=null)
            conditions.put("surname",estudianteSearchInputDto.getSurname());
        if (estudianteSearchInputDto.getCompany_email()!=null)
            conditions.put("company_email",estudianteSearchInputDto.getCompany_email());
        if (estudianteSearchInputDto.getPersonal_email()!=null)
            conditions.put("personal_email",estudianteSearchInputDto.getPersonal_email());
        if (estudianteSearchInputDto.getCity()!=null)
            conditions.put("city",estudianteSearchInputDto.getCity());
        if (estudianteSearchInputDto.getComments()!=null)
            conditions.put("comments",estudianteSearchInputDto.getComments());
        if (estudianteSearchInputDto.getBranch()!=null)
            conditions.put("branch",estudianteSearchInputDto.getBranch());
        if (estudianteSearchInputDto.getCreatedDate()!=null)
            conditions.put("createdDate",estudianteSearchInputDto.getCreatedDate());
        if (estudianteSearchInputDto.getTerminationDate()!=null)
            conditions.put("terminationDate",estudianteSearchInputDto.getTerminationDate());
        if (estudianteSearchInputDto.isActive()!=false)
            conditions.put("active",estudianteSearchInputDto.isActive());

        List<Predicate> predicates = new ArrayList<>();
        conditions.forEach((field,value)->{
            switch (field){
                case "name":
                    predicates.add(cb.like(root.get(field),"%"+(String) value+"%"));
                    break;
                case "surname":
                    predicates.add(cb.like(root.get(field),"%"+(String) value+"%"));
                    break;
                case "company_email":
                    predicates.add(cb.like(root.get(field),"%"+(String) value+"%"));
                    break;
                case "personal_email":
                    predicates.add(cb.like(root.get(field),"%"+(String) value+"%"));
                    break;
                case "city":
                    predicates.add(cb.like(root.get(field),"%"+(String) value+"%"));
                    break;
                case "comments":
                    predicates.add(cb.like(root.get(field),"%"+(String) value+"%"));
                    break;
                case "branch":
                    predicates.add(cb.like(root.get(field),"%"+(String) value+"%"));
                    break;
                case "createdDate":
                    predicates.add(cb.like(root.get(field),"%"+(String) value+"%"));
                    break;
                case "terminationDate":
                    predicates.add(cb.like(root.get(field),"%"+(String) value+"%"));
                    break;
                case "active":
                    predicates.add(cb.equal(root.get(field),(Boolean) value));
                    break;
            }
        });
        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
         List<Student> students = entityManager.createQuery(query).getResultList();
         return students.stream().map(e-> getEstudianteOutput(e)).collect(Collectors.toList());

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
