package sushigame.tournament;

import sushigame.controller.ChefController;
import sushigame.model.BeltEvent;
import sushigame.model.Chef;

public class McaimChefController implements ChefController {

	//instructions @1353
	private Chef chef;
	private int belt_size;
	
	public McaimChefController(Chef c, int belt_size) {
		chef = c;
		this.belt_size = belt_size;
	}
	
	@Override
	public void handleBeltEvent(BeltEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
