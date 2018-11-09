package com.example.edgoo.bakingapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeIngredientsListAdapter extends RecyclerView.Adapter<RecipeIngredientsListAdapter.ViewHolder> {

    private ArrayList mIngredientsList;
    private ArrayList mIngredientsQtyList;
    private ArrayList mIngredientsMeasureList;

    RecipeIngredientsListAdapter(ArrayList ingredientsList, ArrayList ingredientsQty, ArrayList ingredientsMeasure) {
        this.mIngredientsList = ingredientsList;
        this.mIngredientsQtyList = ingredientsQty;
        this.mIngredientsMeasureList = ingredientsMeasure;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientName;
        TextView ingredientQty;
        TextView ingredientMeasure;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.ingredient_name);
            ingredientQty = itemView.findViewById(R.id.ingredient_qty);
            ingredientMeasure = itemView.findViewById(R.id.ingredient_measure);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeIngredientsListAdapter.ViewHolder viewHolder, int i) {
        viewHolder.ingredientName.setText((CharSequence) mIngredientsList.get(i));
        viewHolder.ingredientQty.setText((CharSequence) mIngredientsQtyList.get(i));
        viewHolder.ingredientMeasure.setText((CharSequence) mIngredientsMeasureList.get(i));
        }

    @NonNull
    @Override
    public RecipeIngredientsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_ingredients_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (null == mIngredientsList) return 0;
        return mIngredientsList.size();
    }
}
