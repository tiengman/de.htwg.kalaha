package de.htwg.kalaha.model;

import java.util.List;

public class AI {

	private Board board;

	private int best;
	private Player p1,p2;

    
	private int startDepth = 0;
    
	public AI(Board board) {
		this.board = board.getClone();
		
		p2 = board.getActivePlayer();
		board.switchActivePlayer();
		p1 = board.getActivePlayer();
		board.switchActivePlayer();
	}
    
    public int getBestHollow(int depth) {
        best = 0;
        startDepth = depth;

        miniMax(p2, depth, board);

        return best;
    }
  
    
    int miniMax(Player player, int tiefe, Board currentBoard) {
    	List<Hollow> npmList = currentBoard.getPossibleMoves();
        if (tiefe == 0 || npmList.isEmpty()) {
        	if (player.equals(p1)) {
        		return currentBoard.getKalaha(p1).getMarbleCount();
        	} else {
            	return currentBoard.getKalaha(p2).getMarbleCount();
        	}
        	
        }
        int maxWert = Integer.MIN_VALUE;
        
        for (Hollow h : npmList)
        {
        	Board newBoard = currentBoard.getClone();
        	// Get same Hollow on new board
        	int hollowNum = currentBoard.getHollowNumber(h);

        	Hollow hCurrent = newBoard.getHollow(newBoard.getActivePlayer(), hollowNum);
        	
        	Hollow current = newBoard.takeMarbles(hCurrent);
        	newBoard.catchMarbles(current);
        	newBoard.takeAllMarbles();
        	newBoard.checkExtraTurn(current);

           int wert = -miniMax(newBoard.getActivePlayer(), tiefe-1, newBoard);

           if (wert > maxWert) {
              maxWert = wert;
              if (tiefe == startDepth) {
                 best = hollowNum;
              }
           }
        }
        return maxWert;
     }
    
	
}
