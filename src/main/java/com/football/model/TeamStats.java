package com.football.model;

import java.util.List;

public class TeamStats {
	
	String teamName;
	List<TopStats> topStats;
	
	
	public TeamStats(String teamName, List<TopStats> topStats) {
		super();
		this.teamName = teamName;
		this.topStats = topStats;
	}

	public List<TopStats> getTopStats() {
		return topStats;
	}

	public void setTopStats(List<TopStats> topStats) {
		this.topStats = topStats;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	
}

