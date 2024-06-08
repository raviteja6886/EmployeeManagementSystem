package com.springJPA.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springJPA.Dao.EmployeeDao;
import com.springJPA.model.Employee;
import com.springJPA.objects.EmployeeRequest;
import com.springJPA.objects.EmployeeResponse;
import com.springJPA.objects.PdfResponse;
import com.springJPA.services.EmployeeService;
@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	@Autowired
	private EmployeeDao dao;
	@Autowired
	private EmployeeResponse response;
	
	
	@GetMapping("/employees")
	public List<Employee>getEmployees(){
		return service.getEmployees();
		
		
	}
	@PostMapping("/employee")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Employee> addEmployee(@RequestBody Employee emp) {
		service.addEmployee(emp);
		return service.getEmployees();
	}
	@PutMapping("/employee")
	public String updateEmployee(@RequestBody Employee emp) {
		service.updateEmployee(emp);
		return "employee updated";
	}
	
	@DeleteMapping("/employee/{id}")
	public String deleteEmployee(@PathVariable int id) {
		
		service.deleteEmployee(id);
		
		return "deleted successfully";
		
	}

	/*
	 * @GetMapping("/count") public long employeeeRecords() { return
	 * service.countRecords(); }
	 */
	@GetMapping("/empNames")
	public List<String>getEmpNames(){
		return service.getEmpNames();
	}
	@GetMapping("/countEmployees")
	public long countEmployees() {
		return service.EmployeeCount();
	}
	
	//Using responseEntity
	
	@GetMapping("/getEmployee")
	public ResponseEntity<EmployeeResponse>getEmpDetails() {
		
		//EmployeeResponse response = new EmployeeResponse();
		
		response = dao.getEmpDetails();
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	
	@PostMapping("/addEmployee")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<EmployeeResponse>addEmployee(@RequestBody EmployeeRequest request)throws IOException{
		
		EmployeeResponse response = new EmployeeResponse();
		response = dao.addEmployee(request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	/*
	 * @GetMapping("/get") public ResponseEntity<EmployeeResponse> getAll(){
	 * 
	 * response = service.getEmpDetails();
	 * 
	 * return new ResponseEntity<>(response,HttpStatus.CREATED); }
	 */
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<EmployeeResponse>deleteEmpById(@PathVariable int id){
		
		response=dao.deleteEmpById(id);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
		
	}
	
	/*
	 * @GetMapping("/generatePDF") public ResponseEntity<PdfResponse> generatePDF()
	 * throws Exception {
	 * 
	 * PdfResponse response=new PdfResponse(); response = dao.generatePDF();
	 * 
	 * return new ResponseEntity<>(response,HttpStatus.OK);     }
	 */
	@GetMapping("/generatePDF")
	public ResponseEntity<PdfResponse>generatePdf()throws Exception{
		
		PdfResponse response = new PdfResponse();
		
		response = dao.generatePDF();
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/RESTemployees")
	public String getEmployeewithRest() {
		
		RestTemplate template = new RestTemplate();
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String>entity = new HttpEntity<String>(headers);
		return template.exchange("http://localhost:5005/employees", HttpMethod.GET,entity,String.class).getBody();
	}
	
}
