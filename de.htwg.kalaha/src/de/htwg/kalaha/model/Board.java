package de.htwg.kalaha.model;

public class Board {
	
	private static final int HOLLOW_NUMBER = 6;
	
	private Player player1, player2;
	
	private KalahaHollow player1Kalaha;
	private KalahaHollow player2Kalaha;
	
	public Board(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		
		generateHollows(HOLLOW_NUMBER);
			
		//    2 2 2 2 2 2 
		// (2)           (1)
		//    1 1 1 1 1 1 
		
	}
	
	
	private void generateHollows(int hollowCount) {
		
		// Create Player Kalaha Hollows
		player1Kalaha = new KalahaHollow(player1);
		player2Kalaha = new KalahaHollow(player2);
		
		Hollow p1Current = null;
		Hollow p2Current = null;
		
		
		
		for (int i = 0; i < hollowCount; i++) {
			// Generate hollow
			Hollow p1Next = new Hollow(player1);
			Hollow p2Next = new Hollow(player2);
			
			if (i == 0) {
				player2Kalaha.setNext(p1Next); 
				player1Kalaha.setNext(p2Next);
				p1Next.setPrev(player2Kalaha);
				p2Next.setPrev(player1Kalaha);
			} else {
				// Link to next hollow
				p1Current.setNext(p1Next);
				p2Current.setNext(p2Next);
				p1Next.setPrev(p1Current);
				p2Next.setPrev(p2Current);
			}
			
			p1Current = p1Next;
			p2Current = p2Next;	
		}
		
		p1Current.setNext(player1Kalaha);
		p2Current.setNext(player2Kalaha);
		player1Kalaha.setPrev(p1Current);
		player2Kalaha.setPrev(p2Current);
		
		linkOppositeHollows();
	}
	
	private void linkOppositeHollows()  {
		Hollow current1 = player1Kalaha;
		Hollow current2 = player1Kalaha;
		
		do
		{
			current1 = current1.getNext();
			current2 = current2.getPrev();
			
			current1.setOpposite(current2);
			current2.setOpposite(current1);
			
		} while (!current1.equals(current2));
	}
	
	
	public Hollow getHollow(Player player, int num)  {
		Hollow current;
		if (player.equals(player1)) {
			current = player2Kalaha;
		} else {
			current = player1Kalaha;
		}
		
		for (int i = 0; i < num; i++) {
			current = current.getNext();
		}
		
		
		return current;
	}
	
	

}
