package annin.my.android.bakingapp.utils;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import annin.my.android.bakingapp.custom.Ingredients;
import annin.my.android.bakingapp.custom.Recipes;
import annin.my.android.bakingapp.custom.Steps;

import static android.content.ContentValues.TAG;

/**
 * Created by Maino96-10022 on 11/27/2017.
 */

public class NetworkUtils {


    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    private static final String KEY_RECIPE_ID = "id";

    private static final String KEY_RECIPE_NAME = "name";

    private static final String KEY_RECIPE_IMAGE = "image";

    private static final String KEY_RECIPE_SERVINGS = "servings";

    private static final String KEY_INGREDIENT_QUANTITY = "quantity";

    private static final String KEY_INGREDIENT_MEASURE = "measure";

    private static final String KEY_INGREDIENT_NAME = "ingredient";

    private static final String KEY_STEPS_ID = "id";

    private static final String KEY_STEPS_SHORT_DESC = "shortDescription";

    private static final String KEY_STEPS_DESCRIPTION = "description";

    private static final String KEY_STEPS_VIDEO_URL = "videoURL";

    private static final String KEY_STEPS_THUMBNAIL_URL = "thumbnailURL";

    public NetworkUtils() {
    }

    private static ArrayList<Recipes> fetchRecipesData(String requestUrl) {

        // Create a URL object
        URL url = buildUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Recipes}s
        ArrayList<Recipes> recipesList = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Recipes}s
        return recipesList;
    }

    /**
     * @param
     * @return recipes
     */
    public static URL buildUrl(String recipeId) {
        URL urlRecipe = null;
        try {
            Uri recipeQueryUri = Uri.parse(BASE_URL).buildUpon()
                    .build();
            urlRecipe = new URL(recipeQueryUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Built URI " + urlRecipe);
        return urlRecipe;
    }


    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */

    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        Log.i("URL: ", url.toString());
        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving movie JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static ArrayList<Recipes> extractFeatureFromJson(String recipeJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(recipeJSON)) {
            return null;
        }
        ArrayList<Recipes> recipes = new ArrayList<>();
        try {

            // Create a JSONObject from the JSON response string

            JSONArray recipeArray = new JSONArray(recipeJSON);

// For each recipe in the recipeArray, create an {@link Recipes} object
            for (int i = 0; i < recipeArray.length(); i++) {

                // Get a single movie description at position i within the list of recipes
                JSONObject currentRecipe = recipeArray.getJSONObject(i);

                // Extract the value for the key called "name"
                String recipeName = currentRecipe.getString(KEY_RECIPE_NAME);

                String recipeId = currentRecipe.getString(KEY_RECIPE_ID);

                String recipeImage = currentRecipe.getString(KEY_RECIPE_IMAGE);

                int recipeServings = currentRecipe.getInt(KEY_RECIPE_SERVINGS);

                ArrayList<Ingredients> ingredients = new ArrayList<>();
                JSONArray ingredientsArray = currentRecipe.getJSONArray("ingredients");

                for (int j = 0; j < ingredientsArray.length(); j++) {

                    JSONObject currentIngredient = ingredientsArray.getJSONObject(j);

                    int ingredientQuantity = currentIngredient.getInt(KEY_INGREDIENT_QUANTITY);

                    String ingredientMeasure = currentIngredient.getString(KEY_INGREDIENT_MEASURE);

                    String ingredientName = currentIngredient.getString(KEY_INGREDIENT_NAME);

                    Ingredients ingredient = new Ingredients(ingredientQuantity, ingredientMeasure, ingredientName);
                    ingredients.add(ingredient);

                    ArrayList<Steps> steps = new ArrayList<>();
                    JSONArray stepsArray = currentRecipe.getJSONArray("steps");

                    for (int k = 0; k < stepsArray.length(); k++) {

                        JSONObject currentStep = stepsArray.getJSONObject(k);

                        String stepId = currentStep.getString(KEY_STEPS_ID);

                        String stepShortDescription = currentStep.getString(KEY_STEPS_SHORT_DESC);

                        String stepDescription = currentStep.getString(KEY_STEPS_DESCRIPTION);

                        String videoURL = currentStep.getString(KEY_STEPS_VIDEO_URL);

                        String thumbnailURL = currentStep.getString(KEY_STEPS_THUMBNAIL_URL);

                        Steps step = new Steps(stepId, stepShortDescription, stepDescription, videoURL, thumbnailURL);
                        steps.add(step);

                        Recipes recipe = new Recipes(recipeName, recipeId, recipeImage, recipeServings, ingredients, steps);
                        recipes.add(recipe);
                    }
                }
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing recipes JSON results", e);
        }

        // Return the list of recipes
        return recipes;

    }
}

    