package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SeasonalPlayerStats {
	
	@JsonProperty("position")
	private String position;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("shirtNumber")
	private String shirtNumber;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("shortFirstName")
	private String shortFirstName;
	
	@JsonProperty("shortLastName")
	private String shortLastName;
	
	@JsonProperty("knownName")
	private String knownName;
	
	@JsonProperty("matchName")
	private String matchName;
	
	@JsonProperty("stat")
	private List<Stat> stat;
	
	@JsonProperty("currentTeamOnly")
	private CurrentTeamOnly currentTeamOnly;

	public SeasonalPlayerStats() {
		super();
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShirtNumber() {
		return shirtNumber;
	}

	public void setShirtNumber(String shirtNumber) {
		this.shirtNumber = shirtNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getShortFirstName() {
		return shortFirstName;
	}

	public void setShortFirstName(String shortFirstName) {
		this.shortFirstName = shortFirstName;
	}

	public String getShortLastName() {
		return shortLastName;
	}

	public void setShortLastName(String shortLastName) {
		this.shortLastName = shortLastName;
	}

	public String getKnownName() {
		return knownName;
	}

	public void setKnownName(String knownName) {
		this.knownName = knownName;
	}

	public String getMatchName() {
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	public List<Stat> getStat() {
		return stat;
	}

	public void setStat(List<Stat> stat) {
		this.stat = stat;
	}

	public CurrentTeamOnly getCurrentTeamOnly() {
		return currentTeamOnly;
	}

	public void setCurrentTeamOnly(CurrentTeamOnly currentTeamOnly) {
		this.currentTeamOnly = currentTeamOnly;
	}

	@Override
	public String toString() {
		return "SeasonalPlayerStats [position=" + position + ", id=" + id + ", shirtNumber=" + shirtNumber
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", shortFirstName=" + shortFirstName
				+ ", shortLastName=" + shortLastName + ", knownName=" + knownName + ", matchName=" + matchName
				+ ", stat=" + stat + ", currentTeamOnly=" + currentTeamOnly + "]";
	}

}