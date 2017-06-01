package com.anand.store.simulator.actors;

public abstract class Cashier {
	
	static int id = 0;
	
	public Cashier(){
		id++;
	}
	public abstract int getItemProcessingTime();
}
