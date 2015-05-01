package com.anan.anancooking.model;

import java.util.*;

/**
 * Created by zhouyangdi on 4/29/15.
 */
public interface RecipeInterface {
    
	ArrayList<Step> getSteps();
    List<String> getIngredients();
    int getTime();
    String getDescription();
    byte[] getPreviewByteCode();
    String getCook();
    String getName();
    
    RecipeInterface setName(String name);
    RecipeInterface setTime(int time);
    RecipeInterface setIngredients(List<String> ingredients);
    RecipeInterface setDescription(String description);
    RecipeInterface setPreviewByteCode(byte[] previewByteCode);
    RecipeInterface setSteps(ArrayList<Step> steps);
    RecipeInterface setCook(String cook);
}
