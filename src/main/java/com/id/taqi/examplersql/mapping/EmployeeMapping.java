package com.id.taqi.examplersql.mapping;

import com.id.taqi.examplersql.dao.EmployeeReq;
import com.id.taqi.examplersql.domain.entity.Employee;
import org.springframework.stereotype.Component;

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

    public EmployeeReq toRequest(Employee employee){
        return EmployeeReq.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .gender(employee.getGender())
                .email(employee.getEmail())
                .address(employee.getAddress())
                .build();
    }
}
