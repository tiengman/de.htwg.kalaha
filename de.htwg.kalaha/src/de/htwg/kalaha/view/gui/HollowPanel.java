package de.htwg.kalaha.view.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class HollowPanel extends ImagePanel {
	private static final int FONT_SIZE = 20;
	private static final int MARBLE_SIZE = 16;
	private static final int PLAYER_1 = 1;
	private static final int CIRCLE_1_LIMIT = 7;
	private static final int CIRCLE_2_LIMIT = 19;
	private static final double CIRCLE_1_MULTIPLIER = 1.1;
	private static final double CIRCLE_2_MULTIPLIER = 2.2;
	private static final double CIRCLE_3_MULTIPLIER = 3.3;

	private static final int PLAYER_1_OFFSET_X = 22;
	private static final int PLAYER_1_OFFSET_Y = 30;
	private static final int PLAYER_2_OFFSET_X = 6;
	private static final int PLAYER_2_OFFSET_Y = 14;
	
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
	
	private int num;
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	private int player;
	
	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}
	

	public HollowPanel(String file) {
		super(file);
	}
	
	public HollowPanel(String file, int x, int y) {
		super(file, x, y);
	}
	
	public HollowPanel(String file, boolean stretch) {
		super(file, stretch);
	}
	
	public HollowPanel(String file, int x, int y, int sizeX, int sizeY) {
		super(file, x, y, sizeX, sizeY);
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int w = this.getSize().width;
		
		int min;
		if (this.getSize().width < this.getSize().height) {
			min = this.getSize().width;
		} else {
			min =this.getSize().height;
		}
		
		int y = getY();
		if (this.isAlignBottom()) {
			y = this.getSize().height - min;
		}
		
		int centerY = y + w / 2;
		int centerX = this.getImgX() + w / 2;
		
		int offsetX = - (MARBLE_SIZE/2);
		int offsetY = - (MARBLE_SIZE/2);
		
		Font fnt = new Font("Arial",Font.BOLD, FONT_SIZE);
		g.setFont(fnt);
		g.setColor(Color.WHITE);
		
		if (player == PLAYER_1 ) {
			g.drawString(""+marbles.size(), this.getSize().width - PLAYER_1_OFFSET_X, PLAYER_1_OFFSET_Y);
		} else {
			g.drawString(""+marbles.size(), PLAYER_2_OFFSET_X, this.getSize().height - PLAYER_2_OFFSET_Y);
		}
		
		for (int i = 0; i < marbles.size(); i++) {
			Image image = marbles.get(i).getImg();
			
			if (i == 0)
			{
				g.drawImage(image, centerX + offsetX, centerY + offsetY,MARBLE_SIZE,MARBLE_SIZE,this);
			} else {

				g.setColor(Color.BLACK);

				int radius;
				int maxN;
				int n;
				
				
				if (i < CIRCLE_1_LIMIT) {
					radius = (int) (MARBLE_SIZE * CIRCLE_1_MULTIPLIER);
					n = i - 1;
					maxN = CIRCLE_1_LIMIT - 1;
					
				} else if (i < CIRCLE_2_LIMIT){
					radius = (int) (MARBLE_SIZE * CIRCLE_2_MULTIPLIER);
					n = i - CIRCLE_1_LIMIT;
					maxN = CIRCLE_2_LIMIT - CIRCLE_1_LIMIT;
				} else {
					radius = (int) (MARBLE_SIZE * CIRCLE_3_MULTIPLIER);
					n = i - CIRCLE_2_LIMIT;
					maxN = CIRCLE_2_LIMIT + 1;
				}
				

				
				int newX2 = (int) (radius * Math.cos((double)n / maxN * 2 * Math.PI) + centerX);
				int newY2 = (int) (radius * Math.sin((double)n / maxN * 2 * Math.PI) + centerY);
				
				g.drawImage(image, newX2 + offsetX,newY2 + offsetY,MARBLE_SIZE,MARBLE_SIZE,this);
				
			}
		}	
		
	}


	

}
