package annin.my.android.bakingapp.recyclerviewadapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.pojo.Recipes;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesAdapterViewHolder>
{
    private static final String TAG = RecipesAdapter.class.getSimpleName();

    private ArrayList<Recipes> recipesList = new ArrayList<Recipes>();
    private Context context;
    private RecipesAdapterOnClickHandler mRecipeClickHandler;
    public static final int IMAGE_HEIGHT = 185;
    public static final int IMAGE_WIDTH = 50;

    /**
     * The interface that receives onClick messages.
     */
    public interface RecipesAdapterOnClickHandler
    {
        void onClick(Recipes imageRecipeClick);
    }

    /**
     * Creates a RecipesAdapter.
     *
     * @param recipeClickHandler The on-click handler for this adapter. This single handler is called
     *                     when an item is clicked.
     */
    public RecipesAdapter(RecipesAdapterOnClickHandler recipeClickHandler, ArrayList<Recipes> recipesList, Context context)
    {
        mRecipeClickHandler = recipeClickHandler;
        this.recipesList = recipesList;
        this.context = context;
    }

    /**
     * Cache of the children views for a recipes list item.
     */
    public class RecipesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        @BindView(R.id.imageView)
        ImageView imageView;

        @BindView(R.id.recipeView)
        TextView recipeView;


        public RecipesAdapterViewHolder(View view)
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
            Recipes imageRecipeClick = recipesList.get(adapterPosition);
            mRecipeClickHandler.onClick(imageRecipeClick);
        }
    }

    @Override
    public RecipesAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recipe_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new RecipesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipesAdapterViewHolder holder, int position)
    {
        //Binding data
        final Recipes recipesView = recipesList.get(position);

        holder.recipeView.setText(recipesView.getRecipeName());

        if (!recipesView.getRecipeImage().isEmpty())
        {
            Picasso.with(context)
                    .load(recipesView.getRecipeImage())
                    .error(R.drawable.user_placeholder_error)
                    .into(holder.imageView);

        }
        else if (recipesView.getRecipeName().equals("Nutella Pie"))
        {
            Picasso.with(context)
                    .load(R.drawable.nutella_pie)
                    //if the image can't be loaded the following error message/image will be displayed
                    .error(R.drawable.user_placeholder_error)
                    .into(holder.imageView);
        }
        else if (recipesView.getRecipeName().equals("Brownies"))
        {
            Picasso.with(context)
                    .load(R.drawable.brownies_recipe)
                    //if the image can't be loaded the following error message/image will be displayed
                    .into(holder.imageView);

        }
        else if (recipesView.getRecipeName().equals("Yellow Cake"))
        {
            Picasso.with(context)
                    .load(R.drawable.yellow_cake)
                    //if the image can't be loaded the following error message/image will be displayed
                    .error(R.drawable.user_placeholder_error)
                    .into(holder.imageView);

        }
        else if (recipesView.getRecipeName().equals("Cheesecake"))
        {
            Picasso.with(context)
                    .load(R.drawable.cheesecake_recipe)
                    //if the image can't be loaded the following error message/image will be displayed
                    .error(R.drawable.user_placeholder_error)
                    .into(holder.imageView);
        }
        else
            {
            Picasso.with(context)
                    .load(R.drawable.user_placeholder_error)
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount()
    {
        return recipesList.size();
    }

    public void setRecipesList(ArrayList<Recipes> mRecipesList)
    {
        this.recipesList = mRecipesList;
        notifyDataSetChanged();
    }
}
