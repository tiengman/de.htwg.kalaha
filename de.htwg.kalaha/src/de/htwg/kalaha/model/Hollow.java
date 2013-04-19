package de.htwg.kalaha.model;

import java.util.ArrayList;
import java.util.List;

public class Hollow {
	
	private Player owner;
	private List<Marble> marbles;
	
	public Hollow(Player owner) {
		this.setOwner(owner);
		this.marbles = new ArrayList<Marble>();
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
	
	public boolean isEmpty() {
		return (this.marbles.size() == 0);
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
	
	public List<Marble> getMarbles() {
		List<Marble> marbleList = new ArrayList<Marble>();
		marbleList.addAll(marbles);
		marbles.clear();
		return marbleList;
	}
}
