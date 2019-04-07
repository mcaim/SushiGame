package sushigame.view;

import java.util.Comparator;

import sushigame.model.Chef;

public class FoodSoldComparator implements Comparator<Chef> {

	@Override
	public int compare(Chef a, Chef b) {
		return (int) (Math.round(b.foodEaten()*100.0) - 
				Math.round(a.foodEaten()*100));
	}

}
