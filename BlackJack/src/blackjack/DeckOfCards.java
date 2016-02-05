package blackjack;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class DeckOfCards {
	
	private String type[] = {"Clubs" , "Diamonds" , "Hearts" , "Spades"};
	private ArrayList<Card> deck;

	/*
	 * Creates a deck of cards.
	 */
	public DeckOfCards() {
		
		deck = new ArrayList<Card>();		

		for(String t : type) {
			
			deck.add(new Card("A" , t));
			for(int j = 2 ; j <= 10 ; ++j)
				deck.add(new Card(String.valueOf(j) , t));
			deck.add(new Card("Q" , t));
			deck.add(new Card("J" , t));
			deck.add(new Card("K" , t));
		}
	}

	/*
	 * Shuffles the deck of cards.
	 */
	public void shuffle() {
		
		Random rnd = new Random();		
		for(int i = 0 ; i < 1200 ; ++i) {
			
			int j = rnd.nextInt(52) , k = rnd.nextInt(52);
			Collections.swap(deck , j , k);
		}
	}
	
	/*
	 * Prints out the deck on console.
	 */
	public void getDeck() {
		
		for(Card i : deck)
			System.out.println(i);

	}

	/*
	 * Deals a card, which then is removed from the deck.
	 * @return the card that was dealt.
	 */
	public Card deal() {
		
		if(deck.isEmpty())
			return null;

		return deck.remove(0);
	}
}
