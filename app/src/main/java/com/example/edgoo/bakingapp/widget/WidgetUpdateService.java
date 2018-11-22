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
    private ArrayList mIngredients = RecipeAdapter.ingredients;

    public WidgetUpdateService()
    {
        super("WidgetServiceUpdate");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingAppWidgetProvider.class));
            BakingAppWidgetProvider.updateAppWidget(this, appWidgetManager, appWidgetIds,mIngredients);
    }
}
