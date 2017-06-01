package com.anand.store.simulator.entities;

public abstract class AbstractProduct implements Product {
	
	static int id;
	
	public AbstractProduct(){
		id++;
	}
	
	public float getPrice() {
		return 0;
	}

	public String getUnit() {
		return null;
	}

	public float getQuantity() {
		return 0;
	}
	
	@Override
	public String toString() {
		return "Product "+id;
	}
}
