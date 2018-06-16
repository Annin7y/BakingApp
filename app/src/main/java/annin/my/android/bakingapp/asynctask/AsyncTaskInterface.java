package annin.my.android.bakingapp.asynctask;

import java.util.ArrayList;

import annin.my.android.bakingapp.custom.Recipes;

public interface AsyncTaskInterface {
    void returnData(ArrayList<Recipes> simpleJsonRecipeData);

}
