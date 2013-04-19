package de.htwg.kalaha.view.tui;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.htwg.kalaha.controller.KalahaController;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObserver;


public class TextUI implements IObserver {
	private KalahaController controller;
	private Scanner scanner;

	public TextUI(KalahaController controller){
		this.controller = controller;
		controller.addObserver(this);
		scanner = new Scanner (System.in);	
	}

	public void update(Event e) {
		printTUI();
	}

	public boolean iterate() {
		return handleInputOrQuit(scanner.next());
	}

	public void printTUI() {
		System.out.println(controller.getBoardString());
		System.out.println(controller.getStatus());
		System.out.println("Please enter a command( q - quit, u - update, n - new, px,y - take marbles from player x, hollow y):");
	}
	
	public boolean handleInputOrQuit(String line) {	
		boolean quit=false;
		if (line.equalsIgnoreCase("q")) {
			quit=true;
		}
		
		// Start a new game
		if (line.equalsIgnoreCase("n")) {
			controller.prepareNewGame();
		}
		
		// Take marbles out of a hollow (p1,3 p2,6 ...)
		if (line.matches("p[1-2],[1-6]")){
			Pattern p = Pattern.compile("[0-9]");
			Matcher m = p.matcher(line);
			
			int[] arg = new int[2];
			for (int i = 0; i < arg.length; i++) {
				m.find();
				arg[i] = Integer.parseInt(m.group());
			} 
			controller.takeMarbles(arg[0], arg[1]);
		}
		
		return quit;
	}



}
