package blackjack;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.util.ArrayList;


public class Frame extends JFrame {
	
	private Game game;
	private JButton jbHit, jbStop;
	private JPanel jpPlayer1, jpPlayer2, jpPlayer1Cards, jpPlayer2Cards,  jpCards;
	private JLabel jlPlayer1Score, jlPlayer2Score;
	private ArrayList<String> cards;
	
	/**
	 * Constructs the main window of the BlackJack game.
	 */
	public Frame() {
		
		super("BlackJack");
		setLayout(new BorderLayout());
		
		game = new Game(new Player ("Player1") , new Player ("Dealer"), this);
		
		// panel which will contain all cards
		jpCards = new JPanel();
		jpCards.setLayout(new BorderLayout());
		add(jpCards, BorderLayout.CENTER);
		
		//Setting up the panel for player1
		jpPlayer1 = new JPanel();
		jpPlayer1.setLayout(new FlowLayout());
		createPlayer1();
		
		//Setting up the panel for player 2
		jpPlayer2 = new JPanel();
		jpPlayer2.setLayout(new FlowLayout());
		createPlayer2();
		
		setPreferredSize(new Dimension(400, 200));
		pack();
		setVisible(true);
		
	}
	/**
	 * Creates the buttons of Player1 and also the Score label.
	 */
	private void createPlayer1() {
		
		
		//player1 buttons
		jbHit = new JButton("Hit"); // hit buttons
		jbStop = new JButton("Stop"); // stop button
		jbHit.addActionListener(new Handler());
		jbStop.addActionListener(new Handler()); //adding handlers
		jpPlayer1.add(jbHit); // adding them to the frame
		jpPlayer1.add(jbStop);

		//adding score to player 1
		jlPlayer1Score = new JLabel(game.getScore(true)); // adding score label
		jpPlayer1.add(jlPlayer1Score);
		
		add(jpPlayer1, BorderLayout.SOUTH); // add them to the bottom part of the frame
		
		//panel which contains the cards of player1
		jpPlayer1Cards = new JPanel();
		jpPlayer1Cards.setLayout(new FlowLayout());
		
		printCardsPlayer1(); 
		
		jpCards.add(jpPlayer1Cards, BorderLayout.SOUTH);

		
	}
	
	/*
	 * Asking the Game class to provide me with the cards of Player1.
	 */
	private void printCardsPlayer1() {
		
		//adding cards to players1 panel
		cards = (ArrayList<String>) game.getCards(true);
		for(String s : cards) {
						
			jpPlayer1Cards.add(new JLabel(s));
						
		}
		
	}
	
	/*
	 * Creating player 2 with a score label.
	 * 
	 */
	private void createPlayer2() {
			
		jlPlayer2Score = new JLabel(game.getScore(false));
		jpPlayer2.add(jlPlayer2Score);
		
		add(jpPlayer2, BorderLayout.NORTH);
		
		//panel which contains the cards of player2
		jpPlayer2Cards = new JPanel();
		jpPlayer2Cards.setLayout(new FlowLayout());
		
		printCardsPlayer2();
		
		jpCards.add(jpPlayer2Cards, BorderLayout.NORTH);


	}
	
	/*
	 * Same as above but for player2.
	 */
	private void printCardsPlayer2() {
		
		//adding cards to players2 panel
		cards = (ArrayList<String>) game.getCards(false);
		for(String s : cards) {
						
			jpPlayer2Cards.add(new JLabel(s));
						
		}
		
	}
	
	/*
	 * If a card is played, the game will notify the GUI to print that card to the frame.
	 */
	public void recordCard() {
		
		jpPlayer2Cards.add(new JLabel(game.getLastCardPlayed()), BorderLayout.SOUTH);
		repaint();
		jlPlayer2Score.setText(game.getScore(false)); 
		
	}
	
	/*
	 * Simple handler for the buttons.
	 */
	private class Handler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == jbHit) {
				
				game.play(); // If a player wants to hit, the game continues.

				jpPlayer1Cards.add(new JLabel(game.getLastCardPlayed()), BorderLayout.SOUTH);
				repaint();
				jlPlayer1Score.setText(game.getScore(true)); 
				game.checkWinner();
			}
			else // if the players presses the stop button.
				game.stop();
			
		}
		
	}
	
	/*
	 * Resets the GUI when the game is finished.
	 */
	protected void reset() {
		
		jlPlayer1Score.setText(game.getScore(true));
		jlPlayer2Score.setText(game.getScore(false));

		jpPlayer1Cards.removeAll();
		jpPlayer2Cards.removeAll();
		
		printCardsPlayer1();
		printCardsPlayer2();
		
		repaint();

		
	}
	
	/*
	 * Let's the user know if a player wins the game.
	 */
	
	public void printWinner(Player player) {
		
		JOptionPane.showMessageDialog(this, "Winner is " + player.getName());
		
	}

}
