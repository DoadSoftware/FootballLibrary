package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class possessionValue {
	@JsonProperty("contestant")
	private List<PossessionValueContestant> Contestant;

	
	public List<PossessionValueContestant> getContestant() {
		return Contestant;
	}


	public void setContestant(List<PossessionValueContestant> contestant) {
		Contestant = contestant;
	}


	@Override
	public String toString() {
		return "possessionValue [Contestant=" + Contestant + "]";
	}
	
}
