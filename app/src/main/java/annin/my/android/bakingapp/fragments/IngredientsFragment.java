package annin.my.android.bakingapp.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.asynctask.AsyncTaskInterface;
import annin.my.android.bakingapp.custom.Ingredients;
import annin.my.android.bakingapp.custom.Recipes;
import annin.my.android.bakingapp.recyclerviewadapters.IngredientsAdapter;
import annin.my.android.bakingapp.recyclerviewadapters.RecipesAdapter;
import annin.my.android.bakingapp.ui.IngredientStepsActivity;
import annin.my.android.bakingapp.ui.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;


public class IngredientsFragment extends Fragment implements RecipesAdapter.RecipesAdapterOnClickHandler, AsyncTaskInterface  {

    private static final String TAG = IngredientsFragment.class.getSimpleName();

    Recipes recipes;

    private IngredientsAdapter ingredientsAdapter;

    private ArrayList<Ingredients> ingredientsArrayList = new ArrayList<>();

    @BindView(R.id.recyclerview_ingredients)

    private RecipesAdapter recipesAdapter;

    private ArrayList<Recipes> recipesArrayList = new ArrayList<>();


    private RecyclerView mRecyclerView;

    private Context context;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment
     */
    public IngredientsFragment() {
    }
    /**
     * Inflates the fragment layout file and sets the correct resource for the image to display
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_ingredient, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_ingredients);
        recipesAdapter = new RecipesAdapter(this, recipesArrayList, context);
        mRecyclerView.setAdapter(recipesAdapter);

        //specifying how the images will be displayed
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, calculateNoOfColumns(context));
        mRecyclerView.setLayoutManager(mLayoutManager);

        return rootView;
       // ArrayList<Ingredients> ingredientsArrayList = new ArrayList<>();
        //   poster = (TextView) findViewById(R.id.recipeView);
     //   ingredientsAdapter = new IngredientsAdapter(ingredientsArrayList, context);
        //  ingredientsAdapter.setIngredientsList(ingredientsArrayList);
      //  mRecyclerView.setAdapter(ingredientsAdapter);

     //   RecyclerView.LayoutManager mIngredientLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
     //   mRecyclerView.setLayoutManager(mIngredientLayoutManager);
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
            recipesAdapter = new RecipesAdapter(this, simpleJsonRecipeData, getActivity());
            recipesArrayList = simpleJsonRecipeData;
            mRecyclerView.setAdapter(recipesAdapter);
            recipesAdapter.setRecipesList(recipesArrayList);

    }
    @Override
    public void onClick(Recipes recipes) {

    }

}