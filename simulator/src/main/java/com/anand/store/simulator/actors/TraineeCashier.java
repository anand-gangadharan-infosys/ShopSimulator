package com.anand.store.simulator.actors;

import com.anand.store.simulator.Const;

public class TraineeCashier extends Cashier {

	@Override
	public int getItemProcessingTime() {
		// TODO Auto-generated method stub
		return Const.TWO_MIN;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "t-cashier "+objIdentifier;
	}

}
