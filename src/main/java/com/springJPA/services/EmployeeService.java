package com.springJPA.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springJPA.model.Employee;
import com.springJPA.objects.EmployeeResponse;
import com.springJPA.objects.PdfResponse;
import com.springJPA.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository repo;
	@Autowired
	private EmployeeResponse response;
	
	public List<Employee>getEmployees(){
		
		List<Employee>list=repo.findAll();
		System.out.println(list);
		return list;
	
		
	}
	
	public Employee addEmployee(Employee emp) {
		//repo.save(emp);
		return repo.save(emp);
		
	}
	public void getEmployeeById(int id) {
		repo.findById(id);
		
	}
	public void updateEmployee(Employee emp) {
		repo.saveAndFlush(emp);
	}
	public void deleteEmployee(int id) {
		repo.deleteById(id);
	}

	/*
	 * public long countRecords() { return repo.countByName(); }
	 */
	public List<String> getEmpNames() {
		return repo.EmployeeNames();
	}
	public long EmployeeCount() {
		return repo.count();
	}

	
	/*
	 * public EmployeeResponse getEmpDetails() {
	 * 
	 * List<Employee> list = repo.findAll(); if (list.size() > 0) {
	 * response.setEmployeesList(list); response.setMessage("Employee details");
	 * response.setStatus("200 ok"); } return response;
	 * 
	 * }
	 */
	
}
