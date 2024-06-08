 package com.springJPA.Dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itextpdf.text.DocumentException;
import com.springJPA.model.Employee;
import com.springJPA.objects.EmployeeRequest;
import com.springJPA.objects.EmployeeResponse;
import com.springJPA.objects.PdfResponse;
import com.springJPA.repository.EmployeeRepository;
import com.springJPA.services.EmployeeService;
import com.springJPA.util.AESencryptionDecryption;
import com.springJPA.util.PdfGenerator;
@Repository
public class EmployeeDaoImpl implements EmployeeDao{
	@Autowired
	EmployeeRepository repo;
	@Autowired
	EmployeeService service;
	@Autowired
	EmployeeResponse response;
	@Autowired
	AESencryptionDecryption aesEnc;
	@Autowired
	PdfGenerator pdfGenerator;
	
	public EmployeeResponse getEmpDetails() {
		//EmployeeResponse response = new EmployeeResponse();

		
		List<Employee> empList = new ArrayList<>();
		
		List<Employee> list = repo.findAll();
		
		
		if(list.size()>0) {
			List<Employee>employeeList=repo.findAll();
			for (int i = 0; i < employeeList.size(); i++) {
				if (employeeList.get(i).getPassword() != null) {
					String pass = AESencryptionDecryption.getDecryption(employeeList.get(i).getPassword(),
							AESencryptionDecryption.getKey());
					employeeList.get(i).setPassword(pass);
				}
				employeeList.forEach(empList::add);
			}
			//repo.findAll().forEach(empList::add);
			//System.out.println("Size:::"+empList.size());
			response.setEmployeesList(empList);
			response.setMessage("Retrived Details");
			response.setStatus("Success");
		}
		return response;
		
	}
	
	

	@Override
	public EmployeeResponse addEmployee(EmployeeRequest request) {
		
		//EmployeeResponse response = new EmployeeResponse();
		
		Employee emp  = repo.findByName(request.getName().trim());
		if(emp==null) {
			Employee e = new Employee();
			
			e.setName(request.getName().trim());
			e.setCountry(request.getCountry());
			String encPassword = AESencryptionDecryption.getEncryption(request.getPassword(), AESencryptionDecryption.getKey());
			e.setPassword(encPassword);
			repo.saveAndFlush(e);
			response.setMessage("succcessfully added");
			response.setStatus("success");
			response.setEmployeesList(repo.findAll());
			 
		}
		else {
			response.setMessage("user is already exists....");
			response.setStatus("failed");
		}
		
		return response;
	}



	@Override
	public EmployeeResponse deleteEmpById(int id) {
		//Employee emp = new Employee();
	//	EmployeeRequest req = new EmployeeRequest();
		Optional<Employee>e = repo.findById(id);
		
		if(e.isPresent()) {
			repo.deleteById(id);
			response.setMessage("deleted successfully");
			response.setStatus("OK");
			response.setEmployeesList(repo.findAll());
		}
		else {
			response.setMessage("The employee is not available");
			response.setStatus("Error");
		}
		return response;
	}



	@Override
	public PdfResponse generatePDF() {
		PdfResponse response = new PdfResponse();

		List<Employee> empList = new ArrayList<>();
		
		List<Employee> list = repo.findAll();
		
		
		if(list.size()>0) {
			
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getPassword() != null) {
					String pass = AESencryptionDecryption.getDecryption(list.get(i).getPassword(),
							AESencryptionDecryption.getKey());
					list.get(i).setPassword(pass);
				}
				list.forEach(empList::add);
			}
			//repo.findAll().forEach(empList::add);
			//System.out.println("Size:::"+empList.size());
			
			try {
				response = pdfGenerator.employeeList(list);
			} catch (DocumentException | IOException e) {
				
				e.printStackTrace();
			}
		}

		
		
		return response;
	}

	
}
