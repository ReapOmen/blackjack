package blackjack;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Card {
	
	private String cardType;
	private String value;
	private Image image;

	/*
	 * Creates a card that later can be used in the BlackJack game.
	 * @param a The value of the card.
	 * @param type The type of the card.
	 */
	public Card(String a , String type) {
		
		value = a;
		cardType = type;
		try {
			
			image = ImageIO.read(getClass().getResource("resources/"+cardType+value));
			
		}
		catch(IOException e) {
			
			image = null;
			
		}
		catch(IllegalArgumentException e2){
			
			image = null;
			
		}
	}

	/*
	 * Creates a card from an existing card.
	 */
	public Card(Card d) {
		
		value = d.value;
		cardType = d.cardType;
		image = d.toImage();
	}

	/*
	 * Returns the type of the card.
	 * @return the string representation of the card type.
	 */
	public String getCardType() {
		
		return cardType;
	}

	/*
	 * Returns the value of the card.
	 * @return the string representation of the value of the card.
	 */
	public String getValue() {
		
		
		return value;
	}
	
	/*
	 * Returns the image of the card.
	 * @return the image representation of the card.
	 */
	public Image toImage() {
		
		return image;
		
	}
	
	/*
	 * Returns the string of the card.
	 * @return the string representation of the card.
	 */
	public String toString() {
		
		String s = "";
		
		if(cardType.equals("Spades"))
			s += "\u2660";
		else 
			if(cardType.equals("Clubs"))
				s += "\u2663";
			else
				if(cardType.equals("Diamonds"))
					s += "\u2666";
				else
					s += "\u2665";
		return s + value;
	}
}
