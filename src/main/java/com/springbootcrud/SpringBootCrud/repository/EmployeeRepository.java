package com.springbootcrud.SpringBootCrud.repository;

import com.springbootcrud.SpringBootCrud.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
