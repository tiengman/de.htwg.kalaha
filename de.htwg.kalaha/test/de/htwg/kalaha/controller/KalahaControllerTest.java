package de.htwg.kalaha.controller;

import de.htwg.kalaha.controller.impl.KalahaController;
import junit.framework.TestCase;

public class KalahaControllerTest extends TestCase {
    KalahaController controller;
    
	public void setUp() {
		controller = new KalahaController(6,6);
	}
	
    public void testPrepareNewGame() {
    	controller.prepareNewGame();
    	assertEquals(controller.getStatus(),"New game started");
    }
    
    public void testGetBoardString() {
    	assertNotNull(controller.getBoardString());
    }
    
    public void testIsAiTurn() {
    	assertFalse(controller.isAiTurn());
    }
    
    public void testSetAiLevel() {
    	controller.setAILevel(1);
    	controller.setAILevel(2);
    	controller.setAILevel(3);
    	assertFalse(controller.isAiTurn());
    }
    
    public void testGetWinner() {
    	controller = new KalahaController(1,2);
    	controller.takeMarbles(1, 1);
    	assertNotNull(controller.getWinner());
    }
    
    public void testTakeMarbles() {
    	
    	
    	// Catch p1
    	controller = new KalahaController(1,3);
    	controller.takeMarbles(1, 1);
    	assertEquals(controller.getStatus(), "The hollow p1,1 is now empty"); 
    	
    	// Not allowed move
    	controller = new KalahaController(1,3);
    	controller.takeMarbles(2, 1);
    	assertEquals(controller.getStatus(), "You are not allowed to take the marbles of the hollow p2,1");
    	
    	

    	
        
     	controller = new KalahaController(3,3);
     	// Extra Turn
     	controller.takeMarbles(1, 1);
     	controller.takeMarbles(1, 2);
     	controller.takeMarbles(2, 1);
     	// Catch
     	controller.takeMarbles(1, 1);

     	controller = new KalahaController(3,3);
     	controller.takeMarbles(1, 2);
     	controller.takeMarbles(2, 3);
     	controller.takeMarbles(1, 3);
     	// Extra Turn
     	controller.takeMarbles(2, 3);
     	assertEquals(controller.getStatus(), "The hollow p2,3 is now empty");
     	
     	// Catch, but opposite already empty
     	controller = new KalahaController(6,2);
     	controller.takeMarbles(1, 3);
     	controller.takeMarbles(2, 4);
     	controller.takeMarbles(1, 6);
     	controller.takeMarbles(2, 2);
     	assertEquals(controller.getStatus(), "The hollow p2,2 is now empty");
    	
    }
    
    public void testWinSituation() {
    	controller = new KalahaController(1,2);
    	controller.takeMarbles(1, 1);
    	controller.takeMarbles(2, 1);
    	controller.checkWinSituation();
    	assertEquals(controller.getStatus(), "Player 2 wins the game!"); 
    	
    	controller = new KalahaController(1,3);
    	controller.takeMarbles(1, 1);
    	controller.checkWinSituation();
    	assertEquals(controller.getStatus(), "Player 1 wins the game!"); 
    	
    	controller = new KalahaController(1,1);
    	controller.takeMarbles(1, 1);
    	controller.checkWinSituation();
    	assertEquals(controller.getStatus(), "Draw!"); 
    }
    
    public void testGetActivePlayer() {
    	assertEquals(controller.getActivePlayer(), 1); 
    	controller.takeMarbles(1, 2);
    	assertEquals(controller.getActivePlayer(), 2); 
    }
	
    
    public void testGetKalahaContent() {
    	controller.takeMarbles(1, 2);
    	controller.takeMarbles(2, 2);
    	assertNotNull(controller.getKalahaContent(1));
    }
	
}
