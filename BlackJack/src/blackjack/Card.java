package blackjack;
public class Card {
	
	private String cardType;
	private String value;

	public Card(String a , String type) {
		
		value = a;
		cardType = type;
	}

	public Card(Card d) {
		
		value = d.value;
		cardType = d.cardType;
	}

	public String getCardType() {
		
		return cardType;
	}

	public String getValue() {
		
		
		return value;
	}
	
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
