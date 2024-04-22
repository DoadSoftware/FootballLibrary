package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class MatchInfo {

	@JsonProperty("id")
	private String id;
	  
	@JsonProperty("date")
	private String date;
	  
	@JsonProperty("time")	
	private String time;
	
	@JsonProperty("numberOfPeriods")	
	private int numberOfPeriods;
	
	@JsonProperty("periodLength")	
	private int periodLength;
	
	@JsonProperty("description")
	private String description;
	  

	@JsonProperty("contestant")
	private List<Contestant> contestant;
	
	
	@JsonProperty("venue")
	private Sport venue;

	@JsonProperty("sport")
	private Sport sport;
	
	@JsonProperty("ruleset")
	private Sport ruleset;
	
	@JsonProperty("competition")
	private Sport competition;
	
	@JsonProperty("tournamentCalendar")
	private Sport tournamentCalendar;
	
	@JsonProperty("stage")
	private Sport stage;

	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<Contestant> getContestant() {
		return contestant;
	}


	public void setContestant(List<Contestant> contestant) {
		this.contestant = contestant;
	}


	public int getNumberOfPeriods() {
		return numberOfPeriods;
	}


	public void setNumberOfPeriods(int numberOfPeriods) {
		this.numberOfPeriods = numberOfPeriods;
	}


	public int getPeriodLength() {
		return periodLength;
	}


	public void setPeriodLength(int periodLength) {
		this.periodLength = periodLength;
	}


	public Sport getVenue() {
		return venue;
	}


	public void setVenue(Sport venue) {
		this.venue = venue;
	}


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


	public Sport getStage() {
		return stage;
	}


	public void setStage(Sport stage) {
		this.stage = stage;
	}


	@Override
	public String toString() {
		return "MatchInfo [id=" + id + ", date=" + date + ", time=" + time + ", numberOfPeriods=" + numberOfPeriods
				+ ", periodLength=" + periodLength + ", description=" + description + ", contestant=" + contestant
				+ ", venue=" + venue + ", sport=" + sport + ", ruleset=" + ruleset + ", competition=" + competition
				+ ", tournamentCalendar=" + tournamentCalendar + ", stage=" + stage + "]";
	}


}
