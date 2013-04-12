package de.htwg.kalaha.model;

import junit.framework.TestCase;

public class BoardTest extends TestCase {
	
	Board board;
	Player player1, player2;
	
	public void setUp() {
		player1 = new Player("Player 1");
		player2 = new Player("Player 2");
		board = new Board(player1,player2);
	}
	
	
	public void testGetHollow() {
		assertEquals(board.getHollow(player1, 1), board.getHollow(player2, 8));
		assertEquals(board.getHollow(player1, 6), board.getHollow(player2, 1).getOpposite());
	}

}
