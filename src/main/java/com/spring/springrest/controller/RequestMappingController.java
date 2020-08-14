package com.spring.springrest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class RequestMappingController {

    @GetMapping("/ex/foods")
    public String getFoods() {
        return "Apple,Orange,Mango";
    }

    @PostMapping("/ex/foods")
    public String postFoods() {
        return "Post Apple,Orange,Mango";
    }

    @GetMapping(value = "/ex/foods/head", headers = "key=val")
    public String getFoodsHeader() {
        return "Get some Foos with Header";
    }

    @GetMapping(
            value = "/ex/foods",
            headers = "Accept=application/json")
    public String getFoosAsJsonFromBrowser() {
        return "Get some Food s with Header Old";
    }

    @GetMapping(
            value = "/ex/foods/json",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getFoosAsJsonFromRest() {
        return "Get some Foods with json";
    }

    @GetMapping(value = "/ex/foods/path/{id}")
    public String getFoodsBySimplePathWithPathVariable(
            @PathVariable("id") long id) {
        return "Get a specific Foo with id=" + id;
    }

    @GetMapping(value = "/ex/foods/{id}")
    public String getFoodsBySimplePathWithPathVariable(
            @PathVariable String id) {
        return "Get a specific Foo with id=" + id;
    }

    @GetMapping(value = "/ex/foods/{fooid}/bar/{barid}")
    public String getFoosBySimplePathWithPathVariables
            (@PathVariable long fooid, @PathVariable long barid) {
        return "Get a specific Bar with id=" + barid +
                " from a Foo with id=" + fooid;
    }

    @GetMapping(value = "/ex/foods/{numericId:[\\d]+}")
    public String getBarsBySimplePathWithPathVariable(
            @PathVariable long numericId) {
        return "Get a specific Bar with id=" + numericId;
    }

    @GetMapping(value = "/ex/foods/parm")
    public String getBarBySimplePathWithRequestParam(
            @RequestParam("id") long id) {
        return "Get a specific Bar with id=" + id;
    }


    @GetMapping(value = "/ex/foods/parm", params = {"id", "second"})
    public String getBarBySimplePathWithExplicitRequestParam(
            @RequestParam("id") long id, @RequestParam("second") long second) {
        System.out.println("id=" + id + "second" + second);
        return "Get a specific Bar with id=" + id + " second=" + second;
    }

    @RequestMapping(
            value = "/ex/foos/multiple",
            method = {RequestMethod.PUT, RequestMethod.POST}
    )
    public String putAndPostFoos() {
        return "Advanced - PUT and POST within single method";

    }

}


