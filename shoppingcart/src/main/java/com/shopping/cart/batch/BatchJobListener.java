package com.shopping.cart.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class BatchJobListener implements JobExecutionListener {
	
	private static Logger LOGGER = LoggerFactory.getLogger(BatchJobListener.class);
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		LOGGER.info("Job Started "+jobExecution.getStatus().toString());
		
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		
		LOGGER.info("Job Completed "+jobExecution.getStatus().toString());
		
	}

}
