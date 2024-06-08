package com.springJPA.objects;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springJPA.model.Employee;
@Component
public class EmployeeResponse {

	
	private List<Employee>employeesList;
	private String message;
	private String status;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Employee> getEmployeesList() {
		return employeesList;
	}
	public void setEmployeesList(List<Employee> employeesList) {
		this.employeesList = employeesList;
	}
	
}
