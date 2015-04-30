package com.anan.anancooking.model;

/**
 * Created by zhouyangdi on 4/29/15.
 */
import java.util.*;


public class RecipeImplementation implements RecipeInterface {

    List<String> ingredients = null;
    int time = 0;
    String description = null;
    String cook = null;
    ArrayList<Step> steps = new ArrayList<Step>();
    byte[] previewByteCode;




    @Override
    public RecipeInterface setSteps(ArrayList<Step> steps) {
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
    public java.util.ArrayList<Step> getSteps() {
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

}

