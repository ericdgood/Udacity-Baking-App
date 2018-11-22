package com.example.edgoo.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.edgoo.bakingapp.Fragments.StepsListFragment;
import com.example.edgoo.bakingapp.widget.WidgetUpdateService;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipeStepList extends AppCompatActivity {

    @BindView(R.id.recycler_view_ingred) RecyclerView recyclerView;
    private ArrayList mIngredients = RecipeAdapter.ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobile_recipe_step_list);

        setTitle(RecipeAdapter.recipeName);
        startWidgetService();

//        TABLET LAYOUT
        if (findViewById(R.id.mobile_step_list) == null) {

            Intent intent = new Intent(this, RecipeStep.class);
            this.startActivity(intent);

        } else {

            ButterKnife.bind(this);
//        RECYCLERVIEW FOR INGREDIENTS
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            RecipeIngredientsListAdapter mRecipeIngredientsListAdapter = new RecipeIngredientsListAdapter();
            recyclerView.setAdapter(mRecipeIngredientsListAdapter);

            // CREATES A NEW FRAGMENT FOR STEPS LIST
            StepsListFragment stepsFragment = new StepsListFragment();

            // Add the fragment to its container using a FragmentManager and a Transaction
            FragmentManager fragmentManager = getSupportFragmentManager();

            stepsFragment.StepsList(this);
            fragmentManager.beginTransaction()
                    .add(R.id.recipe_step_list_frag, stepsFragment)
                    .commit();

        }
    }

    void startWidgetService()
    {
        Intent i = new Intent(this, WidgetUpdateService.class);
//        Bundle bundle = new Bundle();
//        bundle.putCharSequenceArrayList(MainActivity.INGREDIENTS, mIngredients);
//        i.putExtra(MainActivity.BUNDLE, bundle);
//        i.setAction(WidgetUpdateService.WIDGET_UPDATE_ACTION);
        startService(i);
    }
}
