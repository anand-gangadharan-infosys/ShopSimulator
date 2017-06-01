package com.anand.store.simulator.util;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class StoreRandomGenrator {
	static Random rngen = new Random();
	private static final Logger logger = LogManager.getLogger(StoreRandomGenrator.class);

	public static Float getRandomPrice() {
		Float x = rngen.nextFloat() * 100;
		logger.trace("Price "+x);
		return x;
	}
	
	public static int getNoItemsToShop() {
		int x = rngen.nextInt(10);
		logger.trace("getNoItemsToShop "+x);
		return x;
	}
	
	public static int getNoCustomers() {
		int x = rngen.nextInt(100);
		logger.trace("getNoCustomers "+x);
		return x;
	}

}
