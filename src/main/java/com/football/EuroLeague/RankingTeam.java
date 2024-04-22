package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RankingTeam {

	@JsonProperty("name")
	private String name;
	  
	@JsonProperty("team")
	private List<TopPerformerTeams> team;

	public RankingTeam() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TopPerformerTeams> getTeam() {
		return team;
	}

	public void setTeam(List<TopPerformerTeams> team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "RankingTeam [name=" + name + ", team=" + team + "]";
	}
	
}