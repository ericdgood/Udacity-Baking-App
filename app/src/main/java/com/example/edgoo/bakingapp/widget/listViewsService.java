package com.example.edgoo.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.edgoo.bakingapp.R;
import com.example.edgoo.bakingapp.RecipeAdapter;
import com.example.edgoo.bakingapp.RecipeData.Recipes;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class listViewsService extends RemoteViewsService
{

    /**
     * @param intent intent that triggered this service
     * @return new ListViewsFactory Object with the appropriate implementation
     */
    public ListViewsFactory onGetViewFactory(Intent intent)
    {
        return new ListViewsFactory(this.getApplicationContext());
    }
}

class ListViewsFactory implements RemoteViewsService.RemoteViewsFactory
{
    private Context mContext;
    private ArrayList mIngredients = RecipeAdapter.ingredients;

    public ListViewsFactory(Context mContext)
    {
        this.mContext = mContext;
    }

    @Override
    public void onCreate()
    {
    }

    //Very Important,this is the place where the data is being changed each time by the adapter.
    @Override
    public void onDataSetChanged()
    {
        mIngredients = BakingAppWidgetProvider.mIngredients;
    }

    @Override
    public void onDestroy()
    {

    }

    @Override
    public int getCount()
    {
        if (mIngredients == null)
            return 0;
        return mIngredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position)
    {
        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_layout);
        views.setTextViewText(R.id.widgetIngredient, (CharSequence) mIngredients.get(position));
        return views;
    }

    @Override
    public RemoteViews getLoadingView()
    {
        return null;
    }

    @Override
    public int getViewTypeCount()
    {
        return 1;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }
}
