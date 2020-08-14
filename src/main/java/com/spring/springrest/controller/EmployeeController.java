package com.spring.springrest.controller;

import com.spring.springrest.dto.EmployeeDTO;
import com.spring.springrest.exception.RecordNotFoundException;
import com.spring.springrest.model.Employee;
import com.spring.springrest.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee){
          employeeRepo.save(employee);
          return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeId(@PathVariable("id") int id ){
        EmployeeDTO emp =null;
        try {
            emp = new EmployeeDTO(employeeRepo.getOne(id));
        }catch(EntityNotFoundException e){
            throw new RecordNotFoundException("Invalid employee id" + id);
        }

        return new  ResponseEntity<>(emp,HttpStatus.OK);

    }
}
