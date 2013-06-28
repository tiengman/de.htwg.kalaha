package de.htwg.kalaha.view.gui;

import java.awt.Toolkit;

import javax.swing.JFrame;

import junit.framework.TestCase;
public class HollowPanelTest extends TestCase {

		HollowPanel p;
		MarbleImage m;
		public void setUp() throws Exception {
			p = new HollowPanel("");
			m = new MarbleImage(Toolkit.getDefaultToolkit().getImage("res/Chalk/marble1.png"));
		}
		
		public void testPanel() {
			p.setImg("res/Chalk/marble1.png");
			
			assertNotNull(p);
			
			p.setSizeX(5);
			assertEquals(5,p.getSizeX());
			p.setSizeY(4);
			assertEquals(4,p.getSizeY());
			assertFalse(p.isScaled());

			p.setImgX(0);
			p.setImgY(0);
			assertEquals(0,p.getImgX());
			assertEquals(0,p.getImgY());
			
			
			p = new HollowPanel("",5,5,10,10);
			p.setSizeX(100);
			p.setSizeY(100);
			p.setImg("res/Chalk/marble1.png");
			assertNotNull(p);
			p = new HollowPanel("res/Avatar/player1.png",true);
			
			for (int i = 0; i < 30; i++) {
				p.addMarble(new MarbleImage(Toolkit.getDefaultToolkit().getImage("res/Chalk/marble1.png")));
			}
			
			assertEquals(30,p.getMarbleCount());
			assertNotNull(p.getMarble(0));
			assertEquals(0,p.getNum());
			assertEquals(0,p.getPlayer());
			p.repaint();
			JFrame j = new JFrame();
			j.show();
			p.paintComponent(j.getGraphics());
			p = new HollowPanel("res/Avatar/player1.png",false);
			p.repaint();
			p.paintComponent(j.getGraphics());
			p = new HollowPanel("res/Avatar/player1.png",false);
			p.repaint();
			p.setSize(10, 100);
			p.paintComponent(j.getGraphics());
			assertNotNull(p);
			p = new HollowPanel("res/Avatar/player1.png",5,5);
			assertNotNull(p);
		}
		
		
	}

