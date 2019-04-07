package sushigame.tournament;

import sushigame.controller.ChefController;
import sushigame.model.Chef;

public interface ChefTournamentContestant {
	
	String getOnyen();
	String getRealName();
	String getChefName();
	ChefController createChefController(Chef chef, int belt_size);
}
