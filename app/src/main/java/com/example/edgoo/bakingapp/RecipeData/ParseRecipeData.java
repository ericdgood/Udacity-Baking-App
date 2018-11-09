package com.example.edgoo.bakingapp.RecipeData;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

class ParseRecipeData {

    public static Recipes[] ParseRecipeJson(String jsonresponse) throws JSONException {

//          CREATES JSON OBJECT WITH JSON STRING
        JSONArray recipesArray = new JSONArray(jsonresponse);
        Recipes[] recipe = new Recipes[recipesArray.length()];

        for (int i = 0; i < recipesArray.length(); i++) {
            recipe[i] = new Recipes();
            JSONObject currentRecipe = recipesArray.getJSONObject(i);

            recipe[i].setRecipeId(currentRecipe.getString("id"));
            recipe[i].setRecipeItemName(currentRecipe.getString("name"));
            recipe[i].setRecipeServings(currentRecipe.getString("servings"));

//            PARSES INGREDIENTS ARRAY
            JSONArray ingredientsArray = currentRecipe.getJSONArray("ingredients");
            ArrayList<String> ingredientsList = new ArrayList<String>();
            for (int j = 0; j < ingredientsArray.length(); j++) {

                JSONObject currentIngredients = ingredientsArray.getJSONObject(j);
                ingredientsList.add(currentIngredients.getString("ingredient"));
            }

            recipe[i].setRecipeIngredient(ingredientsList);

//            PARSES STEPS ARRAY
            JSONArray stepsArray = currentRecipe.getJSONArray("steps");
            for (int k = 0; k < stepsArray.length(); k++) {

                JSONObject currentStep = stepsArray.getJSONObject(i);
                recipe[i].setStepId(currentStep.getString("id"));
                recipe[i].setShortDescription(currentStep.getString("shortDescription"));
                recipe[i].setDescription(currentStep.getString("description"));
                recipe[i].setVideoUrl(currentStep.getString("videoURL"));
                recipe[i].setThumbnilUrl(currentStep.getString("thumbnailURL"));
            }
        }
        return recipe;
    }

//    [
//      {
//        "id": 1,
//            "name": "Nutella Pie",

//            "ingredients":
//     [
//        {
//            "quantity": 2,
//                "measure": "CUP",
//                "ingredient": "Graham Cracker crumbs"
//        },
//    ],
//        "steps":
//     [
//        {
//            "id": 0,
//                "shortDescription": "Recipe Introduction",
//                "description": "Recipe Introduction",
//                "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4",
//                "thumbnailURL": ""
//        },
//    ],
//        "servings": 8,
//            "image": ""
//    },

}
