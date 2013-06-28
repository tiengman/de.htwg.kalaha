package de.htwg.kalaha.view.gui;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
	private Image img;


	private int imgX;
	private int imgY;
	private int sizeX;


	private int sizeY;
	private boolean scaled;
	private boolean stretch;
	private boolean alignBottom;
	
	public boolean isAlignBottom() {
		return alignBottom;
	}

	public void setAlignBottom(boolean alignBottom) {
		this.alignBottom = alignBottom;
	}

	public ImagePanel(String file) {
		img = this.getToolkit().getImage(file);
		this.imgX = 0;
		this.imgY = 0;
		this.sizeX = 0;
		this.sizeY = 0;
		this.scaled = false;
	}
	
	public ImagePanel(String file, int x, int y) {
		this(file);
		this.imgX = x;
		this.imgY = y;
		this.scaled = false;
	}
	
	public ImagePanel(String file, boolean stretch) {
		this(file);
		this.stretch = stretch;
	}
	
	public ImagePanel(String file, int x, int y, int sizeX, int sizeY) {
		this(file, x, y);
		this.scaled = true;
		img = this.getToolkit().getImage(file).getScaledInstance(sizeX,sizeY,Image.SCALE_SMOOTH);
		this.sizeX = 0;
		this.sizeY = 0;
	}
	
	
	public void setImg(String file) {
		if (this.scaled) {
			img = this.getToolkit().getImage(file).getScaledInstance(sizeX,sizeY,Image.SCALE_SMOOTH);
		} else {
			img = this.getToolkit().getImage(file);
		}
		this.repaint();
	}
	
	public Image getImg() {
		return this.img;
	}
	
	public int getImgX() {
		return this.imgX;
	}

	public void setImgX(int x) {
		this.imgY = x;
	}

	public int getImgY() {
		return this.imgY;
	}

	public void setImgY(int y) {
		this.imgY = y;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (this.stretch) {
			g.drawImage(img, getImgX(),getImgY(),this.getSize().width,this.getSize().height,this);
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
			if (this.alignBottom) {
				y = this.getSize().height - min;
			}
			g.drawImage(img, getImgX(), y,min,min,this);
		}
			
	}


	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
	
	public boolean isScaled() {
		return scaled;
	}



}
