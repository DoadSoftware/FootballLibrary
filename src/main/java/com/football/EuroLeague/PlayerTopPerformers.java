package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerTopPerformers {

	@JsonProperty("ranking")
	private List<RankingPlayer> ranking;

	public PlayerTopPerformers() {
		super();
	}

	public List<RankingPlayer> getRanking() {
		return ranking;
	}

	public void setRanking(List<RankingPlayer> ranking) {
		this.ranking = ranking;
	}

	@Override
	public String toString() {
		return "PlayerTopPerformers [ranking=" + ranking + "]";
	}

}