package com.anan.anancooking.model;

/**
 * Created by zhouyangdi on 4/29/15.
 */
import java.util.*;


public class RecipeImplementation implements RecipeInterface {
	String recipeID = null;
    List<String> ingredients = null;
    int time = 0;
    String description = null;
    String cook = null;
    String name = null;
    List<Step> steps = new ArrayList<Step>();
    byte[] previewByteCode;

    


    @Override
    public RecipeInterface setSteps(List<Step> list) {
    	this.steps = list;
        return null;
    }

    @Override
    public RecipeInterface setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public RecipeInterface setPreviewByteCode(byte[] previewByteCode) {
        this.previewByteCode = previewByteCode;
        return this;
    }


    @Override
    public RecipeInterface setTime(int time) {
        this.time = time;
        return this;
    }

    @Override
    public RecipeInterface setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    @Override
    public List<Step> getSteps() {
        return this.steps;
    }

    @Override
    public List<String> getIngredients() {
        return this.ingredients;
    }

    @Override
    public int getTime() {
        return this.time;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public byte[] getPreviewByteCode() {
        return this.previewByteCode;
    }

    @Override
    public String getCook() {
        return this.cook;
    }

    @Override
    public RecipeInterface setCook(String cook) {
        this.cook = cook;
        return this;
    }

	@Override
	public String getName() {
		return this.name;
		
	}

	@Override
	public RecipeInterface setName(String name) {
		this.name = name;
		return this;
	}
	
	public String getRecipeID() {
		return this.recipeID;
	}
	
	public void setRecipeID(String recipeID) {
		this.recipeID = recipeID;
	}



}

