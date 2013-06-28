package de.htwg.kalaha.model;

import java.util.List;




import junit.framework.TestCase;

public class HollowTest extends TestCase {
	
	Hollow hollow;
	Player player;
	Marble newMarble;
	
	public void setUp() throws Exception {
		player = new Player("Player 1");
		hollow = new Hollow(player);
		newMarble = new Marble();
	}
	
	public void testGetOwner() {
		assertEquals(this.player, hollow.getOwner());
	}
	
	public void testAddMarble() {
		hollow.addMarble(newMarble);
		assertEquals(1, hollow.getMarbleCount());
	}
	
	public void testGetMarbles() {
		hollow.addMarble(newMarble);
		List<Marble> marbleList = hollow.getMarbles();
		assertEquals(0, hollow.getMarbleCount());
		assertEquals(1, marbleList.size());	
		hollow.setMarbles(5);
		assertEquals(5, hollow.getMarbleCount());
	}
	
	public void testIsEmpty() {
		assertTrue(hollow.isEmpty());
		hollow.addMarble(newMarble);
		assertFalse(hollow.isEmpty());
	}
	
	

}
