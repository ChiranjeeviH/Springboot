package com.shopping.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.model.Item;
import com.shopping.cart.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	ItemRepository itemRepository;

	@Override
	public Item createItem(Item item) {
		// TODO Auto-generated method stub
		return itemRepository.save(item);
	}

	@Override
	public Item getItem(long id) {
		return itemRepository.findById(id).orElseThrow();
	}

	@Override
	public Item updateItem(long id, Item item) {
		Item currentItem = itemRepository.findById(id).orElseThrow();
		currentItem.setItemName(item.getItemName());
		currentItem.setItemPrice(item.getItemPrice());
		itemRepository.save(currentItem);
		
		return itemRepository.findById(id).orElseThrow();
	}

	@Override
	public void deleteItem(long id) {
		 itemRepository.deleteById(id);

	}

	@Override
	public List<Item> getItems() {
		
		return itemRepository.findAll();
	}

}
