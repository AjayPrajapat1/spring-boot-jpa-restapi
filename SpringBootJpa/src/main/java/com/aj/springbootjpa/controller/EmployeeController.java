package com.aj.springbootjpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aj.springbootjpa.dao.EmployeeDao;
import com.aj.springbootjpa.model.Employee;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeDao employeeDao;

	@GetMapping("/")
	public List<Employee> getList() {
		return employeeDao.findAll();
	}
	
	@PostMapping("/employee")
	public Employee save(@RequestBody Employee employeeObj) {
		return employeeDao.save(employeeObj);
	}
	
	@GetMapping("/employee/{id}")
	public Employee get(@PathVariable long id) {
		Optional<Employee> employee = employeeDao.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}else {
			throw new RuntimeException("Employee not found for the id "+id);
		}
	}
	
	@DeleteMapping("/employee/{id}")
	public String delete(@PathVariable long id) {
		Optional<Employee> employee = employeeDao.findById(id);
		if(employee.isPresent()) {
			employeeDao.delete(employee.get());
			return "Employee is deleted with id "+id;
		}else {
			throw new RuntimeException("Employee not found for the id "+id);
		}
	}
	
	@PutMapping("/employee/{id}")
	public Employee update(@RequestBody Employee employeeObj, @PathVariable long id) {
		Employee isExist = employeeDao.findById(id).orElseThrow(()-> new RuntimeException("employee id not found"));
		return employeeDao.save(employeeObj);
	}
}
