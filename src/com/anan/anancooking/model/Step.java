package com.anan.anancooking.model;

/**
 * Created by zhouyangdi on 4/29/15.
 */

public class Step {
    private String recipeID;
    private int stepID;
    private String description;
    private byte[] image;
//    private ImageView imageView;
//
//
//    public Step(String description, ImageView imageView, int stepID){
//        this.stepID = stepID;
//        this.description = description;
//        this.imageView = imageView;
//    }


    public Step(String recipeID, int stepID, String des, byte[] image) {
        this.recipeID = recipeID;
        this.stepID = stepID;
        this.image = image;
        this.description = des;
    }

    public Step(){

    }

    public void setStepID(int stepID){
        this.stepID = stepID;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setBytes(byte[] image) {
        this.image = image;
    }

    public void setRecipeID(String id) {
        this.recipeID = id;
    }

//    public void setImageView(ImageView imageView){
//        this.imageView = imageView;
//    }
//
//    public String getDescription(){
//        return this.description;
//    }
//
//    public ImageView getImageView(){
//        return this.imageView;
//    }

    public String getDes() {
        return this.description;
    }

    public byte[] getBytes() {
        return this.image;
    }

    public String getRecipeID() {
        return this.recipeID;
    }
    public int getStepID(){ return this.stepID;}
}


