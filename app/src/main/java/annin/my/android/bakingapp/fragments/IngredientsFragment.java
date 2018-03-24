package annin.my.android.bakingapp.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.asynctask.AsyncTaskInterface;
import annin.my.android.bakingapp.custom.Ingredients;
import annin.my.android.bakingapp.custom.Recipes;
import annin.my.android.bakingapp.recyclerviewadapters.IngredientsAdapter;


public class IngredientsFragment extends Fragment {

    private static final String TAG = IngredientsFragment.class.getSimpleName();

    Recipes recipes;

    private IngredientsAdapter ingredientsAdapter;

    private ArrayList<Ingredients> ingredientsArrayList = new ArrayList<>();

    RecyclerView mRecyclerView;

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

        return rootView;
        private ArrayList<Ingredients> ingredientsArrayList = new ArrayList<>();
        //   poster = (TextView) findViewById(R.id.recipeView);
        ingredientsAdapter = new IngredientsAdapter(ingredientsArrayList,context);
      //  ingredientsAdapter.setIngredientsList(ingredientsArrayList);
        mRecyclerView.setAdapter(ingredientsAdapter);

        RecyclerView.LayoutManager mIngredientLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mIngredientLayoutManager);



    }
}