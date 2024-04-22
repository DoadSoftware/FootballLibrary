package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Fixtures_and_Results {
	@JsonProperty("match")
	private List<Match> match;

	public List<Match> getMatch() {
		return match;
	}

	public void setMatch(List<Match> match) {
		this.match = match;
	}

	@Override
	public String toString() {
		return "Fixtures_and_Results [match=" + match + "]";
	}
	
}
