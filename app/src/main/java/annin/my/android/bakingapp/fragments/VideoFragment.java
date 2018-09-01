package annin.my.android.bakingapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import annin.my.android.bakingapp.R;
import butterknife.BindView;

public class VideoFragment extends Fragment {

    private static final String TAG = VideoFragment.class.getSimpleName();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment
     */
    public VideoFragment() {
    }

    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the Steps fragment layout
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);

        mPlayerView = (SimpleExoPlayerView) findViewById(R.id.playerView);




//    @Override
//    public void onPause() {
//        super.onPause();
//
//            releasePlayer();
//        }
//
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//            releasePlayer();
//        }
    }



