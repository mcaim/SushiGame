package sushigame.tournament;

import sushigame.controller.ChefController;
import sushigame.model.Chef;

public class McaimContestant implements ChefTournamentContestant {

	@Override
	public String getOnyen() {
		return "mcaim";
	}

	@Override
	public String getRealName() {
		return "Aidan McRitchie";
	}

	@Override
	public String getChefName() {
		return "Agent Smith";
	}

	@Override
	public ChefController createChefController(Chef chef, int belt_size) {
		return new McaimChefController(chef, belt_size);
	}

}
