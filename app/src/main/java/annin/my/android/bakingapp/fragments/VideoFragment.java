package annin.my.android.bakingapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import annin.my.android.bakingapp.R;
import butterknife.BindView;

public class VideoFragment extends Fragment {

    // Tag for logging
    private static final String TAG = VideoFragment.class.getSimpleName();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment
     */
    public VideoFragment() {
    }

    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;

    private long mPosition;

    private static final String KEY_POSITION = "position";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the Steps fragment layout
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);

        mPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.playerView);

        // Return the root view
        return rootView;

        //  initializePlayer();
    }
}


// private void initializePlayer(){
// if (mExoplayer == null) {
//TrackSelector trackSelector = new DefaultTrackSelector();
//  LoadControl loadControl = new DefaultLoadControl();
//mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
//        mPlayerView.setPlayer((SimpleExoPlayer) mExoPlayer);
//        String userAgent = Util.getUserAgent(getContext(), "BakingApp");
//        MediaSource mediaSource = new ExtractorMediaSource(videoURL,new DefaultDataSourceFactory(
//                this, userAgent), new DefaultExtractorsFactory(), null, null);
//        new DefaultDataSourceFactory(getContext(), userAgent),
//        new DefaultExtractorsFactory(), null, null);
//        mExoPlayer.prepare(mediaSource);
//        mExoPlayer.setPlayWhenReady(true);


//    @Override
//    public void onPause() {
//        super.onPause();
// if (Util.SDK_INT <= 23) {
//            releasePlayer();
//}
//        }
//
//
//    @Override
//    public void onStop() {
//        super.onStop();
//if (Util.SDK_INT <= 23) {
//            releasePlayer();
//        }
//        }
//private void releasePlayer() {
  //  if (mExoPlayer != null) {
   //     mPosition = mExoPlayer.getCurrentPosition();
   //     mExoPlayer.release();
    //    mExoPlayer = null;





