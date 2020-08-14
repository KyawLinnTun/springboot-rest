package com.spring.springrest.dto;

import com.spring.springrest.model.Employee;
import org.springframework.context.annotation.EnableMBeanExport;

public class EmployeeDTO {

    private int id;
    private String  name;

    public EmployeeDTO(Employee employee){
        this.id=employee.getId();
        this.name=employee.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
