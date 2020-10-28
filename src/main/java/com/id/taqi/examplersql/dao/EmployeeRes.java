package com.id.taqi.examplersql.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRes {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String gender;
}