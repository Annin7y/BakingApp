package annin.my.android.bakingapp.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.fragments.VideoFragment;
import annin.my.android.bakingapp.pojo.Steps;

public class VideoPhoneActivity extends AppCompatActivity
{
    private static final String LOG_TAG = VideoPhoneActivity.class.getSimpleName();

    private Steps stepClicked;
    public boolean mTwoPane;
    public int stepIndex;
    public ArrayList<Steps> stepsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videophone);


        if (getIntent() != null && getIntent().getExtras() != null)
        {
            stepClicked = getIntent().getExtras().getParcelable("Steps");
            stepIndex = getIntent().getIntExtra("StepIndex", -1);
            mTwoPane = getIntent().getBooleanExtra("TwoPane", true);
            stepsArrayList = new ArrayList<>();
            stepsArrayList = getIntent().getParcelableArrayListExtra("StepsArrayList");

            FragmentManager fragmentManager = getSupportFragmentManager();
            VideoFragment videoFragment = new VideoFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("Steps", stepClicked);
            bundle.putBoolean("TwoPane", mTwoPane);
            bundle.putInt("StepIndex", stepIndex);
            bundle.putParcelableArrayList("StepsArrayList",stepsArrayList);
            videoFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.video_fragment_container, videoFragment).commit();
            }
        }
    }

