package Server;

import java.util.ArrayList;

public class MealPlanner {
	
	//TODO: Change the return type to a JSON-formatted String
	public ArrayList<MealItem> createMealPlan(ArrayList<MealItem> mealItems, Constraints constraints){
		ArrayList<MealItem> finalItems = new ArrayList<>();
		
		//1. Iterate over the MealItems, identify Locked items with a set quantity, move them to the final list and adjust the Constraints
		for(MealItem item : mealItems){
			if(item.isLocked() && item.getNumServings() > 0){
				adjustConstraints(item, constraints);
				mealItems.remove(item);
				finalItems.add(item);
			}
		}
		//2. TODO: Refactor out the fitness function and instead use a Factory class to build one dynamically using the contents of the Constraints
		
		
		//3. TODO: Send a request over to the main algorithm using the adjusted list of MealItems and the adjusted Constraints
		
		
		//4. Iterate over the MealItems, identify all items with a non-zero quantity, and move them to the final list
		for(MealItem item : mealItems){
			if(item.getNumServings() > 0){
				finalItems.add(item);
			}
		}
		//5. TODO: Serialize the finalItems list into JSON format before returning
		return finalItems;
	}
	
	//Helper method for use with the first step of the Meal Planner request handling - for each field of the Constraints, if that field
	// has a positive value, reduce it by the number of servings specified for the Locked item multiplied by the Locked item's corresponding field value
	private void adjustConstraints(MealItem mealItem, Constraints constraints){
		if(constraints.getMinCals() > 0) constraints.setMinCals(Math.max(constraints.getMinCals() - (mealItem.getNumServings() * mealItem.getFoodItem().getCalPerServing()), 0));
		if(constraints.getMaxCals() > 0) constraints.setMaxCals(Math.max(constraints.getMaxCals() - (mealItem.getNumServings() * mealItem.getFoodItem().getCalPerServing()), 0));
		if(constraints.getMinCarbs() > 0) constraints.setMinCarbs(Math.max(constraints.getMinCarbs() - (mealItem.getNumServings() * mealItem.getFoodItem().getCalsCarbPerServing()), 0));
		if(constraints.getMaxCarbs() > 0) constraints.setMaxCarbs(Math.max(constraints.getMaxCarbs() - (mealItem.getNumServings() * mealItem.getFoodItem().getCalsCarbPerServing()), 0));
		if(constraints.getMinProt() > 0) constraints.setMinProt(Math.max(constraints.getMinProt() - (mealItem.getNumServings() * mealItem.getFoodItem().getCalsProtPerServing()), 0));
		if(constraints.getMaxProt() > 0) constraints.setMaxProt(Math.max(constraints.getMaxProt() - (mealItem.getNumServings() * mealItem.getFoodItem().getCalsProtPerServing()), 0));
		if(constraints.getMinFat() > 0) constraints.setMinFat(Math.max(constraints.getMinFat() - (mealItem.getNumServings() * mealItem.getFoodItem().getCalsFatPerServing()), 0));
		if(constraints.getMaxFat() > 0) constraints.setMaxFat(Math.max(constraints.getMaxFat() - (mealItem.getNumServings() * mealItem.getFoodItem().getCalsFatPerServing()), 0));
	}
}
