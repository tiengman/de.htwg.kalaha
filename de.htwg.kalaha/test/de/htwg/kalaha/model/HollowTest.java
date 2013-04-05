package de.htwg.kalaha.model;

import java.util.ArrayList;

import junit.framework.TestCase;

public class HollowTest extends TestCase{
	
	Hollow hollow;
	Hollow hollowPrev;
	Hollow hollowNext;
	Player player;
	Marble newMarble;
	
	public void setUp() throws Exception {
		player = new Player("Player 1");
		hollow = new Hollow(player, 6);
		hollowPrev = new Hollow(player, 6);
		hollowNext = new Hollow(player, 6);
		newMarble = new Marble();
	}
	
	public void testGetPrev() {
		hollow.setPrev(hollowPrev);
		assertEquals(this.hollowPrev, hollow.getPrev());
	}
	
	public void testGetNext() {
		hollow.setNext(hollowNext);
		assertEquals(this.hollowNext, hollow.getNext());
	}
	
	public void testGetOwner() {
		assertEquals(this.player, hollow.getOwner());
	}
	
	public void testSetMarbles() {
		assertEquals(6, hollow.getMarbleCount());
	}
	
	public void testAddMarble() {
		hollow.addMarble(newMarble);
		assertEquals(7, hollow.getMarbleCount());
	}
	
	public void testGetMarbles() {
		ArrayList<Marble> marbleList = hollow.getMarbles();
		assertEquals(0, hollow.getMarbleCount());
		assertEquals(6, marbleList.size());		
	}
	
	

}
