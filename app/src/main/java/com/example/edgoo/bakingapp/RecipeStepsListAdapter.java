package com.example.edgoo.bakingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepsListAdapter extends RecyclerView.Adapter<RecipeStepsListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList mStepId;
    private ArrayList mShortDescription;

    public RecipeStepsListAdapter(Context context, ArrayList stepId, ArrayList shortDescription) {
        this.mContext = context;
        this.mStepId = stepId;
        this.mShortDescription = shortDescription;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.step_id) TextView stepId;
        @BindView(R.id.short_description) TextView shortDescription;
        @BindView(R.id.recipe_step_list_layout) ConstraintLayout recipeStepListLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

        @Override
        public void onBindViewHolder(@NonNull RecipeStepsListAdapter.ViewHolder viewHolder, final int i) {
            viewHolder.stepId.setText((CharSequence) mStepId.get(i));
            viewHolder.shortDescription.setText((CharSequence) mShortDescription.get(i));

            viewHolder.recipeStepListLayout.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, RecipeStep.class);
                CharSequence step_id = ((CharSequence) mStepId.get(i));
                intent.putExtra("step_id", step_id);
                mContext.startActivity(intent);
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

