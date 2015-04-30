package com.anan.anancooking.server.database;

import java.util.HashSet;

/**
 * Created by zhouyangdi on 4/29/15.
 */
public class RecipeIDGenerater {
    static HashSet<String> ids = new HashSet<String>();
    static String current = "1000";

    public static String createID() {
        String id;
        do {
            id = "" + (Integer.parseInt(current) + 1);
        }
        while (ids.contains(ids));
        ids.add(id);
        return id;
    }
}
