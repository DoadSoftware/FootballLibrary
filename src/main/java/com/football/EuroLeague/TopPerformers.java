package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopPerformers {
	
	@JsonProperty("playerTopPerformers")
	private List<RankingPlayer> playerTopPerformers;	
	
	@JsonProperty("teamTopPerformers")
	private List<RankingPlayer> teamTopPerformers;

	public TopPerformers() {
		super();
	}

	public List<RankingPlayer> getPlayerTopPerformers() {
		return playerTopPerformers;
	}

	public void setPlayerTopPerformers(List<RankingPlayer> playerTopPerformers) {
		this.playerTopPerformers = playerTopPerformers;
	}

	public List<RankingPlayer> getTeamTopPerformers() {
		return teamTopPerformers;
	}

	public void setTeamTopPerformers(List<RankingPlayer> teamTopPerformers) {
		this.teamTopPerformers = teamTopPerformers;
	}

	@Override
	public String toString() {
		return "TopPerformers [playerTopPerformers=" + playerTopPerformers + ", teamTopPerformers=" + teamTopPerformers
				+ "]";
	}

	
	
}