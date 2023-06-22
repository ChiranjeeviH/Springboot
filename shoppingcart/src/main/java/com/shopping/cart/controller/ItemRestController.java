package com.shopping.cart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.cart.model.Item;
import com.shopping.cart.service.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/item")
@Tag(name="Item Rest Endpoint")
public class ItemRestController {

	@Autowired
	ItemService itemService;

	Logger LOGGER = LoggerFactory.getLogger(ItemRestController.class);

	@Operation(
		      summary = "Create Item ",
		      description = "Create a Item object by specifying its id. The response is Item object with id, name, price.",
		      tags = { "Item", "get" })
		  @ApiResponses({
		      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Item.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
		      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PostMapping("/create")
	public Item createItem(@Valid @RequestBody Item item) {
		LOGGER.debug("Creating item with the values " + item.toString());
		return itemService.createItem(item);

	}
	
	@Operation(
		      summary = "Retrieve Item by Id",
		      description = "Get a Item object by specifying its id. The response is Item object with id, name, price.",
		      tags = { "Item", "get" })
		  @ApiResponses({
		      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Item.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
		      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/get/{id}")
	@Transactional(readOnly = true)
	@Cacheable("item-cache")
	public Item getItem(@PathVariable(value = "id") Long id) {
		LOGGER.debug("Getting item using id " + id);
		return itemService.getItem(id);

	}
	
	@Operation(
		      summary = "Retrieve all items",
		      description = "Get a Item object by specifying its id. The response is Item object with id, name, price.",
		      tags = { "Item", "get" })
		  @ApiResponses({
		      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Item.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
		      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/get")
	public List<Item> getItems() {
		LOGGER.debug("Getting All items ");

		return itemService.getItems();

	}
	
	@Operation(
		      summary = "Update Item ",
		      description = "Update a Item object by specifying its id. The response is Item object with id, name, price.",
		      tags = { "Item", "get" })
		  @ApiResponses({
		      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Item.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
		      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PutMapping("/put/{id}")
	public Item updateItem(@PathVariable(value = "id") Long id, @RequestBody Item item) {

		LOGGER.debug("Update item details for the ID " + id + "Old values are " + itemService.getItem(id).toString());

		return itemService.updateItem(id, item);

	}

	@Operation(
		      summary = "Deletre Item ",
		      description = "Delete a Item object by specifying its id. The response is Item object with id, name, price.",
		      tags = { "Item", "get" })
		  @ApiResponses({
		      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Item.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
		      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@DeleteMapping("/delete/{id}")
	@CacheEvict("item-cache")
	public void deleteItem(@PathVariable(value = "id") Long id) {
		LOGGER.debug("deleting item for the id " + id);

		itemService.deleteItem(id);

	}

	/*
	 * This is for UI rendering using Thymeleaf
	 */

	@GetMapping("/getUI")
	public String getItem() {

		return "items";

	}

	@GetMapping("/sendData")
	public ModelAndView sendData() {
		ModelAndView mav = new ModelAndView("data");
		mav.addObject("message", "Stay Hungry , Stay Foolish");

		return mav;
	}

	@GetMapping("/details/{id}")
	public ModelAndView itemDetails(@PathVariable(value="id") long id) {
		ModelAndView mav = new ModelAndView("item");
		Item item = itemService.getItem(id);
		LOGGER.debug("Getting item with the values " + item.toString());
		mav.addObject("item", item);
		return mav;
	}
	
	@GetMapping("/details")
	public ModelAndView itemDetails() {
		ModelAndView mav = new ModelAndView("items");
		List<Item> items = itemService.getItems();
		//LOGGER.debug("Getting item with the values " + item.toString());
		mav.addObject("items", items);
		return mav;
	}
	
	@RequestMapping("/addItem")
	public ModelAndView addItem(@ModelAttribute Item item) {
		ModelAndView mav = new ModelAndView("addItem");
		mav.addObject("item", item);
		itemService.createItem(item);
		return mav;
	}
	
	@RequestMapping("/saveItem")
	public ModelAndView saveItems(@ModelAttribute Item item) {
		ModelAndView mav = new ModelAndView("saveItem");
		mav.addObject("item", item);
		return mav;
	}
	
	
	

}
