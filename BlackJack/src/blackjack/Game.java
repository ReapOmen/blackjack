package blackjack;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Game {
	
	private DeckOfCards deck;
	private Player player1, player2;
	private boolean turnOfPlayer1, winner;
	private Frame frame;

	/* 
	 * Constructs a BlackJack game with a GUI.
	 * @param p1 The first player that will play.
	 * @param p2 The 2nd player that will play, in this case the computer.
	 * @param f The frame to which the game will print the progress.
	 */
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
	
	/*
	 * Plays a card in the BlackJack game.
	 */
	public void play() {
		
		if(turnOfPlayer1)
			player1.hit();
		else {
			
			//the computer will try to beat the player.
			while(player2.getScore() <= 21 
					&& player2.getScore() < player1.getScore()) {
				
				player2.hit();
				frame.recordCard();
				
			}
			
			checkWinner();
		}
		
	}
	
	/*
	 * Checks for the last card played on the board.
	 * @return a String representations of the last card played.
	 */
	public Pair<String, Image> getLastCardPlayed() {
		
		Pair<String, Image> p;
		
		if(turnOfPlayer1) {
			
			int lastIndex = player1.getCards().size() - 1;
			p = new Pair<String, Image>(player1.getCards().get(lastIndex).toString(), 
					player1.getCards().get(lastIndex).toImage());
			
			return p;
			
		}
		else {
			
			int lastIndex = player2.getCards().size() - 1;
			p = new Pair<String, Image>(player2.getCards().get(lastIndex).toString(), 
					player2.getCards().get(lastIndex).toImage());
			
			return p;
			
		}

		
	}
	
	/*
	 * Generates and returns a list of cards of a certain player.
	 * @param human If the player is human or the computer, aka player2.
	 * @return a list of cards in it's string representation.
	 */
	public List<Pair<String, Image>> getCards(boolean human) {
		
		ArrayList<Card> cards = null;
		
		if(human)
			cards = (ArrayList<Card>) player1.getCards();
		else
			cards = (ArrayList<Card>) player2.getCards();

		ArrayList<Pair<String, Image>> cards2 = new ArrayList<Pair<String, Image>>();
		for(Card c : cards) {
			
			cards2.add(new Pair(c.toString(), c.toImage()));
			
		}
		
		return cards2;
		
	}
	
	
	/*
	 * Changes the turn from player1 to the other one.
	 */
	public void stop() {
		
		turnOfPlayer1 = false;
		play();
		
	}
	
	/*
	 * Resets the game.
	 */
	public void reset() {
		
		deck = new DeckOfCards();
		deck.shuffle();

		player1.setDeck(deck);
		player2.setDeck(deck);
		turnOfPlayer1 = true;
		frame.reset();
	}
	
	/*
	 * Checks if there is a winner. If there is it will inform the GUI.
	 */
	public void checkWinner() {
		
		if(!winner) {
		
			if( player1.getScore() > 21) {
					
				frame.printWinner(player2);
				reset();

			}
			
			else
				if(!turnOfPlayer1) {
					
					if(player2.getScore() > 21) {
						
						frame.printWinner(player1);
						reset();
					}
					else
						if(player1.getScore() > player2.getScore()) {
							
							frame.printWinner(player1);
							reset();

						}
						else {
							
							frame.printWinner(player2);
							reset();
						}
					
				}
		}
		
	}
	
	/*
	 * Returns the score of a player.
	 * @param human True if you need the score of the user, or false for the computer.
	 * @return a string representation of the score of one player.
	 */
	public String getScore(boolean human) {
		
		if(human)
			return "Score: " + player1.getScore();
		else
			return "Score: " + player2.getScore();

		
	}
	
}
