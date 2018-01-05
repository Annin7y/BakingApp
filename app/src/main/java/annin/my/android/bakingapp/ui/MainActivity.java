package annin.my.android.bakingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ProgressBar;

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

    @BindView(R.id.recyclerview_main) RecyclerView mRecyclerView;

    private RecipesAdapter recipesAdapter;

    private ArrayList<Recipes> recipesArrayList = new ArrayList<>();

    private Context context;

    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        // Bind the views
        ButterKnife.bind(this);
        recipesAdapter = new RecipesAdapter(this, recipesArrayList, context);
        mRecyclerView.setAdapter(recipesAdapter);

        //specifying how the images will be displayed
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, calculateNoOfColumns(context));
        mRecyclerView.setLayoutManager(mLayoutManager);

        RecipesAsyncTask myTask = new RecipesAsyncTask(this);
     myTask.execute(NetworkUtils.buildUrl());



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
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        recipesAdapter = new RecipesAdapter(this, simpleJsonRecipeData, MainActivity.this);
        recipesArrayList = simpleJsonRecipeData;
        mRecyclerView.setAdapter(recipesAdapter);
        recipesAdapter.setRecipesList(recipesArrayList);
    }

    @Override
    public void onClick(Recipes recipes) {
        Intent intent = new Intent(MainActivity.this, IngredientsStepsActivity.class);
        intent.putExtra("Recipes", recipes);
        startActivity(intent);
    }

}
