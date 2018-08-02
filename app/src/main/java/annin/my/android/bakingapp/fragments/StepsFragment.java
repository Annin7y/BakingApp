package annin.my.android.bakingapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.custom.Recipes;
import annin.my.android.bakingapp.custom.Steps;
import annin.my.android.bakingapp.recyclerviewadapters.RecipesAdapter;
import annin.my.android.bakingapp.recyclerviewadapters.StepsAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;


public class StepsFragment extends Fragment implements StepsAdapter.StepsAdapterOnClickHandler {

    private final String TAG = StepsFragment.class.getSimpleName();

    @BindView(R.id.recyclerview_steps)
    RecyclerView mRecyclerView;

    ArrayList<Steps> stepsArrayList;

    Recipes recipes;
    // Define a new interface OnImageClickListener that triggers a callback in the host activity
    OnStepsClickListener mCallback;

    // OnImageClickListener interface, calls a method in the host activity named onImageSelected
    public interface OnStepsClickListener {
        void onStepSelected(int position);
    }

    // Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mCallback = (OnStepsClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnSTEPSClickListener");
        }
    }

    //  private SimpleExoPlayer mExoPlayer;
    // private SimpleExoPlayerView mPlayerView;

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
        stepsArrayList = recipes.getRecipeSteps();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        Log.i("listSteps", stepsArrayList.size() + "");

      //  mRecyclerView.setAdapter(stepsAdapter);

        StepsAdapter stepsAdapter = new StepsAdapter(getActivity(), stepsArrayList, new StepsAdapter.StepsAdapterOnClickHandler() {

            @Override
            public void onClick(int position) {

            }
        });

                return rootView;

        }}