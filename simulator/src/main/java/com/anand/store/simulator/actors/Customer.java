package com.anand.store.simulator.actors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.anand.store.simulator.entities.GroceryProduct;
import com.anand.store.simulator.entities.ShoppingCart;
import com.anand.store.simulator.entities.Wallet;
import com.anand.store.simulator.util.StoreRandomGenrator;

public class Customer {

	private String objIdentifier;
	private Wallet myMallet = new Wallet();
	private ShoppingCart cart;
	private static final Logger logger = LogManager.getLogger(Customer.class);
	static int id = 0;
	
	public Customer(){
		id++;
		objIdentifier = Integer.toString(id);
		logger.info("Customer created "+this);
	}
	public void pickUpShoppingCart(ShoppingCart aCart) {
		this.cart = aCart;
	}

	public void startShopping() {
		logger.info("Customer "+this+" started shopping");
		if(cart == null){
			logger.error(this +" Please pick up a cart ");
		}
		int noOfItemsForMeToShop = StoreRandomGenrator.getNoItemsToShop();
		for (int i = 0; i <  noOfItemsForMeToShop;i++) {
			// Normally customers don't create product. But for simulation this
			// is ok.
			cart.addProduct(new GroceryProduct());
		}
		logger.info("Customer"+this+" finished shopping");
	}
	
	public ShoppingCart getCart(){
		return cart;
	}
	
	public boolean isHavingFunds(){
		return cart.billItems()<= myMallet.getBalance();
	}
	
	public boolean confirmSale(){
		myMallet.deduct(cart.billItems());
		logger.info("Customer"+this+" done biling");
		return true;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "customer-"+objIdentifier;
	}
		
}
