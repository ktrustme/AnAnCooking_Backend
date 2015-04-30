package com.anan.anancooking.model;

/**
 * Created by kuoxin on 4/13/15.
 */
public interface StepInterface {
    void setName(String name);
    void setDescription(String description);
    void setImageByteCode(byte[] imageByteCode);
    String getName();
    String getDescription();
    byte[] getImageByteCode();

}
