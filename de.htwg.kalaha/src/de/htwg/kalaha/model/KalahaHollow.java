package de.htwg.kalaha.model;

import java.util.List;


public class KalahaHollow extends Hollow {
	
	/**
	 * Instantiates a new kalaha hollow.
	 *
	 * @param owner the owner
	 */
	public KalahaHollow(Player owner) {
		super(owner);
	}
	

	@Override
	public List<Marble> getMarbles() {
		return null;
	}
}