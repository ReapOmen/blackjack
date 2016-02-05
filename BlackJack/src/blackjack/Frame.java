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
	
	private void createPlayer1() {
		
		
		//player1 buttons
		jbHit = new JButton("Hit");
		jbStop = new JButton("Stop");
		jbHit.addActionListener(new Handler());
		jbStop.addActionListener(new Handler());
		jpPlayer1.add(jbHit);
		jpPlayer1.add(jbStop);

		//adding score to player 1
		jlPlayer1Score = new JLabel(game.getScore(true));
		jpPlayer1.add(jlPlayer1Score);
		
		add(jpPlayer1, BorderLayout.SOUTH);
		
		//panel which contains the cards of player1
		jpPlayer1Cards = new JPanel();
		jpPlayer1Cards.setLayout(new FlowLayout());
		
		printCardsPlayer1();
		
		jpCards.add(jpPlayer1Cards, BorderLayout.SOUTH);

		
	}
	
	private void printCardsPlayer1() {
		
		//adding cards to players1 panel
		cards = (ArrayList<String>) game.getCards(true);
		for(String s : cards) {
						
			jpPlayer1Cards.add(new JLabel(s));
						
		}
		
	}
	
	private void createPlayer2() {
			
		//adding utilities to computer aka player2
		jlPlayer2Score = new JLabel(game.getScore(false));
		jpPlayer2.add(jlPlayer2Score);
		
		add(jpPlayer2, BorderLayout.NORTH);
		
		//panel which contains the cards of player2
		jpPlayer2Cards = new JPanel();
		jpPlayer2Cards.setLayout(new FlowLayout());
		
		printCardsPlayer2();
		
		jpCards.add(jpPlayer2Cards, BorderLayout.NORTH);


	}
	
	private void printCardsPlayer2() {
		
		//adding cards to players1 panel
		cards = (ArrayList<String>) game.getCards(false);
		for(String s : cards) {
						
			jpPlayer2Cards.add(new JLabel(s));
						
		}
		
	}
	
	public void recordCard() {
		
		jpPlayer2Cards.add(new JLabel(game.getLastCardPlayed()), BorderLayout.SOUTH);
		repaint();
		jlPlayer2Score.setText(game.getScore(false)); 
		
	}
	
	private class Handler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == jbHit) {
				
				game.play();

				jpPlayer1Cards.add(new JLabel(game.getLastCardPlayed()), BorderLayout.SOUTH);
				repaint();
				jlPlayer1Score.setText(game.getScore(true)); 
				game.checkWinner();
			}
			else
				game.stop();
			
		}
		
	}
	
	public void reset() {
		
		jlPlayer1Score.setText(game.getScore(true));
		jlPlayer2Score.setText(game.getScore(false));

		jpPlayer1Cards.removeAll();
		jpPlayer2Cards.removeAll();
		
		printCardsPlayer1();
		printCardsPlayer2();
		
		repaint();

		
	}
	
	public void printWinner(Player player) {
		
		JOptionPane.showMessageDialog(this, "Winner is " + player.getName());
		
	}

}
