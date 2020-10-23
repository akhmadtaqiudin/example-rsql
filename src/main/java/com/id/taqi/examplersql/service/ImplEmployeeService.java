package com.id.taqi.examplersql.service;

import com.id.taqi.examplersql.dao.EmployeeReq;
import com.id.taqi.examplersql.domain.entity.Employee;
import com.id.taqi.examplersql.domain.repository.EmployeeRepository;
import com.id.taqi.examplersql.mapping.EmployeeMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class ImplEmployeeService implements EmployeeService{

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private EmployeeMapping mapping;

    @Override
    public Page<EmployeeReq> findAllLike(String filter, Pageable pgb) {
        return null;
    }

    @Override
    public Page<EmployeeReq> findAllIn(String filter, Pageable pgb) {
        return null;
    }

    @Override
    public Optional<EmployeeReq> save(EmployeeReq req) {
        Optional<Employee> existing = Optional.ofNullable(repository.save(mapping.toEntity(req)));
        return Optional.of(mapping.toRequest(existing.get()));
    }

    @Override
    public Optional<EmployeeReq> update(EmployeeReq req) {
        return Optional.empty();
    }
}
