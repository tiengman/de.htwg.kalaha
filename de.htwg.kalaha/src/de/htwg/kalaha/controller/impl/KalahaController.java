package de.htwg.kalaha.controller.impl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.kalaha.controller.IKalahaController;
import de.htwg.kalaha.model.AI;
import de.htwg.kalaha.model.Board;
import de.htwg.kalaha.model.Hollow;
import de.htwg.kalaha.model.KalahaHollow;
import de.htwg.kalaha.model.Marble;
import de.htwg.kalaha.model.Player;
import de.htwg.util.observer.Observable;

public class KalahaController extends Observable implements IKalahaController {
	
	private static final int AI_EASY_ROUNDS = 2;
	private static final int AI_MEDIUM_ROUNDS = 4;
	private static final int AI_HARD_ROUNDS = 6;
	private static final int AI_EASY = 1;
	private static final int AI_MEDIUM = 2;
	private static final int AI_HARD = 3;
	private static final int PLAYER_1 = 1;
	private static final int PLAYER_2 = 2;
	
	private String statusMessage = "Welcome!";
	private Board board;
	private Player player1, player2;
	private int aiLevel;
	
	private int hollowCount;
	private int startMarbles;
	
	public int getHollowCount() {
		return hollowCount;
	}

	public int getStartMarbles() {
		return startMarbles;
	}

	/**
	 * Instantiates a new kalaha controller.
	 */
	public KalahaController(int hollowCount, int startMarbles) {
		this.player1 = new Player("Player 1");
		this.player2 = new Player("Player 2");
		this.aiLevel = AI_EASY_ROUNDS;
		this.hollowCount = hollowCount;
		this.startMarbles = startMarbles;
		this.board = new Board(this.player1,this.player2, hollowCount, startMarbles);	
		board.prepareBoard();
		statusMessage = "Welcome to Kalaha!";
		notifyObservers();
	}
	
	/**
	 * Prepare the board for a new game.
	 */
	public void prepareNewGame() {
		board.prepareBoard();
		setStatusMessage("New game started");
		notifyObservers();
	}
	
	
	/**
	 * Take marbles out of a hollow and distribute them.
	 *
	 * @param playernum the player number
	 * @param number the hollow number
	 */
	public void takeMarbles(int playernum, int number) {
		Player player;
		if (playernum == PLAYER_1) {
			player = player1;
		} else {
			player = player2;
		}
		
		Hollow hollow = board.getHollow(player, number);
		if (!player.equals(board.getActivePlayer())) {
			setStatusMessage("You are not allowed to take the marbles of the hollow p" + playernum + "," + number);
		} else if (!hollow.isEmpty()) {
			Hollow current = board.takeMarbles(hollow);
			setStatusMessage("The hollow p" + playernum + "," + number + " is now empty");
			
			board.catchMarbles(current);
			board.takeAllMarbles();
			board.checkExtraTurn(current);	
			
		} else {
			setStatusMessage("The hollow p" + playernum + "," + number + " is already empty");
		}

		notifyObservers();
		
	}

	public boolean isWinSituation() {
		return board.isWinSituation();
	}
	
	public void checkWinSituation() {
		if (board.isWinSituation()) {
			board.cleanBoard();
			if (player1.equals(board.getWinner())) {
				setStatusMessage("Player 1 wins the game!");
			} else if (player2.equals(board.getWinner())) {
				setStatusMessage("Player 2 wins the game!");
			} else {
				setStatusMessage("Draw!");
			}
			notifyObservers();
		}
	}
	
	public void setAILevel(int level) {
		if (level == AI_EASY) {
			aiLevel = AI_EASY_ROUNDS;
			setStatusMessage("AI level set to easy.");
		} else if (level == AI_MEDIUM) {
			aiLevel = AI_MEDIUM_ROUNDS;
			setStatusMessage("AI level set to medium.");
		} else if (level == AI_HARD) {
			aiLevel = AI_HARD_ROUNDS;
			setStatusMessage("AI level set to hard.");
		}
		notifyObservers();
	}
	
	public boolean isAiTurn() {
		return board.getActivePlayer().equals(player2);
	}
	
	public void doAiTurn() {
		AI ki = new AI(board);
		
		int best = ki.getBestHollow(aiLevel);
		setStatusMessage("CPU Turn: p2," + best + " - "+ this.getStatus());
		this.takeMarbles(2, best);

		notifyObservers();
	}
	
	public String getWinner() {
		return board.getWinner().getName();
	}
	
	/**
	 * Gets the status message.
	 *
	 * @return the status message
	 */
	public String getStatus() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	/**
	 * Gets the game board as a string.
	 *
	 * @return the game board as a string
	 */
	public String getBoardString() {
		return board.toString();
	}
	
	public List<Integer> getHollowContent(int playernum, int num) {
		List<Integer> content = new ArrayList<Integer>();
		Player player;
		if (playernum == PLAYER_1) {
			player = player1;
		} else {
			player = player2;
		}
		
		Hollow current = board.getHollow(player, num);
		
		for (Marble m : current.getMarblesList()) {
			content.add(m.getNumber());
		}
		
		
		return content;
	}
	
	public List<Integer> getKalahaContent(int playernum) {
		List<Integer> content = new ArrayList<Integer>();
		Player player;
		if (playernum == PLAYER_1) {
			player = player1;
		} else {
			player = player2;
		}
		KalahaHollow current = board.getKalaha(player);
		
		for (Marble m : current.getMarblesList()) {
			content.add(m.getNumber());
		}
		
		return content;
	}
	
	public int getActivePlayer() {
		if (board.getActivePlayer().equals(player1)) {
			return PLAYER_1;
		} else {
			return PLAYER_2;
		}
	}

}
