package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)


public class SeasonalTeams {
	@JsonProperty("contestant")
	List<TournamentContestants>contestant;
	@JsonProperty("lastUpdated")
	String lastUpdated;
	@Override
	public String toString() {
		return "SeasonalTeams [contestant=" + contestant + "]";
	}

	public SeasonalTeams() {
		super();
	}

	public List<TournamentContestants> getContestant() {
		return contestant;
	}

	public void setContestant(List<TournamentContestants> contestant) {
		this.contestant = contestant;
	}

	

}
