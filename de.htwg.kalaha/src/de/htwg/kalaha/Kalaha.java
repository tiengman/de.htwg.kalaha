package de.htwg.kalaha;

import de.htwg.kalaha.controller.KalahaController;
import de.htwg.kalaha.view.tui.TextUI;

public final class Kalaha {
	
	Kalaha() {
		TextUI tui = new TextUI(new KalahaController());
		tui.printTUI();
		// continue until the user decides to quit
		boolean quit = false;
		while (!quit) {
		    quit = tui.iterate();		
		}
	}
	
	public static void main(String[] args) {
		new Kalaha();
	}

}
