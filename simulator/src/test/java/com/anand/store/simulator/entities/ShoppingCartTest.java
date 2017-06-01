package com.anand.store.simulator.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShoppingCartTest {

	@Test
	public void testProductAdd() {
		ShoppingCart cart = new ShoppingCart();
		
		cart.addProduct(new GroceryProduct());
		cart.addProduct(new GroceryProduct());
		cart.addProduct(new GroceryProduct());
		cart.addProduct(new GroceryProduct());
		
		assertEquals("Cart should have 4 items", cart.getCartSize(),4);
		assertEquals("Cart bill should be more than 0", cart.billItems()>0f,true);
		
	}

}
