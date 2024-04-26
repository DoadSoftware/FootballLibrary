package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerStat {

	@JsonProperty("competitionId")
	private String competitionId;
	
	@JsonProperty("competitionName")
	private String competitionName;
	  
	@JsonProperty("tournamentCalendarId")
	private String tournamentCalendarId;
	
	@JsonProperty("tournamentCalendarName")
	private String tournamentCalendarName;
	
	@JsonProperty("goals")
	private int goals;
	
	@JsonProperty("assists")
	private int assists;

	@JsonProperty("penaltyGoals")
	private int penaltyGoals;
	
	@JsonProperty("appearances")
	private int appearances;
	
	@JsonProperty("yellowCards")
	private int yellowCards;
	
	@JsonProperty("secondYellowCards")
	private int secondYellowCards;
	
	@JsonProperty("redCards")
	private int redCards;
	
	@JsonProperty("substituteIn")
	private int substituteIn;
	
	@JsonProperty("substituteOut")
	private int substituteOut;
	
	@JsonProperty("subsOnBench")
	private int subsOnBench;
	
	@JsonProperty("minutesPlayed")
	private int minutesPlayed;
	
	@JsonProperty("shirtNumber")
	private int shirtNumber;
	
	@JsonProperty("competitionFormat")
	private String competitionFormat;
	
	@JsonProperty("isFriendly")
	private String isFriendly;
	
	public PlayerStat() {
		super();
	}

	public String getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(String competitionId) {
		this.competitionId = competitionId;
	}

	public String getCompetitionName() {
		return competitionName;
	}

	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}

	public String getTournamentCalendarId() {
		return tournamentCalendarId;
	}

	public void setTournamentCalendarId(String tournamentCalendarId) {
		this.tournamentCalendarId = tournamentCalendarId;
	}

	public String getTournamentCalendarName() {
		return tournamentCalendarName;
	}

	public void setTournamentCalendarName(String tournamentCalendarName) {
		this.tournamentCalendarName = tournamentCalendarName;
	}

	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public int getPenaltyGoals() {
		return penaltyGoals;
	}

	public void setPenaltyGoals(int penaltyGoals) {
		this.penaltyGoals = penaltyGoals;
	}

	public int getAppearances() {
		return appearances;
	}

	public void setAppearances(int appearances) {
		this.appearances = appearances;
	}

	public int getYellowCards() {
		return yellowCards;
	}

	public void setYellowCards(int yellowCards) {
		this.yellowCards = yellowCards;
	}

	public int getSecondYellowCards() {
		return secondYellowCards;
	}

	public void setSecondYellowCards(int secondYellowCards) {
		this.secondYellowCards = secondYellowCards;
	}

	public int getRedCards() {
		return redCards;
	}

	public void setRedCards(int redCards) {
		this.redCards = redCards;
	}

	public int getSubstituteIn() {
		return substituteIn;
	}

	public void setSubstituteIn(int substituteIn) {
		this.substituteIn = substituteIn;
	}

	public int getSubstituteOut() {
		return substituteOut;
	}

	public void setSubstituteOut(int substituteOut) {
		this.substituteOut = substituteOut;
	}

	public int getSubsOnBench() {
		return subsOnBench;
	}

	public void setSubsOnBench(int subsOnBench) {
		this.subsOnBench = subsOnBench;
	}

	public int getMinutesPlayed() {
		return minutesPlayed;
	}

	public void setMinutesPlayed(int minutesPlayed) {
		this.minutesPlayed = minutesPlayed;
	}

	public int getShirtNumber() {
		return shirtNumber;
	}

	public void setShirtNumber(int shirtNumber) {
		this.shirtNumber = shirtNumber;
	}

	public String getCompetitionFormat() {
		return competitionFormat;
	}

	public void setCompetitionFormat(String competitionFormat) {
		this.competitionFormat = competitionFormat;
	}

	public String getIsFriendly() {
		return isFriendly;
	}

	public void setIsFriendly(String isFriendly) {
		this.isFriendly = isFriendly;
	}

	@Override
	public String toString() {
		return "PlayerStat [competitionId=" + competitionId + ", competitionName=" + competitionName
				+ ", tournamentCalendarId=" + tournamentCalendarId + ", tournamentCalendarName="
				+ tournamentCalendarName + ", goals=" + goals + ", assists=" + assists + ", penaltyGoals="
				+ penaltyGoals + ", appearances=" + appearances + ", yellowCards=" + yellowCards
				+ ", secondYellowCards=" + secondYellowCards + ", redCards=" + redCards + ", substituteIn="
				+ substituteIn + ", substituteOut=" + substituteOut + ", subsOnBench=" + subsOnBench
				+ ", minutesPlayed=" + minutesPlayed + ", shirtNumber=" + shirtNumber + ", competitionFormat="
				+ competitionFormat + ", isFriendly=" + isFriendly + "]";
	}

}