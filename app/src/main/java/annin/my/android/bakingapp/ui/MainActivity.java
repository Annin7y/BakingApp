package annin.my.android.bakingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.asynctask.AsyncTaskInterface;
import annin.my.android.bakingapp.asynctask.RecipesAsyncTask;
import annin.my.android.bakingapp.decoration.VerticalSpacingDecoration;
import annin.my.android.bakingapp.pojo.Recipes;
import annin.my.android.bakingapp.recyclerviewadapters.RecipesAdapter;
import annin.my.android.bakingapp.utils.NetworkUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements RecipesAdapter.RecipesAdapterOnClickHandler, AsyncTaskInterface
{
    // Tag for logging
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.recyclerview_main)
    RecyclerView mRecipeRecyclerView;

    private RecipesAdapter recipesAdapter;
    private ArrayList<Recipes> recipesArrayList = new ArrayList<>();
    private Context context;
    private static final String KEY_RECIPES_LIST = "recipes_list";
    CoordinatorLayout mCoordinatorLayout;

    @BindView(R.id.pb_loading_indicator)
    ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        // Bind the views
        ButterKnife.bind(this);
        mCoordinatorLayout = findViewById(R.id.coordinatorLayout);

        recipesAdapter = new RecipesAdapter(this, recipesArrayList, context);
        mRecipeRecyclerView.setAdapter(recipesAdapter);

        //specifying how the images will be displayed
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, calculateNoOfColumns(context));
        mRecipeRecyclerView.setLayoutManager(mLayoutManager);

        /*
         *  Starting the asyncTask so that recipes load upon launching the app.
         */
        if (savedInstanceState == null)
        {
            if (isNetworkStatusAvailable(this))
            {
                RecipesAsyncTask myTask = new RecipesAsyncTask(this);
                myTask.execute(NetworkUtils.buildUrl());
            }
            else
                {
                Snackbar
                        .make(mCoordinatorLayout, "Please check your internet connection", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Retry", new MyClickListener())
                        .show();
            }
        }
        else
            {
            recipesArrayList = savedInstanceState.getParcelableArrayList(KEY_RECIPES_LIST);
            recipesAdapter.setRecipesList(recipesArrayList);
        }

        //specifying the space between images
        mRecipeRecyclerView.addItemDecoration(new VerticalSpacingDecoration(10));

        mLoadingIndicator.setVisibility(View.INVISIBLE);
        Log.i("list", recipesArrayList.size() + "");
       // Timber.i("list" , recipesArrayList.size());
    }

    public static int calculateNoOfColumns(Context context)
    {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 180;
        int noOfColumns = (int) (dpWidth / scalingFactor);
        return noOfColumns;
    }

    public class MyClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            // Run the AsyncTask in response to the click
            RecipesAsyncTask myTask = new RecipesAsyncTask(MainActivity.this);
            myTask.execute();
        }
    }

    @Override
    public void returnData(ArrayList<Recipes> simpleJsonRecipeData)
    {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        if (null != simpleJsonRecipeData)
        {
            recipesAdapter = new RecipesAdapter(this, simpleJsonRecipeData, MainActivity.this);
            recipesArrayList = simpleJsonRecipeData;
            mRecipeRecyclerView.setAdapter(recipesAdapter);
            recipesAdapter.setRecipesList(recipesArrayList);
        }
        else
            {
            showErrorMessage();
        }
    }

    @Override
    public void onClick(Recipes recipes)
    {
        Intent intent = new Intent(MainActivity.this, IngredientStepsActivity.class);
        intent.putExtra("Recipes", recipes);
        startActivity(intent);
    }

    //Display if there is no internet connection
    public void showErrorMessage()
    {
        Snackbar
                .make(mCoordinatorLayout, "Please check your internet connection", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", new MyClickListener())
                .show();
        mRecipeRecyclerView.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    public static boolean isNetworkStatusAvailable(Context context)
    {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putParcelableArrayList(KEY_RECIPES_LIST, recipesArrayList);
        super.onSaveInstanceState(outState);
    }
}
