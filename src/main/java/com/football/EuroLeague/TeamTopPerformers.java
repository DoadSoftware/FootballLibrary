package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamTopPerformers {

	@JsonProperty("ranking")
	private List<RankingTeam> ranking;

	public TeamTopPerformers() {
		super();
	}

	public List<RankingTeam> getRanking() {
		return ranking;
	}

	public void setRanking(List<RankingTeam> ranking) {
		this.ranking = ranking;
	}

	@Override
	public String toString() {
		return "TeamTopPerformers [ranking=" + ranking + "]";
	}

}