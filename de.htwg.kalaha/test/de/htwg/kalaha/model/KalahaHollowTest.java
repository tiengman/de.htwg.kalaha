package de.htwg.kalaha.model;

import junit.framework.TestCase;

public class KalahaHollowTest extends TestCase {
	
	KalahaHollow kHollow;
	Player player;
	
	public void setUp() throws Exception {
		player = new Player("Player 1");
		kHollow = new KalahaHollow(player);
	}
	
	public void testGetOwner() {
		assertEquals(this.player, kHollow.getOwner());
	}
	
	public void testGetMarbles() {
		assertEquals(null,kHollow.getMarbles());
	}

}