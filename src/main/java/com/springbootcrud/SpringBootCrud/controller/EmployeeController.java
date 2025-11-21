package com.springbootcrud.SpringBootCrud.controller;

import org.springframework.ui.Model;
import com.springbootcrud.SpringBootCrud.models.Employee;
import com.springbootcrud.SpringBootCrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping({"/", "/viewEmployees"})
    public String viewEmployees(String message,Model model) {
        List<Employee> employeeList = employeeService.getAllEmployees();

        model.addAttribute("empList", employeeList);
        model.addAttribute("message", message);

        return "viewEmployees";
    }
    @GetMapping("/addEmployee")
    public String newEmployee(@ModelAttribute("Message") String message, Model model){

        model.addAttribute("emp", new Employee());
        model.addAttribute("message", message);

        return "addEmployee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(Employee employee, RedirectAttributes redirectAttributes){
        if(employeeService.saveOrUpdateEmployee(employee)){
            redirectAttributes.addFlashAttribute("Message", "Employee has been saved successfully");
            return "redirect:/viewEmployees";
        }

        redirectAttributes.addFlashAttribute("Message", "Employee has failed to save");
        return "redirect:/addEmployees";
    }

    @GetMapping("/editEmployee/{id}")
    public String editEmployee(@PathVariable Integer id, String Message,Model model){


        Employee employee = employeeService.getEmployeeId(id);
        model.addAttribute("emp",employee);
        model.addAttribute("message", Message);

        return "EditEmployee";

    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if(employeeService.deleteEmployee(id)){
            redirectAttributes.addFlashAttribute("Message", "Employee has been deleted successfully");
            return "redirect:/viewEmployees";
        }

        redirectAttributes.addFlashAttribute("Message", "Employee has failed to be deleted");
        return "redirect:/viewEmployees";
    }
    //Finish later

    @PostMapping("/editSaveEmployee")
    public String editSaveEmployee(Employee emp, RedirectAttributes redirectAttributes){
        if(employeeService.saveOrUpdateEmployee(emp)) {
            redirectAttributes.addFlashAttribute("Message", "Employee has been saved successfully");
            return "redirect:/viewEmployees";
        }
        redirectAttributes.addFlashAttribute("Message", "Employee has failed to save");
        return "redirect:/editEmployees" + emp.getId();
    }
}
