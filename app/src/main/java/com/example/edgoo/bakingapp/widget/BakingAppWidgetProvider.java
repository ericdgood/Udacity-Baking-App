package com.example.edgoo.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.edgoo.bakingapp.R;

import java.util.ArrayList;


public class BakingAppWidgetProvider extends AppWidgetProvider {

        public static ArrayList mIngredients;

        public BakingAppWidgetProvider()
        {

        }

        static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                    int[] appWidgetIds, ArrayList ingredients)
        {
            mIngredients = ingredients;
            for (int appWidgetId : appWidgetIds)
            {
                Intent intent = new Intent(context, listViewsService.class);
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget_provider);
                views.setRemoteAdapter(R.id.widget_listview, intent);
                ComponentName component = new ComponentName(context, BakingAppWidgetProvider.class);
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_listview);
                appWidgetManager.updateAppWidget(component, views);
            }

        }


    @Override
        public void onEnabled(Context context)
        {

        }

        @Override
        public void onDisabled(Context context)
        {

        }


    }



