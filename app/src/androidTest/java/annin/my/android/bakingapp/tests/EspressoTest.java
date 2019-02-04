package annin.my.android.bakingapp.tests;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.ui.MainActivity;

@RunWith(AndroidJUnit4.class)
public class EspressoTest
{

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
        new ActivityTestRule<>(MainActivity.class);

    //Testing clicking on a position in the RecyclerView
    //Code based on the following YouTube video: https://www.youtube.com/watch?v=56xINIkzBy8
   @Test
    public void scrollToPosition()
   {
       Espresso.onView(ViewMatchers.withId(R.id.recyclerview_main)).perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
   }

}
