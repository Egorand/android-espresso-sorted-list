package me.egorand.teams;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView teamsRecyclerView = (RecyclerView) findViewById(android.R.id.list);
        teamsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        TeamsAdapter teamsAdapter = new TeamsAdapter(LayoutInflater.from(this));
        teamsAdapter.setTeams(createTeams());
        teamsRecyclerView.setAdapter(teamsAdapter);
    }

    private List<Team> createTeams() {
        List<Team> teams = new ArrayList<>();
        String[] teamNames = getResources().getStringArray(R.array.team_names);
        TypedArray teamLogos = getResources().obtainTypedArray(R.array.team_logos);
        for (int i = 0; i < teamNames.length; i++) {
            Team team = new Team(teamNames[i], teamLogos.getResourceId(i, -1));
            teams.add(team);
        }
        teamLogos.recycle();
        Collections.sort(teams, Team.BY_NAME_ALPHABETICAL);
        return teams;
    }
}
