package com.anan.anancooking.model;

/**
 * Created by zhouyangdi on 4/29/15.
 */

  import java.util.ArrayList;
  import java.util.HashSet;
  import java.util.Random;


public class RecipePreviewImplementation implements RecipePreviewInterface {
    String recipeID = null;
    String name = null;
    int time = 0;
    String ingredients = null;
    String cook = null;
    byte[] previewByteCode = null;

    public String getRecipeID(){
    	return recipeID;
    }
    public void setRecipeID(String recipeID){
    	this.recipeID=recipeID;
    }
    
    
    public RecipePreviewImplementation(){
    	super();
    }
    public RecipePreviewImplementation(String id, String name, int time, String cook, String ingredients, byte[] image) {
        this.recipeID = id;
        this.time = time;
        this.name = name;
        this.ingredients = ingredients;
        this.cook = cook;
        this.previewByteCode = image;
    }

    @Override
    public RecipePreviewInterface setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public RecipePreviewInterface setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    @Override
    public RecipePreviewInterface setTime(int time) {
        this.time = time;
        return this;
    }

    @Override
    public RecipePreviewInterface setPreviewByteCode(byte[] previewByteCode) {
        this.previewByteCode = previewByteCode;
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getIngredients() {
        return this.ingredients;
    }

    @Override
    public int getTime() {
        return this.time;
    }

    @Override
    public byte[] getPreviewByteCode() {
        return this.previewByteCode;
    }

}

