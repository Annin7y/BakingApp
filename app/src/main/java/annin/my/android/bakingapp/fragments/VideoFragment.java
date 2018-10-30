package annin.my.android.bakingapp.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class VideoFragment extends Fragment
{

    // Tag for logging
    private static final String TAG = VideoFragment.class.getSimpleName();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment
     */
    public VideoFragment()
    {
    }

    ArrayList<Steps> stepsArrayList;
    Steps stepClick;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    ImageView thumbnailUrlImage;
    private int stepsIndex;
    private long mPosition;
    String videoUrl;
    Uri videoUrl_Parse;
    Uri thumbnailUrl_Parse;
    String thumbnailUrl;

    private static final String KEY_POSITION = "position";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //Inflate the Steps fragment layout
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);

        mPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.playerView);
        thumbnailUrlImage = (ImageView) rootView.findViewById(R.id.thumbnail_url);
        Bundle bundle = this.getArguments();
        if (bundle != null) {

            stepClick= getArguments().getParcelable("Steps");
                if(stepClick != null) {
                    videoUrl = stepClick.getVideoUrl();
                    videoUrl_Parse = Uri.parse(videoUrl);
                    initializePlayer(videoUrl_Parse);

                    thumbnailUrl = stepClick.getThumbnailUrl();
                    thumbnailUrl_Parse = Uri.parse(thumbnailUrl);
                }
                    if(thumbnailUrl != null) {
                        Picasso.with(getContext())
                                .load(thumbnailUrl_Parse)
                                .into(thumbnailUrlImage);
                }
        }
        // Return the root view
        return rootView;

    }


    public void initializePlayer(Uri videoUrl)
    {
        if (mExoPlayer == null)
        {
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
    public void onStart()
    {
        super.onStart();
        if (Util.SDK_INT <= 23) {
//            if(thumbnailUrl != null) {
//                Picasso.with(getContext())
//                        .load(thumbnailUrl_Parse)
//                        .into(thumbnailUrlImage);
//            }
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }


    @Override
    public void onStop()
    {
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
}





