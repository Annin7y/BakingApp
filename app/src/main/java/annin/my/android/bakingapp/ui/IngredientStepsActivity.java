package annin.my.android.bakingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.fragments.VideoFragment;
import annin.my.android.bakingapp.model.Ingredients;
import annin.my.android.bakingapp.model.Recipes;
import annin.my.android.bakingapp.fragments.IngredientsFragment;
import annin.my.android.bakingapp.fragments.StepsListFragment;
import annin.my.android.bakingapp.model.Steps;

public class IngredientStepsActivity extends AppCompatActivity implements StepsListFragment.OnStepClickListener
{
    private static final String TAG = IngredientStepsActivity.class.getSimpleName();

    private Context context;
    Recipes recipes;

    // Track whether to display a two-pane or single-pane UI
    public boolean mTwoPane;
    public int stepsIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredientsteps);
        // Determine if you're creating a two-pane or single-pane display
        if (savedInstanceState == null)
        {
            if (getIntent() != null && getIntent().getExtras() != null)
            {
                recipes = getIntent().getExtras().getParcelable("Recipes");

                if(findViewById(R.id.tablet_detail_layout) != null)
        {
            // This LinearLayout will only initially exist in the two-pane tablet case
            mTwoPane = true;

            // Only create new fragments when there is no previously saved state


        /*
        Add the fragment to its container using a FragmentManager and a Transaction
        Send the ingredients array list in Parcelable to the Ingredients Fragment
        */
                FragmentManager fragmentManager = getSupportFragmentManager();

                Bundle ingredientsBundle = new Bundle();
                ingredientsBundle.putParcelable("Recipes", recipes);

                //Pass Over the bundle to the Ingredients Fragment
                IngredientsFragment ingredientsFragment = new IngredientsFragment();
                ingredientsFragment.setArguments(ingredientsBundle);

                fragmentManager.beginTransaction().replace(R.id.ingredients_fragment_container, ingredientsFragment).commit();

                //Pack Data in a bundle call the bundle "stepsBundle" to differentiate it from the "ingredientsBundle"
                Bundle stepsBundle = new Bundle();
                stepsBundle.putParcelable("Recipes", recipes);

                //Pass Over the bundle to the Steps Fragment
                StepsListFragment stepsListFragment = new StepsListFragment();
                stepsListFragment.setArguments(stepsBundle);

                fragmentManager.beginTransaction().replace(R.id.steps_fragment_container, stepsListFragment).commit();
            }
            else
                {
                // We're in single-pane mode and displaying fragments on a phone in separate activities

                    mTwoPane = false;
                FragmentManager fragmentManager = getSupportFragmentManager();

                Bundle ingredientsBundle = new Bundle();
                ingredientsBundle.putParcelable("Recipes", recipes);

                //Pass Over the bundle to the Ingredients Fragment
                IngredientsFragment ingredientsFragment = new IngredientsFragment();
                ingredientsFragment.setArguments(ingredientsBundle);

                fragmentManager.beginTransaction().replace(R.id.ingredients_fragment_container, ingredientsFragment).commit();

                //Pack Data in a bundle call the bundle "stepsBundle" to differentiate it from the "ingredientsBundle"
                Bundle stepsBundle = new Bundle();
                stepsBundle.putParcelable("Recipes", recipes);

                //Pass Over the bundle to the Steps Fragment
                StepsListFragment stepsListFragment = new StepsListFragment();
                stepsListFragment.setArguments(stepsBundle);

                    fragmentManager.beginTransaction().replace(R.id.steps_fragment_container, stepsListFragment).commit();
                }
            }
        }}

       @Override
       public void onClick(Steps stepClick)
       {
           if (mTwoPane == true)
           {
               Log.i("Step: ", stepClick.getStepShortDescription());
              Bundle stepsVideoBundle = new Bundle();
              stepsVideoBundle.putParcelable("Steps", stepClick);
              stepsVideoBundle.putBoolean("TwoPane", mTwoPane);
              stepsVideoBundle.putInt("StepsIndex", stepsIndex);

               VideoFragment videoFragment = new VideoFragment();
              videoFragment.setArguments(stepsVideoBundle);
               getSupportFragmentManager().beginTransaction().replace(R.id.video_fragment_container, videoFragment).commit();

//               Bundle stepsDetailedBundle = new Bundle();
//               stepsDetailedBundle.putParcelable("Recipes", recipes);

           }
           else
               {
                   Log.i("Step: ", stepClick.getStepShortDescription());
              Intent intent = new Intent(IngredientStepsActivity.this, VideoPhoneActivity.class);
               intent.putExtra("Steps", stepClick);
              startActivity(intent);

           }
        }
  }






