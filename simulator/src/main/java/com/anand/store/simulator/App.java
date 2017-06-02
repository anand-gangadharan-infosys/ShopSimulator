package com.anand.store.simulator;

import java.util.Iterator;
import java.util.List;
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
	Customer[] allCustomers = new Customer[noOfCustomers];
	ShopFloor myBigShop = new ShopFloor();

	ExecutorService billingExecutor = Executors.newFixedThreadPool(noOfCashiers);
	ExecutorService shoppingExecutor = Executors.newFixedThreadPool(Const.MAX_SHOPPING_THREADS);

	private static final Logger logger = LogManager.getLogger(App.class);

	public void initShopWithActorsAndEntities() {
		
		for (int i = 0; i < allCustomers.length; i++) {
			allCustomers[i]=new Customer();
		}
		
		for (Cashier cashier : cashiers) {
			BillingDesk aBillingDesk = new BillingDesk(myBigShop);
			cashier = new ExpertCashier();
			aBillingDesk.manBillingDesk(cashier);
			myBigShop.addBillingDesks(aBillingDesk);
		}
	}

	public void startShoppingSimulation() {
		logger.info("Start Shopping Simulation");

		for (int i=0;i< allCustomers.length;i++) {
			ShopperGenerator shoperGenrator = new ShopperGenerator(allCustomers[i], myBigShop);
			logger.trace(" shoperGenrator using customer  "+allCustomers[i]);
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
		List< BillingDesk> billingsDesks = myBigShop.getBillingDesks();
		
		for (Iterator<BillingDesk> iterator = billingsDesks.iterator(); iterator.hasNext();) {
			BillingDesk billingDesk = iterator.next();
			billingExecutor.execute(billingDesk);
			
		}
	}

	public void startSimulation() {
		initShopWithActorsAndEntities();
		startBillingSimulation();
		startShoppingSimulation();
	}

	public static void main(String[] args) {
		new App().startSimulation();
	}
}
