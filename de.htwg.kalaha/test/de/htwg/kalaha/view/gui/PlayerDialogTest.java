package de.htwg.kalaha.view.gui;


import junit.framework.TestCase;
public class PlayerDialogTest extends TestCase {

		PlayerDialog dialog;
		PlayerPanel p;
		public void setUp() throws Exception {
			p = new PlayerPanel("res/Avatar/player1.png","res/Avatar/player1.png");
		}
		
		public void testDialog() {
			dialog = new PlayerDialog(p);
			assertNotNull(dialog);
		}
		
		
	}

