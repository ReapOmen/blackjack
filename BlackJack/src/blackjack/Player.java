package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private String name;
	private int score;
	private DeckOfCards deck;
	private List<Card> handOfCards;
	
	/*
	 * Creates a player.
	 * @param name The name of the player.
	 */
	public Player(String name) {
		
		this.name = name;
		deck =  null;
		
	}
	
	/*
	 * Sets a deck to the player.
	 * @param deck The deck that the player will use.
	 */
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
	
	/*
	 * Returns the hand of the player.
	 * @return a list of cards that are in the hand of the player.
	 */
	public List<Card> getCards() {
		
		return handOfCards;
		
	}
	
	/*
	 * A player draws a card and recalculates his score.
	 */
	public void hit() {
		
		handOfCards.add(deck.deal());
		score += getScoreOf(handOfCards.get(handOfCards.size() - 1));
		
	}
	
	/*
	 * Returns the score of the player.
	 * @return the score of the player.
	 */
	public int getScore() {
		
		return score;
		
	}
	
	/*
	 * Returns the name of the player.
	 * @return the name of the player.
	 */
	public String getName() {
		
		return name;
		
	}
	
	/*
	 * 
	 * @return a string representation of a player.
	 */
	@Override
	public String toString() {
		
		return name;
		
	}

}
