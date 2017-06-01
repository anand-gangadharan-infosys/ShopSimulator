package com.anand.store.simulator.util;

import com.anand.store.simulator.ShopFloor;
import com.anand.store.simulator.actors.Customer;
import com.anand.store.simulator.entities.ShoppingCart;

public class ShopperGenerator implements Runnable {

	private Customer customer;
	private ShopFloor shopFloor;

	public ShopperGenerator(Customer customer,ShopFloor shopFloor) {
		this.customer = customer;
		this.shopFloor = shopFloor;
	}

	public Customer getCustomer() {
		return customer;
	}

	@Override
	public void run() {
		customer.pickUpShoppingCart(new ShoppingCart());
		customer.startShopping();
		try {
			shopFloor.joinBillingQueue(customer);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
