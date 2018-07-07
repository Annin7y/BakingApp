package annin.my.android.bakingapp.recyclerviewadapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.custom.Recipes;
import annin.my.android.bakingapp.custom.Steps;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsAdapterViewHolder> {

    private static final String TAG = StepsAdapter.class.getSimpleName();
    private Context context;
    private ArrayList<Steps> stepsList = new ArrayList<Steps>();
    private StepsAdapter.StepsAdapterOnClickHandler mClickHandler;

    /**
     * The interface that receives onClick messages.
     */
    public interface StepsAdapterOnClickHandler {
        void onClick(Steps textClick);
    }
    /**
     * Creates a RecipesAdapter.
     *
     * @param clickHandler The on-click handler for this adapter. This single handler is called
     *                     when an item is clicked.
     */
    public StepsAdapter(StepsAdapter.StepsAdapterOnClickHandler clickHandler, ArrayList<Steps> stepsList, Context context) {
        this.stepsList = stepsList;
        this.context = context;
        mClickHandler = clickHandler;
    }

    /**
     * Cache of the children views for a steps list item.
     */
    public class StepsAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ingredient_quantity)
        public TextView ingredientQuantity;

        @BindView(R.id.ingredient_measure)
        public TextView ingredientMeasure;

        @BindView(R.id.ingredient_name)
        public TextView ingredientName;


        public StepsAdapterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
