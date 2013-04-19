package de.htwg.util.observer;

import junit.framework.TestCase;

public class EventTest extends TestCase {
	
	class TestEvent extends Event {
		public TestEvent() {
		}
	}
	
	public void setUp()  throws Exception {
	}
	
	public void testNew() {
		Event e = new TestEvent();
		assertNotNull(e);
	}

}
