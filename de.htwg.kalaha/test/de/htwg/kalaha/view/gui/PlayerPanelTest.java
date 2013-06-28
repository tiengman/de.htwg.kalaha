package de.htwg.kalaha.view.gui;

import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;

import junit.framework.TestCase;
public class PlayerPanelTest extends TestCase {

		PlayerPanel p;
		public void setUp() throws Exception {
			p = new PlayerPanel("res/Avatar/player1.png","res/Avatar/player1.png");
		}
		
		public void testPanel() {
			p.setSizeX(5);
			assertEquals(5,p.getSizeX());
			p.setSizeY(4);
			assertEquals(4,p.getSizeY());
			assertFalse(p.isActive());
			assertFalse(p.isScaled());

			p.setImgX(0);
			p.setImgY(0);
			assertEquals(0,p.getImgX());
			assertEquals(0,p.getImgY());
			
			assertEquals("res/Avatar/player1.png",p.getPlayerActiveFile());
			assertEquals("res/Avatar/player1.png",p.getPlayerFile());
			p.setPlayerFiles("a", "b");
			p = new PlayerPanel("res/Avatar/player1.png","res/Avatar/player1.png",true);
			p.repaint();
			JFrame j = new JFrame();
			j.show();
			p.paintComponent(j.getGraphics());
			p = new PlayerPanel("res/Avatar/player1.png","res/Avatar/player1.png",false);
			p.repaint();
			p.paintComponent(j.getGraphics());
			p = new PlayerPanel("res/Avatar/player1.png","res/Avatar/player1.png",false);
			p.repaint();
			p.setSize(10, 100);
			p.paintComponent(j.getGraphics());
			assertNotNull(p);
		}
		
		
	}

