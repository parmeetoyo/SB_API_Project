package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.employee;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<employee, Long>{

}
