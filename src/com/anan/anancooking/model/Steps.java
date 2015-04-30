package com.anan.anancooking.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by kuoxin on 4/4/15.
 */
public class Steps {
    static final StepInterface[] steps={
            new StepImplementation("Whip The Egg!","OK First we need to whip the egg, right? If you don't have an egg beater, either chopsticks or a spoon would be fine...",null),
            new StepImplementation("Stir The Rice!","Now let's prepare the rice. Put some oil and keep stiring the rice in a big bow,",null),
            new StepImplementation("Cut The Evil Onion!","Chop it! Cut it! Kill it! Let's go buddy! Take care of your hand!",null),
            new StepImplementation("You Are Almost Done!","Now it's the final time! Put them together! Stir it! Shake it all!",null),
            new StepImplementation("That's It! You Make It!","OMG! Look at that! !%#%@$#%@$#@",null)
    };


    public static ArrayList<StepInterface> asList() {
        ArrayList<StepInterface> items = new ArrayList<StepInterface>();
        for (int i = 0, z = steps.length ; i < z ; i++) {
            items.add(steps[i]);
        }
        return items;
    }
}
