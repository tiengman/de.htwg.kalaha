package de.htwg.kalaha.view.gui;

import java.awt.Toolkit;

import javax.swing.JFrame;

import junit.framework.TestCase;
public class KalahaPanelTest extends TestCase {

		KalahaPanel p;
		MarbleImage m;
		public void setUp() throws Exception {
			p = new KalahaPanel("");
			m = new MarbleImage(Toolkit.getDefaultToolkit().getImage("res/Chalk/marble1.png"));
		}
		
		public void testPanel() {
			assertTrue(p.addMarble(m));
			p.setPlayer(1);
			assertEquals(1,p.getPlayer());
			assertEquals(1,p.getMarbleCount());
			assertEquals(m,p.getMarble(0));
			assertNotNull(new KalahaPanel ("",1,1));
			assertNotNull(new KalahaPanel ("",1,1,1,1));
			
			
			p.setSizeX(5);
			assertEquals(5,p.getSizeX());
			p.setSizeY(4);
			assertEquals(4,p.getSizeY());
			assertFalse(p.isScaled());

			p.setImgX(0);
			p.setImgY(0);
			assertEquals(0,p.getImgX());
			assertEquals(0,p.getImgY());
			
			
			p = new KalahaPanel("res/Avatar/player1.png",true);
			
			for (int i = 0; i < 30; i++) {
				p.addMarble(new MarbleImage(Toolkit.getDefaultToolkit().getImage("res/Chalk/marble1.png")));
			}
			
			assertEquals(30,p.getMarbleCount());
			assertNotNull(p.getMarble(0));
			assertEquals(0,p.getPlayer());
			p.repaint();
			JFrame j = new JFrame();
			j.show();
			p.paintComponent(j.getGraphics());

		
		
	}
}

