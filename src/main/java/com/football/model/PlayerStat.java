package com.football.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Column;

@Entity
@Table(name = "PlayerStats")
public class PlayerStat {

  @Id
  @Column(name = "PlayerStatsId")
  private int playerStatsId;
  
  @Column(name = "PlayerId")
  private int playerId;
  
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
  
  @Column(name = "ValueStats1")
  private String ValueStats1;
  
  @Column(name = "ValueStats2")
  private String valueStats2;
  
  @Column(name = "ValueStats3")
  private String valueStats3;
  
  @Column(name = "ValueStats4")
  private String valueStats4;
  
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

public String getValueStats1() {
	return ValueStats1;
}

public void setValueStats1(String valueStats1) {
	ValueStats1 = valueStats1;
}

public String getValueStats2() {
	return valueStats2;
}

public void setValueStats2(String valueStats2) {
	this.valueStats2 = valueStats2;
}

public String getValueStats3() {
	return valueStats3;
}

public void setValueStats3(String valueStats3) {
	this.valueStats3 = valueStats3;
}

public String getValueStats4() {
	return valueStats4;
}

public void setValueStats4(String valueStats4) {
	this.valueStats4 = valueStats4;
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

}