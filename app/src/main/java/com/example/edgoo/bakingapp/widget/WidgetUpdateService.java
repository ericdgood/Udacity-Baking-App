package com.example.edgoo.bakingapp.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.example.edgoo.bakingapp.MainActivity;
import com.example.edgoo.bakingapp.RecipeAdapter;
import com.example.edgoo.bakingapp.RecipeData.Recipes;

import java.util.ArrayList;

public class WidgetUpdateService extends IntentService
{
//    public static final String WIDGET_UPDATE_ACTION = "com.example.edgoo.bakingapp.update_widget";
    private ArrayList mIngredients = RecipeAdapter.ingredients;

    public WidgetUpdateService()
    {
        super("WidgetServiceUpdate");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
//        if (intent != null && intent.getAction().equals(WIDGET_UPDATE_ACTION))
//        {
//            Bundle bundle = intent.getBundleExtra(MainActivity.BUNDLE);
//            Parcelable[] parcelables = bundle.getParcelableArray(MainActivity.INGREDIENTS);
//            if (parcelables != null)
//            {
//                mIngredientsList = new Recipes[parcelables.length];
//                ArrayList<String> ingredientsList = new ArrayList<String>();
//                for (int i = 0; i < mIngredients.size(); i++)
//                {
//                    mIngredientsList[i] = (CharSequence) mIngredients.get(i);
//                }
//            }

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingAppWidgetProvider.class));
            BakingAppWidgetProvider.updateAppWidget(this, appWidgetManager, appWidgetIds,mIngredients);
//        }
    }
}
