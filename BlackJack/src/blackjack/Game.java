package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Game {
	
	private DeckOfCards deck;
	private Player player1, player2;
	private boolean turnOfPlayer1, winner;
	private Frame frame;

	public Game(Player p1, Player p2, Frame f) {
		
		deck = new DeckOfCards();
		deck.shuffle();
		frame = f;
		player1 = p1;
		player2 = p2;
		player1.setDeck(deck);
		player2.setDeck(deck);
		turnOfPlayer1 = true;
		winner = false;
		
	}
	
	public void play() {
		
		if(turnOfPlayer1)
			player1.hit();
		else {
			
			while(player2.getScore() <= 21 
					&& player2.getScore() < player1.getScore()) {
				
				player2.hit();
				frame.recordCard();
				
			}
			
			checkWinner();
		}
		
	}
	
	public String getLastCardPlayed() {
		
		if(turnOfPlayer1)
			return player1.getCards().get(player1.getCards().size() - 1).toString();
		else
			return player2.getCards().get(player2.getCards().size() - 1).toString();

		
	}
	
	public List<String> getCards(boolean human) {
		
		ArrayList<Card> cards = null;
		
		if(human)
			cards = (ArrayList<Card>) player1.getCards();
		else
			cards = (ArrayList<Card>) player2.getCards();

		ArrayList<String> cards2 = new ArrayList<String>();
		for(Card c : cards) {
			
			cards2.add(c.toString());
			
		}
		
		return cards2;
		
	}
	
	public void stop() {
		
		turnOfPlayer1 = false;
		play();
		
	}
	
	public void reset() {
		
		deck = new DeckOfCards();
		deck.shuffle();

		player1.setDeck(deck);
		player2.setDeck(deck);
		turnOfPlayer1 = true;
		frame.reset();
	}
	
	public void checkWinner() {
		
		if(!winner) {
		
			if( player1.getScore() > 21)
				frame.printWinner(player2);
			else
				if(!turnOfPlayer1) {
					
					if(player2.getScore() > 21)
						frame.printWinner(player1);
					else
						if(player1.getScore() > player2.getScore())
							frame.printWinner(player1);
						else
							frame.printWinner(player2);
				
					
				}
		}
		
	}
	
	public String getScore(boolean human) {
		
		if(human)
			return "Score: " + player1.getScore();
		else
			return "Score: " + player2.getScore();

		
	}
	
}
