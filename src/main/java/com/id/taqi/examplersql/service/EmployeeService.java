package com.id.taqi.examplersql.service;

import com.id.taqi.examplersql.dao.EmployeeReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmployeeService {
    Page<EmployeeReq> findAllLike(String filter, Pageable pgb);
    Page<EmployeeReq> findAllIn(String filter, Pageable pgb);
    Optional<EmployeeReq> save(EmployeeReq req);
    Optional<EmployeeReq> update(EmployeeReq req);
}
