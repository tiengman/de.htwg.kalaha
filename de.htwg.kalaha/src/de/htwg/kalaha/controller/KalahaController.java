package de.htwg.kalaha.controller;

import de.htwg.kalaha.model.Board;
import de.htwg.kalaha.model.Player;
import de.htwg.util.observer.Observable;

public class KalahaController extends Observable {
	
	private Board board;
	private Player player1, player2;
	
	public KalahaController() {
		this.player1 = new Player("Player 1");
		this.player2 = new Player("Player 2");
		this.board = new Board(this.player1,this.player2);	
	}

}
