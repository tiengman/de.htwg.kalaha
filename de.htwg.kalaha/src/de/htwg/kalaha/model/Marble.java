package de.htwg.kalaha.model;

public class Marble {
	public static enum MarbleType {BONE,DIAMOND,MARBLE,SEED,STICK,STONE};
	
	private MarbleType type;
	
	/**
	 * Instantiates a new marble.
	 */
	public Marble() {
		this.type = MarbleType.MARBLE;
		
	}

	/**
	 * Gets the marble type.
	 *
	 * @return the type
	 */
	public MarbleType getType() {
		return type;
	}

	/**
	 * Sets the marble type.
	 *
	 * @param type the new type
	 */
	public void setType(MarbleType type) {
		this.type = type;
	}

}
