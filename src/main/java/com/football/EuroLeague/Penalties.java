package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Penalties {

	@JsonProperty("contestant")
	private List<PenaltyContestant> contestant;

	public Penalties() {
		super();
	}

	public List<PenaltyContestant> getContestant() {
		return contestant;
	}

	public void setContestant(List<PenaltyContestant> contestant) {
		this.contestant = contestant;
	}

	@Override
	public String toString() {
		return "Penalties [contestant=" + contestant + "]";
	}

}