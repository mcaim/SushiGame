package sushigame.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import comp401.sushi.AvocadoPortion;
import comp401.sushi.EelPortion;
import comp401.sushi.IngredientPortion;
import comp401.sushi.Nigiri;
import comp401.sushi.Roll;
import comp401.sushi.Sashimi;
import comp401.sushi.SeaweedPortion;
import comp401.sushi.Sushi;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import comp401.sushi.*;

public class PlayerChefView extends JPanel implements ActionListener, BeltObserver{

	private List<ChefViewListener> listeners;
	private Sushi random_roll;
	private JComboBox<String> colorBox, seafoodBox, sashimiornigiriBox;
	private JComboBox<Integer> positionBox;

	private JLabel plate_color, gold_plate_price, 
	plate_position, sashimi_or_nigiri, seafood_type, crab_amount,
	eel_amount, rice_amount, salmon_amount, seaweed_amount, shrimp_amount,
	tuna_amount, avocado_amount;

	private JSlider gold_slider, crab_slider, eel_slider, 
	rice_slider, salmon_slider, seaweed_slider, shrimp_slider,
	tuna_slider, avocado_slider;

	public PlayerChefView(int belt_size) {
		listeners = new ArrayList<ChefViewListener>();

		setLayout(new GridLayout(15,2));

		plate_color = new JLabel("Plate Color");
		add(plate_color);

		String[] colorSelection = {"Red", "Blue", "Green", "Gold"};
		colorBox = new JComboBox<String>(colorSelection);
		colorBox.setActionCommand("color");
		colorBox.addActionListener(this);
		add(colorBox);

		gold_plate_price = new JLabel("Gold Plate Price");
		add(gold_plate_price);

		Hashtable<Integer, JLabel> goldlabelTable = new Hashtable<Integer, JLabel>();
		goldlabelTable.put( new Integer( 500 ), new JLabel("$5.00") );
		goldlabelTable.put( new Integer( 750 ), new JLabel("$7.50") );
		goldlabelTable.put( new Integer( 1000 ), new JLabel("$10.00") );

		gold_slider = new JSlider(500,1000,500);
		gold_slider.setPaintTicks(true);
		gold_slider.setSnapToTicks(false);
		gold_slider.setPaintLabels(true);
		gold_slider.setMajorTickSpacing(100);
		gold_slider.setLabelTable(goldlabelTable);
		add(gold_slider);
		
		showGoldPlatePrice();

		plate_position = new JLabel("Plate Position");
		add(plate_position);

		positionBox = new JComboBox<Integer>();
		for (int i = 0; i < 20; i++) {
			positionBox.addItem(i);
		}
		add(positionBox);

		sashimi_or_nigiri = new JLabel("Sashimi or Nigiri?");
		add(sashimi_or_nigiri);

		String[] sashimiornigiri = {"Sashimi", "Nigiri"};
		sashimiornigiriBox = new JComboBox<String>(sashimiornigiri);
		add(sashimiornigiriBox);

		seafood_type = new JLabel("Seafood Type");
		add(seafood_type);

		String[] seafood = {"Tuna", "Crab", "Eel", "Salmon", "Shrimp"};
		seafoodBox = new JComboBox<String>(seafood);
		add(seafoodBox);

		JButton make_plate = new JButton("Make Sashimi/Nigiri");
		make_plate.setActionCommand("make_plate");
		make_plate.addActionListener(this);
		add(make_plate);

		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put( new Integer( 0 ), new JLabel("0.0") );
		labelTable.put( new Integer( 75 ), new JLabel("0.75") );
		labelTable.put( new Integer( 150 ), new JLabel("1.50") );

		JLabel roll_info = new JLabel("Stuff below is for creating rolls");
		add(roll_info);

		crab_amount = new JLabel("Crab Amount (oz)");
		add(crab_amount);

		crab_slider = new JSlider(0,150,0);
		crab_slider.setPaintTicks(true);
		crab_slider.setPaintLabels(true);
		crab_slider.setMajorTickSpacing(50);
		crab_slider.setMinorTickSpacing(25);
		crab_slider.setLabelTable( labelTable );
		add(crab_slider);

		eel_amount = new JLabel("Eel Amount (oz)");
		add(eel_amount);

		eel_slider = new JSlider(0,150,0);
		eel_slider.setPaintTicks(true);
		eel_slider.setPaintLabels(true);
		eel_slider.setMajorTickSpacing(50);
		eel_slider.setMinorTickSpacing(25);
		eel_slider.setLabelTable( labelTable );
		add(eel_slider);

		rice_amount = new JLabel("Rice Amount (oz)");
		add(rice_amount);

		rice_slider = new JSlider(0,150,0);
		rice_slider.setPaintTicks(true);
		rice_slider.setPaintLabels(true);
		rice_slider.setMajorTickSpacing(50);
		rice_slider.setMinorTickSpacing(25);
		rice_slider.setLabelTable( labelTable );
		add(rice_slider);

		salmon_amount = new JLabel("Salmon Amount (oz)");
		add(salmon_amount);

		salmon_slider = new JSlider(0,150,0);
		salmon_slider.setPaintTicks(true);
		salmon_slider.setPaintLabels(true);
		salmon_slider.setMajorTickSpacing(50);
		salmon_slider.setMinorTickSpacing(25);
		salmon_slider.setLabelTable( labelTable );
		add(salmon_slider);

		seaweed_amount = new JLabel("Seaweed Amount (oz)");
		add(seaweed_amount);

		seaweed_slider = new JSlider(0,150,0);
		seaweed_slider.setPaintTicks(true);
		seaweed_slider.setPaintLabels(true);
		seaweed_slider.setMajorTickSpacing(50);
		seaweed_slider.setMinorTickSpacing(25);
		seaweed_slider.setLabelTable( labelTable );
		add(seaweed_slider);

		shrimp_amount = new JLabel("Shrimp Amount (oz)");
		add(shrimp_amount);

		shrimp_slider = new JSlider(0,150,0);
		shrimp_slider.setPaintTicks(true);
		shrimp_slider.setPaintLabels(true);
		shrimp_slider.setMajorTickSpacing(50);
		shrimp_slider.setMinorTickSpacing(25);
		shrimp_slider.setLabelTable( labelTable );
		add(shrimp_slider);

		tuna_amount = new JLabel("Tuna Amount (oz)");
		add(tuna_amount);

		tuna_slider = new JSlider(0,150,0);
		tuna_slider.setPaintTicks(true);
		tuna_slider.setPaintLabels(true);
		tuna_slider.setMajorTickSpacing(50);
		tuna_slider.setMinorTickSpacing(25);
		tuna_slider.setLabelTable( labelTable );
		add(tuna_slider);

		avocado_amount = new JLabel("Avocado Amount (oz)");
		add(avocado_amount);

		avocado_slider = new JSlider(0,150,0);
		avocado_slider.setPaintTicks(true);
		avocado_slider.setPaintLabels(true);
		avocado_slider.setMajorTickSpacing(50);
		avocado_slider.setMinorTickSpacing(25);
		avocado_slider.setLabelTable( labelTable );
		add(avocado_slider);

		JButton make_roll = new JButton("Make Roll");
		make_roll.setActionCommand("make_roll");
		make_roll.addActionListener(this);
		add(make_roll);	
	}

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}

	private void makeEmptyPlateRequest() {
		for (ChefViewListener l : listeners) {
			l.handleEmptyPlateRequest();
		}
	}

	private void showGoldPlatePrice() {
		if (colorBox.getSelectedItem().toString().equals("Gold")) {
			gold_plate_price.setVisible(true);
			gold_slider.setVisible(true);
		} else {
			gold_plate_price.setVisible(false);
			gold_slider.setVisible(false);
		}
	}

	public IngredientPortion[] rollMaker() {
		List<IngredientPortion> roll_list = new ArrayList<IngredientPortion>();

		if (!(eel_slider.getValue() <= 0)) {
			roll_list.add(new EelPortion(eel_slider.getValue()/100.0));
		}
		if (!(avocado_slider.getValue() <= 0)) {
			roll_list.add(new AvocadoPortion(avocado_slider.getValue()/100.0));
		}
		if (!(seaweed_slider.getValue() <= 0)) {
			roll_list.add(new SeaweedPortion(seaweed_slider.getValue()/100.0));
		}
		if (!(rice_slider.getValue() <= 0)) {
			roll_list.add(new RicePortion(rice_slider.getValue()/100.0));
		}
		if (!(crab_slider.getValue() <= 0)) {
			roll_list.add(new CrabPortion(crab_slider.getValue()/100.0));
		}
		if (!(tuna_slider.getValue() <= 0)) {
			roll_list.add(new TunaPortion(tuna_slider.getValue()/100.0));
		}
		if (!(salmon_slider.getValue() <= 0)) {
			roll_list.add(new SalmonPortion(salmon_slider.getValue()/100.0));
		}
		if (!(shrimp_slider.getValue() <= 0)) {
			roll_list.add(new ShrimpPortion(shrimp_slider.getValue()/100.0));
		}

		IngredientPortion[] roll = new IngredientPortion[roll_list.size()];
		roll = roll_list.toArray(roll);

		return roll;
	}

	public Sashimi getSashimiType() {
		Sashimi sashimi=null;
		switch (seafoodBox.getSelectedItem().toString()) { 
		case "Tuna":
			sashimi = new Sashimi(Sashimi.SashimiType.TUNA);
			break;
		case "Crab":
			sashimi = new Sashimi(Sashimi.SashimiType.CRAB);
			break;
		case "Eel":
			sashimi = new Sashimi(Sashimi.SashimiType.EEL);
			break;
		case "Salmon":
			sashimi = new Sashimi(Sashimi.SashimiType.SALMON);
			break;
		case "Shrimp":
			sashimi = new Sashimi(Sashimi.SashimiType.SHRIMP);
			break;
		}
		return sashimi;
	}

	public Nigiri getNigiriType() {
		Nigiri nigiri=null;
		switch (seafoodBox.getSelectedItem().toString()) { 
		case "Tuna":
			nigiri = new Nigiri(Nigiri.NigiriType.TUNA);
			break;
		case "Crab":
			nigiri = new Nigiri(Nigiri.NigiriType.CRAB);
			break;
		case "Eel":
			nigiri = new Nigiri(Nigiri.NigiriType.EEL);
			break;
		case "Salmon":
			nigiri = new Nigiri(Nigiri.NigiriType.SALMON);
			break;
		case "Shrimp":
			nigiri = new Nigiri(Nigiri.NigiriType.SHRIMP);
			break;
		}
		return nigiri;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		random_roll = new Roll("Random Roll", rollMaker());

		if (e.getActionCommand().equals("make_plate")) {
			switch (colorBox.getSelectedItem().toString()) {
			case "Red":
				if (sashimiornigiriBox.getSelectedItem().toString().equals("Sashimi")) {
					makeRedPlateRequest(getSashimiType(), positionBox.getSelectedIndex());
				} else {
					makeRedPlateRequest(getNigiriType(), positionBox.getSelectedIndex());
				}
				break;
			case "Blue":
				if (sashimiornigiriBox.getSelectedItem().toString().equals("Sashimi")) {
					makeBluePlateRequest(getSashimiType(), positionBox.getSelectedIndex());
				} else {
					makeBluePlateRequest(getNigiriType(), positionBox.getSelectedIndex());
				}				
				break;
			case "Green":
				if (sashimiornigiriBox.getSelectedItem().toString().equals("Sashimi")) {
					makeGreenPlateRequest(getSashimiType(), positionBox.getSelectedIndex());
				} else {
					makeGreenPlateRequest(getNigiriType(), positionBox.getSelectedIndex());
				}				
				break;
			case "Gold":
				if (sashimiornigiriBox.getSelectedItem().toString().equals("Sashimi")) {
					makeGoldPlateRequest(getSashimiType(), positionBox.getSelectedIndex(), gold_slider.getValue()/100.0);
				} else {
					makeGoldPlateRequest(getNigiriType(), positionBox.getSelectedIndex(), gold_slider.getValue()/100.0);
				}
				break;
			}
		}

		if (e.getActionCommand().equals("make_roll")) {
			//if roll is empty, do nothing
			//this should be replaced with an exception that prints 
			//a msg to the GUI
			if (rollMaker().length == 0) {
				makeEmptyPlateRequest();
				return;
			}
			switch (colorBox.getSelectedItem().toString()) {
			case "Red":
				makeRedPlateRequest(random_roll, positionBox.getSelectedIndex());
				break;
			case "Blue":
				makeBluePlateRequest(random_roll, positionBox.getSelectedIndex());
				break;
			case "Green":
				makeGreenPlateRequest(random_roll, positionBox.getSelectedIndex());
				break;
			case "Gold":
				makeGoldPlateRequest(random_roll, positionBox.getSelectedIndex(), gold_slider.getValue()/100.0);
				break;
			}
		}
		
		if (e.getActionCommand().equals("color")) {
			showGoldPlatePrice();
		}
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			//stuff to reset sliders
		}		
	}


}
