package com.shopping.cart.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.shopping.cart.model.Item;
import com.shopping.cart.service.ItemService;

public class BatchProcessor implements ItemProcessor<Item, Item> {
	
	private static Logger LOGGER = LoggerFactory.getLogger(BatchProcessor.class);
	@Autowired
	ItemService itemService;

	@Override
	public Item process(Item item) throws Exception {
		LOGGER.warn("Inside BatchProcess");
		return itemService.createItem(item);
	}

}
