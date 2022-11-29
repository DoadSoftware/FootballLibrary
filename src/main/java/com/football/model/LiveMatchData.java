package com.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LiveMatchData {
	
	@JsonProperty("data")
	private TeamShortMatchStats teamShortMatchStats;

	public TeamShortMatchStats getTeamShortMatchStats() {
		return teamShortMatchStats;
	}

	public void setTeamShortMatchStats(TeamShortMatchStats teamShortMatchStats) {
		this.teamShortMatchStats = teamShortMatchStats;
	}

	@Override
	public String toString() {		
		return "LiveMatchData [teamShortMatchStats=" + teamShortMatchStats + "]";
	}
	
}
