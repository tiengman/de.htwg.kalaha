package de.htwg.kalaha.model;

import java.util.ArrayList;
import java.util.List;

public class Hollow {
	
	/** The owner of the hollow. */
	private Player owner;
	
	/** The marbles in the hollow. */
	private List<Marble> marbles;
	
	/**
	 * Instantiates a new hollow.
	 *
	 * @param owner the owner of the hollow
	 */
	public Hollow(Player owner) {
		this.setOwner(owner);
		this.marbles = new ArrayList<Marble>();
	}
	
	/**
	 * Gets the owner of the hollow.
	 *
	 * @return the owner
	 */
	public Player getOwner() {
		return this.owner;
	}

	/**
	 * Sets the owner of the hollow.
	 *
	 * @param owner the new owner
	 */
	private void setOwner(Player owner) {
		this.owner = owner;
	}
	
	/**
	 * Gets the number of marbles in the hollow.
	 *
	 * @return the marble count
	 */
	public int getMarbleCount() {
		return this.marbles.size();
	}
	
	/**
	 * Checks if the hollow is empty.
	 *
	 * @return true, if the hollow is empty
	 */
	public boolean isEmpty() {
		return (this.marbles.size() == 0);
	}
	
	/**
	 * Sets the number of marbles in the hollow.
	 *
	 * @param count the new marbles
	 */
	public void setMarbles(int count) {
		marbles.clear();
		for (int i = 0; i < count; i++) {
			this.marbles.add(new Marble());
		}
	}
	
	/**
	 * Adds a marble into the hollow.
	 *
	 * @param marble the marble
	 */
	public void addMarble(Marble marble) {
		marbles.add(marble);
	}
	
	/**
	 * Gets the marbles out of the hollow and clears the hollow.
	 *
	 * @return the marbles list
	 */
	public List<Marble> getMarbles() {
		List<Marble> marbleList = new ArrayList<Marble>();
		marbleList.addAll(marbles);
		marbles.clear();
		return marbleList;
	}
}
