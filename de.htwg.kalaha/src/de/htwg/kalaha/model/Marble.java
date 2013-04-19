package de.htwg.kalaha.model;

public class Marble {
	public static enum MarbleType {BONE,DIAMOND,MARBLE,SEED,STICK,STONE};
	
	private MarbleType type;
	
	public Marble() {
		this.type = MarbleType.MARBLE;
		
	}

	public MarbleType getType() {
		return type;
	}

	public void setType(MarbleType type) {
		this.type = type;
	}

}
