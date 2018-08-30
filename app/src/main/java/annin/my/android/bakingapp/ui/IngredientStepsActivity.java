package annin.my.android.bakingapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.custom.Recipes;
import annin.my.android.bakingapp.fragments.IngredientsFragment;
import annin.my.android.bakingapp.fragments.StepsListFragment;

public class IngredientStepsActivity extends AppCompatActivity implements StepsListFragment.OnStepsClickListener {

    private static final String TAG = IngredientStepsActivity.class.getSimpleName();

    private Context context;

    Recipes recipes;

    // Track whether to display a two-pane or single-pane UI
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredientsteps);

        if (getIntent() != null && getIntent().getExtras() != null) {
            recipes = getIntent().getExtras().getParcelable("Recipes");

            TextView originalTitle = (TextView) findViewById(R.id.recipeView);
            originalTitle.setText(recipes.getRecipeName());

            // Only create new fragments when there is no previously saved state
            if (savedInstanceState == null) {

        /*
        Add the fragment to its container using a FragmentManager and a Transaction
        Send the ingredients array list in Parcelable to the Ingredients Fragment
        */
                FragmentManager fragmentManager = getSupportFragmentManager();

                Bundle ingredientsBundle = new Bundle();
                ingredientsBundle.putParcelable("Recipes", recipes);

                //Pass Over the bundle to the Ingredients Fragment
                IngredientsFragment ingredientsFragment = new IngredientsFragment();
                //or IngredientsFragment ingredientsFragment = (IngredientsFragment)
                // fragmentManager.findFragmentByTag(IngredientsFagment.class.getCanonicalName());
                // if (ingredientsFragment == null) { ingredientsFragment
                ingredientsFragment.setArguments(ingredientsBundle);

                fragmentManager.beginTransaction().replace(R.id.ingredients_fragment_container, ingredientsFragment).commit();

                //Pack Data in a bundle(call the bundle "stepsBundle" to differentiate it from the "ingredientsBundle"
                Bundle stepsBundle = new Bundle();
                stepsBundle.putParcelable("Recipes", recipes);

                //Pass Over the bundle to the Steps Fragment
                StepsListFragment stepsListFragment = new StepsListFragment();
                stepsListFragment.setArguments(stepsBundle);

                fragmentManager.beginTransaction().replace(R.id.steps_fragment_container, stepsListFragment).commit();
            }
        }
        // Define the behavior for onImageSelected
        @Override
        public void onStepSelected(int position){

        }
    }
}





