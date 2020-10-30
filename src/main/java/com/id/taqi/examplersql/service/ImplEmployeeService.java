package com.id.taqi.examplersql.service;

import com.id.taqi.examplersql.dao.EmployeeReq;
import com.id.taqi.examplersql.dao.EmployeeRes;
import com.id.taqi.examplersql.domain.entity.Employee;
import com.id.taqi.examplersql.domain.repository.EmployeeRepository;
import com.id.taqi.examplersql.mapping.EmployeeMapping;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.github.perplexhub.rsql.RSQLJPASupport.toSpecification;

@Service
@Slf4j
public class ImplEmployeeService implements EmployeeService{

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private EmployeeMapping mapping;

    @Override
    public Page<EmployeeRes> findAllLike(String filter, Pageable pgb) {
        log.info("param filter = {}",filter);
        Page<Employee> page = (StringUtils.isBlank(filter)) ? repository.findAll(pgb) : repository.findAll(toSpecification(filter), pgb);
        return new PageImpl<>(mapping.toRequest(page.getContent()), pgb, page.getTotalElements());
    }

    @Override
    public Page<EmployeeRes> findAllIn(String firstName1, String firstName2, Pageable pgb) {
        if (StringUtils.isBlank(firstName1) || StringUtils.isBlank(firstName2))
            return null;

        String filter = "firstName=in=("+firstName1+","+firstName2+")";
        log.info("param filter = {}",filter);
        Page<Employee> page =  repository.findAll(toSpecification(filter), pgb);
        return new PageImpl<>(mapping.toRequest(page.getContent()), pgb, page.getTotalElements());
    }

    @Override
    public Page<EmployeeRes> findAllCriteria(String firstName, String lastName, Pageable pgb) {
        Page<Employee> page =  repository.findAll(findSpec(firstName, lastName), pgb);
        return new PageImpl<>(mapping.toRequest(page.getContent()), pgb, page.getTotalElements());
    }

    private Specification<Employee> findSpec(String firstName, String lastName) {
        return (Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> p = new ArrayList<>();

            if(StringUtils.isNotBlank(firstName)) {
                p.add(cb.like(root.get("firstName"), "%"+firstName+"%"));
            }

            if(StringUtils.isNotBlank(lastName)) {
                p.add(cb.like(root.get("lastName"), "%"+lastName+"%"));
            }

//            if(StringUtils.isNotBlank(firstName)) {
//                p.add(cb.equal(root.get("firstName"), firstName));
//            }

            return cb.and(p.toArray(new Predicate[]{}));
        };
    }

    @Override
    public Optional<EmployeeRes> save(EmployeeReq req) {
        Optional<Employee> existing = Optional.ofNullable(repository.save(mapping.toEntity(req)));
        return Optional.of(mapping.toRequest(existing.get()));
    }

    @Override
    public Optional<EmployeeRes> update(EmployeeReq req) {
        Optional<Employee> existing = Optional.ofNullable(repository.findByEmail(req.getEmail()));
        if (!existing.isPresent())
            return Optional.empty();

        Employee employee = existing.get();
        employee.setAddress(req.getAddress());
        employee.setFirstName(req.getFirstName());
        employee.setLastName(req.getLastName());
        employee.setGender(req.getGender());
        employee = repository.save(employee);
        return Optional.of(mapping.toRequest(employee));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
