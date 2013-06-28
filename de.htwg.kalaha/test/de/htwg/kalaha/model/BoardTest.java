package de.htwg.kalaha.model;

import java.util.List;




import junit.framework.TestCase;

public class BoardTest extends TestCase {
	
	Board board;
	Player player1, player2;
	
	public void setUp() {
		player1 = new Player("Player 1");
		player2 = new Player("Player 2");
		board = new Board(player1,player2, 6,6);
	}
	
	
	public void testGetHollow() {
		assertEquals(board.getHollow(player1, 2), board.getNextHollow(board.getHollow(player1, 1)));
		assertEquals(board.getHollow(player2, 6), board.getNextHollow(board.getHollow(player2, 5)));
		assertNull(board.getHollow(new Player("Someone else"), 1));
	}
	
	public void testGetHollowNumber() {
		assertEquals(board.getHollowNumber(board.getHollow(player1, 2)),2);
		assertEquals(board.getHollowNumber(board.getHollow(player2, 2)),2);
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
	
	public void testGetClone() {
		assertNotNull(board.getClone());
		board.switchActivePlayer();
		assertNotNull(board.getClone());
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
		board = new Board(player1,player2, 1,6);
		
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
	
	public void testToString() {
		assertNotNull(board.toString());
		
		board.getNextHollow(board.getHollow(player1, 6)).setMarbles(15);
		board.switchActivePlayer();
		board.getNextHollow(board.getHollow(player2, 6)).setMarbles(15);
		
		assertNotNull(board.toString());
	}
	
	public void testGetHollowMarbles() {
		board = new Board(player1,player2, 2,6);
		List<Marble> marbles = board.getHollowsMarbles(player1);
		assertEquals(marbles.size(), 0);
		
		board.getHollow(player2, 1).setMarbles(6);
		board.getHollow(player2, 2).setMarbles(8);
		marbles = board.getHollowsMarbles(player2);
		assertEquals(marbles.size(), 14);
		
		
	}
	
	public void testGetWinner() {
		board = new Board(player1,player2, 1,6);
		
		board.getKalaha(player1).setMarbles(6);
		board.getKalaha(player2).setMarbles(8);
		assertEquals(board.getWinner(), player2);
		
		board.getKalaha(player1).setMarbles(10);
		board.getKalaha(player2).setMarbles(5);
		assertEquals(board.getWinner(), player1);
		
		board.getKalaha(player1).setMarbles(5);
		board.getKalaha(player2).setMarbles(5);
		assertNull(board.getWinner());
		
	}
	
	public void testCleanBoard() {
		board = new Board(player1,player2, 1,1);
		board.getHollow(player1, 1).setMarbles(3);
		board.getHollow(player2, 1).setMarbles(4);
		
		board.cleanBoard();

		assertEquals(board.getKalaha(player1).getMarbleCount(),3);
		assertEquals(board.getKalaha(player2).getMarbleCount(),4);
	}

	
	public void testGetPossibleMoves() {
		board = new Board(player1,player2, 2,1);
		board.prepareBoard();
		board.getHollow(player1, 1).setMarbles(0);

		assertEquals(board.getPossibleMoves().size(), 1);
		assertEquals(board.getPossibleMoves().get(0), board.getHollow(player1, 2));
		assertTrue(board.getPossibleMoves().contains(board.getHollow(player1, 2)));
		
		board.switchActivePlayer();
		assertTrue(board.getPossibleMoves().contains(board.getHollow(player2, 1)));
		assertTrue(board.getPossibleMoves().contains(board.getHollow(player2, 2)));
		
		board = new Board(player1,player2, 1,0);
		board.prepareBoard();
		assertEquals(board.getPossibleMoves().size(), 0);
		
		
	}

	public void testCatchMarbles() {
		board = new Board(player1,player2, 2,1);
		board.prepareBoard();
		
		// Kalaha Hollow
		board.catchMarbles(board.getKalaha(player1));
		
		// Wrong marble number
		board.getHollow(player1, 1).setMarbles(0);
		board.catchMarbles(board.getHollow(player1,1));
		
		// Wrong player
		board.catchMarbles(board.getHollow(player2,1));
		
		// Opposite Hollow is empty
		board.getHollow(player1, 1).setMarbles(1);
		board.getHollow(player2, 2).setMarbles(0);
		board.catchMarbles(board.getHollow(player1,1));
		
		// Catch successfull
		board.getHollow(player2, 2).setMarbles(1);
		board.catchMarbles(board.getHollow(player1,1));
	}

	public void testTakeMarbles() {
		board = new Board(player1,player2, 2,1);
		board.prepareBoard();
		
		assertEquals(board.takeMarbles(board.getHollow(player1, 1)),board.getHollow(player1, 2));
		assertEquals(board.takeMarbles(board.getHollow(player1, 2)).getMarbleCount(),2);
	}
	
	public void testCheckExtraTurn() {
		board = new Board(player1,player2, 1,1);
		board.prepareBoard();
		
		Player p = board.getActivePlayer();
		board.checkExtraTurn(board.getKalaha(p));
		assertEquals(board.getActivePlayer(),p);
		
		board.checkExtraTurn(board.getHollow(p,1));
		assertNotSame(board.getActivePlayer(),p);
	}
	
	public void testTakeAllMarbles() {
		board = new Board(player1,player2, 1,1);
		board.prepareBoard();
		
		board.getHollow(player1, 1).setMarbles(1);
		board.takeAllMarbles();
		assertEquals(board.getHollow(player2, 1).getMarbleCount(),1);
		
		board.getHollow(player1, 1).setMarbles(0);
		board.takeAllMarbles();
		assertEquals(board.getHollow(player2, 1).getMarbleCount(),0);
	}

}
