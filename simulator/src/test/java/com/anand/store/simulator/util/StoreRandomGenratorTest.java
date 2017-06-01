package com.anand.store.simulator.util;

import junit.framework.TestCase;

public class StoreRandomGenratorTest extends TestCase {

	public void testGetRandomPrice() {
		
		assertEquals("Price should be greater than 0", StoreRandomGenrator.getRandomPrice() > 0, true);
		assertEquals("Price should be greater less than 100", StoreRandomGenrator.getRandomPrice() < 100, true);
	}
}
