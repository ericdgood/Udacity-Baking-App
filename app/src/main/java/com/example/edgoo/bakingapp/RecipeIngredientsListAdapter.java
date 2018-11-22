package com.example.edgoo.bakingapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeIngredientsListAdapter extends RecyclerView.Adapter<RecipeIngredientsListAdapter.ViewHolder> {

    private final ArrayList ingredients = RecipeAdapter.ingredients;
    private final ArrayList ingredientsQty = RecipeAdapter.ingredients_qty;
    private final ArrayList ingredientsMeasure = RecipeAdapter.ingredients_measure;

    RecipeIngredientsListAdapter() {
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ingredient_name) TextView ingredientName;
        @BindView(R.id.ingredient_qty) TextView ingredientQty;
        @BindView(R.id.ingredient_measure) TextView ingredientMeasure;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeIngredientsListAdapter.ViewHolder viewHolder, int i) {
        viewHolder.ingredientName.setText((CharSequence) ingredients.get(i));
        viewHolder.ingredientQty.setText((CharSequence) ingredientsQty.get(i));
        viewHolder.ingredientMeasure.setText((CharSequence) ingredientsMeasure.get(i));
        }

    @NonNull
    @Override
    public RecipeIngredientsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_ingredients_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (null == ingredients) return 0;
        return ingredients.size();
    }
}
