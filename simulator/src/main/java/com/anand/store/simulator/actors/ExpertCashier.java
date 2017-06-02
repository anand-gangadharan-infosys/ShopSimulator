package com.anand.store.simulator.actors;

import com.anand.store.simulator.Const;

public class ExpertCashier extends Cashier {

	@Override
	public int getItemProcessingTime() {
		// TODO Auto-generated method stub
		return Const.ONE_MIN;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "e-cashier "+objIdentifier;
	}

}
