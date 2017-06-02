package com.anand.store.simulator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.anand.store.simulator.actors.Cashier;
import com.anand.store.simulator.actors.Customer;
import com.anand.store.simulator.actors.ExpertCashier;
import com.anand.store.simulator.util.ShopperGenerator;
import com.anand.store.simulator.util.StoreRandomGenrator;

/**
 * Hello world!
 *
 */
public class App {
	int noOfCustomers = StoreRandomGenrator.getNoCustomers();
	int noOfCashiers = 10;
	Cashier cashiers[] = new Cashier[noOfCashiers];
	ShopFloor myBigShop = new ShopFloor();

	ExecutorService billingExecutor = Executors.newFixedThreadPool(noOfCashiers);
	ExecutorService shoppingExecutor = Executors.newFixedThreadPool(Const.MAX_SHOPPING_THREADS);
	private static final Logger logger = LogManager.getLogger(App.class);
	
	public void startShoppingSimulation() {
		logger.info("Start Shopping Simulation");
		Customer[] allCustomers = new Customer[noOfCustomers];

		for (Customer customer : allCustomers) {
			customer = new Customer();
			ShopperGenerator shoperGenrator = new ShopperGenerator(customer, myBigShop);
			shoppingExecutor.execute(shoperGenrator);
		}
		shoppingExecutor.shutdownNow();
		while (!shoppingExecutor.isTerminated()) {
		}
		logger.info("All shoppers done shopping. Shop shut down.");
		myBigShop.shutShopForTheDay();
		
		billingExecutor.shutdown();
		while (!billingExecutor.isTerminated()) {
		}
	}

	public void startBillingSimulation() {
		logger.info("Start Billing Simulation");
		for (Cashier cashier : cashiers) {
			BillingDesk aBillingDesk = new BillingDesk(myBigShop);
			cashier = new ExpertCashier();
			aBillingDesk.manBillingDesk(cashier);
			billingExecutor.execute(aBillingDesk);
		}
	}

	public void startSimulation() {
		startBillingSimulation();
		startShoppingSimulation();
	}

	public static void main(String[] args) {
		new App().startSimulation();
	}
}
