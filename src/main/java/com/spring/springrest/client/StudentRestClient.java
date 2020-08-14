package com.spring.springrest.client;

import com.spring.springrest.model.Employee;
import com.spring.springrest.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class StudentRestClient {

    private static final String RESOURCE_PATH = "/rest/employees";

    private Logger LOG = LoggerFactory.getLogger(StudentRestClient.class);
    private String REQUEST_URI;
    private RestTemplate restTemplate;

    public StudentRestClient(RestTemplate restTemplate, String host, int port) {
        this.restTemplate = restTemplate;
        this.REQUEST_URI = host + ":" + port + RESOURCE_PATH;
    }


    /**
     * Requests the employee resource for the specified id via HTTP GET using RestTemplate method getForEntity.
     * @param id the id of the employee resource
     * @return a ResponseEntity that wraps http status code, http headers and the body of type {@link Employee}
     */
    public ResponseEntity<Student> getForEntity(int id) {
        ResponseEntity<Student> entity = restTemplate.getForEntity(REQUEST_URI + "/{id}",
                Student.class,id);

        // LOG.info("Status code value: " + domain.getStatusCodeValue());
        // LOG.info("HTTP Header 'ContentType': " + domain.getHeaders().getContentType());

        return entity;
    }


    /**
     * Requests a specified amount of employee resources via HTTP GET using RestTemplate method getForEntity.
     * The amount is specified by the given page and pageSize parameter.
     * @param page the page
     * @param pageSize the amount of elements per page
     * @return a list of employees
     */
    public List<Student> getAll(int page, int pageSize) {
        String requestUri = REQUEST_URI + "?page={page}&pageSize={pageSize}";

        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("page", Integer.toString(page));
        urlParameters.put("pageSize", Long.toString(pageSize));

        ResponseEntity<Student[]> entity = restTemplate.getForEntity(requestUri,
                Student[].class,
                urlParameters);

        return entity.getBody() != null? Arrays.asList(entity.getBody()) : Collections.emptyList();
    }

    /**
     * Requests the employee resource for the specified id via HTTP GET using RestTemplate method getForObject.
     * @param id the id of the employee resource
     * @return the employee as {@link Optional} or an empty {@link Optional} if resource not found.
     */
    public Optional<Employee> getForObject(long id) {
        Employee employee = restTemplate.getForObject(REQUEST_URI + "/{id}",
                Employee.class,
                Long.toString(id));

        return Optional.ofNullable(employee);
    }


}
