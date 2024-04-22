package com.football.EuroLeague;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PenaltyPreview {
	
	@JsonProperty("matchInfo")
	private MatchInfo matchInfo;	
	
	@JsonProperty("penalties")
	private Penalties penalties;

	public PenaltyPreview() {
		super();
	}

	public MatchInfo getMatchInfo() {
		return matchInfo;
	}

	public void setMatchInfo(MatchInfo matchInfo) {
		this.matchInfo = matchInfo;
	}

	public Penalties getPenalties() {
		return penalties;
	}

	public void setPenalties(Penalties penalties) {
		this.penalties = penalties;
	}

	@Override
	public String toString() {
		return "PenaltyPreview [matchInfo=" + matchInfo + ", penalties=" + penalties + "]";
	}
	
}