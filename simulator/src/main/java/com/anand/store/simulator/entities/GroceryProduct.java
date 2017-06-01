package com.anand.store.simulator.entities;

import com.anand.store.simulator.util.StoreRandomGenrator;

public class GroceryProduct extends AbstractProduct {
	
	@Override
	public float getPrice() {
		return StoreRandomGenrator.getRandomPrice();
	}
}
