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
            ArrayList<String> ingredientsQtyList = new ArrayList<String>();
            ArrayList<String> ingredientsMeasureList = new ArrayList<String>();
            for (int j = 0; j < ingredientsArray.length(); j++) {

                JSONObject currentIngredients = ingredientsArray.getJSONObject(j);
                ingredientsList.add(currentIngredients.getString("ingredient"));
                ingredientsQtyList.add(currentIngredients.getString("quantity"));
                ingredientsMeasureList.add(currentIngredients.getString("measure"));
            }

            recipe[i].setRecipeIngredient(ingredientsList);
            recipe[i].setIngredientQuantity(ingredientsQtyList);
            recipe[i].setIngredientMeasure(ingredientsMeasureList);

//            PARSES INGREDIENTS ARRAY
            JSONArray stepsArray = currentRecipe.getJSONArray("steps");
            ArrayList<String> stepId = new ArrayList<String>();
            ArrayList<String> shortDescription = new ArrayList<String>();
            ArrayList<String> description = new ArrayList<String>();
            ArrayList<String> videoURL = new ArrayList<String>();
            ArrayList<String> thumbnailURL = new ArrayList<String>();
            for (int j = 0; j < stepsArray.length(); j++) {

                JSONObject currentStep = stepsArray.getJSONObject(j);
                stepId.add(currentStep.getString("id"));
                shortDescription.add(currentStep.getString("shortDescription"));
                description.add(currentStep.getString("description"));
                videoURL.add(currentStep.getString("videoURL"));
                thumbnailURL.add(currentStep.getString("thumbnailURL"));
            }

            recipe[i].setStepId(stepId);
            recipe[i].setShortDescription(shortDescription);
            recipe[i].setDescription(description);
            recipe[i].setVideoUrl(videoURL);
            recipe[i].setThumbnilUrl(thumbnailURL);
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
