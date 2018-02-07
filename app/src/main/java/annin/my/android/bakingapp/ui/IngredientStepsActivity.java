package annin.my.android.bakingapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.custom.Ingredients;
import annin.my.android.bakingapp.custom.Recipes;
import annin.my.android.bakingapp.recyclerviewadapters.IngredientsAdapter;
import annin.my.android.bakingapp.recyclerviewadapters.RecipesAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Maino96-10022 on 1/13/2018.
 */

public class IngredientStepsActivity extends AppCompatActivity {


    private static final String TAG = IngredientStepsActivity.class.getSimpleName();

    private Context context;

    Recipes recipes;

    private IngredientsAdapter ingredientsAdapter;

    private ArrayList<Ingredients> ingredientsArrayList = new ArrayList<>();

    TextView poster;

    @BindView(R.id.recyclerview_ingredients)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredientsteps);

        ButterKnife.bind(this);

        context = getApplicationContext();

        //   poster = (TextView) findViewById(R.id.recipeView);
        ingredientsAdapter = new IngredientsAdapter(ingredientsArrayList, context);

        RecyclerView.LayoutManager mIngredientLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mIngredientLayoutManager);

        if (getIntent() != null && getIntent().getExtras() != null) {
            recipes = getIntent().getExtras().getParcelable("Recipes");
            ingredientsArrayList = recipes.getRecipeIngredients();
        }
        ingredientsAdapter.setIngredientsList(ingredientsArrayList);
        mRecyclerView.setAdapter(ingredientsAdapter);

        TextView originalTitle = (TextView) findViewById(R.id.recipeView);
        originalTitle.setText(recipes.getRecipeName());


    }

}

