package annin.my.android.bakingapp.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.fragments.VideoFragment;
import annin.my.android.bakingapp.pojo.Steps;

public class VideoPhoneActivity extends AppCompatActivity
{
    private static final String LOG_TAG = VideoPhoneActivity.class.getSimpleName();

    private Steps stepClicked;
    private static final String KEY_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videophone);

        if (getIntent() != null && getIntent().getExtras() != null) {
            stepClicked = getIntent().getExtras().getParcelable("Steps");

            FragmentManager fragmentManager = getSupportFragmentManager();
            VideoFragment videoFragment = new VideoFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("Steps", stepClicked);
            videoFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.video_fragment_container, videoFragment).commit();
            }
        }
    }

