package com.anan.anancooking.server.database;

import com.anan.anancooking.model.RecipeImplementation;

public interface FetchRecipe {
	public RecipeImplementation fetchRecipe(String recipeID);
}
