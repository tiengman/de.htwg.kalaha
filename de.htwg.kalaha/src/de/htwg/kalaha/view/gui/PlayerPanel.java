package de.htwg.kalaha.view.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

@SuppressWarnings("serial")
public class PlayerPanel extends ImagePanel {
	private static final int FONT_SIZE = 20;
	private static final int FONT_POS_DIVIDER = 4;
	private static final int OFFSET_TEXT_Y = 10;
	private Image img;
	private Image imgActive;
	private boolean stretch;
	
	private String name;
	
	private String file;
	private String fileActive;
	
	private boolean active;
	
	public PlayerPanel(String file) {
		super(file);
	}

	public PlayerPanel(String file, String fileActive) {
		super(file);
		img = this.getToolkit().getImage(file);
		imgActive = this.getToolkit().getImage(fileActive);
		
		super.setImgX(0);
		super.setImgY(0);
		super.setSizeX(0);
		super.setSizeY(0);

		this.name = "Player";
		this.file = file;
		this.fileActive = fileActive;
	}

	public PlayerPanel(String file, String fileActive, boolean stretch) {
		this(file, fileActive);
		this.stretch = stretch;
	}

	

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	

	public String getPlayerActiveFile() {
		return this.fileActive;
	}
	
	public String getPlayerFile() {
		return this.file;
	}
	
	public void setPlayerFiles(String file, String fileActive) {
		this.file = file;
		this.fileActive = fileActive;
		
		img = this.getToolkit().getImage(file);
		imgActive = this.getToolkit().getImage(fileActive);
		repaint();
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Image playerImg;
		
		if (this.active) {
			playerImg = imgActive;
		} else {
			playerImg = img;
		}
		
		
		if (this.stretch) {
			g.drawImage(playerImg, getImgX(),getImgY(),this.getSize().width,this.getSize().height,this);
		}
		else
		{
			int min;
			if (this.getSize().width < this.getSize().height) {
				min = this.getSize().width;
			} else {
				min =this.getSize().height;
			}
			
			int y = getY();

			g.drawImage(playerImg, getImgX(), y,min,min,this);
		}
		

		Font fnt = new Font("Arial",Font.BOLD, FONT_SIZE);
		g.setFont(fnt);
		g.setColor(Color.WHITE);
		g.drawString(name, this.getSize().width / FONT_POS_DIVIDER, this.getSize().height - OFFSET_TEXT_Y);
		
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}




}
