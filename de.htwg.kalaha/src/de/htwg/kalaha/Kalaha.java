package de.htwg.kalaha;

import org.apache.log4j.PropertyConfigurator;

import de.htwg.kalaha.controller.KalahaController;
import de.htwg.kalaha.view.tui.TextUI;

public final class Kalaha {
	
	private static final int HOLLOW_NUMBER = 1;

	private Kalaha() {
		kalahaTui();
	}
	
	private static void kalahaTui() {
		TextUI tui = new TextUI(new KalahaController(HOLLOW_NUMBER));
		tui.printTUI();
		// continue until the user decides to quit
		boolean quit = false;
		while (!quit) {
		    quit = tui.iterate();
		} 
	}
	
	public static void main(String[] args) {
		// Set up logging through log4j
		PropertyConfigurator.configure("log4j.properties");
		
		new Kalaha();
	}

}
