package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FixturesLiveMatch {

	@JsonProperty("matchDetails")
	private MatchDetails matchDetails;
	  
	@JsonProperty("goal")
	private List<Goal> goal;
	
	@JsonProperty("card")
	private List<Card> card;
	
	@JsonProperty("substitute")
	private List<Substitute> substitute;
	
	@JsonProperty("matchDetailsExtra")
	private MatchDetailsExtra matchDetailsExtra;

	public MatchDetails getMatchDetails() {
		return matchDetails;
	}

	public void setMatchDetails(MatchDetails matchDetails) {
		this.matchDetails = matchDetails;
	}

	public List<Goal> getGoal() {
		return goal;
	}

	public void setGoal(List<Goal> goal) {
		this.goal = goal;
	}

	public List<Card> getCard() {
		return card;
	}

	public void setCard(List<Card> card) {
		this.card = card;
	}

	public List<Substitute> getSubstitute() {
		return substitute;
	}

	public void setSubstitute(List<Substitute> substitute) {
		this.substitute = substitute;
	}

	public MatchDetailsExtra getMatchDetailsExtra() {
		return matchDetailsExtra;
	}

	public void setMatchDetailsExtra(MatchDetailsExtra matchDetailsExtra) {
		this.matchDetailsExtra = matchDetailsExtra;
	}
	
	
}