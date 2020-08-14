package com.spring.springrest.controller;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.InetSocketAddress;
import java.util.Map;

@RestController
@RequestMapping("/head")
public class HeaderController {


    @GetMapping("/greeting")
    public ResponseEntity<String> greeting(@RequestHeader(value = "accept-language") String language) {
        // code that uses the language variable
        return new ResponseEntity<String>("greeting "+language, HttpStatus.OK);
    }

    @GetMapping("/double")
    public ResponseEntity<String> doubleNumber(@RequestHeader("my-number") int myNumber) {
        return new ResponseEntity<String>(String.format("%d * 2 = %d",
                myNumber, (myNumber * 2)), HttpStatus.OK);
    }

    @GetMapping("/listHeaders")
    public ResponseEntity<String> listAllHeaders(@RequestHeader Map<String, String> headers) {
      headers.forEach((key, value) -> {
     System.out.println(String.format("Header '%s' = %s", key, value));
      }) ;
         return new ResponseEntity<String>(String.format("Listed %d headers", headers.size()), HttpStatus.OK);
    }

    @GetMapping("/getBaseUrl")
    public ResponseEntity<String> getBaseUrl(@RequestHeader HttpHeaders headers) {
        InetSocketAddress host = headers.getHost();
        String url = "http://" + host.getHostName() + ":" + host.getPort();
        return new ResponseEntity<String>(String.format("Base URL = %s", url), HttpStatus.OK);
    }

    @GetMapping("/http-servlet-response")
    public String usingHttpServletResponse(HttpServletResponse response) {
        response.addHeader("Baeldung-Example-Header", "Value-HttpServletResponse");
        return "Response with header using HttpServletResponse";
    }

    @GetMapping("/response-entity-builder-with-http-headers")
    public ResponseEntity<String> usingResponseEntityBuilderAndHttpHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Baeldung-Example-Header", "Value-ResponseEntityBuilderWithHttpHeaders");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("Response with header using ResponseEntity");
    }
}
