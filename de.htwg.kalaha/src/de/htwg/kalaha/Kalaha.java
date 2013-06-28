package de.htwg.kalaha;

import org.apache.log4j.PropertyConfigurator;

import de.htwg.kalaha.controller.impl.KalahaController;
import de.htwg.kalaha.view.gui.GUI;
import de.htwg.kalaha.view.tui.TextUI;

public final class Kalaha {
	
	private static final int HOLLOW_NUMBER = 6;
	private static final int START_MARBLES = 6;
	

	private Kalaha() {
		KalahaController con = new KalahaController(HOLLOW_NUMBER,START_MARBLES);
		kalahaGui(con);
		kalahaTui(con);
		
		
	}
	
	private static void kalahaTui(KalahaController con) {
		TextUI tui = new TextUI(con);
		tui.printTUI();
		// continue until the user decides to quit
		boolean quit = false;
		while (!quit) {
		    quit = tui.iterate();
		} 
	}
	
	private static void kalahaGui(KalahaController con) {
		new GUI(con);
	}
	
	public static void main(String[] args) {
		// Set up logging through log4j
		PropertyConfigurator.configure("log4j.properties");
		
		new Kalaha();
	}

}
