package annin.my.android.bakingapp.recyclerviewadapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.pojo.Steps;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsAdapterViewHolder>
{
    private static final String TAG = StepsAdapter.class.getSimpleName();
    private ArrayList<Steps> stepsList = new ArrayList<Steps>();
    private StepsAdapter.StepsAdapterOnClickListener mClickListener;

    /**
     * The interface that receives onClick messages.
     */
    public interface StepsAdapterOnClickListener
    {
        void onClick(Steps stepClick);
    }

    /**
     * Creates a StepsAdapter.
     *
     * @param clickListener The on-click listener for this adapter. This single listener is called
     *                      when an item is clicked.
     */
    public StepsAdapter(StepsAdapterOnClickListener clickListener, ArrayList<Steps> stepsList)
    {
        mClickListener = clickListener;
        this.stepsList = stepsList;
    }

    /**
     * Cache of the children views for a steps list item.
     */
    public class StepsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        @BindView(R.id.step_short_desc)
        public TextView stepShortDescription;

        public StepsAdapterViewHolder(View view)
        {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        /**
         * This gets called by the child views during a click.
         *
         * @param v The View that was clicked
         */
        @Override
        public void onClick(View v)
        {
            int adapterPosition = getAdapterPosition();
            Steps position = stepsList.get(adapterPosition);
            mClickListener.onClick(position);
        }
    }

    @Override
    public StepsAdapter.StepsAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.steps_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new StepsAdapter.StepsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsAdapter.StepsAdapterViewHolder holder, int position)
    {
        //Binding data
        final Steps stepsView = stepsList.get(position);
        holder.stepShortDescription.setText(stepsView.getStepShortDescription());

    }

    @Override
    public int getItemCount()
    {
        return stepsList.size();
    }

    public void setStepsList(ArrayList<Steps> mStepsList)
    {
        this.stepsList = mStepsList;
        notifyDataSetChanged();
    }
}



