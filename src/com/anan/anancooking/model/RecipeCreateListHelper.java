package com.anan.anancooking.model;

import java.util.ArrayList;

/**
 * Created by zihsiangsyu on 4/17/15.
 */
public class RecipeCreateListHelper {
    private static ArrayList<Step> steps = new ArrayList<Step>();

    public RecipeCreateListHelper(){

    }

    public ArrayList<Step> getArrayList(){
        return this.steps;
    }

    public void addStepByIdx(int idx, Step s ){
        this.steps.add(idx,s);
    }

    public void setStepByIdx(int idx, Step s){
        this.steps.set(idx,s);
    }

    public Step getStepByIdx(int idx){
        return steps.get(idx);
    }

    public void delStepByIdx(int idx){
        steps.remove(idx);
    }

    public void emptySteps(){
        steps.clear();
    }

}
