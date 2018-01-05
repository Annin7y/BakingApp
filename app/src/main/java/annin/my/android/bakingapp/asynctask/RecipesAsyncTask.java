package annin.my.android.bakingapp.asynctask;

import android.os.AsyncTask;

import java.net.URL;
import java.util.ArrayList;

import annin.my.android.bakingapp.custom.Recipes;
import annin.my.android.bakingapp.utils.NetworkUtils;

/**
 * Created by Maino96-10022 on 12/21/2017.
 */

public class RecipesAsyncTask extends AsyncTask<URL, Void, ArrayList<Recipes>> {

    private static final String TAG = RecipesAsyncTask.class.getSimpleName();
    private AsyncTaskInterface listener;

    public RecipesAsyncTask(AsyncTaskInterface listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Recipes> doInBackground(URL... params) {

        URL trailerRequestUrl = NetworkUtils.buildUrl();

        try {

            String jsonRecipeResponse = NetworkUtils
                    .makeHttpRequest(trailerRequestUrl);

            return NetworkUtils.extractFeatureFromJson(jsonRecipeResponse);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    protected void onPostExecute(ArrayList<Recipes> mRecipesList) {
        super.onPostExecute(mRecipesList);
        if (mRecipesList != null) {
            listener.returnData(mRecipesList);
        }
    }


}