package de.htwg.kalaha.view.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class KalahaPanel extends ImagePanel {
	private static final int FONT_SIZE = 20;
	private static final int MARBLE_SIZE = 16;
	private static final int MARBLES_PER_COL = 25;
	private static final int MAX_ROWS = 5;
	private static final double ROW_SPAN = 3.5;
	private static final int TEXT_OFFSET_X = 8;
	private static final int TEXT_OFFSET_Y = 20;
	private static final int HEIGHT_DIVIDER = 4;
	private List<MarbleImage>  marbles = new ArrayList<MarbleImage>();  

	public boolean addMarble(MarbleImage e) {
		return marbles.add(e);
	}

	public void clear() {
		marbles.clear();
	}

	public MarbleImage getMarble(int index) {
		return marbles.get(index);
	}

	public int getMarbleCount() {
		return marbles.size();
	}
	
	private int player;
	
	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public KalahaPanel(String file) {
		super(file);
	}
	
	public KalahaPanel(String file, int x, int y) {
		super(file, x, y);
	}
	
	public KalahaPanel(String file, boolean stretch) {
		super(file, stretch);
	}
	
	public KalahaPanel(String file, int x, int y, int sizeX, int sizeY) {
		super(file, x, y, sizeX, sizeY);
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		
		// Draw kalaha image
		int imgSizeY = (int) (this.getSize().height / MAX_ROWS * ROW_SPAN );
		int newPosY = (this.getSize().height - imgSizeY) / 2;
		g.drawImage(getImg(), getImgX(),newPosY,this.getSize().width,imgSizeY,this);	
		
		// Draw number of marbles in kalaha
		Font fnt = new Font("Arial",Font.BOLD, FONT_SIZE);
		g.setFont(fnt);
		g.setColor(Color.WHITE);
		g.drawString(""+marbles.size(), (this.getSize().width / 2) - TEXT_OFFSET_X, this.getSize().height - newPosY + TEXT_OFFSET_Y);
		
		int centerX = this.getImgX() + this.getSize().width / 2;
		int offsetY = - (MARBLE_SIZE/2);

		
		for (int i = 0; i < marbles.size(); i++) {
			Image image = marbles.get(i).getImg();
			
			int col = i / MARBLES_PER_COL;
			int row = i % MARBLES_PER_COL;
			
			int posX = col * MARBLE_SIZE;
			int posY = row * MARBLE_SIZE;
			
			g.drawImage(image, centerX + posX - MARBLE_SIZE, (this.getSize().height / HEIGHT_DIVIDER) + posY + offsetY,MARBLE_SIZE,MARBLE_SIZE,this);
		}	
		
	}


	

}
