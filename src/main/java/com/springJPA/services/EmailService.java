package com.springJPA.services;

import com.springJPA.objects.EmailRequest;
import com.springJPA.objects.EmployeeResponse;

public interface EmailService {
	EmployeeResponse sendEmail(EmailRequest request);
}
