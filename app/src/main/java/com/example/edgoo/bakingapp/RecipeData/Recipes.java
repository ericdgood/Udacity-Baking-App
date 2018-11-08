package com.example.edgoo.bakingapp.RecipeData;

import android.os.Parcel;
import android.os.Parcelable;

public class Recipes implements Parcelable {

    private String recipeItemName;
    private String recipeServings;

    private Recipes(Parcel in) {
        recipeItemName = in.readString();
        recipeServings = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recipeItemName);
        dest.writeString(recipeServings);
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
}
