package annin.my.android.bakingapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.asynctask.AsyncTaskInterface;
import annin.my.android.bakingapp.custom.Ingredients;
import annin.my.android.bakingapp.custom.Recipes;
import annin.my.android.bakingapp.recyclerviewadapters.IngredientsAdapter;
import annin.my.android.bakingapp.recyclerviewadapters.RecipesAdapter;

/**
 * Created by Maino96-10022 on 1/13/2018.
 */

public class IngredientStepsActivity extends AppCompatActivity implements IngredientsAdapter, AsyncTaskInterface {

    private static final String TAG = IngredientStepsActivity.class.getSimpleName();

    private Context context;

    Recipes recipes;

    private IngredientsAdapter ingredientsAdapter;

    private ArrayList<Ingredients> ingredientsArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredientsteps);

        context = getApplicationContext();
        if (getIntent() != null && getIntent().getExtras() != null) {
            recipes = getIntent().getExtras().getParcelable("Recipes");

        }

        @Override
        public void returnData(ArrayList< Ingredients> simpleJsonRecipeData) {
            //     mLoadingIndicator.setVisibility(View.INVISIBLE);
            recipesAdapter = new RecipesAdapter(this, simpleJsonRecipeData, MainActivity.this);
            recipesArrayList = simpleJsonRecipeData;
            mRecyclerView.setAdapter(recipesAdapter);
            recipesAdapter.setRecipesList(recipesArrayList);
        }

}}