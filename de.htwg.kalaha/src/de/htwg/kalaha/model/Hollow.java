package de.htwg.kalaha.model;

import java.util.ArrayList;

public class Hollow {

	private Hollow prev;
	private Hollow next;
	private Player owner;
	private ArrayList<Marble> marbles;
	
	
	public Hollow(Player owner, int marbleCount) {
		this.setOwner(owner);
		this.marbles = new ArrayList<Marble>();
		this.setMarbles(marbleCount);
	}

	public Hollow getPrev() {
		return prev;
	}

	public void setPrev(Hollow prev) {
		this.prev = prev;
	}

	public Hollow getNext() {
		return next;
	}

	public void setNext(Hollow next) {
		this.next = next;
	}

	public Player getOwner() {
		return owner;
	}

	private void setOwner(Player owner) {
		this.owner = owner;
	}
	
	public int getMarbleCount() {
		return this.marbles.size();
	}
	
	private void setMarbles(int count) {
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
