package com.jbk.EmployeeAPI;

import com.jbk.EmployeeAPI.entity.Employee;
import com.jbk.EmployeeAPI.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeCache {

    @Autowired
   private EmployeeRepo repo;

    //HashMap

    public HashMap<Long, Employee> hashMap = new HashMap<Long, Employee>();
    public void loadCache()
    {
        List<Employee> empList = repo.findAll();

        List<Employee> sortedList = empList.stream()
                                        .filter(employee -> employee.geteAddress().equals("Pune"))
                                        .collect(Collectors.toList());

        for(Employee employee : empList)
        {
            hashMap.put(employee.getId(), employee);
        }
    } 
}
