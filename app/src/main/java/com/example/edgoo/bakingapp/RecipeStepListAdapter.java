package com.example.edgoo.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edgoo.bakingapp.RecipeData.Recipes;
import com.squareup.picasso.Picasso;

public class RecipeStepListAdapter extends RecyclerView.Adapter<RecipeStepListAdapter.ViewHolder> {

    private Recipes[] mRecipes;

    RecipeStepListAdapter(Recipes[] mRecipes) {
        this.mRecipes = mRecipes;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.ingredient_name);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeStepListAdapter.ViewHolder viewHolder, int i) {
        viewHolder.ingredientName.setText(mRecipes[i].getRecipeItemName());
        }

    @NonNull
    @Override
    public RecipeStepListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_ingredients_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (null == mRecipes) return 0;
        return mRecipes.length;
    }
}
