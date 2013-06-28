package de.htwg.kalaha.view.gui;

import java.awt.Toolkit;

import javax.swing.JFrame;

import junit.framework.TestCase;
public class ImagePanelTest extends TestCase {

		ImagePanel p;
		MarbleImage m;
		public void setUp() throws Exception {
			p = new ImagePanel("");
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
			
			p = new ImagePanel("",5,5,10,10);
			p.setSizeX(100);
			p.setSizeY(100);
			p.setImg("res/Chalk/marble1.png");
			assertNotNull(p);
			p = new ImagePanel("res/Avatar/player1.png",true);
			p.repaint();
			JFrame j = new JFrame();
			j.show();
			p.paintComponent(j.getGraphics());
			p = new ImagePanel("res/Avatar/player1.png",false);
			p.repaint();
			p.paintComponent(j.getGraphics());
			p = new ImagePanel("res/Avatar/player1.png",false);
			p.repaint();
			p.setSize(10, 100);
			p.paintComponent(j.getGraphics());
			assertNotNull(p);
		}
		
		
	}

