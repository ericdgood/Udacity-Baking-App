package com.example.edgoo.bakingapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.edgoo.bakingapp.RecipeData.Recipes;
import com.squareup.picasso.Picasso;

public class RecipeAdapterTab extends BaseAdapter {


    private Recipes[] recipes;
    private final MainActivity mainA;

    RecipeAdapterTab(Recipes[] mRecipes, MainActivity mainActivity) {
        recipes = mRecipes;
        mainA = mainActivity;
    }

    @Override
    public int getCount() {
        if (null == recipes) return 0;
        return recipes.length;
    }

    @Override
    public Object getItem(int position) {
        if (recipes == null || recipes.length == 0) {
            return null;
        }
        return recipes[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView movieTitle;

        if (convertView == null) {
            movieTitle = new ImageView(mainA);
            movieTitle.setAdjustViewBounds(true);
        } else {
            movieTitle = (ImageView) convertView;
        }
        Picasso.with(mainA)
                .load(R.drawable.cheesecake)
                .into(movieTitle);

        return movieTitle;
    }

    public void setRecipeData (Recipes[]recipeData){
        this.recipes = recipeData;
        notifyDataSetChanged();
    }
}
