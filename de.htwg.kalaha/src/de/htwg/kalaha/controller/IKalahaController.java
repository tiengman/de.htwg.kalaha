package de.htwg.kalaha.controller;

import java.util.List;
import de.htwg.util.observer.IObservable;

public interface IKalahaController extends IObservable {

	/**
	 * Prepare the board for a new game.
	 */
	void prepareNewGame();
	
	int getHollowCount();

	int getStartMarbles();
	
	/**
	 * Take marbles out of a hollow and distribute them.
	 *
	 * @param playernum the player number
	 * @param number the hollow number
	 */
	void takeMarbles(int playernum, int number);

	boolean isWinSituation();
	
	void checkWinSituation();
	
	void setAILevel(int level);
	
	boolean isAiTurn();
	
	void doAiTurn();
	
	String getWinner();
	
	/**
	 * Gets the status message.
	 *
	 * @return the status message
	 */
	String getStatus();

	void setStatusMessage(String statusMessage);
	
	/**
	 * Gets the game board as a string.
	 *
	 * @return the game board as a string
	 */
	String getBoardString();
	
	List<Integer> getHollowContent(int playernum, int num);
	
	List<Integer> getKalahaContent(int playernum);
	
	int getActivePlayer();

}
