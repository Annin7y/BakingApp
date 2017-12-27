package annin.my.android.bakingapp.asynctask;

import java.util.ArrayList;

import annin.my.android.bakingapp.custom.Recipes;

/**
 * Created by Maino96-10022 on 12/21/2017.
 */

public interface AsyncTaskInterface {
    void returnData(ArrayList<Recipes> simpleJsonRecipeData);

}
