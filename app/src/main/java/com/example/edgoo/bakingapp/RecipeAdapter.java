package com.example.edgoo.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.edgoo.bakingapp.RecipeData.Recipes;

import org.w3c.dom.Text;

import butterknife.BindView;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private Recipes[] mRecipes;
    private Context mContext;

    RecipeAdapter(Recipes[] mRecipes, Context mContext) {
        this.mRecipes = mRecipes;
        this.mContext = mContext;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemServings;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.mobile_item_name);
            itemServings = itemView.findViewById(R.id.mobile_item_servings);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder viewHolder, int i) {
        viewHolder.itemName.setText(mRecipes[i].getRecipeItemName());
        viewHolder.itemServings.setText(mRecipes[i].getRecipeServings());
    }

    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mobile_main_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (null == mRecipes) return 0;
        return mRecipes.length;
    }

    public void setRecipeData(Recipes[] recipeData) {
        this.mRecipes = recipeData;
        notifyDataSetChanged();
    }
}
