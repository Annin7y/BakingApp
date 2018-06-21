package annin.my.android.bakingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.asynctask.AsyncTaskInterface;
import annin.my.android.bakingapp.asynctask.RecipesAsyncTask;
import annin.my.android.bakingapp.custom.Recipes;
import annin.my.android.bakingapp.recyclerviewadapters.RecipesAdapter;
import annin.my.android.bakingapp.utils.NetworkUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RecipesAdapter.RecipesAdapterOnClickHandler, AsyncTaskInterface {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.recyclerview_main)
    RecyclerView mRecyclerView;

    private RecipesAdapter recipesAdapter;

    private ArrayList<Recipes> recipesArrayList = new ArrayList<>();

    ImageView poster;

    private Context context;

    private static final String KEY_RECIPES_LIST = "recipes_list";

    private ProgressBar mLoadingIndicator;

    private TextView mConnectionMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        // Bind the views
        ButterKnife.bind(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main);
        recipesAdapter = new RecipesAdapter(this, recipesArrayList, context);
        mRecyclerView.setAdapter(recipesAdapter);

        //specifying how the images will be displayed
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, calculateNoOfColumns(context));
        mRecyclerView.setLayoutManager(mLayoutManager);

        /*
         *  Starting the asyncTask so that recipes load upon launching the app.
         */

        if (savedInstanceState == null) {
            RecipesAsyncTask myTask = new RecipesAsyncTask(this);
            myTask.execute(NetworkUtils.buildUrl());

        } else {
            recipesArrayList = savedInstanceState.getParcelableArrayList(KEY_RECIPES_LIST);
            recipesAdapter.setRecipesList(recipesArrayList);
        }
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 180;
        int noOfColumns = (int) (dpWidth / scalingFactor);
        return noOfColumns;
    }


    @Override
    public void returnData(ArrayList<Recipes> simpleJsonRecipeData) {
        //     mLoadingIndicator.setVisibility(View.INVISIBLE);
        recipesAdapter = new RecipesAdapter(this, simpleJsonRecipeData, MainActivity.this);
        recipesArrayList = simpleJsonRecipeData;
        mRecyclerView.setAdapter(recipesAdapter);
        recipesAdapter.setRecipesList(recipesArrayList);
    }

    @Override
    public void onClick(Recipes recipes) {
        Intent intent = new Intent(MainActivity.this, IngredientStepsActivity.class);
        intent.putExtra("Recipes", recipes);
        startActivity(intent);
    }

    //Display if there is no internet connection
    public void showErrorMessage() {
        Toast.makeText(getApplicationContext(), "No internet connection",
                Toast.LENGTH_SHORT).show();
        mRecyclerView.setVisibility(View.INVISIBLE);
        mConnectionMessage.setVisibility(View.VISIBLE);
        mLoadingIndicator.setVisibility(View.VISIBLE);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(KEY_RECIPES_LIST, recipesArrayList);
        super.onSaveInstanceState(outState);
    }
}
