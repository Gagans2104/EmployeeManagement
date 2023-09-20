package com.employeemanagement.service;

import com.employeemanagement.dto.EmployeeDto;
import com.employeemanagement.entity.Employee;
import com.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    public List<Employee> updateSalaryByPlace(String place,Double percentage){
        List<Employee> empDataByPlace = employeeRepository.findByPlace(place);
        List<Employee> employeeDetailsWithUpdatedSalary = new ArrayList<>();
        for(Employee employee: empDataByPlace){
            double salaryInDouble = Double.parseDouble(employee.getSalary());
            Double updatedSalary = salaryInDouble + (salaryInDouble * percentage) /100;
            employee.setSalary(updatedSalary.toString());
            employeeDetailsWithUpdatedSalary.add(employee);
//
//            EmployeeDto dto = new EmployeeDto(employee.getEmployeeName(), employee.getTitle(), employee.getBusinessUnit(),employee.getPlace(), employee.getSupervisorId(), employee.getCompetencies(), updatedSalary);
//            newEmployeeDetails.add(dto);
        }
        employeeRepository.saveAll(employeeDetailsWithUpdatedSalary);
        return employeeRepository.findByPlace(place);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> findByBusinessUnit(String businessUnit) {
        return employeeRepository.findByBusinessUnit(businessUnit);
    }

    public List<Employee> findByPlace(String place) {
        return employeeRepository.findByPlace(place);
    }

    public List<Employee> updatePlaceByTitle(String title, String place){
        List<Employee> employeeListByTitle = employeeRepository.findByTitle(title);
        List<Employee> updatedEmployeePlace = new ArrayList<>();
        for(Employee employee : employeeListByTitle ){
            employee.setPlace(place);

            updatedEmployeePlace.add(employee);
        }
        employeeRepository.saveAll(updatedEmployeePlace);
        return updatedEmployeePlace;
    }

    public Employee insertEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void removeAnEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDto getRangeOfSalaryByTitle(String title) {
       List<Employee> titleEmployees = employeeRepository.findByTitle(title);
//        900,700,1000,500
        double minSalary = Double.MAX_VALUE; // 10000
        double maxSalary = Double.MIN_VALUE; // -10000
        Double minSalaryEmployee = null;
        Double maxSalaryEmployee = null;

        for(Employee employee : titleEmployees){

            double salary = Double.parseDouble(employee.getSalary()); //500
//            500 < 700
            if (salary < minSalary) {
                //500
                minSalary = salary;
                //500
                minSalaryEmployee = minSalary;
            }

            // Check for maximum salary
            //1000 > 900
            if (salary > maxSalary) {
                //1000
                maxSalary = salary;
                //1000
                maxSalaryEmployee = maxSalary;
            }
        }
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setMinSalary(minSalaryEmployee);
        employeeDto.setMaxSalary(maxSalaryEmployee);
        System.out.println("minSalary = " + minSalaryEmployee + "maxSalary = " + maxSalaryEmployee);
        return employeeDto;

    }
}
