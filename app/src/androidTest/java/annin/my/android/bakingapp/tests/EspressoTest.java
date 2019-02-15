package annin.my.android.bakingapp.tests;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import annin.my.android.bakingapp.R;
import annin.my.android.bakingapp.ui.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    //Testing clicking on a position in the RecyclerView
    //Code based on the following YouTube video: https://www.youtube.com/watch?v=56xINIkzBy8
    @Test
    public void scrollToPosition() {
        onView(withId(R.id.recyclerview_main)).perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
    }

    //Testing recipe count shown
    //Code based on the following code samples:
    //http://qaru.site/questions/229713/how-to-count-recyclerview-items-with-espresso
    //https://github.com/twilio/mobile-sdk-sample-android/blob/master/twilio-auth-sample/src/androidTest/java/com/twilio/authsample/matchers/RecyclerViewItemCountAssertion.java
    //https://stackoverflow.com/questions/36399787/how-to-count-recyclerview-items-with-espresso/37339656
    //https://stackoverflow.com/questions/51678563/how-to-test-recyclerview-viewholder-text-with-espresso
    public class RecyclerViewItemCountAssertion implements ViewAssertion
    {
        private final int expectedCount;

        public RecyclerViewItemCountAssertion(int expectedCount)
        {
            this.expectedCount = expectedCount;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException)
        {
            if (noViewFoundException != null)
            {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            assertThat(adapter.getItemCount(), is(expectedCount));

        }
    }

    @Test
    public void recipeCountTest() {
        onView(withId(R.id.recyclerview_main)).check(new RecyclerViewItemCountAssertion(4));
    }
}