package com.example.edgoo.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edgoo.bakingapp.RecipeData.Recipes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private Recipes[] mRecipes;
    private final Context mContext;
    public static ArrayList description;
    public static ArrayList videoUrl;
    public static ArrayList shortDescrip;
    public static String recipeName;
    public static ArrayList step_id;
    public static ArrayList ingredients;
    public static ArrayList ingredients_qty;
    public static ArrayList ingredients_measure;

    RecipeAdapter(Recipes[] mRecipes, Context mContext) {
        this.mRecipes = mRecipes;
        this.mContext = mContext;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mobile_item_name) TextView itemName;
        @BindView(R.id.mobile_item_servings) TextView itemServings;
        @BindView(R.id.mobile_item_image) ImageView recipeImage;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.itemName.setText(mRecipes[i].getRecipeItemName());
        viewHolder.itemServings.setText(mRecipes[i].getRecipeServings());

        viewHolder.recipeImage.setOnClickListener(view -> {

            Intent intent = new Intent(mContext, RecipeStepList.class);
            mContext.startActivity(intent);

            description = (mRecipes[i].getDescription());
            videoUrl = (mRecipes[i].getVideoUrl());
            recipeName = (mRecipes[i].getRecipeItemName());
            shortDescrip = (mRecipes[i].getShortDescription());
            step_id = (mRecipes[i].getStepId());
            ingredients = (mRecipes[i].getRecipeIngredient());
            ingredients_qty = (mRecipes[i].getIngredientQuantity());
            ingredients_measure = (mRecipes[i].getIngredientMeasure());
        });

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
        }
    }

        @NonNull
        @Override
        public RecipeAdapter.ViewHolder onCreateViewHolder (@NonNull ViewGroup viewGroup,int i){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mobile_main_layout, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public int getItemCount () {
            if (null == mRecipes) return 0;
            return mRecipes.length;
        }

        public void setRecipeData (Recipes[]recipeData){
            this.mRecipes = recipeData;
            notifyDataSetChanged();
        }
    }
