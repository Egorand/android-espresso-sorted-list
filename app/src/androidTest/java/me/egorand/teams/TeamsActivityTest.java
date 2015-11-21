package me.egorand.teams;

import android.support.test.espresso.core.deps.guava.collect.Ordering;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TeamsActivityTest {

    @Rule public ActivityTestRule<TeamsActivity> activityTestRule = new ActivityTestRule<>(TeamsActivity.class);

    @Test
    public void teamsListIsSortedAlphabetically() {
        onView(withId(android.R.id.list)).check(matches(isSortedAlphabetically()));
    }

    private static Matcher<View> isSortedAlphabetically() {
        return new TypeSafeMatcher<View>() {

            private final List<String> teamNames = new ArrayList<>();

            @Override
            protected boolean matchesSafely(View item) {
                RecyclerView recyclerView = (RecyclerView) item;
                TeamsAdapter teamsAdapter = (TeamsAdapter) recyclerView.getAdapter();
                teamNames.clear();
                teamNames.addAll(extractTeamNames(teamsAdapter.getTeams()));
                return Ordering.natural().isOrdered(teamNames);
            }

            private List<String> extractTeamNames(List<Team> teams) {
                List<String> teamNames = new ArrayList<>();
                for (Team team : teams) {
                    teamNames.add(team.name);
                }
                return teamNames;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("has items sorted alphabetically: " + teamNames);
            }
        };
    }
}
