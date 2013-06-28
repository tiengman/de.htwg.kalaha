package de.htwg.kalaha.view.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;




@SuppressWarnings("serial")
public class PlayerDialog extends JFrame {
	private static final String[] AVATAR_NAMES = {" Hubert","  Beate","    Bill","   Eva"," Marco"," Sandy"};
	
	private static final int WINDOW_X = 480;
	private static final int WINDOW_Y = 500;
	private static final int ROWS = 3;
	private static final int COLS = 3;
	private static final int TEXT_LENGTH = 20;
	
	private PlayerPanel pPanel;
	private PlayerPanel selected;
	
	private JTextField name;
	private JFrame frame;
	
	public PlayerDialog (PlayerPanel pPanel) {
		frame = this;
		this.pPanel = pPanel;
		

		prepareGUI();

		this.pack();
        this.setVisible(true);
        this.setSize(WINDOW_X, WINDOW_Y);
	}
	
	
	private void prepareGUI() {

		this.setTitle("Player selection");
        this.setResizable(false);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(ROWS,1));
        this.setContentPane(panel);
        
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(1,COLS));
        
        JPanel panelBottom = new JPanel();
        panelBottom.setLayout(new GridLayout(1,COLS));
        
		MouseListener mL = new MouseListener() {
		    public void mouseEntered(MouseEvent e) {
		    	setPanelStatus(e,true,false);
		     }
		     public void mouseExited(MouseEvent e) {
		    	setPanelStatus(e,false,false);
		     }
			public void mouseClicked(MouseEvent e) {
				setPanelStatus(e,true,true);
			}
			public void mousePressed(MouseEvent arg0) {}

			public void mouseReleased(MouseEvent arg0) {}
		};
        
		List<PlayerPanel> avatarList = new ArrayList<PlayerPanel>();
        for (int i = 0; i < AVATAR_NAMES.length; i++) {
        	PlayerPanel avatar = new PlayerPanel("res/Avatar/player" + (i + 1) + ".png","res/Avatar/player" +(i + 1) + "a.png");
        	avatar.setName(AVATAR_NAMES[i]);
        	avatarList.add(avatar);
        	if (i < AVATAR_NAMES.length/2) {
        		panelTop.add(avatar);
        	} else {
        		panelBottom.add(avatar);
        	}
        	avatar.addMouseListener(mL);
        }

        selected = avatarList.get(0);
        selected.setActive(true);

        panel.add(panelTop);
        panel.add(panelBottom);
        
        JPanel panelName = new JPanel();
        panelName.setLayout(new FlowLayout());
        
        name = new JTextField();
        name.setText(pPanel.getName());
        name.setColumns(TEXT_LENGTH);
        
        JLabel nameLabel = new JLabel("Playername: ");
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pPanel.setName(name.getText());
				pPanel.setPlayerFiles(selected.getPlayerFile(), selected.getPlayerActiveFile());
				frame.dispose();		
			}
		});
        
        panelName.add(nameLabel);
        panelName.add(name);
        panelName.add(okButton);

        panel.add(panelName); 
}
	
private void setPanelStatus(MouseEvent e, boolean active, boolean select) {
	PlayerPanel p = (PlayerPanel) e.getSource();

	if (select && !p.equals(selected)) {
	 selected.setActive(false);
	 selected.repaint();
	 selected = p;
	}
	if (!p.equals(selected)) {
	 p.setActive(active);
	}
	p.repaint();
}
	
	
	
}
