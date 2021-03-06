/*
 * Copyright 2015 - 2016 Egor Andreevici
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

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
