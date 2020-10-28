package com.id.taqi.examplersql.mapping;

import com.id.taqi.examplersql.dao.EmployeeReq;
import com.id.taqi.examplersql.dao.EmployeeRes;
import com.id.taqi.examplersql.domain.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapping {

    public Employee toEntity(EmployeeReq req){
        return Employee.builder()
            .firstName(req.getFirstName())
            .lastName(req.getLastName())
            .gender(req.getGender())
            .email(req.getEmail())
            .address(req.getAddress())
            .build();
    }

    public EmployeeRes toRequest(Employee employee){
        return EmployeeRes.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .gender(employee.getGender())
                .email(employee.getEmail())
                .address(employee.getAddress())
                .build();
    }

    public List<EmployeeRes> toRequest(List<Employee> list){
        return list.stream().map(this::toRequest).collect(Collectors.toList());
    }
}