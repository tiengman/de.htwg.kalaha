package de.htwg.kalaha.model;

import de.htwg.kalaha.model.Player;
import junit.framework.TestCase;

public class PlayerTest extends TestCase { 
	
	Player player;

	public void setUp() throws Exception {
		player = new Player("Player 1");
	}
	
	public void testGetName() {
		player.setName("Test");
		assertEquals("Test",player.getName());
	}
	
}
