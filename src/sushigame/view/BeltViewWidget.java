package sushigame.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import comp401.sushi.IngredientPortion;
import comp401.sushi.Plate;
import comp401.sushi.Roll;
import comp401.sushi.Sushi;
import sushigame.model.Belt;
import sushigame.model.Chef;

public class BeltViewWidget extends JPanel {

	private Belt belt;
	private int plate_age;
	private String roll_ingredients;
	private JLabel plabel = new JLabel();
	private JLabel ingLabel = new JLabel();
	private JButton _ingredientsButton;
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();

	//this is all info for one vertical strip
	public BeltViewWidget (Belt b, int position, Plate p) {
		belt = b;

		plabel = new JLabel("");
		plabel.setMinimumSize(new Dimension(800, 50));
		plabel.setPreferredSize(new Dimension(800, 50));
		plabel.setOpaque(true);
		plabel.setBackground(Color.GRAY);
		plabel.setBorder(BorderFactory.createLineBorder(Color.black));
		add(plabel);


		if (p == null) {
			plabel.setText("");
			plabel.setBackground(Color.GRAY);
		} else {
			String chef = p.getChef().getName();
			String sushitype = p.getContents().getName();

			setBackGroundColor(p);

			plate_age = b.getAgeOfPlateAtPosition(position);

			if (!(p.getContents() instanceof Roll)) {

				plabel.setText("TYPE: " + sushitype + "  	 CHEF: " + chef + "  	 AGE: " + plate_age);
			} else {
				IngredientPortion[] ingredients = p.getContents().getIngredients();
				Map<String,String> ings = new HashMap<String,String>();
				for (IngredientPortion ing : ingredients) {
					double ingamount = ((int) ((ing.getAmount() * 100.0)+0.5))/100.0;
					ings.put(ing.getName(), ingamount + " oz");
				}

				roll_ingredients = ings.toString();

				plabel.setToolTipText("hi");

				plabel.setText("TYPE: " + sushitype + " 	  CHEF: " + chef + "  	 AGE: " + plate_age);

				//makes a button that pops out a new dialog box displaying ingredients
				_ingredientsButton = new JButton("ingredients");
				_ingredientsButton.setActionCommand("ingredients");
				add(_ingredientsButton);
				_ingredientsButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent a) {
						JOptionPane.showMessageDialog(frame,
								roll_ingredients,
								"Ingredients",
								JOptionPane.PLAIN_MESSAGE);
					}
				});
			}
		}
	}

	public void registerListener(ActionListener l){
		_ingredientsButton.addActionListener(l);
	}

	public String getRollIngredients() {
		return roll_ingredients;
	}

	public void setBackGroundColor(Plate p) {
		switch (p.getColor()) {
		case RED:
			plabel.setBackground(Color.RED); 
			break;
		case GREEN:
			plabel.setBackground(Color.GREEN); 
			break;
		case BLUE:
			plabel.setBackground(Color.BLUE); 
			break;
		case GOLD:
			plabel.setBackground(Color.YELLOW); 
			break;
		}
	}

}
