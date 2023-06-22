package com.shopping.cart.model;

import java.io.Serializable;

import com.hazelcast.org.codehaus.commons.nullanalysis.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name="item")
public class Item implements Serializable{
	
	public Item() {
		super();
	}

	private static final long serialVersionUID = 1L;

	public long getId() {
		return id;
	}

	public Item(Long id,String itemName, int itemPrice) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String itemName;
	
	@Min(value = 1 , message = "Minimum price should be greater than 1")
	private int itemPrice;

	@Override
	public String toString() {
		return "Item [itemName=" + itemName + ", itemPrice=" + itemPrice + "]";
	}
	
	

}
