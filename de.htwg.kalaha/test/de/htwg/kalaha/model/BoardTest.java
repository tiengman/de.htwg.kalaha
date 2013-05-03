package de.htwg.kalaha.model;

import junit.framework.TestCase;

public class BoardTest extends TestCase {
	
	Board board;
	Player player1, player2;
	
	public void setUp() {
		player1 = new Player("Player 1");
		player2 = new Player("Player 2");
		board = new Board(player1,player2, 6);
	}
	
	
	public void testGetHollow() {
		assertEquals(board.getHollow(player1, 2), board.getNextHollow(board.getHollow(player1, 1)));
		assertEquals(board.getHollow(player2, 6), board.getNextHollow(board.getHollow(player2, 5)));
		assertNull(board.getHollow(new Player("Someone else"), 1));
	}
	
	public void testGetOppositeHollow() {
		assertEquals(board.getHollow(player1, 6), board.getOppositeHollow(board.getHollow(player2, 1)));
		assertEquals(board.getHollow(player2, 1), board.getOppositeHollow(board.getHollow(player1, 6)));
		assertNull(board.getOppositeHollow(null));
	}
	
	public void testGetNextHollow() {
		Hollow newHollow = new Hollow(player1);
		assertNull(board.getNextHollow(newHollow));
		assertEquals(board.getNextHollow(board.getHollow(player1, 1)), board.getHollow(player1, 2));
		assertEquals(board.getNextHollow(board.getHollow(player2, 1)), board.getHollow(player2, 2));
		assertEquals(board.getNextHollow(board.getHollow(player2, 6)), board.getHollow(player1, 1));
		assertFalse(board.getNextHollow(board.getHollow(player1, 6)).equals(board.getHollow(player2, 1)));
		assertEquals(board.getNextHollow(board.getNextHollow(board.getHollow(player1, 6))), board.getHollow(player2, 1));
		board.switchActivePlayer();
		assertEquals(board.getNextHollow(board.getHollow(player1, 6)), board.getHollow(player2, 1));
		assertFalse(board.getNextHollow(board.getHollow(player2, 6)).equals(board.getHollow(player1, 1)));
		assertEquals(board.getNextHollow(board.getNextHollow(board.getHollow(player2, 6))), board.getHollow(player1, 1));
	}
	
	public void testGetKalaha() {
		assertEquals(board.getNextHollow(board.getHollow(player1, 6)), board.getKalaha(player1));
		board.switchActivePlayer();
		assertEquals(board.getNextHollow(board.getHollow(player2, 6)), board.getKalaha(player2));
		Player somebody = new Player("Someone else");
		assertNull(board.getKalaha(somebody));
	}
	
	public void testSwitchActivePlayer() {
		assertEquals(player1,board.getActivePlayer());
		board.switchActivePlayer();
		assertEquals(player2,board.getActivePlayer());
		board.switchActivePlayer();
		assertEquals(player1,board.getActivePlayer());
	}
	
	public void testArePlayerHollowsEmpty() {
		// Board is empty
		assertTrue(board.arePlayerHollowsEmpty(player1));
		
		// Fill board
		board.prepareBoard();
		assertFalse(board.arePlayerHollowsEmpty(player1));
	}
	
	public void testIsWinSituation() {
		board = new Board(player1,player2, 1);
		
		board.getKalaha(player1).setMarbles(7);
		board.getKalaha(player2).setMarbles(3);
		board.getHollow(player1, 1).setMarbles(1);
		board.getHollow(player2, 1).setMarbles(1);
		assertTrue(board.isWinSituation());
		
		board.getKalaha(player1).setMarbles(3);
		board.getKalaha(player2).setMarbles(7);
		board.getHollow(player1, 1).setMarbles(1);
		board.getHollow(player2, 1).setMarbles(1);
		assertTrue(board.isWinSituation());
		
		board.getKalaha(player1).setMarbles(0);
		board.getKalaha(player2).setMarbles(0);
		board.getHollow(player1, 1).setMarbles(12);
		board.getHollow(player2, 1).setMarbles(0);
		assertTrue(board.isWinSituation());
		
		board.getKalaha(player1).setMarbles(0);
		board.getKalaha(player2).setMarbles(0);
		board.getHollow(player1, 1).setMarbles(0);
		board.getHollow(player2, 1).setMarbles(12);
		assertTrue(board.isWinSituation());
		
		
		board.getKalaha(player1).setMarbles(0);
		board.getKalaha(player2).setMarbles(0);
		board.getHollow(player1, 1).setMarbles(6);
		board.getHollow(player2, 1).setMarbles(6);
		assertFalse(board.isWinSituation());
	}
	
	public void testToString(){
		assertNotNull(board.toString());
		
		board.getNextHollow(board.getHollow(player1, 6)).setMarbles(15);
		board.switchActivePlayer();
		board.getNextHollow(board.getHollow(player2, 6)).setMarbles(15);
		
		assertNotNull(board.toString());
	}

}
