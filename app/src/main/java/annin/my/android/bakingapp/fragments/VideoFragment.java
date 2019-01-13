package annin.my.android.bakingapp.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.model.Recipes;
import annin.my.android.bakingapp.model.Steps;
import butterknife.BindView;
import butterknife.ButterKnife;

import static annin.my.android.bakingapp.fragments.StepsListFragment.STEPS_LIST_INDEX;

public class VideoFragment extends Fragment {

    // Tag for logging
    private static final String TAG = VideoFragment.class.getSimpleName();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment
     */
    public VideoFragment() {
    }

    ArrayList<Steps> stepsArrayList;
    Steps stepClicked;
    SimpleExoPlayer mExoPlayer;
    @BindView(R.id.playerView)
    SimpleExoPlayerView mPlayerView;
    @BindView(R.id.thumbnail_url)
    ImageView thumbnailUrlImage;
    public int stepPosition;
    public int currentStep;
    private long mPlayerPosition ;
    String videoUrl;
    Uri videoUrl_Parse;
    Uri thumbnailUrl_Parse;
    String thumbnailUrl;
    @BindView(R.id.previous_button)
    Button previousButton;
    @BindView(R.id.next_button)
    Button nextButton;
    @BindView(R.id.step_long_description)
    TextView stepLongDescription;
    String stepLongDescriptionUrl;
    boolean mTwoPane;


    private static final String KEY_POSITION = "position";
    public static final String STEPS_LIST_INDEX = "list_index";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the Steps fragment layout
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);
        // Bind the views
        ButterKnife.bind(this, rootView);
        Bundle bundle = this.getArguments();

        if (bundle != null)
        {
            //Track whether to display a two-pane or single-pane UI
            stepClicked = getArguments().getParcelable("Steps");
            if (stepClicked != null) {
                mTwoPane = getArguments().getBoolean("TwoPane");
                stepPosition = getArguments().getInt("StepPosition");
                stepsArrayList = getArguments().getParcelableArrayList("StepsArrayList");
                videoUrl = stepClicked.getVideoUrl();
                videoUrl_Parse = Uri.parse(videoUrl);

                thumbnailUrl = stepClicked.getThumbnailUrl();
                thumbnailUrl_Parse = Uri.parse(thumbnailUrl);

                stepLongDescriptionUrl = stepClicked.getStepLongDescription();
                Log.i("Step: ", stepClicked.getStepLongDescription());
                stepLongDescription.setText(stepLongDescriptionUrl);
                if (thumbnailUrl != null)
                {
                    Picasso.with(getContext())
                            .load(thumbnailUrl_Parse)
                            .into(thumbnailUrlImage);
                }
                }
                if (mTwoPane) {
                    previousButton.setVisibility(View.INVISIBLE);
                    nextButton.setVisibility(View.INVISIBLE);

                }
                else
                    {
                    previousButton.setVisibility(View.VISIBLE);
                    nextButton.setVisibility(View.VISIBLE);

                        //https://stackoverflow.com/questions/45253477/implementing-next-button-in-audio-player-android
                    nextButton.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {

                            if (currentStep < stepsArrayList.size() - 1)
                            {
                                stepPosition = stepPosition + 1;
                            }
                        }});

                        previousButton.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            if (stepPosition > 0)
                            {
                                stepPosition = stepPosition - 1;
                            }
                        }});
                    }}
            if (savedInstanceState != null)
            {
                stepsArrayList = savedInstanceState.getParcelableArrayList(STEPS_LIST_INDEX);
                mPlayerPosition = savedInstanceState.getLong(KEY_POSITION);
            }

        // Return the root view
        return rootView;
    }

    //ExoPlayer code based on: https://codelabs.developers.google.com/codelabs/exoplayer-intro/#2

    public void initializePlayer(Uri videoUrl) {
        if (mExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            mPlayerView.setPlayer((SimpleExoPlayer) mExoPlayer);
            String userAgent = Util.getUserAgent(getContext(), "Baking App");
            MediaSource mediaSource = new ExtractorMediaSource(videoUrl,
                    new DefaultDataSourceFactory(getContext(), userAgent),
                    new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            if(mPlayerPosition != C.TIME_UNSET) {
                mExoPlayer.seekTo(mPlayerPosition);
            }
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        if (Util.SDK_INT > 23 || mExoPlayer == null)
        {
            initializePlayer(videoUrl_Parse);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
            if (mExoPlayer != null)
            {
                mPlayerPosition = mExoPlayer.getCurrentPosition();
            }
            if (Util.SDK_INT <= 23)
            {
            releasePlayer();
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if ((Util.SDK_INT <= 23 || mExoPlayer == null))
        {
                mPlayerPosition = mExoPlayer.getCurrentPosition();
            }
       }

    @Override
    public void onStop()
    {
        super.onStop();
            if (Util.SDK_INT > 23 ||  mExoPlayer != null)
            {
                mExoPlayer.getCurrentPosition();
            }
        releasePlayer();
        }

    /**
     * Release ExoPlayer.
     */
    private void releasePlayer()
    {
        if (mExoPlayer != null)
        {
            mPlayerPosition = mExoPlayer.getCurrentPosition();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        //Save the fragment's state here
        outState.putParcelableArrayList(STEPS_LIST_INDEX, stepsArrayList);
        outState.putLong(KEY_POSITION, mPlayerPosition);
        super.onSaveInstanceState(outState);
    }
}



