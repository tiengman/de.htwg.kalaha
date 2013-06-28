package de.htwg.kalaha.model;

import java.util.ArrayList;
import java.util.List;

public final class Board implements Cloneable {
	private static final int CHECK_NUM = 10;
	
	private Player player1, player2;
	
	private Player activePlayer;
	
	private KalahaHollow player1Kalaha;
	private KalahaHollow player2Kalaha;
	private List<Hollow> player1Hollows;
	private List<Hollow> player2Hollows;
	private int startMarbles;
	private int hollowCount;

	
	/**
	 * Instantiates a new game board.
	 *
	 * @param player1 the player 1
	 * @param player2 the player 2
	 */
	public Board(Player player1, Player player2, int hollowCount, int startMarbles) {
		this.player1 = player1;
		this.player2 = player2;
		this.startMarbles = startMarbles;
		this.hollowCount = hollowCount;
		
		activePlayer = this.player1;
		generateHollows(hollowCount);
	}
	
	public int getHollowNumber(Hollow hollow) {	
		List<Hollow> playerHollows = new ArrayList<Hollow>();
		if (hollow.getOwner().equals(player1)) {
			playerHollows = player1Hollows;
		} else {
			playerHollows = player2Hollows;
		}
		return playerHollows.indexOf(hollow) + 1;
	}
	
	public Board getClone() {
		Board newBoard = new Board(player1, player2, hollowCount, startMarbles);
		if (activePlayer.equals(player2)) {
			newBoard.switchActivePlayer();
		}
		// Copy Kalahas
	    int tmp = getKalaha(player1).getMarbleCount();
	    newBoard.getKalaha(player1).setMarbles(tmp);
	    tmp = getKalaha(player2).getMarbleCount();
	    newBoard.getKalaha(player2).setMarbles(tmp);
	    
	    // Copy Hollows
	    for (int i=0; i < player1Hollows.size(); i++) {
	    	tmp = player1Hollows.get(i).getMarbleCount();
	    	newBoard.getHollow(player1, i + 1).setMarbles(tmp);
	    	tmp = player2Hollows.get(i).getMarbleCount();
	    	newBoard.getHollow(player2, i + 1).setMarbles(tmp);
	    }
	    
	    return newBoard;
	}
	
	private int getHollowCount() {
		return player1Hollows.size();
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
			int oppNum = getHollowCount() - player1Hollows.indexOf(hollow) - 1;
			return player2Hollows.get(oppNum);
			
		} else if (player2Hollows.contains(hollow)) {
			int oppNum = getHollowCount() - player2Hollows.indexOf(hollow) - 1;
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
	
	
	public List<Marble> getHollowsMarbles(Player player)  {
		List<Marble> marbles = new ArrayList<Marble>();
		
		for (int i = 1; i <= getHollowCount(); i++) {
			marbles.addAll(getHollow(player,i).getMarbles());
		}
		
	   return marbles;
	}
	
	public boolean isWinSituation() {
		int halfMarbles = getHollowCount() * this.startMarbles;
		
		return player1Kalaha.getMarbleCount() > halfMarbles || player2Kalaha.getMarbleCount() > halfMarbles
			|| arePlayerHollowsEmpty(player1) || arePlayerHollowsEmpty(player2);
	}
	
	public Player getWinner() {
		if (player1Kalaha.getMarbleCount() > player2Kalaha.getMarbleCount()) {
			return player1;
		} else if (player2Kalaha.getMarbleCount() > player1Kalaha.getMarbleCount()) {
			return player2;
		}
		return null;	
	}
	
	public void cleanBoard() {
		getKalaha(player1).addMarbles(getHollowsMarbles(player1));
		getKalaha(player2).addMarbles(getHollowsMarbles(player2));
	}
	
	
	public boolean arePlayerHollowsEmpty(Player player) {
		for (int i = 1; i <= getHollowCount(); i++) {
			if (!getHollow(player, i).isEmpty()) {
				return false;
			}
		}

		return true;
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
			if (index == getHollowCount() - 1) {
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
			if (index == getHollowCount() - 1) {
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
		int marbleNum = 0;
		for (int i = 1; i <= getHollowCount(); i++) {
			List<Marble> mList1 = new ArrayList<Marble>();
			List<Marble> mList2 = new ArrayList<Marble>();
			
			// Create marbles for both hollows
			for (int j = 0; j < count; j++){
				Marble marble1 = new Marble();
				Marble marble2 = new Marble();
				marble1.setNumber(marbleNum++);
				marble2.setNumber(marbleNum++);
				mList1.add(marble1);
				mList2.add(marble2);
			}
			// Clear marbles
			getHollow(player1, i).setMarbles(0);
			getHollow(player2, i).setMarbles(0);
			
			// Add new marbles
			getHollow(player1, i).addMarbles(mList1);
			getHollow(player2, i).addMarbles(mList2);
		}
	}
	
	public void prepareBoard() {
		activePlayer = this.player1;
		player1Kalaha.setMarbles(0);
		player2Kalaha.setMarbles(0);
		fillHollows(this.startMarbles);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Active Player: " + getActivePlayer().getName() + "\n\n");
		
		// Line 1
		sb.append("\t ____\t");
		for (int i = getHollowCount(); i >= 1; i--) {
			sb.append("p2," + i + "\t");
		}
		sb.append(" ____\n");
		
		// Line 2
		sb.append("\t|    |");
		for (int i = getHollowCount(); i >= 1; i--) {
			sb.append("\t[" +  getHollow(player2,i).getMarbleCount() + "]");
		}
		sb.append("\t|    |\n");
		
		// Line 3
		sb.append("\t| " + player2Kalaha.getMarbleCount());
		if (player2Kalaha.getMarbleCount() < CHECK_NUM) {
			sb.append(" ");
		}
		sb.append(" |\t");
		for (int i = 1; i <= getHollowCount(); i++) {
			sb.append("\t");
		}
		sb.append("| " + player1Kalaha.getMarbleCount());
		if (player1Kalaha.getMarbleCount() < CHECK_NUM) {
			sb.append(" ");
		}
		sb.append(" |\n");
		
		// Line 4
		sb.append("\t|____|");
		for (int i = 1; i <= getHollowCount(); i++) {
			sb.append("\t[" + getHollow(player1,i).getMarbleCount() + "]");
		}
		sb.append("\t|____|\n");
		
		// Line 5
		sb.append("\t  P2\t");
		for (int i = 1; i <= getHollowCount(); i++) {
			sb.append("p1," + i + "\t");
		}
		sb.append("  P1\n");
		
		return sb.toString();
	}
	
	

	public List<Hollow> getPossibleMoves()
	{
		List<Hollow> playerHollows = new ArrayList<Hollow>();
		if (getActivePlayer().equals(player1)) {
			playerHollows = player1Hollows;
		} else {
			playerHollows = player2Hollows;
		}
		
		List<Hollow> moveList = new ArrayList<Hollow>();
		if (!isWinSituation()) 
		{
			for (Hollow h : playerHollows) {
				if (!h.isEmpty()) {
					moveList.add(h);
				}
			}
		}
		return moveList;
	}
	
	public void catchMarbles(Hollow current) {
		KalahaHollow playerKalaha = getKalaha(getActivePlayer());

		if (!current.equals(playerKalaha) && current.getMarbleCount() == 1 && current.getOwner() == getActivePlayer() &&
				!getOppositeHollow(current).isEmpty()) {
			List<Marble> marbles = getOppositeHollow(current).getMarbles();
			marbles.addAll(current.getMarbles());
			playerKalaha.addMarbles(marbles);
		}
	}
	
	public Hollow takeMarbles(Hollow hollow) {
		List<Marble> marbles = hollow.getMarbles();
		Hollow current = hollow;
		int count = marbles.size();
		for (int i = 1; i <= count; i++) {
			current = getNextHollow(current);
			current.addMarble(marbles.get(0));
			marbles.remove(0);
		}
		return current;
	}
	
	public void checkExtraTurn(Hollow current) {
		KalahaHollow  playerKalaha = getKalaha(getActivePlayer());
		
		if (!current.equals(playerKalaha)) {
			// Switch the active player after a turn
			switchActivePlayer();
		}
	}
	
	public void takeAllMarbles() {
		Player player = getActivePlayer();
		   
		if (arePlayerHollowsEmpty(player))
		{
			switchActivePlayer();
		    player = getActivePlayer();
		    
			
			KalahaHollow kalaha = getKalaha(player);
		    kalaha.addMarbles(getHollowsMarbles(player));
		    switchActivePlayer();
		}
	}

}
