package com.football.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Column;

@Entity
@Table(name = "Players")
public class Player implements Comparable<Player>
{

  @Id
  @Column(name = "PLAYERID")
  private int playerId;
  
  @Column(name = "JERSEYNUMBER")
  private int jersey_number;
	
  @Column(name = "FULLNAME")
  private String full_name;

  @Column(name = "FIRSTNAME")
  private String firstname;

  @Column(name = "SURNAME")
  private String surname;
  
  @Column(name = "TICKERNAME")
  private String ticker_name;

  @Column(name = "ROLE")
  private String role;
  
  @Column(name = "TEAMID")
  private Integer teamId;
  
  @Column(name = "PHOTO")
  private String photo;
 
  @Transient
  private Integer playerPosition;

  @Transient
  private String captainGoalKeeper;

  @Transient
  private String player_type;

  public Player() {
	 super();
  }

  public Player(int playerId, Integer playerPosition, String player_type) {
	super();
	this.playerId = playerId;
	this.playerPosition = playerPosition;
	this.player_type = player_type;
  }

public String getCaptainGoalKeeper() {
	return captainGoalKeeper;
}

public void setCaptainGoalKeeper(String captainGoalKeeper) {
	this.captainGoalKeeper = captainGoalKeeper;
}

public String getFirstname() {
	return firstname;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}

public String getTicker_name() {
	return ticker_name;
}

public void setTicker_name(String ticker_name) {
	this.ticker_name = ticker_name;
}

public int getPlayerId() {
	return playerId;
}

public void setPlayerId(int playerId) {
	this.playerId = playerId;
}

public int getJersey_number() {
	return jersey_number;
}

public void setJersey_number(int jersey_number) {
	this.jersey_number = jersey_number;
}

public String getFull_name() {
	return full_name;
}

public void setFull_name(String full_name) {
	this.full_name = full_name;
}

public String getSurname() {
	return surname;
}

public void setSurname(String surname) {
	this.surname = surname;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public Integer getTeamId() {
	return teamId;
}

public void setTeamId(Integer teamId) {
	this.teamId = teamId;
}

public Integer getPlayerPosition() {
	return playerPosition;
}

public void setPlayerPosition(Integer playerPosition) {
	this.playerPosition = playerPosition;
}

public String getPlayer_type() {
	return player_type;
}

public void setPlayer_type(String player_type) {
	this.player_type = player_type;
}

public String getPhoto() {
	return photo;
}

public void setPhoto(String photo) {
	this.photo = photo;
}

@Override
public String toString() {
	return "Player [playerId=" + playerId + ", jersey_number=" + jersey_number + ", full_name=" + full_name
			+ ", firstname=" + firstname + ", surname=" + surname + ", ticker_name=" + ticker_name + ", role=" + role
			+ ", teamId=" + teamId + ", photo=" + photo + ", playerPosition=" + playerPosition + ", captainGoalKeeper="
			+ captainGoalKeeper + ", player_type=" + player_type + "]";
}

@Override
public int compareTo(Player pm) {
	return (int) (this.getPlayerPosition()-pm.getPlayerPosition());
}


}