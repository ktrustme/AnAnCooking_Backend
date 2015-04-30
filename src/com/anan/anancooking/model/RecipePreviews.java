package com.anan.anancooking.model;

//import android.content.Context;

//import com.anan.anancooking.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by kuoxin on 4/16/15.
 */
public class RecipePreviews {

    static final RecipePreviewInterface[] recipePreviews={
            new RecipePreviewImplementation().setName("Fried Chicken").setTime(10).setIngredients("Chicken;Pepper;"),
            new RecipePreviewImplementation().setName("Fried Egg").setTime(3).setIngredients("Egg;Salt"),
            new RecipePreviewImplementation().setName("Fried Rice").setTime(10).setIngredients("Rice;Egg;Pepper;Onion"),
            new RecipePreviewImplementation().setName("Spanish Pella").setTime(20).setIngredients("Rice;Chicken;Egg"),
            new RecipePreviewImplementation().setName("Chicken Leg").setTime(15).setIngredients("Chicken Leg;Egg;Pepper"),
            new RecipePreviewImplementation().setName("Lamian").setTime(10).setIngredients("Noodles;Tomato;Egg"),
    };

    {

    }


    public static ArrayList<RecipePreviewInterface> asList() {
        ArrayList<RecipePreviewInterface> items = new ArrayList<RecipePreviewInterface>();
        for (int i = 0, z = recipePreviews.length; i < z; i++) {
            items.add(recipePreviews[i]);
        }
        return items;
    }

    /**
     * Return a list of random cheeses.
     *
     * @param count the amount of cheeses to return.
     */
    public static ArrayList<RecipePreviewInterface> randomList(int count) {
        Random random = new Random();
        HashSet<RecipePreviewInterface> items = new HashSet<RecipePreviewInterface>();

        // Make sure that don't infinity loop
        count = Math.min(count, recipePreviews.length);

        while (items.size() < count) {
            items.add(recipePreviews[random.nextInt(recipePreviews.length)]);
        }

        return new ArrayList<RecipePreviewInterface>(items);
    }
}
