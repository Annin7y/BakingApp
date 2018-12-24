package annin.my.android.bakingapp.widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.model.Ingredients;

public class RecipeWidgetViewFactory implements RemoteViewsService.RemoteViewsFactory
{
    private ArrayList<Ingredients> mIngredientsList;
    private Context mContext;

    public RemoteWidgetViewFactory(Context context)
    {
        mContext = context;
        mIngredientsList = new ArrayList<Ingredients>();
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
        // Heavy lifting code can go here without blocking the UI.
        // You would update the data in your collection here as well.
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public void onDestroy() {

    }


}
