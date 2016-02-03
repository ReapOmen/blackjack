package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private String name;
	private int score;
	private DeckOfCards deck;
	private List<Card> handOfCards;
	
	public Player(String name) {
		
		this.name = name;
		deck =  null;
		
	}
	
	public void setDeck(DeckOfCards deck) {
		
		this.deck = deck;
		score = 0;
		handOfCards = new ArrayList<Card>();
		handOfCards.add(deck.deal());
		handOfCards.add(deck.deal());
		score += getScoreOf(handOfCards.get(0)) + getScoreOf(handOfCards.get(1));
		
	}
	
	private int getScoreOf(Card card) {
		
		try {
			
			int x = Integer.parseInt(card.getValue());
			return x;
			
		}
		catch(NumberFormatException e) {
			
			String s = card.getValue();
			if(s.equals("A"))
				return 11;
			else
				return 10;
		}
		
	}
	
	public List<Card> getCards() {
		
		return handOfCards;
		
	}
	
	public void hit() {
		
		handOfCards.add(deck.deal());
		score += getScoreOf(handOfCards.get(handOfCards.size() - 1));
		
	}
	
	public int getScore() {
		
		return score;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public String toString() {
		
		return name;
		
	}

}
