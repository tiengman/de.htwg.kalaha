package de.htwg.kalaha.model;
import junit.framework.TestCase;
public class AITest extends TestCase {
		AI ki;
		Board board;
		Player player1, player2;
		public void setUp() throws Exception {
			player1 = new Player("Player 1");
			player2 = new Player("Player 2");
			board = new Board(player1,player2, 6,6);
			board.prepareBoard();
			ki = new AI(board);
		}
		
		public void testMiniMax() {
			assertEquals(ki.getBestHollow(1),2);
			assertEquals(ki.miniMax(player1, 0, board),0);
			board = new Board(player1,player2, 6,0);
			assertEquals(ki.miniMax(player2, 2, board),0);
		}
		
	}
