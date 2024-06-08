package com.springJPA.services;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class SchedulerService {
	private Logger logger = LoggerFactory.getLogger(SchedulerService.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	 @Scheduled(cron = "10 * * * * *") 
	   public void scheduledMethod() {
		 logger.info("Hello cron Scheduler Service: " +dateFormat.format(new Date())); 
	   } 
	 
		@Scheduled(fixedDelay = 6000)
	   public void reportCurrentTime() {
			logger.info("The time  fixedDelay is now {}", dateFormat.format(new Date()));

		}

		@Scheduled(fixedRate = 5000) 
		public void reportfixedRate() {
		   logger.info("The time  fixedRate is now {}", dateFormat.format(new Date()));
		}

}

