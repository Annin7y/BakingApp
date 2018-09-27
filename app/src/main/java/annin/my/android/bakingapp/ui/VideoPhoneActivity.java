package annin.my.android.bakingapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import annin.my.android.bakingapp.R;

public class VideoPhoneActivity extends AppCompatActivity
{
    private static final String TAG = VideoPhoneActivity.class.getSimpleName();

    TextView stepDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videophone);
    }
}
