package com.anand.store.simulator;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.anand.store.simulator.actors.Customer;
import com.anand.store.simulator.util.BQueue;

public class ShopFloor {
	private BQueue<Customer> customerQueue = new BQueue<Customer>(Const.MAX_BILL_DESK_QUEUE_SIZE);
	private static final Logger logger = LogManager.getLogger(ShopFloor.class);

	private List<BillingDesk> billingDesks = new ArrayList<BillingDesk>();

	public void joinBillingQueue(Customer aCustomer) throws InterruptedException {
		if (customerQueue.isAllProducersExited()) {
			logger.warn("Shop closed. Turning away customer " + aCustomer);
			return;
		}
		logger.trace(aCustomer + " Joined Billing Queue");
		customerQueue.put(aCustomer);
	}

	public boolean hasMore() {
		return !customerQueue.isEmpty();
	}

	public Customer nextCustomer() throws InterruptedException {
		return customerQueue.get();
	}

	public void shutShopForTheDay() {
		customerQueue.allProducersExited();
	}

	public boolean isShopClosed() {
		return customerQueue.isAllProducersExited();
	}

	public List<BillingDesk> getBillingDesks() {
		return billingDesks;
	}

	public void setBillingDesks(List<BillingDesk> billingDesks) {
		this.billingDesks = billingDesks;
	}

	public void addBillingDesks(BillingDesk abillingDesk) {
		this.billingDesks.add(abillingDesk);
	}
}
