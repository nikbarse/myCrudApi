package com.jbk.EmployeeAPI.controller;

import com.jbk.EmployeeAPI.EmployeeCache;
import com.jbk.EmployeeAPI.repository.EmployeeRepo;
import com.jbk.EmployeeAPI.entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepo repo;
	// 35:09
	@Autowired
	private EmployeeCache employeeCache;

	@GetMapping("/msg")
	public String getMsg() {
		return "hey its Employee CRUD APIs";
	}

// POST-->create API
// GET--> search API
// PUT--> Update API
// Delete-->Delete entity From Db API

	// API for add Employee
	@PostMapping(value = "/addemp")
	public Employee addEmployee(@RequestBody Employee employee) {
		Employee emp = repo.save(employee);
		return emp;
	}

	// API for fetch Employee by their id
	// take a input of id to the api with path variable
	// if employee founds return the complete body of employee else return employee
	// not found

	@GetMapping(value = "/getemp/{id}")
//	public Optional<Employee> getEmployee(@PathVariable long id) {
//		Optional<Employee> emp = repo.findById(id); //--> it may cause null pointer exception
//		return emp;
//	}
	public Employee getEmployee(@PathVariable long id) {
		 employeeCache.loadCache();
		 return employeeCache.hashMap.get(id);
	}

	// API for fetch all Employee
	@GetMapping(value = "/getallemp")
	public List<Employee> getallEmployee() {
		List<Employee> list = repo.findAll();
		return list;
	}

	// API for update Employee Details
	@PutMapping(value = "/updateemp")
	public Employee updateEmp(@RequestBody Employee employee) {
		
		// First we need to check employee present or not
		// If present the transform it
		// replace all old info with new info
		// assign it to all attributes
		// save the new info
		// return new updated info to user
		
		long tempId = employee.getId();
		Employee oldEmployee = repo.getById(tempId);
		if (oldEmployee.getId() != 0) {
			oldEmployee.seteName(employee.geteName());
			oldEmployee.seteAddress(employee.geteAddress());
			return repo.save(oldEmployee);
		} else {
			return null;
		}
	}

	// API for delete Employee by their id
	@DeleteMapping("/deleteemp/{id}")
	public String deleteEmp(@PathVariable long id) {
		repo.deleteById(id);
		return "deleted Successfully";
	}
}
