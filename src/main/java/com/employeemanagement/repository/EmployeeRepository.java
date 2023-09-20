package com.employeemanagement.repository;

import com.employeemanagement.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>, JpaSpecificationExecutor<Employee> {

         List<Employee> findByBusinessUnit(String businessUnit);

         List<Employee> findByPlace(String place);

         List<Employee> findByTitle(String title);
}
