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
