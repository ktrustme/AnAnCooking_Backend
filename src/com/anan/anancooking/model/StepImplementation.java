package com.anan.anancooking.model;

/**
 * Created by kuoxin on 4/13/15.
 */
public class StepImplementation implements StepInterface {
    String name;
    String description;
    byte[] imageByteCode;


    public StepImplementation(String name, String description, byte[] imageByteCode){
        setName(name);
        setDescription(description);
        setImageByteCode(imageByteCode);
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setImageByteCode(byte[] imageByteCode) {
        this.imageByteCode = imageByteCode;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public byte[] getImageByteCode() {
        return imageByteCode;
    }
}
