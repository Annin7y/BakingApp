package annin.my.android.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.model.Recipes;
import annin.my.android.bakingapp.ui.IngredientStepsActivity;
import annin.my.android.bakingapp.ui.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {
    //The following code is based on the code in these links:
    //https://joshuadonlan.gitbooks.io/onramp-android/content/widgets/collection_widgets.html
    //http://www.vogella.com/tutorials/AndroidWidgets/article.html

    public static final String ACTION_VIEW_DETAILS =
            "annin.my.android.RecipeWidgetProvider.ACTION_VIEW_DETAILS";

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

            //    Build the intent to call the service
            Intent intent = new Intent(context.getApplicationContext(),
                    RecipeWidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
             Log.d("onUpdate","method working");
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);
            views.setRemoteAdapter(R.id.appwidget_list, intent);
            views.setEmptyView(R.id.appwidget_list, R.id.empty);

            Intent detailIntent = new Intent(context,IngredientStepsActivity.class);
            PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, detailIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setPendingIntentTemplate(R.id.appwidget_list, pIntent);

            appWidgetManager.updateAppWidget(widgetId,views);
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds);

        }

    public static void sendUpdateIntent(Context context)
    {
        Intent i = new Intent(context, RecipeWidgetProvider.class);
        i.setAction(RecipeWidgetProvider.ACTION_VIEW_DETAILS);
        context.sendBroadcast(i);
    }


    @Override
    public void onReceive(Context context, Intent intent) {
     //   http://android-er.blogspot.com/2010/10/update-widget-in-onreceive-method.html
        super.onReceive(context, intent);

        if (ACTION_VIEW_DETAILS.equals(intent.getAction())) {

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName thisAppWidget = new ComponentName(context.getPackageName(), RecipeWidgetProvider.class.getName());
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);

            onUpdate(context, appWidgetManager, appWidgetIds);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.appwidget_list);

        }
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

