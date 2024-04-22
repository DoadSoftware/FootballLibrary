package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentMatchPlayer {

	@JsonProperty("playerId")
	private String playerId;
	  
	@JsonProperty("firstName")
	private String firstName;
	  
	@JsonProperty("lastName")	
	private String lastName;
	
	@JsonProperty("matchName")
	private String matchName;
	  
	@JsonProperty("shirtNumber")
	private int shirtNumber;
	
	@JsonProperty("position")
	private String position;
	  
	@JsonProperty("positionSide")
	private String positionSide;
	  
	@JsonProperty("positionX")
	private String positionX;
	
	@JsonProperty("positionY")
	private String positionY;
	
	@JsonProperty("formationPlace")
	private String formationPlace;

	public CurrentMatchPlayer() {
		super();
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
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

	public String getMatchName() {
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	public int getShirtNumber() {
		return shirtNumber;
	}

	public void setShirtNumber(int shirtNumber) {
		this.shirtNumber = shirtNumber;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPositionSide() {
		return positionSide;
	}

	public void setPositionSide(String positionSide) {
		this.positionSide = positionSide;
	}

	public String getPositionX() {
		return positionX;
	}

	public void setPositionX(String positionX) {
		this.positionX = positionX;
	}

	public String getPositionY() {
		return positionY;
	}

	public void setPositionY(String positionY) {
		this.positionY = positionY;
	}

	public String getFormationPlace() {
		return formationPlace;
	}

	public void setFormationPlace(String formationPlace) {
		this.formationPlace = formationPlace;
	}

	@Override
	public String toString() {
		return "CurrentMatchPlayer [playerId=" + playerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", matchName=" + matchName + ", shirtNumber=" + shirtNumber + ", position=" + position
				+ ", positionSide=" + positionSide + ", positionX=" + positionX + ", positionY=" + positionY
				+ ", formationPlace=" + formationPlace + "]";
	}
}