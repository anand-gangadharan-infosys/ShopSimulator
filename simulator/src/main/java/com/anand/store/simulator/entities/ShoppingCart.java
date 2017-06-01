package com.anand.store.simulator.entities;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.anand.store.simulator.util.StoreRandomGenrator;


public class ShoppingCart {

	private List<Product> products;
	private static final Logger logger = LogManager.getLogger(StoreRandomGenrator.class);
	static int id;
	
	public ShoppingCart(){
		products = new ArrayList<Product>();
		id++;
	}
	
	public void addProduct(Product aProduct){
		logger.trace("Adding "+aProduct+" to  "+this);
		products.add(aProduct);
	}
	
	public boolean removeProduct(Product p){
		return products.remove(p);
	}
	
	public Float billItems(){
		logger.info("Billing for cart "+this);
		Float totalBillAmount = 0f;
		
		for (Product product : products) {
			totalBillAmount += product.getPrice();
		}
		logger.trace("Bill amount for cart "+totalBillAmount);
		return totalBillAmount;
	}
	
	public int getCartSize(){
		return products.size();
	}
	
	@Override
	public String toString() {
		return "Cart-"+id;
	}
}
