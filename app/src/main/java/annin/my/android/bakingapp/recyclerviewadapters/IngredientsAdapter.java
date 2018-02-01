package annin.my.android.bakingapp.recyclerviewadapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.custom.Ingredients;

/**
 * Created by Maino96-10022 on 1/25/2018.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsAdapterViewHolder> {

    private static final String TAG = IngredientsAdapter.class.getSimpleName();

    private ArrayList<Ingredients> ingredientsList = new ArrayList<Ingredients>();
    private Context context;

    /**
     * Creates an Ingredients Adapter.
     */
    public IngredientsAdapter(ArrayList<Ingredients> ingredientsList, Context context) {
        this.ingredientsList = ingredientsList;
        this.context = context;
    }

    /**
     * Cache of the children views for an ingredients list item.
     */
    public class IngredientsAdapterViewHolder extends RecyclerView.ViewHolder  {

        public TextView ingredientQuantity;
        public TextView ingredientMeasure;
        public TextView ingredientName;


        public IngredientsAdapterViewHolder(View view) {
            super(view);
            ingredientQuantity = (TextView) view.findViewById(R.id.ingredient_name);
            ingredientMeasure  = (TextView) view.findViewById(R.id.ingredient_measure);
            ingredientName = (TextView) view.findViewById(R.id.ingredient_measure);
        }
    }

    @Override
    public IngredientsAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.ingredient_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new IngredientsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientsAdapterViewHolder holder, int position) {

        //Binding data
        final Ingredients ingredientsView = ingredientsList.get(position);

        holder.ingredientQuantity.setText(ingredientsView.getIngredientQuantity());
        holder.ingredientMeasure.setText(ingredientsView.getIngredientMeasure());
        holder.ingredientName.setText(ingredientsView.getIngredientName());
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    public void setIngredientsList(ArrayList<Ingredients> mIngredientsList) {
        this.ingredientsList.addAll(mIngredientsList);
        notifyDataSetChanged();
    }

}
