package com.shopping.cart.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import com.shopping.cart.model.Item;

public class BatchWriter implements ItemWriter<Item>{
	
	private static Logger LOGGER = LoggerFactory.getLogger(BatchWriter.class);

	@Override
	public void write(Chunk<? extends Item> item) throws Exception {
		
		LOGGER.info("Inside BatchWriter");
		LOGGER.info("Writing data :"+item);
		
	}

}
