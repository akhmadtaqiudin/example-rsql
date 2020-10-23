package com.id.taqi.examplersql.controller;

import com.id.taqi.examplersql.dao.EmployeeReq;
import com.id.taqi.examplersql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(name = "employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping
    public ResponseEntity save(@RequestBody EmployeeReq req){
        Optional<EmployeeReq> employeeReq = service.save(req);
        ResponseEntity entity = employeeReq.isPresent() ? ResponseEntity.of(employeeReq) : ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(entity);
    }
}
