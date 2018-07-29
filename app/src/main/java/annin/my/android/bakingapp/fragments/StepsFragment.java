package annin.my.android.bakingapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.custom.Recipes;
import annin.my.android.bakingapp.custom.Steps;
import annin.my.android.bakingapp.recyclerviewadapters.StepsAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;


public class StepsFragment extends Fragment  {

    private final String TAG = StepsFragment.class.getSimpleName();

    @BindView(R.id.recyclerview_steps)
    RecyclerView mRecyclerView;

    ArrayList<Steps> stepsArrayList;

    Recipes recipes;

    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment
     */
    public StepsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the Ingredients fragment layout
        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        // Bind the views
        ButterKnife.bind(this, rootView);

       Bundle bundle = this.getArguments();
       if (bundle != null) {

           recipes = getArguments().getParcelable("Recipes");
        }
        stepsArrayList = new ArrayList<>();
        stepsArrayList  = recipes.getRecipeSteps();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        Log.i("listSteps", stepsArrayList.size() + "");

       StepsAdapter stepsAdapter = new StepsAdapter(stepsArrayList);
        mRecyclerView.setAdapter(stepsAdapter);
        return rootView;
    }
}
