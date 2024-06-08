package com.springJPA.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.springJPA.objects.EmailRequest;
import com.springJPA.objects.EmployeeResponse;
import com.springJPA.services.EmailService;
@RestController
public class EmailController {
	
		private org.slf4j.Logger logger = LoggerFactory.getLogger(EmailController.class);
			@Autowired
			EmailService emailService;
		  
		
		 
		 @PostMapping("/sendEmail")
			public ResponseEntity<EmployeeResponse> sendEmail(@RequestBody EmailRequest request) throws Exception {
			 logger.info("addEmployee start");
				EmployeeResponse response=new EmployeeResponse();
				response = emailService.sendEmail(request);
				
				 Gson gson = new Gson();
			       // response=gson.toJson(res);
				logger.info("addEmployee end"+gson.toJson(response));
				return new ResponseEntity<>(response, HttpStatus.OK);
			}


}
