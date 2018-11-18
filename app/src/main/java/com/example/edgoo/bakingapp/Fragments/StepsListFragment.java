package com.example.edgoo.bakingapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.edgoo.bakingapp.R;
import com.example.edgoo.bakingapp.RecipeAdapter;
import com.example.edgoo.bakingapp.RecipeStepList;
import com.example.edgoo.bakingapp.RecipeStepsListAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class StepsListFragment extends Fragment {

    private Context mContext;


    public void StepsList(Context context) {
        this.mContext = context;
    }

    public StepsListFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        final ArrayList mShortDescription = RecipeAdapter.shortDescrip;
        final ArrayList mStep_id = RecipeAdapter.step_id;

        View rootView = inflater.inflate(R.layout.recyclerview, container, false);
        RecyclerView recyclerStepView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        //        RECYCLERVIEW FOR STEPS
        recyclerStepView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RecipeStepsListAdapter mRecipeStepsListAdapter = new RecipeStepsListAdapter(mContext, mStep_id, mShortDescription);
        recyclerStepView.setAdapter(mRecipeStepsListAdapter);

        // Return the rootView
        return rootView;
    }
}
