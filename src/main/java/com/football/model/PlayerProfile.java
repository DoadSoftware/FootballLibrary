package com.football.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Column;

@Entity
@Table(name = "PlayerProfile")
public class PlayerProfile {

  @Id
  @Column(name = "PlayerStatsId")
  private int playerStatsId;
  
  @Column(name = "PlayerId")
  private int playerId;
  
  @Column(name = "StatsNumbers")
  private int StatsNumbers;
  
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
  
  @Column(name = "HeadStats5")
  private String headStats5;
  
  @Column(name = "HeadStats6")
  private String headStats6;
  
  @Column(name = "Value1")
  private String Value1;
  
  @Column(name = "Value2")
  private String Value2;
  
  @Column(name = "Value3")
  private String Value3;
  
  @Column(name = "Value4")
  private String Value4;
  
  @Column(name = "Value5")
  private String Value5;
  
  @Column(name = "Value6")
  private String Value6;
  
  @Transient
  private Team team;

  @Transient
  private Player player;
  

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

public int getStatsNumbers() {
	return StatsNumbers;
}

public void setStatsNumbers(int statsNumbers) {
	StatsNumbers = statsNumbers;
}

public String getHeadStats5() {
	return headStats5;
}

public void setHeadStats5(String headStats5) {
	this.headStats5 = headStats5;
}

public String getHeadStats6() {
	return headStats6;
}

public void setHeadStats6(String headStats6) {
	this.headStats6 = headStats6;
}

public String getValue1() {
	return Value1;
}

public void setValue1(String value1) {
	Value1 = value1;
}

public String getValue2() {
	return Value2;
}

public void setValue2(String value2) {
	Value2 = value2;
}

public String getValue3() {
	return Value3;
}

public void setValue3(String value3) {
	Value3 = value3;
}

public String getValue4() {
	return Value4;
}

public void setValue4(String value4) {
	Value4 = value4;
}

public String getValue5() {
	return Value5;
}

public void setValue5(String value5) {
	Value5 = value5;
}

public String getValue6() {
	return Value6;
}

public void setValue6(String value6) {
	Value6 = value6;
}

}