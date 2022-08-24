package net.sourcecode.springboot.service.service.impl;

import net.sourcecode.springboot.model.Employee;
import net.sourcecode.springboot.repository.EmployeeRepo;
import net.sourcecode.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll(); // findall method simply get all employees
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepo.save(employee);
    }

    @Override
    public Employee getEmployeeByid(long id) {
        Optional<Employee> optional = employeeRepo.findById(id);
        Employee employee = null;
        if(optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new RuntimeException("Employee Not Found for id ::" + id);
        }
        return employee;
    }

    @Override
    public void deleteEmployeeById(long id) {
        this.employeeRepo.deleteById(id);
    }
}
