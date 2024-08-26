package com.football.EuroLeague;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopPerformers {
	
	@JsonProperty("playerTopPerformers")
	private PlayerTopPerformers playerTopPerformers;	
	
	@JsonProperty("teamTopPerformers")
	private TeamTopPerformers teamTopPerformers;

	public TopPerformers() {
		super();
	}

	public PlayerTopPerformers getPlayerTopPerformers() {
		return playerTopPerformers;
	}

	public void setPlayerTopPerformers(PlayerTopPerformers playerTopPerformers) {
		this.playerTopPerformers = playerTopPerformers;
	}

	public TeamTopPerformers getTeamTopPerformers() {
		return teamTopPerformers;
	}

	public void setTeamTopPerformers(TeamTopPerformers teamTopPerformers) {
		this.teamTopPerformers = teamTopPerformers;
	}

	@Override
	public String toString() {
		return "TopPerformers [playerTopPerformers=" + playerTopPerformers + ", teamTopPerformers=" + teamTopPerformers
				+ "]";
	}
	
}