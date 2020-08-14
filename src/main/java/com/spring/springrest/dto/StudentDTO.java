package com.spring.springrest.dto;

import com.spring.springrest.model.Student;

public class StudentDTO {

    private int id;
    private String name;
    private String phone;
    private String address;

    public StudentDTO(Student student) {
        this.id=student.getId();
        this.name=student.getName();
        this.phone=student.getPhone();
        this.address=student.getAddress();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
