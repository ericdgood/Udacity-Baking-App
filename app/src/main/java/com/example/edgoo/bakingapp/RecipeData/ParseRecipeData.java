package com.example.edgoo.bakingapp.RecipeData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ParseRecipeData {

    public static Recipes[] ParseRecipeJson(String jsonresponse) throws JSONException {

//          CREATES JSON OBJECT WITH JSON STRING
        JSONArray recipesArray = new JSONArray(jsonresponse);
        Recipes[] recipe = new Recipes[recipesArray.length()];

        for (int i = 0; i < recipesArray.length(); i++) {
            recipe[i] = new Recipes();
            JSONObject currentRecipe = recipesArray.getJSONObject(i);

            recipe[i].setRecipeItemName(currentRecipe.getString("name"));
            recipe[i].setRecipeServings(currentRecipe.getString("servings"));

        }
        return recipe;
    }

}
