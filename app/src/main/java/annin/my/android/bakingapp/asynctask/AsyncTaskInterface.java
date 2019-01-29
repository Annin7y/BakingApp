package annin.my.android.bakingapp.asynctask;

import java.util.ArrayList;

import annin.my.android.bakingapp.pojo.Recipes;

public interface AsyncTaskInterface
{
    void returnData(ArrayList<Recipes> simpleJsonRecipeData);
}
