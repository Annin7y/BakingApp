package annin.my.android.bakingapp.recyclerviewadapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.custom.Steps;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsAdapterViewHolder> {

    private static final String TAG = StepsAdapter.class.getSimpleName();
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
    public StepsAdapter(StepsAdapter.StepsAdapterOnClickHandler clickHandler, ArrayList<Steps> stepsList) {
        this.stepsList = stepsList;
        mClickHandler = clickHandler;
    }

    /**
     * Cache of the children views for a steps list item.
     */
    public class StepsAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.step_short_desc)
        public TextView stepShortDescription;

        @BindView(R.id.step_description)
        public TextView stepDescription;

        public StepsAdapterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    @Override
    public StepsAdapter.StepsAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.steps_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new StepsAdapter.StepsAdapterViewHolder(view);
    }


    @Override
    public void onBindViewHolder(StepsAdapter.StepsAdapterViewHolder holder, int position) {

        //Binding data
        final Steps stepsView = stepsList.get(position);

        holder.stepShortDescription.setText(stepsView.getStepShortDescription());
        holder.stepDescription.setText(stepsView.getStepDescription());
    }

    @Override
    public int getItemCount()

    {
        return stepsList.size();
    }

    public void setStepsList(ArrayList<Steps> mStepsList) {
        this.stepsList = mStepsList;
        notifyDataSetChanged();
    }
}



