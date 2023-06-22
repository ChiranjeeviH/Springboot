package com.shopping.cart.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.shopping.cart.model.Item;

//@Configuration
public class BatchcsvConfig {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Bean
	public Job job() {
		return new JobBuilder("job-7",jobRepository).flow(step()).end().build();
	}
	
	@Bean 
	public Step step() {
		StepBuilder stepBuilder = new StepBuilder("step-1", jobRepository);
		return stepBuilder.<Item ,Item>chunk(1,transactionManager).reader(reader())
				.processor(processor()).writer(writer()).build();
	}
	
	@Bean
	public ItemReader<Item> reader(){
		
		FlatFileItemReader<Item> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(new ClassPathResource("Items.csv"));
		
		DefaultLineMapper<Item> lineMapper=new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames("id","itemName","itemPrice");
		BeanWrapperFieldSetMapper<Item> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Item.class);
		
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		flatFileItemReader.setLineMapper(lineMapper);
		
		return flatFileItemReader;
		
	}
	
	@Bean
	public ItemProcessor<Item, Item> processor(){
		
		return (i)-> {
			i.setItemPrice(i.getItemPrice()+100);
			return i;
		};
	}
	
	@Bean
	public ItemWriter<Item> writer(){
		JdbcBatchItemWriter<Item> writer = new JdbcBatchItemWriter<>();
		writer.setDataSource(dataSource);
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Item>());
		writer.setSql("INSERT INTO ITEM (ID,ITEM_NAME,ITEM_PRICE) VALUES (:id,:itemName,:itemPrice)");
		
		return writer;
		
	}


}
