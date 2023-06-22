package com.shopping.cart.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.shopping.cart.model.Item;

@Configuration
public class BatchConfig {
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Bean
	public Job job() {
		return new JobBuilder("job-5",jobRepository).start(step()).listener(listner()).build();
	}
	
	@Bean 
	public Step step() {
		StepBuilder stepBuilder = new StepBuilder("step-1", jobRepository);
		return stepBuilder.<Item ,Item>chunk(1,transactionManager).reader(reader())
				.processor(processor()).writer(writer()).build();
	}

	@Bean
	public BatchReader reader() {

		return new BatchReader();

	}

	@Bean
	public BatchWriter writer() {

		return new BatchWriter();

	}

	@Bean
	public BatchProcessor processor() {

		return new BatchProcessor();

	}

	@Bean
	public BatchJobListener listner() {
		return new BatchJobListener();
	}

}
