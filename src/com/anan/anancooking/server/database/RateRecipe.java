package com.anan.anancooking.server.database;

public interface RateRecipe {

	
	public void setRating(String recipeID, int rate, int number);
	
	public int fetchRating(String recipeID);
	
	public int fetchNumberOfPeople(String recipeID);
	
}
