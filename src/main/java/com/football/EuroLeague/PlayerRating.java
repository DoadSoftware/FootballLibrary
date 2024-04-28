package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class PlayerRating {

	@JsonProperty("contestant")
	private List<Contestant> contestant;

	public PlayerRating() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Contestant> getContestant() {
		return contestant;
	}

	public void setContestant(List<Contestant> contestant) {
		this.contestant = contestant;
	}

	@Override
	public String toString() {
		return "PlayerRating [contestant=" + contestant + "]";
	}

}
