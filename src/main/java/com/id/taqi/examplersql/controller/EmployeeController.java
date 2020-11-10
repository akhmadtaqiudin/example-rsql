package com.id.taqi.examplersql.controller;

import com.id.taqi.examplersql.dao.EmployeeReq;
import com.id.taqi.examplersql.dao.EmployeeRes;
import com.id.taqi.examplersql.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1/employee", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Employees", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Employees"})
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", required = false, defaultValue = "0", paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "size", required = false, defaultValue = "10", paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "sort", required = false, paramType = "query", dataType = "String")
    })
    public ResponseEntity getAll(@ApiParam(hidden = true) @ApiIgnore @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC, sort = "createdDate") Pageable pageable,
                                 @RequestParam(name = "filter", required = false) String filter){
        Page<EmployeeRes> page = service.findAllLike(filter, pageable);
        if (page.isEmpty())
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "findIn")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", required = false, defaultValue = "0", paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "size", required = false, defaultValue = "10", paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "sort", required = false, paramType = "query", dataType = "String")
    })
    public ResponseEntity getIn(@ApiParam(hidden = true) @ApiIgnore @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC, sort = "createdDate") Pageable pageable,
                                 @RequestParam(name = "filter", required = true) String filter){
        Page<EmployeeRes> page = service.findAllIn(filter, pageable);
        if (page.isEmpty())
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "findCriteria")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", required = false, defaultValue = "0", paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "size", required = false, defaultValue = "10", paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "sort", required = false, paramType = "query", dataType = "String")
    })
    public ResponseEntity getCriteria(@ApiParam(hidden = true) @ApiIgnore @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC, sort = "createdDate") Pageable pageable,
                                @RequestParam(name = "firstName", required = false) String firstName,
                                @RequestParam(name = "lastName", required = false) String lastName){
        Page<EmployeeRes> page = service.findAllCriteria(firstName, lastName, pageable);
        if (page.isEmpty())
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody EmployeeReq req){
        Optional<EmployeeRes> employeeReq = service.save(req);
        ResponseEntity entity = employeeReq.isPresent() ? ResponseEntity.of(employeeReq) : ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(entity);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody EmployeeReq req){
        Optional<EmployeeRes> employeeReq = service.update(req);
        ResponseEntity entity = employeeReq.isPresent() ? ResponseEntity.of(employeeReq) : ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Long id){
        service.delete(id);
        return ResponseEntity.ok("Data Berhasil dihapus");
    }
}
