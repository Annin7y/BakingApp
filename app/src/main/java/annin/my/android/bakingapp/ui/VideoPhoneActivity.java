package annin.my.android.bakingapp.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.fragments.VideoFragment;
import annin.my.android.bakingapp.pojo.Steps;

import static annin.my.android.bakingapp.fragments.VideoFragment.STEPS_LIST_INDEX;

public class VideoPhoneActivity extends AppCompatActivity
{
    private static final String LOG_TAG = VideoPhoneActivity.class.getSimpleName();

    private Steps stepClicked;
    public boolean mTwoPane;
    public int stepIndex;
    public ArrayList<Steps> stepsArrayList;
    public static final String STEPS_LIST_INDEX = "list_index";
    public static final String KEY_VIDEO_FRAGMENT = "videoFragment";
    public VideoFragment videoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videophone);

        //Fragment savedInstanceState code based on this github example:
        //https://github.com/nnjoshi14/android-poc/blob/master/FragmentState/app/src/main/java/com/njoshi/androidpoc/fragmentstate/MainActivity.java
        if (savedInstanceState == null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            videoFragment = new VideoFragment();

        if (getIntent() != null && getIntent().getExtras() != null) {
            stepClicked = getIntent().getExtras().getParcelable("Steps");
            stepIndex = getIntent().getIntExtra("StepIndex", -1);
            mTwoPane = getIntent().getBooleanExtra("TwoPane", true);
            stepsArrayList = new ArrayList<>();
            stepsArrayList = getIntent().getParcelableArrayListExtra("StepsArrayList");

            Bundle bundle = new Bundle();
            bundle.putParcelable("Steps", stepClicked);
            bundle.putBoolean("TwoPane", mTwoPane);
            bundle.putInt("StepIndex", stepIndex);
            bundle.putParcelableArrayList("StepsArrayList", stepsArrayList);
            videoFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.video_fragment_container, videoFragment).commit();
        } }
       else
        {
            stepsArrayList = savedInstanceState.getParcelableArrayList(STEPS_LIST_INDEX);
            videoFragment = (VideoFragment)getSupportFragmentManager().getFragment(savedInstanceState,KEY_VIDEO_FRAGMENT);

        }}


    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putParcelableArrayList(STEPS_LIST_INDEX, stepsArrayList);
        getSupportFragmentManager().putFragment(outState,KEY_VIDEO_FRAGMENT, videoFragment);
        super.onSaveInstanceState(outState);
    }
    }

