package com.example.edgoo.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.edgoo.bakingapp.RecipeData.Recipes;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

class RecipeStepsListAdapter extends RecyclerView.Adapter<RecipeStepsListAdapter.ViewHolder> {

    private Context mContext;
    private final ArrayList mDescription;
    private ArrayList mStepId;
    private ArrayList mShortDescription;

    RecipeStepsListAdapter(RecipeStepList recipeStepList, ArrayList stepId, ArrayList shortDescription, ArrayList description) {
        this.mContext = recipeStepList;
        this.mStepId = stepId;
        this.mShortDescription = shortDescription;
        this.mDescription = description;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView stepId;
        TextView shortDescription;
        ConstraintLayout recipeStepListLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            stepId = itemView.findViewById(R.id.step_id);
            shortDescription = itemView.findViewById(R.id.short_description);
            recipeStepListLayout = itemView.findViewById(R.id.recipe_step_list_layout);
        }
    }

        @Override
        public void onBindViewHolder(@NonNull RecipeStepsListAdapter.ViewHolder viewHolder, final int i) {
            viewHolder.stepId.setText((CharSequence) mStepId.get(i));
            viewHolder.shortDescription.setText((CharSequence) mShortDescription.get(i));

            viewHolder.recipeStepListLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String stepDescription = (String) mDescription.get(i);
                    Intent intent = new Intent(mContext, RecipeStep.class);
                    intent.putExtra("description", stepDescription);
                    mContext.startActivity(intent);
                }
            });
        }


    @NonNull
    @Override
    public RecipeStepsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_steps_list_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (null == mStepId) return 0;
        return mStepId.size();
    }
}

