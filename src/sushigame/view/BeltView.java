package sushigame.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import comp401.sushi.Plate;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;

public class BeltView extends JPanel implements BeltObserver {

	private Belt belt;
	private JPanel[] belt_labels;

	public BeltView(Belt b) {
		this.belt = b;
		belt.registerBeltObserver(this);
		setLayout(new GridLayout(belt.getSize(), 1));
		belt_labels = new JPanel[belt.getSize()];

		for (int i = 0; i < belt.getSize(); i++) {
			JPanel pPanel = new JPanel();
			pPanel.setMinimumSize(new Dimension(800, 50));
			pPanel.setPreferredSize(new Dimension(800, 50));
			pPanel.setBackground(Color.GRAY);
			pPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			add(pPanel);
			setBackground(Color.GRAY);
			belt_labels[i] = pPanel;
		}

		refresh();
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {	
		refresh();
	}

	private void refresh() {
		for (int i = 0; i < belt.getSize(); i++) {
			belt_labels[i].removeAll();
			Plate p = belt.getPlateAtPosition(i);
			repaint();
			JPanel panel = new BeltViewWidget(belt,i,p);
			belt_labels[i].add(panel);
			belt_labels[i].revalidate();
		}
	}
}
