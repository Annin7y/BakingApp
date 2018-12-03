package annin.my.android.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViewsService;

public class RecipeWidgetService extends RemoteViewsService
{
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent)
    {
        return new RecipeWidgetViewFactory(getApplicationContext());

    }

}
