package com.example.edgoo.bakingapp.RecipeData;

import android.os.Parcel;
import android.os.Parcelable;

public class Recipes implements Parcelable {

    private String recipeItem;
    private String recipeServings;

    private Recipes(Parcel in) {
        recipeItem = in.readString();
        recipeServings = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recipeItem);
        dest.writeString(recipeServings);
    }

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

    public String getRecipeItem() {
        return recipeItem;
    }

    public void setRecipeItem(String recipeItem) {
        this.recipeItem = recipeItem;
    }

    public String getRecipeServings() {
        return recipeServings;
    }

    public void setRecipeServings(String recipeServings) {
        this.recipeServings = recipeServings;
    }
}
