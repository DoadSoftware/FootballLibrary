package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class TournamentSimulation {

	@JsonProperty("sport")
	private Sport sport;
	
	@JsonProperty("ruleset")
	private Sport ruleset;
	
	@JsonProperty("competition")
	private Sport competition;
	
	@JsonProperty("tournamentCalendar")
	private Sport tournamentCalendar;
	
	@JsonProperty("stage")
	private List<Stage> stage;

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public Sport getRuleset() {
		return ruleset;
	}

	public void setRuleset(Sport ruleset) {
		this.ruleset = ruleset;
	}

	public Sport getCompetition() {
		return competition;
	}

	public void setCompetition(Sport competition) {
		this.competition = competition;
	}

	public Sport getTournamentCalendar() {
		return tournamentCalendar;
	}

	public void setTournamentCalendar(Sport tournamentCalendar) {
		this.tournamentCalendar = tournamentCalendar;
	}

	public List<Stage> getStage() {
		return stage;
	}

	public void setStage(List<Stage> stage) {
		this.stage = stage;
	}

	@Override
	public String toString() {
		return "TournamentSimulation [sport=" + sport + ", ruleset=" + ruleset + ", competition=" + competition
				+ ", tournamentCalendar=" + tournamentCalendar + ", stage=" + stage + "]";
	}
	
}