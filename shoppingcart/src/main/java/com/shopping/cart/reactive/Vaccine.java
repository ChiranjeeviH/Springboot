package com.shopping.cart.reactive;

public class Vaccine {
	
	public Vaccine(String name) {
		this.name = name;
	}
	
	public Vaccine(String name, boolean delivered) {
		this.name = name;
		this.delivered = delivered;
	}
	private String name;
	private boolean delivered;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDelivered() {
		return delivered;
	}
	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

}
