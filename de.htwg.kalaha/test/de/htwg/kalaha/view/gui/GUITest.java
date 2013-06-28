package de.htwg.kalaha.view.gui;


import de.htwg.kalaha.controller.impl.KalahaController;
import junit.framework.TestCase;
public class GUITest extends TestCase {

		GUI g;
		PlayerPanel p;
		KalahaController con;
		public void setUp() throws Exception {
			p = new PlayerPanel("res/Avatar/player1.png","res/Avatar/player1.png");
			con = new KalahaController(6,6);
		}
		
		public void testDialog() {
			g = new GUI(con);
			assertNotNull(g);
			con.prepareNewGame();
			g.setSkin("Wood");
			g.updateBoard();
		}
		
		
	}

