package com.example.edgoo.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edgoo.bakingapp.Fragments.StepsListFragment;
import com.example.edgoo.bakingapp.widget.WidgetUpdateService;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipeStepList extends AppCompatActivity {

    @BindView(R.id.recycler_view_ingred)
    RecyclerView recyclerView;
    @BindView(R.id.recipe_name)
    TextView recipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobile_recipe_step_list);

        setTitle(RecipeAdapter.recipeName);
//        startWidgetService();

//        TABLET LAYOUT
        if (findViewById(R.id.mobile_step_list) == null) {

            Intent intent = new Intent(this, StepDetailActivity.class);
            this.startActivity(intent);

        } else {

            ButterKnife.bind(this);
            recipeName.setText(RecipeAdapter.recipeName);

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

    private void startWidgetService()
    {
        Intent i = new Intent(this, WidgetUpdateService.class);
        startService(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.widget_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
//        boolean recipeAdded;
        if (itemId == R.id.action_add) {
            startWidgetService();
            Toast.makeText(this, "Ingredients added to widget", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
