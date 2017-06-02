package com.anand.store.simulator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.anand.store.simulator.actors.Cashier;
import com.anand.store.simulator.actors.Customer;

public class BillingDesk implements Runnable {

	private static final Logger logger = LogManager.getLogger(BillingDesk.class);

	private Cashier cashier;
	private ShopFloor shopFloor;
	static int id = 0;

	public BillingDesk(ShopFloor theShop) {
		this.shopFloor = theShop;
		id++;
	}

	public void manBillingDesk(Cashier cashier) {
		logger.info("Bill desk " + this + " manned by " + cashier);
		this.cashier = cashier;
	}

	@Override
	public void run() {
		logger.trace(this + " started functioning ...");
		while (!shopFloor.isShopClosed() || shopFloor.hasMore()) {
			logger.trace("is Shop closed?" + shopFloor.isShopClosed() + " are people in queue " + shopFloor.hasMore());
			try {
				Customer aCustomer = shopFloor.nextCustomer();
				if (aCustomer != null) {
					logger.info("Bill desk " + cashier + " attending " + aCustomer);
					if (aCustomer.isHavingFunds()) {
						aCustomer.confirmSale();
					} else {
						logger.error("Bill desk " + cashier + " customer has no money " + aCustomer);
					}
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "BillDesk-" + id;
	}

}
