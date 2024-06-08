package com.springJPA;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springJPA.model.Employee;
import com.springJPA.repository.EmployeeRepository;
import com.springJPA.services.EmployeeService;


@SpringBootTest

class SpringBootJpaExampleApplicationTests {
	
	@MockBean
	private EmployeeRepository repo;
	@Autowired
	EmployeeService service;
	/*
	 * @Test void contextLoads() { }
	 */
	
	@Test
	public void getEmployeesTest()
	{	
		List<Employee>empList=new ArrayList<>();
		empList.add(new Employee(1,"ravi","hyd"));
		empList.add(new Employee(2,"kavya","vskp"));
		when(repo.findAll()).thenReturn(empList);
		
		assertEquals(2, service.getEmployees().size());
	}
	
	@Test
	public void saveEmployeeTest() {
		
		Employee e = new Employee(15,"Prasad","bangalore");
		
		repo.save(e);
		
		//verify(repo,times(1)).save(e);
		
		when(repo.save(e)).thenReturn(e);
		
		Assertions.assertEquals(e,service.addEmployee(e));
	}
	
	

}
