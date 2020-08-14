package com.spring.springrest.controller;

import com.spring.springrest.dto.StudentDTO;
import com.spring.springrest.model.Student;
import com.spring.springrest.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rest/students")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> get(@PathVariable int id) {
        Optional<Student> student = Optional.ofNullable(studentRepo.getOne(id));
        StudentDTO dto=new StudentDTO(student.get());

        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Student>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                                      @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        List<Student> employeesSubList = calculateEmployeeSubList(page, pageSize);
        return ResponseEntity.ok(employeesSubList);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> createStudent(@RequestBody Student student,@RequestHeader ("User-Agent") String useragent) {
        studentRepo.save(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", "/rest/employees/" + student.getId())
                .header("user-agent",useragent)
                .body(student);
    }


    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> update(@PathVariable long id, @RequestBody Student request) {
      Optional<Student> student = studentRepo.findById((int) id);
        if (!student.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        else {
            student.get().setName(request.getName());
            student.get().setPhone(request.getPhone());
            student.get().setAddress(request.getAddress());
            studentRepo.saveAndFlush(student.get());
            return ResponseEntity.ok(student.get());
        }
    }

    private List<Student> calculateEmployeeSubList(int page, int pageSize) {
        List<Student> employeeList = studentRepo.findAll();

        int startIndex = page * pageSize - pageSize;
        int endIndex = Math.min(page * pageSize, employeeList.size());

        try {
            return employeeList.subList(startIndex, endIndex);
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
