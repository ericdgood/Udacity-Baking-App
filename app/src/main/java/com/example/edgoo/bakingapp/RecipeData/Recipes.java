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
    private ArrayList ingredientQuantity;
    private ArrayList ingredientMeasure;
    //    STEPS ITEMS
    private ArrayList stepId;
    private ArrayList shortDescription;
    private ArrayList description;
    private ArrayList videoUrl;
    private ArrayList thumbnilUrl;

    private Recipes(Parcel in) {
        recipeId = in.readString();
        recipeItemName = in.readString();
        recipeServings = in.readString();
        recipeIngredient = in.readArrayList(getClass().getClassLoader());
        ingredientQuantity = in.readArrayList(getClass().getClassLoader());
        ingredientMeasure = in.readArrayList(getClass().getClassLoader());
        stepId = in.readArrayList(getClass().getClassLoader());
        shortDescription = in.readArrayList(getClass().getClassLoader());
        description = in.readArrayList(getClass().getClassLoader());
        videoUrl = in.readArrayList(getClass().getClassLoader());
        thumbnilUrl = in.readArrayList(getClass().getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recipeId);
        dest.writeString(recipeItemName);
        dest.writeString(recipeServings);
        dest.writeArray(new ArrayList[]{recipeIngredient});
        dest.writeArray(new ArrayList[]{ingredientQuantity});
        dest.writeArray(new ArrayList[]{ingredientMeasure});
        dest.writeArray(new ArrayList[]{stepId});
        dest.writeArray(new ArrayList[]{shortDescription});
        dest.writeArray(new ArrayList[]{description});
        dest.writeArray(new ArrayList[]{videoUrl});
        dest.writeArray(new ArrayList[]{thumbnilUrl});
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

    public ArrayList<String> getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(ArrayList<String> ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    public ArrayList<String> getIngredientMeasure() {
        return ingredientMeasure;
    }

    public void setIngredientMeasure(ArrayList<String> ingredientMeasure) {
        this.ingredientMeasure = ingredientMeasure;
    }


    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }



    public ArrayList getStepId() {
        return stepId;
    }

    public void setStepId(ArrayList stepId) {
        this.stepId = stepId;
    }

    public ArrayList getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(ArrayList shortDescription) {
        this.shortDescription = shortDescription;
    }

    public ArrayList getDescription() {
        return description;
    }

    public void setDescription(ArrayList description) {
        this.description = description;
    }

    public ArrayList getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(ArrayList videoUrl) {
        this.videoUrl = videoUrl;
    }

    public ArrayList getThumbnilUrl() {
        return thumbnilUrl;
    }

    public void setThumbnilUrl(ArrayList thumbnilUrl) {
        this.thumbnilUrl = thumbnilUrl;
    }

}
