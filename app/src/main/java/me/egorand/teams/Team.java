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

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import java.util.Comparator;

public class Team {

    public final String name;
    public final @DrawableRes int logoRes;

    public Team(@NonNull String name, @DrawableRes int logoRes) {
        this.name = name;
        this.logoRes = logoRes;
    }

    public static final Comparator<Team> BY_NAME_ALPHABETICAL = new Comparator<Team>() {
        @Override public int compare(Team lhs, Team rhs) {
            return lhs.name.compareTo(rhs.name);
        }
    };
}
