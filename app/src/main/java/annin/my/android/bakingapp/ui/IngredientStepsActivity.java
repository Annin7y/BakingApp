package annin.my.android.bakingapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.custom.Recipes;
import annin.my.android.bakingapp.fragments.IngredientsFragment;

public class IngredientStepsActivity extends AppCompatActivity {

    private static final String TAG = IngredientStepsActivity.class.getSimpleName();

    private Context context;

    Recipes recipes;

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredientsteps);

        if (getIntent() != null && getIntent().getExtras() != null) {
            recipes = getIntent().getExtras().getParcelable("Recipes");

            TextView originalTitle = (TextView) findViewById(R.id.recipeView);
            originalTitle.setText(recipes.getRecipeName());
        }
        sendArrayToIngredientsFragment();

    }

    /*
    Send the array list to the Ingredients Fragment
     */
    private void sendArrayToIngredientsFragment() {
        //Pack Data in a bundle(call the bundle ""ingredientsBundle" to differentiate it from the "stepsBundle"
        Bundle ingredientsBundle = new Bundle();
        ingredientsBundle.putParcelable("Recipes", recipes);

        //Pass Over the bundle to the Ingredients Fragment
        IngredientsFragment ingredientsFragment = new IngredientsFragment();
        ingredientsFragment.setArguments(ingredientsBundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.ingredients_fragment_container, ingredientsFragment).commit();
    }

    /*
        Send the array list to the Steps Fragment
         */
    private void sendArrayToStepsFragment() {
        //Pack Data in a bundle(call the bundle "stepsBundle" to differentiate it from the "ingredientsBundle"
        Bundle stepsBundle = new Bundle();


    }
}

