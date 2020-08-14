package com.spring.springrest.service;

import com.spring.springrest.dto.StudentDTO;
import com.spring.springrest.model.Student;
import com.spring.springrest.repo.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {

    public void create(Student Student);

    public void delete(Student student);

    public StudentDTO findByStudentId(int id);

    public List<StudentDTO> findAllStudents();


}
