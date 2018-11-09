package com.example.edgoo.bakingapp.RecipeData;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Recipes implements Parcelable {

    //    RECIPE ITEMS
    private String recipeId;
    private String recipeItemName;
    private String recipeServings;
//    INGREDIENTS ITEMS
    private ArrayList recipeIngredient;
    private String ingredientQuantity;
    private String ingredientMeasure;
//    STEPS ITEMS
    private String stepId;
    private String shortDescription;
    private String description;
    private String videoUrl;
    private String thumbnilUrl;

    private Recipes(Parcel in) {
        recipeId = in.readString();
        recipeItemName = in.readString();
        recipeServings = in.readString();
        recipeIngredient = in.readArrayList(getClass().getClassLoader());
        ingredientQuantity = in.readString();
        ingredientMeasure = in.readString();
        stepId = in.readString();
        shortDescription = in.readString();
        description = in.readString();
        videoUrl = in.readString();
        thumbnilUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recipeId);
        dest.writeString(recipeItemName);
        dest.writeString(recipeServings);
        dest.writeArray(new ArrayList[]{recipeIngredient});
        dest.writeString(ingredientQuantity);
        dest.writeString(ingredientMeasure);
        dest.writeString(stepId);
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoUrl);
        dest.writeString(thumbnilUrl);
    }

    Recipes(){    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Recipes> CREATOR = new Creator<Recipes>() {
        @Override
        public Recipes createFromParcel(Parcel in) {
            return new Recipes(in);
        }

        @Override
        public Recipes[] newArray(int size) {
            return new Recipes[size];
        }
    };

    public String getRecipeItemName() {
        return recipeItemName;
    }

    public void setRecipeItemName(String recipeItemName) {
        this.recipeItemName = recipeItemName;
    }

    public String getRecipeServings() {
        return recipeServings;
    }

    public void setRecipeServings(String recipeServings) {
        this.recipeServings = recipeServings;
    }


    public ArrayList<String> getRecipeIngredient() {
        return recipeIngredient;
    }

    public void setRecipeIngredient(ArrayList<String> recipeIngredient) {
        this.recipeIngredient = recipeIngredient;
    }

    public String getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(String ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    public String getIngredientMeasure() {
        return ingredientMeasure;
    }

    public void setIngredientMeasure(String ingredientMeasure) {
        this.ingredientMeasure = ingredientMeasure;
    }


    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnilUrl() {
        return thumbnilUrl;
    }

    public void setThumbnilUrl(String thumbnilUrl) {
        this.thumbnilUrl = thumbnilUrl;
    }

}
