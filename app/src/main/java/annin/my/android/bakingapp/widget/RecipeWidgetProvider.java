package annin.my.android.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.model.Recipes;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {
    //The following code is based on the code in these links:
    //https://joshuadonlan.gitbooks.io/onramp-android/content/widgets/collection_widgets.html
    //http://www.vogella.com/tutorials/AndroidWidgets/article.html

    public static final String EXTRA_ITEM =
            "annin.my.android.RecipeWidgetProvider.EXTRA_ITEM";

    /*
    This method is called once a new widget is created as well as every update interval.
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        for (int i = 0; i < appWidgetIds.length; i++) {

            int widgetId = appWidgetIds[i];


            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);

            // Register an onClickListener
            Intent intent = new Intent(context, RecipeWidgetProvider.class);

            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            // Instruct the widget manager to update the widget
           // appWidgetManager.updateAppWidget(appWidgetId, views);
        }

     //    Build the intent to call the service
        Intent intent = new Intent(context.getApplicationContext(),
              RecipeWidgetService.class);
           intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

       //  Update the widgets via the service
          context.startService(intent);
}

    @Override
    public void onReceive(Context context, Intent intent) {

        super.onReceive(context, intent);
    }


        @Override
        public void onEnabled (Context context){
        // Enter relevant functionality for when the first widget is created
        }


        @Override
        public void onDisabled (Context context){
        // Enter relevant functionality for when the last widget is disabled
        }
    }

