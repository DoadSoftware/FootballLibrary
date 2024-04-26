package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PreviousMeetings {

	@JsonProperty("homeContestantWins")
	private int homeContestantWins;
	  
	@JsonProperty("awayContestantWins")
	private int awayContestantWins;
	
	@JsonProperty("draws")
	private int draws;

	@JsonProperty("homeContestantGoals")
	private int homeContestantGoals;
	
	@JsonProperty("awayContestantGoals")
	private int awayContestantGoals;
	
	@JsonProperty("match")
	private List<PreviewContestant> match;
	

	public PreviousMeetings() {
		super();
	}


	public int getHomeContestantWins() {
		return homeContestantWins;
	}


	public void setHomeContestantWins(int homeContestantWins) {
		this.homeContestantWins = homeContestantWins;
	}


	public int getAwayContestantWins() {
		return awayContestantWins;
	}


	public void setAwayContestantWins(int awayContestantWins) {
		this.awayContestantWins = awayContestantWins;
	}


	public int getDraws() {
		return draws;
	}


	public void setDraws(int draws) {
		this.draws = draws;
	}


	public int getHomeContestantGoals() {
		return homeContestantGoals;
	}


	public void setHomeContestantGoals(int homeContestantGoals) {
		this.homeContestantGoals = homeContestantGoals;
	}


	public int getAwayContestantGoals() {
		return awayContestantGoals;
	}


	public void setAwayContestantGoals(int awayContestantGoals) {
		this.awayContestantGoals = awayContestantGoals;
	}


	public List<PreviewContestant> getMatch() {
		return match;
	}


	public void setMatch(List<PreviewContestant> match) {
		this.match = match;
	}


	@Override
	public String toString() {
		return "PreviousMeetings [homeContestantWins=" + homeContestantWins + ", awayContestantWins="
				+ awayContestantWins + ", draws=" + draws + ", homeContestantGoals=" + homeContestantGoals
				+ ", awayContestantGoals=" + awayContestantGoals + "]";
	}
}