package com.example.edgoo.bakingapp.RecipeData;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.edgoo.bakingapp.RecipeAdapter;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class FetchRecipeData extends AsyncTask<String, Void, Recipes[]> {

    private final RecipeAdapter mRecipeAdapter;

    public FetchRecipeData(RecipeAdapter RecipeAdapter) {
        mRecipeAdapter = RecipeAdapter;
    }

    @Override
    protected Recipes[] doInBackground(String... strings) {

        URL recipeURL = null;
        try {
            recipeURL = getApiUrl();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            assert recipeURL != null;
//                BUILDS RECIPE URL INTO STRING URL
            String jsonresponse = getResponseFromHttpUrl(recipeURL);
//                PARES RECIPE URL
            return ParseRecipeData.ParseRecipeJson(jsonresponse);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static URL getApiUrl() throws MalformedURLException {

        final String RECIPE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        Uri baseUri = Uri.parse(RECIPE_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        return new URL(uriBuilder.toString());
    }

    private String getResponseFromHttpUrl(URL recipeUrl) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) recipeUrl.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    @Override
    protected void onPostExecute(Recipes[] rocketData) {
        mRecipeAdapter.setRecipeData(rocketData);
    }
}
