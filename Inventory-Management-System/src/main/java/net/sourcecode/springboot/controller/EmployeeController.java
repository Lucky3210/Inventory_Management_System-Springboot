package net.sourcecode.springboot.controller;

import net.sourcecode.springboot.model.Employee;
import net.sourcecode.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    //    display employees
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee")
    public String viewEmployee(Model model) {
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "employeeManager";

    }

    @GetMapping("/employee/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
//        create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "createEmployee";
    }

    @PostMapping("employee/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
//        save employee to database
        employeeService.saveEmployee(employee);
        return "redirect:/employee";
    }

    @GetMapping("employee/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

//        get employee from the service
        Employee employee = employeeService.getEmployeeByid(id);

//        set employee as model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "updateEmployee";
    }

    //    delete Employee
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {

//        call the delete employee method from the employeeServiceImpl where we have the implementation
//        this is same with other methods.
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/employee";
    }
}
