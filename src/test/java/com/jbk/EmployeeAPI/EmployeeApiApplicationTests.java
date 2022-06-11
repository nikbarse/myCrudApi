package com.jbk.EmployeeAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jbk.EmployeeAPI.entity.Employee;
import com.jbk.EmployeeAPI.repository.EmployeeRepo;


@SpringBootTest
class EmployeeApiApplicationTests {

	@Autowired
	com.jbk.EmployeeAPI.controller.EmployeeController employeeController;
	
	@Autowired
	EmployeeRepo repo;
	
	@Test
	void contextLoads() {
		//Assertions.assertThat(employeeController).isNot(null);
		System.out.println("Its executed");
	}

	@Test
	public void testFindEmployee() {
		long id = 3;
		String name = "karan";//Expected -->Actual
		Employee employee = new Employee();
		employee = repo.findById(id).get();
		assertEquals(name, employee.geteName());
		System.out.println("test executed");
	}
}
