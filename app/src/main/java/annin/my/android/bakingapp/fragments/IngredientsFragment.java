package annin.my.android.bakingapp.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.custom.Ingredients;
import annin.my.android.bakingapp.custom.Recipes;
import annin.my.android.bakingapp.recyclerviewadapters.IngredientsAdapter;
import annin.my.android.bakingapp.recyclerviewadapters.RecipesAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;


public class IngredientsFragment extends Fragment {

    private static final String TAG = IngredientsFragment.class.getSimpleName();

    @BindView(R.id.recyclerview_ingredients)
    RecyclerView mRecyclerView;

    Recipes recipes;

    private IngredientsAdapter ingredientsAdapter;

    private ArrayList<Ingredients> ingredientsArrayList;

    // Final Strings to store state information about the list of ingredients and list index
    private static final String KEY_INGREDIENTS_LIST = "ingredients_list";

    //    private RecipesAdapter recipesAdapter;

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

//        if (savedInstanceState != null) {
//            //Restore the fragment's state here
//            ingredientsArrayList = savedInstanceState.getParcelableArrayList(KEY_INGREDIENTS_LIST);
//            ingredientsAdapter.setIngredientsList(ingredientsArrayList);
//        }

        //Inflate the Ingredients fragment layout
        View rootView = inflater.inflate(R.layout.fragment_ingredient, container, false);
        // Bind the views
        ButterKnife.bind(this, rootView);

            if (getArguments() != null) {
                recipes = getArguments().getParcelable("recipes");
            }

        ingredientsArrayList = new ArrayList<>();
        recipes.getRecipeIngredients();
        mRecyclerView.setAdapter(ingredientsAdapter);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        return rootView;
    }
}
//    @Override
//   public void onSaveInstanceState(Bundle currentState) {
//        currentState.putParcelableArrayList(KEY_INGREDIENTS_LIST, ingredientsArrayList);
//        super.onSaveInstanceState(currentState);
//    }


