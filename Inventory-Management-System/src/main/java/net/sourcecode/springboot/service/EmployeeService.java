package net.sourcecode.springboot.service;

import net.sourcecode.springboot.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeByid(long id);
    void deleteEmployeeById(long id);
}
