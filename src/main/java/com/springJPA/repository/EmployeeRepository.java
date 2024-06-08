package com.springJPA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springJPA.model.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	/*
	 * @Query("SELECT count(emp) FROM Employee emp ") Long countByName();
	 */
	
	@Query("select emp.name from Employee emp")
	List<String>EmployeeNames();
	Employee findByName(String name);
	Employee findByPassword(String password);

}
