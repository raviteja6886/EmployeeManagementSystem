package com.springJPA.Dao;

import com.springJPA.objects.EmployeeRequest;
import com.springJPA.objects.EmployeeResponse;
import com.springJPA.objects.PdfResponse;

public interface EmployeeDao {
	
	EmployeeResponse addEmployee(EmployeeRequest request);
	EmployeeResponse getEmpDetails();
	EmployeeResponse deleteEmpById(int id);
	PdfResponse generatePDF();
	

}
