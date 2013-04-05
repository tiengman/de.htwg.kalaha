package de.htwg.kalaha.model;

import java.util.ArrayList;

public class Hollow {

	private Hollow opposite;
	private Hollow next;
	private Player owner;
	private ArrayList<Marble> marbles;
	
	
	public Hollow(Player owner) {
		this.setOwner(owner);
		this.marbles = new ArrayList<Marble>();
	}

	public Hollow getOpposite() {
		return this.opposite;
	}

	public void setOpposite(Hollow opposite) {
		this.opposite = opposite;
	}

	public Hollow getNext() {
		return this.next;
	}

	public void setNext(Hollow next) {
		this.next = next;
	}

	public Player getOwner() {
		return this.owner;
	}

	private void setOwner(Player owner) {
		this.owner = owner;
	}
	
	public int getMarbleCount() {
		return this.marbles.size();
	}
	
	public void setMarbles(int count) {
		marbles.clear();
		for (int i = 0; i < count; i++) {
			this.marbles.add(new Marble());
		}
	}
	
	public void addMarble(Marble marble) {
		marbles.add(marble);
	}
	
	public ArrayList<Marble> getMarbles() {
		ArrayList<Marble> marbleList = new ArrayList<Marble>();
		marbleList.addAll(marbles);
		marbles.clear();
		return marbleList;
	}
}
