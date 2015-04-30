package com.anan.anancooking.server.database;

import com.anan.anancooking.model.RecipePreviewImplementation;

import java.util.List;

/**
 * Created by zhouyangdi on 4/29/15.
 */
public interface FetchPreview {
    public List<RecipePreviewImplementation> searchForPreview(List<String> ingredients);
    
    public List<RecipePreviewImplementation> recommendPreview();
}
