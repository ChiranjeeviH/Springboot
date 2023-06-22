package com.shopping.cart.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.shopping.cart.model.Item;

public class BatchReader implements ItemReader<Item> {
	
	Item item = new Item(20L,"Rackets",3000);
	int count=0;
	private static Logger LOGGER = LoggerFactory.getLogger(BatchReader.class);

	@Override
	public Item read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		LOGGER.info("Inside BatchReader");
		if(count!=1) {
			count++;
			return item;
		}
		return null;
		
	}

}
