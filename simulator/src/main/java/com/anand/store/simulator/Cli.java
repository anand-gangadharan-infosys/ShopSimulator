package com.anand.store.simulator;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.anand.store.simulator.util.StoreRandomGenrator;

public class Cli {
	private static final Logger log = Logger.getLogger(Cli.class.getName());
	private String[] args = null;
	private Options options = new Options();

	public Cli(String[] args) {

		this.args = args;

		
		options.addOption("h", "help", false, "show help.");
		options.addOption("s", "shoppers", true, "The number of customers to be simulated");
		options.addOption("c", "cashiers", true, "The number of cashiers to be simulated");

	}

	public void parse() {
		CommandLineParser parser = new BasicParser();
		int shoppers = 1;
		int cashiers = 1;
		
		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);

			if (cmd.hasOption("h"))
				help();
			
			if (cmd.hasOption("c")) {
				log.log(Level.INFO, "Simulating " + cmd.getOptionValue("c")+" cashiers");
				cashiers = Integer.parseInt(cmd.getOptionValue("c"));		
			}else{
				cashiers = 10;   
			}
			
			if (cmd.hasOption("s")) {
				log.log(Level.INFO, "Simulating " + cmd.getOptionValue("s")+" shoppers");
				shoppers = Integer.parseInt(cmd.getOptionValue("s"));
				// Whatever you want to do with the setting goes here
			} else {
				shoppers = StoreRandomGenrator.getNoCustomers();
			}
			
			ShopSimulator shopSimulator = new ShopSimulator();
			shopSimulator.configureSimulation(cashiers,shoppers);
			shopSimulator.startSimulation();

		} catch (ParseException e) {
			log.log(Level.SEVERE, "Failed to parse comand line properties", e);
			help();
		}
	}

	private void help() {
		// This prints out some help
		HelpFormatter formater = new HelpFormatter();

		formater.printHelp("You can run this application", options);
		System.exit(0);
	}
}