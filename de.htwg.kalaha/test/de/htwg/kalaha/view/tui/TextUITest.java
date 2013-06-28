package de.htwg.kalaha.view.tui;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import de.htwg.kalaha.controller.impl.KalahaController;
import junit.framework.TestCase;

public class TextUITest extends TestCase {
	TextUI tui;
	public void setUp() {
		tui = new TextUI(new KalahaController(6,6));
	}
	
	public void testHandleInputOrQuit() {
		assertTrue(tui.handleInputOrQuit("q"));
		assertFalse(tui.handleInputOrQuit("n"));
		assertFalse(tui.handleInputOrQuit("u"));
		assertFalse(tui.handleInputOrQuit("p1,1"));
		assertFalse(tui.handleInputOrQuit("c1"));
		assertFalse(tui.handleInputOrQuit("c"));
		
		// Win Situation
		tui = new TextUI(new KalahaController(1,0));
		assertFalse(tui.handleInputOrQuit("p1,1"));
	}
	
	public void testIterate() { 	
		InputStream stdin = System.in;

		String data = "q\n";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		tui = new TextUI(new KalahaController(6,6));
		assertTrue(tui.iterate());
			
		data = "n\n";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		tui = new TextUI(new KalahaController(6,6));
		assertFalse(tui.iterate());
			
		System.setIn(stdin);
	}
}
