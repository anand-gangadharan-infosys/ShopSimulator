package com.anand.store.simulator.actors;

public abstract class Cashier {
	
	static int id = 0;
	String objIdentifier;
	public Cashier(){
		id++;
		objIdentifier = Integer.toString(id);
	}
	public abstract int getItemProcessingTime();
}
