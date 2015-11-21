package me.egorand.teams;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;

    private final List<Team> teams;

    public TeamsAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
        this.teams = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.row_team, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Team team = teams.get(position);
        holder.teamLogo.setImageResource(team.logoRes);
        holder.teamName.setText(team.name);
    }

    @Override public int getItemCount() {
        return teams.size();
    }

    public void setTeams(List<Team> teams) {
        this.teams.clear();
        this.teams.addAll(teams);
        notifyItemRangeInserted(0, teams.size());
    }

    public List<Team> getTeams() {
        return Collections.unmodifiableList(teams);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView teamLogo;
        TextView teamName;

        public ViewHolder(View itemView) {
            super(itemView);
            teamLogo = (ImageView) itemView.findViewById(R.id.team_logo);
            teamName = (TextView) itemView.findViewById(R.id.team_name);
        }
    }
}
