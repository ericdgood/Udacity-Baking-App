package com.example.edgoo.bakingapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class RecipeStepsListAdapter extends RecyclerView.Adapter<RecipeStepsListAdapter.ViewHolder> {

    private ArrayList mStepId;
    private ArrayList mShortDescription;

    RecipeStepsListAdapter(ArrayList stepId, ArrayList shortDescription) {
        this.mStepId = stepId;
        this.mShortDescription = shortDescription;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView stepId;
        TextView shortDescription;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            stepId = itemView.findViewById(R.id.step_id);
            shortDescription = itemView.findViewById(R.id.short_description);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeStepsListAdapter.ViewHolder viewHolder, int i) {
        viewHolder.stepId.setText((CharSequence)mStepId.get(i));
        viewHolder.shortDescription.setText((CharSequence) mShortDescription.get(i));
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

