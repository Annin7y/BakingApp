package annin.my.android.bakingapp.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.decoration.VerticalSpacingDecoration;
import annin.my.android.bakingapp.pojo.Ingredients;
import annin.my.android.bakingapp.pojo.Recipes;
import annin.my.android.bakingapp.recyclerviewadapters.IngredientsAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.gson.Gson;

public class IngredientsFragment extends Fragment
{
    private static final String TAG = IngredientsFragment.class.getSimpleName();

    @BindView(R.id.recyclerview_ingredients)
    RecyclerView mIngredientRecyclerView;

    ArrayList<Ingredients> ingredientsArrayList;
    Recipes recipes;

    // Final Strings to store state information about the list of ingredients and list index
    private static final String KEY_INGREDIENTS_LIST = "ingredients_list";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment
     */
    public IngredientsFragment()
    {
    }

    /**
     * Inflates the fragment layout file and sets the correct resource for the image to display
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //Inflate the Ingredients fragment layout
        View rootView = inflater.inflate(R.layout.fragment_ingredient, container, false);

        // Bind the views
        ButterKnife.bind(this, rootView);

        Bundle bundle = this.getArguments();
        if (bundle != null)
        {
            recipes = getArguments().getParcelable("Recipes");

            ingredientsArrayList = new ArrayList<>();
            ingredientsArrayList = recipes.getRecipeIngredients();

            if (savedInstanceState != null)
            {
                //Restore the fragment's state here
                ingredientsArrayList = savedInstanceState.getParcelableArrayList(KEY_INGREDIENTS_LIST);
            }

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            mIngredientRecyclerView.setLayoutManager(mLayoutManager);
            Log.i("listIngredients", ingredientsArrayList.size() + "");

            IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(ingredientsArrayList, getContext());
            mIngredientRecyclerView.setAdapter(ingredientsAdapter);

            mIngredientRecyclerView.addItemDecoration(new VerticalSpacingDecoration(25));

            //Store Ingredients in SharedPreferences
            SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences((getActivity()).getApplicationContext());
            SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();

            Gson gson = new Gson();
            String json = gson.toJson(ingredientsArrayList);
            prefsEditor.putString("IngredientsList_Widget", json);
            prefsEditor.apply();
        }
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        //Save the fragment's state here
        outState.putParcelableArrayList(KEY_INGREDIENTS_LIST, ingredientsArrayList);
        super.onSaveInstanceState(outState);
    }
}
