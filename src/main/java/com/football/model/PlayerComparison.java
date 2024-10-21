package com.football.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Column;

@Entity
@Table(name = "PlayerComparison")
public class PlayerComparison {

  @Id
  @Column(name = "PlayerStatsId")
  private int playerStatsId;
  
  @Column(name = "PlayerId")
  private int playerId;
  
  @Column(name = "StatsNumbers")
  private int StatsNumbers;
  
  @Column(name = "PlayerId1")
  private int playerId1;
  
  @Column(name = "SubHeader")
  private String subHeader;
  
  @Column(name = "HeadStats1")
  private String headStats1;
  
  @Column(name = "HeadStats2")
  private String headStats2;
  
  @Column(name = "HeadStats3")
  private String headStats3;
  
  @Column(name = "HeadStats4")
  private String headStats4;
  
  @Column(name = "Player1Value1")
  private String Player1Value1;
  
  @Column(name = "Player1Value2")
  private String Player1Value2;
  
  @Column(name = "Player1Value3")
  private String Player1Value3;
  
  @Column(name = "Player1Value4")
  private String Player1Value4;
  
  @Column(name = "Player2Value1")
  private String Player2Value1;
  
  @Column(name = "Player2Value2")
  private String Player2Value2;
  
  @Column(name = "Player2Value3")
  private String Player2Value3;
  
  @Column(name = "Player2Value4")
  private String Player2Value4;
  
  @Transient
  private Team team;
  
  @Transient
  private Team team1;

  @Transient
  private Player player;
  
  @Transient
  private Player player1;

public int getPlayerStatsId() {
	return playerStatsId;
}

public void setPlayerStatsId(int playerStatsId) {
	this.playerStatsId = playerStatsId;
}

public int getPlayerId() {
	return playerId;
}

public void setPlayerId(int playerId) {
	this.playerId = playerId;
}

public String getHeadStats1() {
	return headStats1;
}

public void setHeadStats1(String headStats1) {
	this.headStats1 = headStats1;
}

public String getHeadStats2() {
	return headStats2;
}

public void setHeadStats2(String headStats2) {
	this.headStats2 = headStats2;
}

public String getHeadStats3() {
	return headStats3;
}

public void setHeadStats3(String headStats3) {
	this.headStats3 = headStats3;
}

public String getHeadStats4() {
	return headStats4;
}

public void setHeadStats4(String headStats4) {
	this.headStats4 = headStats4;
}

public String getPlayer1Value1() {
	return Player1Value1;
}

public void setPlayer1Value1(String player1Value1) {
	Player1Value1 = player1Value1;
}

public String getPlayer1Value2() {
	return Player1Value2;
}

public void setPlayer1Value2(String player1Value2) {
	Player1Value2 = player1Value2;
}

public String getPlayer1Value3() {
	return Player1Value3;
}

public void setPlayer1Value3(String player1Value3) {
	Player1Value3 = player1Value3;
}

public String getPlayer1Value4() {
	return Player1Value4;
}

public void setPlayer1Value4(String player1Value4) {
	Player1Value4 = player1Value4;
}

public String getPlayer2Value1() {
	return Player2Value1;
}

public void setPlayer2Value1(String player2Value1) {
	Player2Value1 = player2Value1;
}

public String getPlayer2Value2() {
	return Player2Value2;
}

public void setPlayer2Value2(String player2Value2) {
	Player2Value2 = player2Value2;
}

public String getPlayer2Value3() {
	return Player2Value3;
}

public void setPlayer2Value3(String player2Value3) {
	Player2Value3 = player2Value3;
}

public String getPlayer2Value4() {
	return Player2Value4;
}

public void setPlayer2Value4(String player2Value4) {
	Player2Value4 = player2Value4;
}

public Team getTeam() {
	return team;
}

public void setTeam(Team team) {
	this.team = team;
}

public Player getPlayer() {
	return player;
}

public void setPlayer(Player player) {
	this.player = player;
}

public String getSubHeader() {
	return subHeader;
}

public void setSubHeader(String subHeader) {
	this.subHeader = subHeader;
}

public int getPlayerId1() {
	return playerId1;
}

public void setPlayerId1(int playerId1) {
	this.playerId1 = playerId1;
}

public Player getPlayer1() {
	return player1;
}

public void setPlayer1(Player player1) {
	this.player1 = player1;
}

public Team getTeam1() {
	return team1;
}

public void setTeam1(Team team1) {
	this.team1 = team1;
}

public int getStatsNumbers() {
	return StatsNumbers;
}

public void setStatsNumbers(int statsNumbers) {
	StatsNumbers = statsNumbers;
}

}