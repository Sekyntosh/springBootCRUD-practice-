package com.springbootcrud.SpringBootCrud.service;

import java.util.ArrayList;
import java.util.List;
import com.springbootcrud.SpringBootCrud.models.Employee;
import com.springbootcrud.SpringBootCrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
       employeeRepository.findAll().forEach(employee -> list.add(employee));

        return list;
    }

    public Employee getEmployeeId(Integer Id) {
        return employeeRepository.findById(Id).get();
    }

    public boolean saveOrUpdateEmployee(Employee employee) {
        Employee emp = employeeRepository.save(employee);
        if (emp != null && employeeRepository.findById(emp.getId()) != null) {
            return true;
        }

        return false;
    }

    public boolean deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
        if (getEmployeeId(id) != null) {
            return false;
        }

        return true;
    }
}
