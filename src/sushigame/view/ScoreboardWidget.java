package sushigame.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;

public class ScoreboardWidget extends JPanel implements BeltObserver, ActionListener {

	private SushiGameModel game_model;
	private JLabel display, displayFoodSold, displayFoodSpoiled;
	private JComboBox<String> scoreboard;
	
	public ScoreboardWidget(SushiGameModel gm) {
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);
		
		setLayout(new GridLayout(5,1));
		
		String[] scoreboardchoices = {"Account Balance", "Food Sold", "Spoiled Food"};
		scoreboard = new JComboBox<String>(scoreboardchoices);
		add(scoreboard);
		scoreboard.setActionCommand("scoreboard");
		scoreboard.addActionListener(this);
		
		display = new JLabel();
		
	}
	
	private String scoreboardFoodSold() {
		String sold = "<html>";
		sold += "<h1>Scoreboard - Food Sold</h1>";
		
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new FoodSoldComparator());
		
		for (Chef c : chefs) {
			sold += c.getName() + " (" + Math.round(c.foodEaten()*100.0)/100.0 + "oz" + ") <br>";
		}
		
		return sold;
	}
	
	private String scoreboardSpoiledFood() {
		String sold = "<html>";
		sold += "<h1>Scoreboard - Food Spoiled</h1>";
		
		Chef[] opponent_chefs = game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i = 1; i < chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new SpoiledFoodComparator());
		
		for (Chef c : chefs) {
			sold += c.getName() + " (" + Math.round(c.foodSpoiled()*100.0)/100.0 + "oz" + ") <br>";
		}
		
		return sold;
	}

	private String makeScoreboardHTML() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard - Account Balance</h1>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new HighToLowBalanceComparator());
		
		for (Chef c : chefs) {
			sb_html += c.getName() + " ($" + Math.round(c.getBalance()*100.0)/100.0 + ") <br>";
		}
		return sb_html;
	}

	public void refresh() {
		
		display.removeAll();
		//display.repaint();
	
		
		switch (scoreboard.getSelectedItem().toString()) {
		case "Account Balance":
			add(display, BorderLayout.CENTER);
			display.setText(makeScoreboardHTML());
			break;
		case "Food Sold":
			add(display, BorderLayout.CENTER);
			display.setText(scoreboardFoodSold());
			break;
		case "Spoiled Food":
			add(display, BorderLayout.CENTER);
			display.setText(scoreboardSpoiledFood());
			break;
		}		
		display.revalidate();
	}
	
	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("scoreboard")) {
			refresh();
		}
		
	}

}
