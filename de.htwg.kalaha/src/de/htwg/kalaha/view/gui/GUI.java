package de.htwg.kalaha.view.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import org.apache.log4j.Logger;

import de.htwg.kalaha.controller.impl.KalahaController;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObserver;

@SuppressWarnings("serial")
public class GUI extends JFrame implements IObserver {
	private static final int WINDOW_X = 1200;
	private static final int WINDOW_Y = 800;
	private static final int HOLLOW_PANEL_MAX_ROWS = 5;
	private static final int PLAYER_1 = 1;
	private static final int PLAYER_2 = 2;
	private KalahaController controller;
	private Logger logger = Logger.getLogger(this.getClass().getName());

	private List<HollowPanel> player1Hollows = new ArrayList<HollowPanel>();
	private List<HollowPanel> player2Hollows = new ArrayList<HollowPanel>();
	
	private List<MarbleImage> marbles = new ArrayList<MarbleImage>(); 
	
	private KalahaPanel p1Kalaha;
	private KalahaPanel p2Kalaha;
	
	private PlayerPanel player1, player2;
	
	private ImagePanel panel;
	
	private boolean winSituation = false;
	
	private boolean aiEnabled = false;
	
	private PlayerDialog pDialog;
	
	private String skin = "Chalk";
	private final String fileBg = getSkinPath() + "background.jpg";
	private final String fileHollow = getSkinPath() + "hollow.png";
	private final String fileKalaha = getSkinPath() + "kalaha.png";

	
	private static final String FILE_PLAYER = "res/Avatar/player1.png";
	private static final String FILE_PLAYER_ACTIVE = "res/Avatar/player1a.png";
	private static final String FILE_PLAYER_2 = "res/Avatar/player5.png";
	private static final String FILE_PLAYER_2_ACTIVE = "res/Avatar/player5a.png";
	private static final String FILE_CPU = "res/Avatar/cpu.png";
	private static final String FILE_CPU_ACTIVE = "res/Avatar/cpua.png";
	
	private static final String[] SKIN_LIST = {"Chalk", "Golf", "Wood", "Nature"};
	


	
	public GUI (KalahaController controller) {
		this.controller = controller;
		controller.addObserver(this);
		
		prepareGUI();

		this.pack();
        this.setVisible(true);
        this.setSize(WINDOW_X, WINDOW_Y);
        this.updateBoard();
	}
	

	private void prepareGUI() {

		this.setTitle("Kalaha");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

		this.setIconImage(this.getToolkit().getImage(fileBg));
		this.setJMenuBar(this.createMenuBar());
		
		generatePlayerFrames();

		panel = new ImagePanel(fileBg, true);
		panel.setLayout(new GridLayout(1,controller.getHollowCount()+2));
		this.setContentPane(panel);
		
		JPanel p1HollowPanel = new JPanel();
		p1HollowPanel.setLayout(new GridLayout(1,controller.getHollowCount()));
		JPanel p2HollowPanel = new JPanel();
		p2HollowPanel.setLayout(new GridLayout(1,controller.getHollowCount()));
		
		
		p1Kalaha = new KalahaPanel(fileKalaha, true);
		p2Kalaha = new KalahaPanel(fileKalaha, true);
		p1Kalaha.setOpaque(false);
		p2Kalaha.setOpaque(false);
		
		
		MouseAdapter mouseHollow = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				HollowPanel p = (HollowPanel) e.getSource();
				if (!winSituation) {
					controller.takeMarbles(p.getPlayer(), p.getNum());
				}
			}
		};

		for (int i = 1; i <= controller.getHollowCount(); i++) {
			
			HollowPanel p1 = new HollowPanel(fileHollow);
			HollowPanel p2 = new HollowPanel(fileHollow);
			
			player1Hollows.add(p1);
			player2Hollows.add(p2);
			
			p1.setPlayer(PLAYER_1);
			p1.setNum(i);
			
			p2.setPlayer(PLAYER_2);
			p2.setNum(i);
			
			p1.addMouseListener(mouseHollow);
			p2.addMouseListener(mouseHollow);
			
		}
		

		// Get marble files from current skin
		File[] marbleFiles = new File(getSkinPath()).listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return (name.toLowerCase().startsWith("marble") && name.toLowerCase().endsWith(".png"));
		    }
		});
		
		// Create marbles
		for (int i = 0; i < controller.getHollowCount() * 2 * controller.getStartMarbles(); i++) {
				int num = (int) (Math.random()* marbleFiles.length);
				MarbleImage mImg = new MarbleImage(this.getToolkit().getImage(getSkinPath() + marbleFiles[num].getName()));
				marbles.add(mImg);
			}
		

		// Create Hollows
		panel.add(p2Kalaha);
		generatePlayerHollows();
		panel.add(p1Kalaha);
		

	}
	
	private void generatePlayerFrames() {
		player1 = new PlayerPanel(FILE_PLAYER,FILE_PLAYER_ACTIVE,true);
		player2 = new PlayerPanel(FILE_PLAYER_2,FILE_PLAYER_2_ACTIVE,true);
		
		player1.setOpaque(false);
		player2.setOpaque(false);
		
		player1.setName("Player 1");
		player2.setName("Player 2");
		
		MouseAdapter maPlayer = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (pDialog != null && pDialog.isVisible()) {
						pDialog.dispose();
				}
				pDialog = new PlayerDialog((PlayerPanel) e.getSource());
			}};
		
		player1.addMouseListener(maPlayer);
		player2.addMouseListener(maPlayer);
	}
	
	private void generatePlayerHollows() {
		for (int i = 0; i < player1Hollows.size(); i++) {

			JPanel hollowPanel = new JPanel();
			hollowPanel.setLayout(new GridLayout(HOLLOW_PANEL_MAX_ROWS,1));
			hollowPanel.setOpaque(false);
			
			JPanel e1 = new JPanel();
			JPanel e2 = new JPanel();
			JPanel e3 = new JPanel();
			e1.setOpaque(false);
			e2.setOpaque(false);
			e3.setOpaque(false);
			
			HollowPanel p1 = player1Hollows.get(i);
			HollowPanel p2 = player2Hollows.get(player2Hollows.size() - i - 1);
			
			p1.setAlignBottom(true);
			
			p1.setOpaque(false);
			p2.setOpaque(false);
			
			hollowPanel.add(p2);
			if (i == 0)
			{
				hollowPanel.add(player2);
			} else {
				hollowPanel.add(e1);
			}
			hollowPanel.add(e2);
			if (i == HOLLOW_PANEL_MAX_ROWS)
			{
				hollowPanel.add(player1);
			} else {
				hollowPanel.add(e3);
			}
			hollowPanel.add(p1);	

			panel.add(hollowPanel);
		}
	}

	private String getSkinPath() {
		return "res/"+skin+"/";
	}
	
	public void setSkin(String skin) {
		this.skin = skin;
		
		this.setIconImage(this.getToolkit().getImage(getSkinPath() + "background.jpg"));
		panel.setImg(getSkinPath() + "background.jpg");

		p1Kalaha.setImg(getSkinPath() + "kalaha.png");
		p2Kalaha.setImg(getSkinPath() + "kalaha.png");
		
		for (int i = 0; i <  player1Hollows.size(); i++) {
			 player1Hollows.get(i).setImg(getSkinPath() + "hollow.png");
			 player2Hollows.get(i).setImg(getSkinPath() + "hollow.png");
		}

		
		// Get marble files from current skin
		File[] marbleFiles = new File(getSkinPath()).listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return (name.toLowerCase().startsWith("marble") && name.toLowerCase().endsWith(".png"));
		    }
		});
		
		for(MarbleImage mImg :  marbles) {
			int num = (int) (Math.random()* marbleFiles.length);
			mImg.setImg(this.getToolkit().getImage(getSkinPath() + marbleFiles[num].getName()));
		}
		
	}
	
	public JMenuBar createMenuBar() {
		JMenuBar mb = new JMenuBar();
		
		JMenu menuGame = new JMenu("Game");
		JMenuItem mNewGame = new JMenuItem("New Game");
		menuGame.add(mNewGame);
		mNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.prepareNewGame();
			}
		});
		
		JMenuItem mExitGame = new JMenuItem("Exit Game");
		menuGame.add(mExitGame);
		mExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		JMenu menuSettings = new JMenu("Settings");
		JCheckBoxMenuItem mAIEnable = new JCheckBoxMenuItem("Enable AI");
		menuSettings.add(mAIEnable);
		
		mAIEnable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aiEnabled = ((JCheckBoxMenuItem) arg0.getSource()).isSelected();
				if (aiEnabled) {
					player2.setPlayerFiles(FILE_CPU, FILE_CPU_ACTIVE);
				} else {
					player2.setPlayerFiles(FILE_PLAYER_2, FILE_PLAYER_2_ACTIVE);
				}
					
				if ( aiEnabled && controller.getActivePlayer() == 2) {
					controller.doAiTurn();
				}
			}
		});
		
		JMenu menuAI = new JMenu("AI Level");
		ButtonGroup aiGrp = new ButtonGroup();
		menuSettings.add(menuAI);
		JRadioButtonMenuItem mAIEasy = new JRadioButtonMenuItem("Easy");
		menuAI.add(mAIEasy);
		aiGrp.add(mAIEasy);		
		mAIEasy.setSelected(true);
		JRadioButtonMenuItem mAIMedium = new JRadioButtonMenuItem("Medium");
		menuAI.add(mAIMedium);
		aiGrp.add(mAIMedium);
		JRadioButtonMenuItem mAIHard = new JRadioButtonMenuItem("Hard");
		menuAI.add(mAIHard);
		aiGrp.add(mAIHard);
		
		mAIEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.setAILevel(1);
			}
		});
		
		mAIMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.setAILevel(2);
			}
		});
		
		mAIHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.setAILevel(2);
			}
		});
		
		
		JMenu menuSkin = new JMenu("Skin");
		ButtonGroup skinGrp = new ButtonGroup();
		menuSettings.add(menuSkin);
		
		for (String s : SKIN_LIST) {
			final JRadioButtonMenuItem mBtn = new JRadioButtonMenuItem(s);
			if (s.equals(skin)) {
				mBtn.setSelected(true);
			}
			menuSkin.add(mBtn);
			skinGrp.add(mBtn);
			mBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setSkin(mBtn.getText());
				}
			});
		}

		mb.add(menuGame);
		mb.add(menuSettings);
		return mb;
	}
	

	public void update(Event e) {
		if (!winSituation) {
			this.updateBoard();
			this.repaint();
			winSituation = controller.isWinSituation();
			controller.checkWinSituation();

			if (winSituation)
			{
				this.updateBoard();
				this.repaint();
				JOptionPane.showMessageDialog(this, controller.getStatus());
			}
			else if (aiEnabled && controller.getActivePlayer() == PLAYER_2) {
					controller.doAiTurn();
			}
		}

	}
	
	public void updateBoard() {
		logger.info(controller.getBoardString());
		logger.info(controller.getStatus());
		List<Integer> listContent = null;
		for (int i = 1; i <= player1Hollows.size(); i++)
		{
			player1Hollows.get(i - 1).clear();
			listContent = controller.getHollowContent(PLAYER_1, i);
			for (int marbleID : listContent) {
				player1Hollows.get(i - 1).addMarble(marbles.get(marbleID));
			}
			
			player2Hollows.get(i - 1).clear();
			listContent = controller.getHollowContent(PLAYER_2, i);
			for (int marbleID : listContent) {
				player2Hollows.get(i - 1).addMarble(marbles.get(marbleID));
			}
			
		}
		
		p1Kalaha.clear();
		listContent = controller.getKalahaContent(1);
		for (int marbleID : listContent) {
			
			p1Kalaha.addMarble(marbles.get(marbleID));
		}
		
		p2Kalaha.clear();
		listContent = controller.getKalahaContent(2);
		for (int marbleID : listContent) {
			
			p2Kalaha.addMarble(marbles.get(marbleID));
		}	
		
		if (controller.getActivePlayer() == PLAYER_1) {
			player1.setActive(true);
			player2.setActive(false);
		} else {
			player1.setActive(false);
			player2.setActive(true);
		}
		
		
		
	}


}
