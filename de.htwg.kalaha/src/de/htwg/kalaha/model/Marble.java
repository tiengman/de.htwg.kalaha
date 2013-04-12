package de.htwg.kalaha.model;

public class Marble {
	public static enum MarbleType {BONE,DIAMOND,MARBLE,STICK,STONE};
	
	private MarbleType type;
	
	public Marble() {
		
	}

	public MarbleType getType() {
		return type;
	}

	public void setType(MarbleType type) {
		this.type = type;
	}

}
