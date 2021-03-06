package de.htwg.kalaha.model;

import de.htwg.kalaha.model.Marble.MarbleType;
import junit.framework.TestCase;

public class MarbleTest extends TestCase {
	Marble marble;
	
	public void setUp() throws Exception {
		marble = new Marble();
	}
	
	public void testGetType() {
		assertEquals(marble.getType(),MarbleType.MARBLE);
		marble.setType(MarbleType.STONE);
		assertEquals(marble.getType(),MarbleType.STONE);
		assertEquals(MarbleType.valueOf("BONE"),MarbleType.BONE);
	}
	
	
}
