package de.htwg.kalaha.model;

public class Player {
	private String name;
	
	/**
	 * Instantiates a new player.
	 *
	 * @param name the player name
	 */
	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the player name.
	 *
	 * @param name the new player name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the player name.
	 *
	 * @return the player name
	 */
	public String getName() {
		return this.name;
	}
}
