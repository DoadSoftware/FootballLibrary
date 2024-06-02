package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Players {

	@JsonProperty("playerId")
	private String playerId;
	  
	@JsonProperty("Id")
	private String Id;
	
	@JsonProperty("firstName")
	private String firstName;
	  
	@JsonProperty("matchName")	
	private String matchName;
	
	@JsonProperty("crossLost")	
	private String crossLost;

	@JsonProperty("crossSuccess")	
	private String crossSuccess;

	@JsonProperty("passLost")	
	private String passLost;

	@JsonProperty("passSuccess")	
	private String passSuccess;
	 
	@JsonProperty("x")	
	private String x;
	
	@JsonProperty("y")	
	private String y;
	 
	@JsonProperty("shirtNumber")
	private int shirtNumber;
	
	@JsonProperty("captain")
	private String captain;
	
	@JsonProperty("subPosition")
	private String subPosition;
	
	@JsonProperty("position")
	private String position;
	
	@JsonProperty("captain")
	private String captain;
	  
	@JsonProperty("positionSide")
	private String positionSide;
	  
	@JsonProperty("formationPlace")
	private String formationPlace;
	  
	@JsonProperty("stat")
	private List<Stat> stat;
	
	@JsonProperty("playerPass")
	private List<PlayerPass> playerPass;

	@JsonProperty("pvCategories")
	private pvCategories pvCategories;
	
	@JsonProperty("matchDataScore")
	private Stat matchDataScore;
	
	public Players() {
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

	public String getMatchName() {
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	public String getCrossLost() {
		return crossLost;
	}

	public void setCrossLost(String crossLost) {
		this.crossLost = crossLost;
	}

	public String getCrossSuccess() {
		return crossSuccess;
	}

	public void setCrossSuccess(String crossSuccess) {
		this.crossSuccess = crossSuccess;
	}

	public String getPassLost() {
		return passLost;
	}

	public void setPassLost(String passLost) {
		this.passLost = passLost;
	}

	public String getPassSuccess() {
		return passSuccess;
	}

	public void setPassSuccess(String passSuccess) {
		this.passSuccess = passSuccess;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public int getShirtNumber() {
		return shirtNumber;
	}

	public void setShirtNumber(int shirtNumber) {
		this.shirtNumber = shirtNumber;
	}

	public String getSubPosition() {
		return subPosition;
	}

	public void setSubPosition(String subPosition) {
		this.subPosition = subPosition;
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

	public String getFormationPlace() {
		return formationPlace;
	}

	public void setFormationPlace(String formationPlace) {
		this.formationPlace = formationPlace;
	}

	public List<Stat> getStat() {
		return stat;
	}

	public void setStat(List<Stat> stat) {
		this.stat = stat;
	}

	public List<PlayerPass> getPlayerPass() {
		return playerPass;
	}

	public void setPlayerPass(List<PlayerPass> playerPass) {
		this.playerPass = playerPass;
	}

	public pvCategories getPvCategories() {
		return pvCategories;
	}

	public void setPvCategories(pvCategories pvCategories) {
		this.pvCategories = pvCategories;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public Stat getMatchDataScore() {
		return matchDataScore;
	}

	public void setMatchDataScore(Stat matchDataScore) {
		this.matchDataScore = matchDataScore;
	}
	
	public String getCaptain() {
		return captain;
	}

	public void setCaptain(String captain) {
		this.captain = captain;
	}

	public String getCaptain() {
		return captain;
	}

	public void setCaptain(String captain) {
		this.captain = captain;
	}

	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", firstName=" + firstName + ", matchName=" + matchName + ", crossLost="
				+ crossLost + ", crossSuccess=" + crossSuccess + ", passLost=" + passLost + ", passSuccess="
				+ passSuccess + ", x=" + x + ", y=" + y + ", shirtNumber=" + shirtNumber + ", subPosition="
				+ subPosition + ", position=" + position + ", positionSide=" + positionSide + ", formationPlace="
				+ formationPlace + ", stat=" + stat + ", playerPass=" + playerPass + ", pvCategories=" + pvCategories
				+ "]";
	}
	
}