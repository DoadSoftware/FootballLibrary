package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PreviewContestant {

	@JsonProperty("id")
	private String id;
	  
	@JsonProperty("date")
	private String date;
	  
	@JsonProperty("competitionCode")	
	private String competitionCode;
	
	@JsonProperty("competitionId")	
	private String competitionId;
	
	@JsonProperty("competitionName")	
	private String competitionName;
	
	@JsonProperty("tournamentCalendarId")	
	private String tournamentCalendarId;
	
	@JsonProperty("tournamentCalendarName")	
	private String tournamentCalendarName;
	  
	@JsonProperty("country")
	private String country;
	  
	@JsonProperty("countryId")
	private String countryId;
	
	@JsonProperty("goal")	
	private List<Goal> goal;
	
	@JsonProperty("contestants")
	private ContestantPreview contestants;
	
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

	public String getCompetitionCode() {
		return competitionCode;
	}

	public void setCompetitionCode(String competitionCode) {
		this.competitionCode = competitionCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public List<Goal> getGoal() {
		return goal;
	}

	public void setGoal(List<Goal> goal) {
		this.goal = goal;
	}

	public ContestantPreview getContestants() {
		return contestants;
	}

	public void setContestants(ContestantPreview contestants) {
		this.contestants = contestants;
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

	@Override
	public String toString() {
		return "PreviewContestant [id=" + id + ", date=" + date + ", competitionCode=" + competitionCode
				+ ", competitionId=" + competitionId + ", competitionName=" + competitionName
				+ ", tournamentCalendarId=" + tournamentCalendarId + ", tournamentCalendarName="
				+ tournamentCalendarName + ", country=" + country + ", countryId=" + countryId + ", goal=" + goal
				+ ", contestants=" + contestants + "]";
	}

}