package com.springJPA.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.springJPA.objects.EmailRequest;
import com.springJPA.objects.EmployeeResponse;


@Service
public class EmailServiceImpl implements EmailService {

	private Logger logger =  LoggerFactory.getLogger(EmailServiceImpl.class);
	
	@Autowired
	private JavaMailSender mailSender;
	 
	    @Value
	    ("${spring.mail.username}") 
	    private String sender;
	    
	@Override
	public EmployeeResponse sendEmail(EmailRequest request) {
		logger.info("sendEmail start:::sender::"+sender);
		EmployeeResponse res=new EmployeeResponse();
		
		  SimpleMailMessage mailMessage = new SimpleMailMessage();

      // Setting up necessary details
      mailMessage.setFrom(sender);
      mailMessage.setTo(request.getToMailAddres());
      mailMessage.setText(request.getMsgBody());
      mailMessage.setSubject(request.getSubject());

      // Sending the mail
      mailSender.send(mailMessage);
      res.setMessage("mail Sent Successfully");
      res.setStatus("Success");	
		return res;
		
		
	}

}
