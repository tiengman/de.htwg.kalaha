package de.htwg.kalaha.model;

import java.util.ArrayList;
import java.util.List;

public final class Board {
	
	private static final int HOLLOW_NUMBER = 6;
	private static final int START_MARBLES = 6;
	
	private static final int CHECK_NUM = 10;
	
	private Player player1, player2;
	
	private Player activePlayer;
	
	private KalahaHollow player1Kalaha;
	private KalahaHollow player2Kalaha;
	private List<Hollow> player1Hollows;
	private List<Hollow> player2Hollows;
	
	/**
	 * Instantiates a new game board.
	 *
	 * @param player1 the player 1
	 * @param player2 the player 2
	 */
	public Board(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		
		generateHollows(HOLLOW_NUMBER);
		
		prepareBoard();
	}
	

 	private void generateHollows(int hollowCount) {
		// Create Player Kalaha Hollows
		player1Kalaha = new KalahaHollow(player1);
		player2Kalaha = new KalahaHollow(player2);
		
		player1Hollows = new ArrayList<Hollow>();
		player2Hollows = new ArrayList<Hollow>();
		
		for (int i = 0; i < hollowCount; i++) {
			player1Hollows.add(new Hollow(player1));
			player2Hollows.add(new Hollow(player2));
		}	
	}
	
	/**
	 * Gets the opposite hollow.
	 *
	 * @param hollow the current hollow
	 * @return the opposite hollow
	 */
	public Hollow getOppositeHollow(Hollow hollow) {		
		if (player1Hollows.contains(hollow)) {
			int oppNum = HOLLOW_NUMBER - player1Hollows.indexOf(hollow) - 1;
			return player2Hollows.get(oppNum);
			
		} else if (player2Hollows.contains(hollow)) {
			int oppNum = HOLLOW_NUMBER - player2Hollows.indexOf(hollow) - 1;
			return player1Hollows.get(oppNum);
		}
		
		return null;
	}
	
	/**
	 * Gets the hollow by player and hollow number.
	 *
	 * @param player the player
	 * @param num the hollow number
	 * @return the hollow
	 */
	public Hollow getHollow(Player player, int num)  {
		if (player.equals(player1)) {
			return player1Hollows.get(num - 1);
		} else if (player.equals(player2)) {
			return player2Hollows.get(num - 1);
		}
		
	   return null;
	}
	
	/**
	 * Gets the kalaha of the player.
	 *
	 * @param player the player
	 * @return the kalaha
	 */
	public KalahaHollow getKalaha(Player player)  {
		if (player.equals(player1)) {
			return player1Kalaha;
		} else if (player.equals(player2)) {
			return player2Kalaha;
		}
		
	   return null;
	}
	
	
	public Hollow getNextHollow(Hollow current)  {
		if (current.equals(player1Kalaha)) {
			return player2Hollows.get(0);
		}
		
		if (current.equals(player2Kalaha)) {
			return player1Hollows.get(0);	
		}
		
		if (player1Hollows.contains(current)) {
			int index = player1Hollows.indexOf(current);
			if (index == HOLLOW_NUMBER - 1) {
				if (activePlayer.equals(player2)) {
					return  player2Hollows.get(0);
				} else {
					return player1Kalaha;
				}
			}
			else {
				return player1Hollows.get(index + 1);
			}
		} 
		
		if (player2Hollows.contains(current)) {
			int index = player2Hollows.indexOf(current);
			if (index == HOLLOW_NUMBER - 1) {
				if (activePlayer.equals(player1)) {
					return  player1Hollows.get(0);
				} else {
					return player2Kalaha;
				}
			}
			else {
				return player2Hollows.get(index + 1);
			}
		} 

		return null;
	}
	
	public void switchActivePlayer() {
		if (activePlayer.equals(player1)) {
			activePlayer = player2;
		} else {
			activePlayer = player1;
		}
	}
	
	public Player getActivePlayer() {
		return activePlayer;
	}
	
	
	private void fillHollows(int count) {
		for (int i = 1; i <= HOLLOW_NUMBER; i++) {
			getHollow(player1, i).setMarbles(count);
			getHollow(player2, i).setMarbles(count);
		}
	}
	
	public void prepareBoard() {
		activePlayer = this.player1;
		player1Kalaha.setMarbles(0);
		player2Kalaha.setMarbles(0);
		fillHollows(START_MARBLES);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Active Player: " + getActivePlayer().getName() + "\n\n");
		
		// Line 1
		sb.append("\t ____\t");
		for (int i = HOLLOW_NUMBER; i >= 1; i--) {
			sb.append("p2," + i + "\t");
		}
		sb.append(" ____\n");
		
		// Line 2
		sb.append("\t|    |");
		for (int i = HOLLOW_NUMBER; i >= 1; i--) {
			sb.append("\t[" +  getHollow(player2,i).getMarbleCount() + "]");
		}
		sb.append("\t|    |\n");
		
		// Line 3
		sb.append("\t| " + player2Kalaha.getMarbleCount());
		if (player2Kalaha.getMarbleCount() < CHECK_NUM) {
			sb.append(" ");
		}
		sb.append(" |\t");
		for (int i = 1; i <= HOLLOW_NUMBER; i++) {
			sb.append("\t");
		}
		sb.append("| " + player1Kalaha.getMarbleCount());
		if (player1Kalaha.getMarbleCount() < CHECK_NUM) {
			sb.append(" ");
		}
		sb.append(" |\n");
		
		// Line 4
		sb.append("\t|____|");
		for (int i = 1; i <= HOLLOW_NUMBER; i++) {
			sb.append("\t[" + getHollow(player1,i).getMarbleCount() + "]");
		}
		sb.append("\t|____|\n");
		
		// Line 5
		sb.append("\t  P2\t");
		for (int i = 1; i <= HOLLOW_NUMBER; i++) {
			sb.append("p1," + i + "\t");
		}
		sb.append("  P1\n");
		
		return sb.toString();
	}
	
	

}
