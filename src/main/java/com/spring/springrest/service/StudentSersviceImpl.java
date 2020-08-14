package com.spring.springrest.service;

import com.spring.springrest.dto.StudentDTO;
import com.spring.springrest.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentSersviceImpl implements StudentService{

    @Override
    public void create(Student Student) {

    }

    @Override
    public void delete(Student student) {

    }

    @Override
    public StudentDTO findByStudentId(int id) {
        return null;
    }

    @Override
    public List<StudentDTO> findAllStudents() {
        return null;
    }
}
