package de.htwg.kalaha.model;

import java.util.ArrayList;

import junit.framework.TestCase;

public class HollowTest extends TestCase{
	
	Hollow hollow;
	Hollow hollowOpposite;
	Hollow hollowNext;
	Player player;
	Marble newMarble;
	
	public void setUp() throws Exception {
		player = new Player("Player 1");
		hollow = new Hollow(player);
		hollowOpposite = new Hollow(player);
		hollowNext = new Hollow(player);
		newMarble = new Marble();
	}
	
	public void testGetOpposite() {
		hollow.setOpposite(hollowOpposite);
		assertEquals(this.hollowOpposite, hollow.getOpposite());
	}
	
	public void testGetNext() {
		hollow.setPrev(hollowNext);
		assertEquals(this.hollowNext, hollow.getPrev());
	}
	
	public void testGetPrev() {
		hollow.setNext(hollowNext);
		assertEquals(this.hollowNext, hollow.getNext());
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
		ArrayList<Marble> marbleList = hollow.getMarbles();
		assertEquals(0, hollow.getMarbleCount());
		assertEquals(1, marbleList.size());	
		hollow.setMarbles(5);
		assertEquals(5, hollow.getMarbleCount());
	}
	
	

}
