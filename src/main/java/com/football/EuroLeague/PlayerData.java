package com.football.EuroLeague;

public class PlayerData {
 private String id;
 private String name;
 private String TeamId;
 private String TeamName;
 private String firstName,LastName;
 private int rank,value,match,minsPlayed,goalconceded,saves,jerseyNumber;

 public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getTeamId() {
	return TeamId;
}
public void setTeamId(String teamId) {
	TeamId = teamId;
}
public String getTeamName() {
	return TeamName;
}
public void setTeamName(String teamName) {
	TeamName = teamName;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return LastName;
}
public void setLastName(String lastName) {
	LastName = lastName;
}
public int getRank() {
	return rank;
}
public void setRank(int rank) {
	this.rank = rank;
}
public int getValue() {
	return value;
}
public void setValue(int value) {
	this.value = value;
}
public int getMatch() {
	return match;
}
public void setMatch(int match) {
	this.match = match;
}
public int getMinsPlayed() {
	return minsPlayed;
}
public void setMinsPlayed(int minsPlayed) {
	this.minsPlayed = minsPlayed;
}
public int getGoalconceded() {
	return goalconceded;
}
public void setGoalconceded(int goalconceded) {
	this.goalconceded = goalconceded;
}
public int getSaves() {
	return saves;
}
public void setSaves(int saves) {
	this.saves = saves;
}
public int getJerseyNumber() {
	return jerseyNumber;
}
public void setJerseyNumber(int jerseyNumber) {
	this.jerseyNumber = jerseyNumber;
}
 
}
