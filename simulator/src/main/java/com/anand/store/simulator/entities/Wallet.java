package com.anand.store.simulator.entities;

public class Wallet {
	
	Float cashBalance = Float.MAX_VALUE;// We only consider billionares.
	String currency;
	
	public void deposit(Float amount){
		//check if sane transaction
		cashBalance+= amount;
	}
	
	public void deduct(Float amount){
		//check if sane transaction
		cashBalance -= amount;
	}
	
	public Float getBalance(){
		return cashBalance;
	}
}
