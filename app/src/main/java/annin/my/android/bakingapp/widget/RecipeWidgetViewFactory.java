package annin.my.android.bakingapp.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.model.Ingredients;

public class RecipeWidgetViewFactory implements RemoteViewsService.RemoteViewsFactory
{
    private ArrayList<Ingredients> mIngredientsList;
    private Context mContext;

    public RemoteWidgetViewFactory(Context context)
    {
        mContext = context;

    }

    @Override
    public void onCreate() {
    }

    @Override
    public int getCount()
    {
        return mIngredientsList.size();
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }
    @Override
    public RemoteViews getViewAt(int position) {

        Ingredients ingredient = mIngredientsList.get(position);

        RemoteViews itemView = new RemoteViews(mContext.getPackageName(), R.layout.ingredient_list_item);

        return itemView;

    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onDataSetChanged() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);

        Gson gson = new Gson();
        Type type = new TypeToken<List<Ingredients>() {}.getType();
        String gsonString = sharedPreferences.getString("userImages", "");
        List<Ingredients> images = gson.fromJson(gsonString, type);

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public void onDestroy() {

    }


}
