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
public class RecipeWidgetProvider extends AppWidgetProvider
{

    private Recipes recipes;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetIds)
    {
        for(int appWidgetId : appWidgetIds)
        {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    /*
    This method is called once a new widget is created as well as every update interval.
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }


    // Build the intent to call the service
//        Intent intent = new Intent(context.getApplicationContext(),
//              RecipeWidgetService.class);
    //   intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);

    // Update the widgets via the service
    //  context.startService(intent);
//}

//    @Override
//    public void onReceive(Context context, Intent intent) {
//
////        if(intent.getAction().equals(APP)) {
////            NewsArticle article = (NewsArticle)intent.getSerializableExtra(EXTRA_ITEM);
//            if(article != null) {
//                // Handle the click here.
//                // Maybe start a details activity?
//                // Maybe consider using an Activity PendingIntent instead of a Broadcast?
//            }
//        }
//
//     //   super.onReceive(context, intent);
//    }


    @Override
    public void onEnabled(Context context) {
//        // Enter relevant functionality for when the first widget is created
    }

    //
    @Override
    public void onDisabled(Context context) {
//        // Enter relevant functionality for when the last widget is disabled
    }
}

