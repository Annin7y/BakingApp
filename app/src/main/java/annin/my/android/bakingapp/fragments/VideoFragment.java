package annin.my.android.bakingapp.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

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
    Steps stepClick;
    SimpleExoPlayer mExoPlayer;
    @BindView(R.id.playerView)
    SimpleExoPlayerView mPlayerView;
    @BindView(R.id.thumbnail_url)
    ImageView thumbnailUrlImage;
    private int stepsIndex;
    private long mPosition;
    String videoUrl;
    Uri videoUrl_Parse;
    Uri thumbnailUrl_Parse;
    String thumbnailUrl;
    @BindView(R.id.previous_button)
    Button previousButton;
    @BindView(R.id.next_button)
    Button nextButton;

    private static final String KEY_POSITION = "position";
    public static final String STEPS_LIST_INDEX = "list_index";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the Steps fragment layout
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);
        // Bind the views
        ButterKnife.bind(this, rootView);
        Bundle bundle = this.getArguments();
        if (bundle != null) {

                stepClick = getArguments().getParcelable("Steps");
                if (stepClick != null) {
                    videoUrl = stepClick.getVideoUrl();
                    videoUrl_Parse = Uri.parse(videoUrl);

                    thumbnailUrl = stepClick.getThumbnailUrl();
                    thumbnailUrl_Parse = Uri.parse(thumbnailUrl);

                    if (thumbnailUrl != null) {
                        Picasso.with(getContext())
                                .load(thumbnailUrl_Parse)
                                .into(thumbnailUrlImage);
                    }
                }
                if (savedInstanceState != null) {
                    stepsArrayList = savedInstanceState.getParcelableArrayList(STEPS_LIST_INDEX);

                }
                }


        // Return the root view
        return rootView;

    }

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
            mExoPlayer.setPlayWhenReady(true);

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT <= 23 || mExoPlayer == null) {
            initializePlayer(videoUrl_Parse);

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            if (mExoPlayer != null) {
                releasePlayer();
                mPosition = mExoPlayer.getCurrentPosition();

            }

        }
    }


    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    /**
     * Release ExoPlayer.
     */
    private void releasePlayer() {
        if (mExoPlayer != null) {
            mPosition = mExoPlayer.getCurrentPosition();
            mExoPlayer.release();
            mExoPlayer = null;

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's state here
        outState.putParcelableArrayList(STEPS_LIST_INDEX, stepsArrayList);
        outState.putLong(KEY_POSITION, mPosition);
        super.onSaveInstanceState(outState);
    }

}



