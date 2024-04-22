package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentLineup {

	@JsonProperty("contestantId")
	private String contestantId;
	  
	@JsonProperty("formationUsed")
	private String formationUsed;
	
	@JsonProperty("player")
	private List<CurrentMatchPlayer> player;

	public CurrentLineup() {
		super();
	}

	public String getContestantId() {
		return contestantId;
	}

	public void setContestantId(String contestantId) {
		this.contestantId = contestantId;
	}

	public String getFormationUsed() {
		return formationUsed;
	}

	public void setFormationUsed(String formationUsed) {
		this.formationUsed = formationUsed;
	}

	public List<CurrentMatchPlayer> getPlayer() {
		return player;
	}

	public void setPlayer(List<CurrentMatchPlayer> player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "CurrentLineup [contestantId=" + contestantId + ", formationUsed=" + formationUsed + ", player=" + player
				+ "]";
	}
}