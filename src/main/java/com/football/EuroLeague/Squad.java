package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Squad {
	@JsonProperty("squad")
    private List<SquadContestant> squad;

	public List<SquadContestant> getSquad() {
		return squad;
	}

	public void setSquad(List<SquadContestant> squadContestant) {
		squad = squadContestant;
	}

	@Override
	public String toString() {
		return "Squad [SquadContestant=" + squad + "]";
	}
	
}
