package com.aj.springbootjpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aj.springbootjpa.model.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
