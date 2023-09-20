package com.employeemanagement.controller;
import com.employeemanagement.dto.EmployeeDto;
import com.employeemanagement.entity.Employee;
import com.employeemanagement.service.EmployeeService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    @PostMapping("/importEmployees")
    public void importCsvToDBJob() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getemployees")
    public List<Employee> getEmployee() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/getbybusinessunit/{businessunit}")
    public List<Employee> getByBusinessUnit(@PathVariable("businessunit") String businessUnit) {
        return employeeService.findByBusinessUnit(businessUnit);
    }

    @GetMapping("/getbyplace/{place}")
    public List<Employee> getByPlace(@PathVariable("place") String place) {
        return employeeService.findByPlace(place);
    }

    @PutMapping("/employee/place/{place}/salary/{percentage}")
    public List<Employee> updateSalaryByPlace(@PathVariable("place") String place, @PathVariable("percentage") Double percentage) {
        return employeeService.updateSalaryByPlace(place, percentage);
    }

    @PutMapping("/employee/title/{title}/place/{place}")
    public List<Employee> updatePlaceByTitle(@PathVariable("title") String title, @PathVariable("place") String place) {
        return employeeService.updatePlaceByTitle(title, place);
    }

    @PostMapping("/employee/insert")
    public Employee insertNewEmployee(@RequestBody Employee employee){
        return employeeService.insertEmployee(employee);
    }

    @DeleteMapping("/employee/delete/{id}")
    public void removeAnEmployee(@PathVariable("id") Integer id){
        employeeService.removeAnEmployee(id);
    }

    @GetMapping("/employee/getsalrangebytitle/{title}")
    public EmployeeDto getRangeOfSalaryByTitle(@PathVariable("title") String title) {
         return employeeService.getRangeOfSalaryByTitle(title);
    }

}
