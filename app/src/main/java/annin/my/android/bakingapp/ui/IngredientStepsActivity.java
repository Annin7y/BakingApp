package annin.my.android.bakingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.fragments.IngredientsFragment;
import annin.my.android.bakingapp.fragments.StepsListFragment;
import annin.my.android.bakingapp.fragments.VideoFragment;
import annin.my.android.bakingapp.pojo.Recipes;
import annin.my.android.bakingapp.pojo.Steps;

public class IngredientStepsActivity extends AppCompatActivity implements StepsListFragment.OnStepClickListener
{
    private static final String TAG = IngredientStepsActivity.class.getSimpleName();

    private Context context;
    Recipes recipes;

    // Track whether to display a two-pane or single-pane UI
    public boolean mTwoPane;
    public int stepIndex;
    public static ArrayList<Steps> stepsArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredientsteps);

        context = getApplicationContext();

        if (savedInstanceState == null)
        {
            if (getIntent() != null && getIntent().getExtras() != null)
            {
                recipes = getIntent().getExtras().getParcelable("Recipes");
                stepsArrayList = new ArrayList<>();
                stepsArrayList = recipes.getRecipeSteps();

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
            }

        // Determine if you're creating a two-pane or single-pane display
        if (findViewById(R.id.tablet_detail_layout) != null)
        {
            // This LinearLayout will only initially exist in the two-pane tablet case
            mTwoPane = true;
        } else {
            // We're in single-pane mode and displaying fragments on a phone in separate activities

            mTwoPane = false;
        }
    }

       @Override
       public void onClick(Steps stepClicked, int stepPosition)
       {
           stepIndex = stepPosition;
           if (mTwoPane)
           {
              Bundle stepsVideoBundle = new Bundle();
              stepsVideoBundle.putParcelable("Steps", stepClicked);
              stepsVideoBundle.putBoolean("TwoPane", mTwoPane);
              stepsVideoBundle.putInt("StepIndex", stepIndex);
              stepsVideoBundle.putParcelableArrayList("StepsArrayList", stepsArrayList);

              VideoFragment videoFragment = new VideoFragment();
              videoFragment.setArguments(stepsVideoBundle);
              getSupportFragmentManager().beginTransaction().replace(R.id.video_fragment_container, videoFragment).commit();
           }
           else
               {
               Log.i("Step: ", stepClicked.getStepShortDescription());
               Intent intent = new Intent(IngredientStepsActivity.this, VideoPhoneActivity.class);
               intent.putExtra("Steps", stepClicked);
                   intent.putExtra("TwoPane", mTwoPane);
                   intent.putExtra("StepIndex", stepIndex);
                   intent.putParcelableArrayListExtra("StepsArrayList", stepsArrayList);

                   Log.i("test","ingredient "+ stepsArrayList.size()+"");
               startActivity(intent);
           }
       }
}






