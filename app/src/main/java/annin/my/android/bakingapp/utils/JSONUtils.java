package annin.my.android.bakingapp.utils;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import annin.my.android.bakingapp.pojo.Ingredients;
import annin.my.android.bakingapp.pojo.Recipes;
import annin.my.android.bakingapp.pojo.Steps;

public class JSONUtils
{
    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    private static final String KEY_RECIPE_ID = "id";
    private static final String KEY_RECIPE_NAME = "name";
    private static final String KEY_RECIPE_IMAGE = "image";
    private static final String KEY_INGREDIENT_QUANTITY = "quantity";
    private static final String KEY_INGREDIENT_MEASURE = "measure";
    private static final String KEY_INGREDIENT_NAME = "ingredient";
    private static final String KEY_STEPS_ID = "id";
    private static final String KEY_STEPS_SHORT_DESC = "shortDescription";
    private static final String KEY_STEPS_LONG_DESC = "description";
    private static final String KEY_STEPS_VIDEO_URL = "videoURL";
    private static final String KEY_STEPS_THUMBNAIL_URL = "thumbnailURL";

    public JSONUtils()
    {
    }

    public static ArrayList<Recipes> extractFeatureFromJson(String recipeJSON)
    {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(recipeJSON))
        {
            return null;
        }
        ArrayList<Recipes> recipes = new ArrayList<>();
        try
        {
            // Create a JSONObject from the JSON response string
            JSONArray recipeArray = new JSONArray(recipeJSON);

            // For each recipe in the recipeArray, create an {@link Recipes} object
            for (int i = 0; i < recipeArray.length(); i++)
            {
                // Get a single recipe description at position i within the list of recipes
                JSONObject currentRecipe = recipeArray.getJSONObject(i);

                //Extract values for the following keys
                String recipeId = currentRecipe.optString(KEY_RECIPE_ID);
                String recipeName = currentRecipe.optString(KEY_RECIPE_NAME);
                String recipeImage = currentRecipe.optString(KEY_RECIPE_IMAGE);

                ArrayList<Ingredients> ingredients = new ArrayList<>();
                JSONArray ingredientsArray = currentRecipe.getJSONArray("ingredients");

                for (int j = 0; j < ingredientsArray.length(); j++)
                {
                    // Get a single ingredient at position i within the list of recipes
                    JSONObject currentIngredient = ingredientsArray.getJSONObject(j);

                    //Extract values for the following keys
                    String ingredientQuantity = currentIngredient.optString(KEY_INGREDIENT_QUANTITY);
                    String ingredientMeasure = currentIngredient.optString(KEY_INGREDIENT_MEASURE);
                    String ingredientName = currentIngredient.optString(KEY_INGREDIENT_NAME);

                    Ingredients ingredient = new Ingredients(ingredientQuantity, ingredientMeasure, ingredientName);
                    ingredients.add(ingredient);
                }

                ArrayList<Steps> steps = new ArrayList<>();
                JSONArray stepsArray = currentRecipe.getJSONArray("steps");

                for (int k = 0; k < stepsArray.length(); k++)
                {
                    JSONObject currentStep = stepsArray.getJSONObject(k);

                    // Extract values for the keys called "id", "shortDescription", "description", "videoURL", "thumbnailURL"
                    int stepId = currentStep.optInt(KEY_STEPS_ID);
                    String stepShortDescription = currentStep.optString(KEY_STEPS_SHORT_DESC);
                    String stepLongDescription = currentStep.optString(KEY_STEPS_LONG_DESC);
                    String videoURL = currentStep.optString(KEY_STEPS_VIDEO_URL);
                    String thumbnailURL = currentStep.optString(KEY_STEPS_THUMBNAIL_URL);

                    Steps step = new Steps(stepId, stepShortDescription, stepLongDescription, videoURL, thumbnailURL);
                    steps.add(step);
                }

                Recipes recipe = new Recipes(recipeId,recipeName, recipeImage, ingredients, steps);
                recipes.add(recipe);
            }

        }
        catch(JSONException e)
        {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing recipes JSON results", e);
        }

        // Return a list of recipes
        return recipes;
    }
}
