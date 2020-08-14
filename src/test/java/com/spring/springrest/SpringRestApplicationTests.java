package com.spring.springrest;

import com.spring.springrest.client.StudentRestClient;
import com.spring.springrest.model.Employee;
import com.spring.springrest.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringRestApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    private StudentRestClient client;

    @BeforeEach
    void setup() {
        assertNotNull(restTemplate);
        client = new StudentRestClient(restTemplate, "http://localhost", 8080);
    }
    @Test
    void test_getForEntity() {
        ResponseEntity<Student> entity = client.getForEntity(1);
        assertNotNull(entity);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(entity.getBody());
        assertEquals("johnDoe", entity.getBody());
    }

    /*
    @Test
    void test_getForEntityNotFound() {
        ResponseEntity<Student> entity = client.getForEntity(-1);
        assertNotNull(entity);
        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
        assertNull(entity.getBody());
    }

     */
/*
    @Test
    void test_getAll() {
        List<Student> employees = client.getAll(1, 3);

        assertNotNull(employees);
        assertEquals(3, employees.size());
        assertEquals("johnDoe", employees.get(0));
        assertEquals("maryJackson", employees.get(1));
        assertEquals("peterGrey", employees.get(2));
    }

 */




}

