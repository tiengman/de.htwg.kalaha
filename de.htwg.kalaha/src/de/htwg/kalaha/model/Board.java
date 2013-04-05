package de.htwg.kalaha.model;

import java.util.ArrayList;

public class Board {
	
	Player player1, player2;
	
	KalahaHollow player1Kalaha;
	KalahaHollow player2Kalaha;
	
//	ArrayList<Hollow> player1Hollows;
//	ArrayList<Hollow> player2Hollows;
	
	public Board(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		
		generateHollows(6);
			
		//    2 2 2 2 2 2 
		//  2             1
		//    1 1 1 1 1 1 
		
	}
	
	
	private void generateHollows(int hollowCount) {
	//	player1Hollows = new ArrayList<Hollow>();
	//	player2Hollows = new ArrayList<Hollow>();
		
		KalahaHollow player1Kalaha = new KalahaHollow(player1);
		KalahaHollow player2Kalaha = new KalahaHollow(player2);
		
		
		Hollow p1Current = null;
		Hollow p2Current = null;

		
//		p1Current.setOpposite(p2Current);
//		p2Current.setOpposite(p1Current);
//		
//		player2Kalaha.setNext(p1Current); 
//		player1Kalaha.setNext(p2Current); 
//		
		for (int i = 0; i < hollowCount - 1; i++) {
			// Generate hollow
			Hollow p1Next = new Hollow(player1);
			Hollow p2Next = new Hollow(player2);
			
			if (i == 0) {
				player2Kalaha.setNext(p1Next); 
				player1Kalaha.setNext(p2Next);
			} else {
				// Link to next hollow
				p1Current.setNext(p1Next);
				p2Current.setNext(p2Next);
			}
			
			p1Current = p1Next;
			p2Current = p2Next;
			
			// Link opposite hollows
			p1Current.setOpposite(p2Current);
			p2Current.setOpposite(p1Current);		
		}
		
		p1Current.setNext(player1Kalaha);
		p2Current.setNext(player2Kalaha);
	}
	
	public Hollow getHollow(Player player, int num)  {
		Hollow current;
		if (player == player1) {
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
