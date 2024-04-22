package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class SquadContestant {

	@JsonProperty("contestantId")
	private String contestantId;
	
	@JsonProperty("contestantName")
	private String contestantName;
	
	@JsonProperty("contestantShortName")
	private String contestantShortName;
	
	@JsonProperty("contestantClubName")
	private String contestantClubName;
	
	@JsonProperty("contestantCode")
	private String contestantCode;
	
	@JsonProperty("tournamentCalendarId")
	private String tournamentCalendarId;
	
	@JsonProperty("tournamentCalendarStartDate")
	private String tournamentCalendarStartDate;
	
	@JsonProperty("tournamentCalendarEndDate")
	private String tournamentCalendarEndDate;
	
	@JsonProperty("competitionName")
	private String competitionName;
	
	@JsonProperty("competitionId")
	private String competitionId;
	
	@JsonProperty("teamType")
	private String teamType;
	
	@JsonProperty("venueName")
	private String venueName;
	
	@JsonProperty("venueId")
	private String venueId;
	
	@JsonProperty("person")
	private List<Person> person;
	
	
	public String getContestantId() {
		return contestantId;
	}
	public void setContestantId(String contestantId) {
		this.contestantId = contestantId;
	}
	public String getContestantName() {
		return contestantName;
	}
	public void setContestantName(String contestantName) {
		this.contestantName = contestantName;
	}
	public String getContestantShortName() {
		return contestantShortName;
	}
	public void setContestantShortName(String contestantShortName) {
		this.contestantShortName = contestantShortName;
	}
	public String getContestantClubName() {
		return contestantClubName;
	}
	public void setContestantClubName(String contestantClubName) {
		this.contestantClubName = contestantClubName;
	}
	public String getContestantCode() {
		return contestantCode;
	}
	public void setContestantCode(String contestantCode) {
		this.contestantCode = contestantCode;
	}
	public String getTournamentCalendarId() {
		return tournamentCalendarId;
	}
	public void setTournamentCalendarId(String tournamentCalendarId) {
		this.tournamentCalendarId = tournamentCalendarId;
	}
	public String getTournamentCalendarStartDate() {
		return tournamentCalendarStartDate;
	}
	public void setTournamentCalendarStartDate(String tournamentCalendarStartDate) {
		this.tournamentCalendarStartDate = tournamentCalendarStartDate;
	}
	public String getTournamentCalendarEndDate() {
		return tournamentCalendarEndDate;
	}
	public void setTournamentCalendarEndDate(String tournamentCalendarEndDate) {
		this.tournamentCalendarEndDate = tournamentCalendarEndDate;
	}
	public String getCompetitionName() {
		return competitionName;
	}
	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}
	public String getCompetitionId() {
		return competitionId;
	}
	public void setCompetitionId(String competitionId) {
		this.competitionId = competitionId;
	}
	public String getTeamType() {
		return teamType;
	}
	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}
	public String getVenueId() {
		return venueId;
	}
	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}
	public List<Person> getPerson() {
		return person;
	}
	public void setPerson(List<Person> person) {
		this.person = person;
	}
	
	public String getVenueName() {
		return venueName;
	}
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
	@Override
	public String toString() {
		return "SquadContestant [contestantId=" + contestantId + ", contestantName=" + contestantName
				+ ", contestantShortName=" + contestantShortName + ", contestantClubName=" + contestantClubName
				+ ", contestantCode=" + contestantCode + ", tournamentCalendarId=" + tournamentCalendarId
				+ ", tournamentCalendarStartDate=" + tournamentCalendarStartDate + ", tournamentCalendarEndDate="
				+ tournamentCalendarEndDate + ", competitionName=" + competitionName + ", competitionId="
				+ competitionId + ", teamType=" + teamType + ", venueName=" + venueName
				+ ", venueId=" + venueId + ", person=" + person + "]";
	}
	
}
