package de.htwg.kalaha.controller;

import java.util.List;

import de.htwg.kalaha.model.Board;
import de.htwg.kalaha.model.Hollow;
import de.htwg.kalaha.model.Marble;
import de.htwg.kalaha.model.Player;
import de.htwg.util.observer.Observable;

public class KalahaController extends Observable {
	
	private String statusMessage = "Welcome!";
	private Board board;
	private Player player1, player2;
	
	public KalahaController() {
		this.player1 = new Player("Player 1");
		this.player2 = new Player("Player 2");
		this.board = new Board(this.player1,this.player2);	
	}
	
	public void prepareNewGame() {
		board.prepareBoard();
		setStatusMessage("New game started");
		notifyObservers();
	}
	
	
	public void takeMarbles(int playernum, int number) {
		Player player;
		if (playernum == 1) {
			player = player1;
		} else {
			player = player2;
		}
		
		Hollow hollow = board.getHollow(player, number);
		
		if (!hollow.isEmpty()) {
			List<Marble> marbles = hollow.getMarbles();
			Hollow current = hollow;
			int count = marbles.size();
			for (int i = 1; i <= count; i++) {
				current = board.getNextHollow(current);
				current.addMarble(marbles.get(0));
				marbles.remove(0);
			}
			setStatusMessage("The hollow p" + playernum + "," + number + " is now empty");
		} else {
			setStatusMessage("The hollow p" + playernum + "," + number + " is already empty");
		}
		
		board.switchActivePlayer();
		
		notifyObservers();
	}
	
	
	public String getStatus() {
		return statusMessage;
	}

	private void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	public String getBoardString() {
		return board.toString();
	}

}
