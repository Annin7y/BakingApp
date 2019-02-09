package annin.my.android.bakingapp.asynctask;

import android.os.AsyncTask;

import java.net.URL;
import java.util.ArrayList;

import annin.my.android.bakingapp.pojo.Recipes;
import annin.my.android.bakingapp.utils.JSONUtils;
import annin.my.android.bakingapp.utils.NetworkUtils;

public class RecipesAsyncTask extends AsyncTask<URL, Void, ArrayList<Recipes>>
{
    private static final String TAG = RecipesAsyncTask.class.getSimpleName();

    private AsyncTaskInterface listener;

    public RecipesAsyncTask(AsyncTaskInterface listener)
    {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Recipes> doInBackground(URL... params)
    {
        URL recipeRequestUrl = NetworkUtils.buildUrl();

        try
        {
            String jsonRecipeResponse = NetworkUtils
                    .makeHttpRequest(recipeRequestUrl);

            return JSONUtils.extractFeatureFromJson(jsonRecipeResponse);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<Recipes> mRecipesList)
    {
        super.onPostExecute(mRecipesList);
         /*the if method is commented out because the error message will be displayed if there is no internet connection
        the if statement is included in the returnData method in the Main Activity
        */
       //   if (mRecipesList != null) {}
            listener.returnData(mRecipesList);
    }
}