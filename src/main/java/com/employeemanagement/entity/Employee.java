package com.employeemanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EMPLOYEE_INFO")
@Getter
@Setter
public class Employee {
//    id,firstName,lastName,email,gender,contactNo,country,dob
//    employeename,title,businessunit,place,supervisorid,competencies,salary

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "employeename")
    private String employeeName;
    @Column(name = "title")
    private String title;
    @Column(name = "businessunit")
    private String businessUnit;
    @Column(name = "place")
    private String place;
    @Column(name = "supervisorid")
    private String supervisorId;
    @Column(name = "competencies")
    private String competencies;
    @Column(name = "salary")
    private String salary;

}
