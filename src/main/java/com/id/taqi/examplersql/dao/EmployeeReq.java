package com.id.taqi.examplersql.dao;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeReq {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String gender;
}
