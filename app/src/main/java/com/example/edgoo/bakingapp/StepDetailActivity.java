package com.example.edgoo.bakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.edgoo.bakingapp.Fragments.StepDetailFrag;
import com.example.edgoo.bakingapp.Fragments.StepsListFragment;
import com.example.edgoo.bakingapp.widget.WidgetUpdateService;

import java.util.Objects;

public class StepDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        setTitle(RecipeAdapter.recipeName);

        int currentStepDisplay;
        if ((getIntent().getStringExtra("step_id")) != null) {
            currentStepDisplay = Integer.parseInt(getIntent().getStringExtra("step_id"));
        } else {
            currentStepDisplay =0;
        }

        if(savedInstanceState == null) {
            StepDetailFrag stepDetailFragment = new StepDetailFrag();
            stepDetailFragment.setArguments(getIntent().getExtras());
            stepDetailFragment.setStepDetail(currentStepDisplay);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_detail_container, stepDetailFragment)
                    .commit();
        }

        if (findViewById(R.id.recipe_step_list_frag) != null) {

            StepsListFragment stepsFragment = new StepsListFragment();
            FragmentManager fragmentIngredManager = getSupportFragmentManager();
            stepsFragment.StepsList(this);
            fragmentIngredManager.beginTransaction()
                    .replace(R.id.recipe_step_list_frag, stepsFragment)
                    .commit();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Objects.requireNonNull(getSupportActionBar()).hide();
        } else {
            Objects.requireNonNull(getSupportActionBar()).show();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (findViewById(R.id.recipe_step_list_frag) != null){
            Intent back = new Intent(this, MainActivity.class);
            startActivity(back);
        } else {
            Intent back = new Intent(this, RecipeStepList.class);
            startActivity(back);
        }
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

    private void startWidgetService() {
        Intent i = new Intent(this, WidgetUpdateService.class);
        startService(i);
    }
}
