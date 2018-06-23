package annin.my.android.bakingapp.recyclerviewadapters;

import android.content.Context;

import annin.my.android.bakingapp.custom.Steps;

public class StepsAdapter {

    private static final String TAG = StepsAdapter.class.getSimpleName();

    private Context context;


    /**
     * The interface that receives onClick messages.
     */
    public interface StepsAdapterOnClickHandler {
        void onClick(Steps textClick);
    }


}
