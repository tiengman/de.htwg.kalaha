package de.htwg.kalaha.controller;

import junit.framework.TestCase;

public class KalahaControllerTest extends TestCase {
    KalahaController controller;
    
	public void setUp() {
		controller = new KalahaController(6);
	}
	
    public void testPrepareNewGame() {
    	controller.prepareNewGame();
    	assertEquals(controller.getStatus(),"New game started");
    }
    
    public void testGetBoardString() {
    	assertNotNull(controller.getBoardString());
    }
    
    public void testTakeMarbles() {
        // Player 1: take p1,1 -> Extra turn
    	controller.takeMarbles(1, 1);
    	assertEquals(controller.getStatus(), "The hollow p1,1 is now empty");
    	// Player 1: take p1,1 (empty)
    	controller.takeMarbles(1, 1);
    	assertEquals(controller.getStatus(), "The hollow p1,1 is already empty");
    	
    	// Player 1: take p1,2
    	controller.takeMarbles(1, 2);
    	assertEquals(controller.getStatus(), "The hollow p1,2 is now empty");
    	
    	// Player 2: take p1,6
    	controller.takeMarbles(1, 6);
    	assertEquals(controller.getStatus(), "The hollow p1,6 is now empty");  
    	// Player 1: take p1,2 (empty)
    	controller.takeMarbles(1, 2);
    	assertEquals(controller.getStatus(), "The hollow p1,2 is already empty"); 
    	

    	// Player 1: take p1,4 (Opposite already empty)
    	controller.takeMarbles(1, 4);
    	assertEquals(controller.getStatus(), "The hollow p1,4 is now empty"); 
    	
    	// Player 2: take p1,6
    	controller.takeMarbles(1, 6);
    	assertEquals(controller.getStatus(), "The hollow p1,6 is now empty");
    	
    	// Player 1: take p1,1 (catch opposite)
    	controller.takeMarbles(1, 1);
    	assertEquals(controller.getStatus(), "The hollow p1,1 is now empty"); 
    	
    	// Player 2: take p1,3 (catch, opposite empty)
        controller.takeMarbles(1, 3);
        assertEquals(controller.getStatus(), "The hollow p1,3 is now empty"); 
    	
        // Player 1: take p2,6
    	controller.takeMarbles(2, 6);
    	assertEquals(controller.getStatus(), "The hollow p2,6 is now empty");
    	
    }
    
    public void testWinSituation() {
    	controller = new KalahaController(1);
    	controller.takeMarbles(1, 1);
    	controller.takeMarbles(1, 1);
    	controller.checkWinSituation();
    	assertEquals(controller.getStatus(), "Player 2 wins the game!"); 
    	
    	controller = new KalahaController(1);
    	controller.takeMarbles(2, 1);
    	controller.takeMarbles(2, 1);
    	controller.takeMarbles(2, 1);
    	controller.checkWinSituation();
    	assertEquals(controller.getStatus(), "Player 1 wins the game!"); 
    	
    	controller = new KalahaController(0);
    	controller.checkWinSituation();
    	assertEquals(controller.getStatus(), "Draw!"); 
    }
	
	
}
