package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SeasonalStats {

	@JsonProperty("competition")
	private Sport Competition;
	
	@JsonProperty("tournamentCalendar")
	private Sport tournamentCalendar;
	
	@JsonProperty("contestant")
	private Contestant contestant;
	
	@JsonProperty("player")
	private List<SeasonalPlayerStats> player;

	public SeasonalStats() {
		super();
	}

	public Sport getCompetition() {
		return Competition;
	}

	public void setCompetition(Sport competition) {
		Competition = competition;
	}

	public Sport getTournamentCalendar() {
		return tournamentCalendar;
	}

	public void setTournamentCalendar(Sport tournamentCalendar) {
		this.tournamentCalendar = tournamentCalendar;
	}

	public Contestant getContestant() {
		return contestant;
	}

	public void setContestant(Contestant contestant) {
		this.contestant = contestant;
	}

	@Override
	public String toString() {
		return "SeasonalStats [Competition=" + Competition + ", tournamentCalendar=" + tournamentCalendar
				+ ", contestant=" + contestant + "]";
	}

}