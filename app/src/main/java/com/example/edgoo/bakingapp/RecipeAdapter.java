package com.example.edgoo.bakingapp;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edgoo.bakingapp.RecipeData.Recipes;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.BindView;

import static android.content.ContentValues.TAG;

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
        ImageView recipeImage;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.mobile_item_name);
            itemServings = itemView.findViewById(R.id.mobile_item_servings);
            recipeImage = itemView.findViewById(R.id.mobile_item_image);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder viewHolder, int i) {
        viewHolder.itemName.setText(mRecipes[i].getRecipeItemName());
        viewHolder.itemServings.setText(mRecipes[i].getRecipeServings());
        switch (mRecipes[i].getRecipeId()) {
            case "1":
                Picasso.with(mContext).load(R.drawable.nutellapie).into(viewHolder.recipeImage);
                break;
            case "2":
                Picasso.with(mContext).load(R.drawable.brownies).into(viewHolder.recipeImage);
                break;
            case "3":
                Picasso.with(mContext).load(R.drawable.yellowcake).into(viewHolder.recipeImage);
                break;
            case "4":
                Picasso.with(mContext).load(R.drawable.cheesecake).into(viewHolder.recipeImage);
                break;
        }
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
