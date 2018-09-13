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

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.model.Recipes;
import annin.my.android.bakingapp.model.Steps;
import annin.my.android.bakingapp.recyclerviewadapters.StepsAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;


public class StepsListFragment extends Fragment implements StepsAdapter.StepsAdapterOnClickListener {

    // Tag for logging
    private final String TAG = StepsListFragment.class.getSimpleName();

    @BindView(R.id.recyclerview_steps)
    RecyclerView mRecyclerView;

    ArrayList<Steps> stepsArrayList;

    Recipes recipes;

    // Final Strings to store state information about the list of steps and list index
    public static final String STEPS_LIST_INDEX = "list_index";


    // Define a new interface OnStepsClickListener that triggers a callback in the host activity
    OnStepClickListener mCallback;

    // OnStepsClickListener interface, calls a method in the host activity named onStepSelected
    public interface OnStepClickListener {
        void onStepSelected(int position);
    }

    // Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

//        // This makes sure that the host activity has implemented the callback interface
//        // If not, it throws an exception
        try {
            mCallback = (OnStepClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnStepSelectedListener");
        }
    }
//
   /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment
     */
    public StepsListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        //Inflate the Steps fragment layout
        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);
//        // Bind the views
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

        StepsAdapter stepsAdapter = new StepsAdapter(this, stepsArrayList);
        mRecyclerView.setAdapter(stepsAdapter);

        mRecyclerView.setOnClickListener(new View.OnClickListener()      {
        @Override
                public void onClick(View view){

            mCallback.onStepSelected(position);

    }
        });

        // Return the root view
        return rootView;
}
}
        