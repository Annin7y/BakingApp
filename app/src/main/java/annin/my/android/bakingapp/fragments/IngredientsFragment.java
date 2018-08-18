package annin.my.android.bakingapp.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.custom.Ingredients;
import annin.my.android.bakingapp.custom.Recipes;
import annin.my.android.bakingapp.recyclerviewadapters.IngredientsAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;


public class IngredientsFragment extends Fragment {

    private static final String TAG = IngredientsFragment.class.getSimpleName();

    @BindView(R.id.recyclerview_ingredients)
    RecyclerView mRecyclerView;

    ArrayList<Ingredients> ingredientsArrayList;

    Recipes recipes;

    // Final Strings to store state information about the list of ingredients and list index
    private static final String KEY_INGREDIENTS_LIST = "ingredients_list";

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

        if (savedInstanceState != null) {
            //Restore the fragment's state here
            ingredientsArrayList = savedInstanceState.getParcelableArrayList(KEY_INGREDIENTS_LIST);
        }
        //Inflate the Ingredients fragment layout
        View rootView = inflater.inflate(R.layout.fragment_ingredient, container, false);

        // Bind the views
        ButterKnife.bind(this, rootView);

        Bundle bundle = this.getArguments();
        if (bundle != null) {

            recipes = getArguments().getParcelable("Recipes");

            ingredientsArrayList = new ArrayList<>();
            ingredientsArrayList = recipes.getRecipeIngredients();

            
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            Log.i("listIngredients", ingredientsArrayList.size() + "");

            IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(ingredientsArrayList);
            mRecyclerView.setAdapter(ingredientsAdapter);

//            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
//               mLayoutManager.getOrientation());
//            mRecyclerView.addItemDecoration(dividerItemDecoration);
           // mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
             //       DividerItemDecoration.VERTICAL));
//            DividerItemDecoration horizontalDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
//                    DividerItemDecoration.VERTICAL);
//            Drawable horizontalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.item_decorator);
//           horizontalDecoration.setDrawable(horizontalDivider);
//            mRecyclerView.addItemDecoration(horizontalDecoration);
        }
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
//                mLayoutManager.getOrientation());
//        mRecyclerView.addItemDecoration(dividerItemDecoration);


        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            //Restore the fragment's state here
            ingredientsArrayList = (ArrayList<Ingredients>) savedInstanceState.getParcelable(KEY_INGREDIENTS_LIST);
                  }
        Log.e("TAG", "onActivityCreated OK");

    }
            @Override
            public void onSaveInstanceState (Bundle currentState){
                //Save the fragment's state here
                currentState.putParcelableArrayList(KEY_INGREDIENTS_LIST, ingredientsArrayList);
                super.onSaveInstanceState(currentState);
            }
        }
