package annin.my.android.bakingapp.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.model.Steps;

public class VideoPhoneActivity extends AppCompatActivity
{
    private static final String LOG_TAG = VideoPhoneActivity.class.getSimpleName();

    TextView stepDescription;
    ArrayList<Steps> stepsArrayList;
    Steps stepClick;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    ImageView thumbnailUrlImage;

    private long mPosition;

    String videoUrl;
    String thumbnailUrl;

    private static final String KEY_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videophone);

        mPlayerView = (SimpleExoPlayerView) findViewById(R.id.playerView);
        thumbnailUrlImage = (ImageView) findViewById(R.id.thumbnail_url);

        if (getIntent() != null && getIntent().getExtras() != null) {
            Steps stepClick = getIntent().getExtras().getParcelable("Step");

            videoUrl = stepClick.getVideoUrl();
            thumbnailUrl = stepClick.getThumbnailUrl();
        }
    }



    }

