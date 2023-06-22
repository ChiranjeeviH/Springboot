package com.shopping.cart.service;

import java.util.List;

import com.shopping.cart.model.Item;

public interface ItemService {
	
	public Item createItem(Item item);
	
	public Item getItem(long id);
	
	public Item updateItem(long id, Item item);
	
	public void deleteItem(long id);
	
	public List<Item> getItems();

}
