package com.springbootcrud.SpringBootCrud;

import com.springbootcrud.SpringBootCrud.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Employeerepo extends JpaRepository<Employee,Integer> {

}
