package com.id.taqi.examplersql.service;

import com.id.taqi.examplersql.dao.EmployeeReq;
import com.id.taqi.examplersql.dao.EmployeeRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmployeeService {
    Page<EmployeeRes> findAllLike(String filter, Pageable pgb);
    Page<EmployeeRes> findAllIn(String filter, Pageable pgb);
    Page<EmployeeRes> findAllCriteria(String firstName, String lastName, Pageable pageable);
    Optional<EmployeeRes> save(EmployeeReq req);
    Optional<EmployeeRes> update(EmployeeReq req);
    void delete(Long id);
}