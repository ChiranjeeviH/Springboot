package com.shopping.cart.controller.mvctest;

import static org.mockito.Mockito.when;
/*import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;*/
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.shopping.cart.model.Item;
import com.shopping.cart.repository.ItemRepository;
import com.shopping.cart.service.ItemService;

//@RunWith(SpringRunner.class)
@WebMvcTest
public class ItemControllerTest {
	
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ItemService itemService;
	
	@MockBean
	private ItemRepository itemRepository;
	
	@Test
	public void testFindAll() throws Exception {
		
		Item item = new Item(1L,"Clothes",1000);
		
		List<Item> items = new ArrayList<>();
		items.add(item);
		
		when(itemService.getItems()).thenReturn(items);
		
		mockMvc.perform(get("/item/get")).andExpect(status().isOk()).andExpect(content().json(
				"[{'id':1,'itemName'='Clothes','itemPrice':1000}]"));
		
	}
	
	@Test
	public void testFindById() throws Exception {
		
		System.out.println();
		
		Item item = new Item(1L,"Clothes",1000);
		
		when(itemService.getItem(item.getId())).thenReturn(item);
		
		mockMvc.perform(get("/item/get/{id}",1L)).andExpect(status().isOk()).andExpect(content().json(
				"{'id':1,'itemName'='Clothes','itemPrice':1000}"));
		
	}


}
