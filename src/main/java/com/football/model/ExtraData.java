package com.football.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "ExtraDatas")
public class ExtraData {

  @Id
  @Column(name = "DataId")
  private int dataId;
  
  @Column(name = "Prompt")
  private String prompt;
  
  @Column(name = "HomePlayer1")
  private int homePlayer1;
  
  @Column(name = "HomePlayer2")
  private int homePlayer2;
  
  @Column(name = "HomePlayer3")
  private int homePlayer3;
  
  @Column(name = "AwayPlayer1")
  private int awayPlayer1;
  
  @Column(name = "AwayPlayer2")
  private int awayPlayer2;
  
  @Column(name = "AwayPlayer3")
  private int awayPlayer3;
	
  @Column(name = "HomeStat1")
  private String homeStat1;
  
  @Column(name = "HomeStat2")
  private String homeStat2;
  
  @Column(name = "HomeStat3")
  private String homeStat3;
  
  @Column(name = "AwayStat1")
  private String awayStat1;
  
  @Column(name = "AwayStat2")
  private String awayStat2;
  
  @Column(name = "AwayStat3")
  private String awayStat3;

  
public int getDataId() {
	return dataId;
}

public void setDataId(int dataId) {
	this.dataId = dataId;
}

public String getPrompt() {
	return prompt;
}

public void setPrompt(String prompt) {
	this.prompt = prompt;
}

public int getHomePlayer1() {
	return homePlayer1;
}

public void setHomePlayer1(int homePlayer1) {
	this.homePlayer1 = homePlayer1;
}

public int getHomePlayer2() {
	return homePlayer2;
}

public void setHomePlayer2(int homePlayer2) {
	this.homePlayer2 = homePlayer2;
}

public int getHomePlayer3() {
	return homePlayer3;
}

public void setHomePlayer3(int homePlayer3) {
	this.homePlayer3 = homePlayer3;
}

public int getAwayPlayer1() {
	return awayPlayer1;
}

public void setAwayPlayer1(int awayPlayer1) {
	this.awayPlayer1 = awayPlayer1;
}

public int getAwayPlayer2() {
	return awayPlayer2;
}

public void setAwayPlayer2(int awayPlayer2) {
	this.awayPlayer2 = awayPlayer2;
}

public int getAwayPlayer3() {
	return awayPlayer3;
}

public void setAwayPlayer3(int awayPlayer3) {
	this.awayPlayer3 = awayPlayer3;
}

public String getHomeStat1() {
	return homeStat1;
}

public void setHomeStat1(String homeStat1) {
	this.homeStat1 = homeStat1;
}

public String getHomeStat2() {
	return homeStat2;
}

public void setHomeStat2(String homeStat2) {
	this.homeStat2 = homeStat2;
}

public String getHomeStat3() {
	return homeStat3;
}

public void setHomeStat3(String homeStat3) {
	this.homeStat3 = homeStat3;
}

public String getAwayStat1() {
	return awayStat1;
}

public void setAwayStat1(String awayStat1) {
	this.awayStat1 = awayStat1;
}

public String getAwayStat2() {
	return awayStat2;
}

public void setAwayStat2(String awayStat2) {
	this.awayStat2 = awayStat2;
}

public String getAwayStat3() {
	return awayStat3;
}

public void setAwayStat3(String awayStat3) {
	this.awayStat3 = awayStat3;
}

@Override
public String toString() {
	return "ExtraData [dataId=" + dataId + ", prompt=" + prompt + ", homePlayer1=" + homePlayer1 + ", homePlayer2="
			+ homePlayer2 + ", homePlayer3=" + homePlayer3 + ", awayPlayer1=" + awayPlayer1 + ", awayPlayer2="
			+ awayPlayer2 + ", awayPlayer3=" + awayPlayer3 + ", homeStat1=" + homeStat1 + ", homeStat2=" + homeStat2
			+ ", homeStat3=" + homeStat3 + ", awayStat1=" + awayStat1 + ", awayStat2=" + awayStat2 + ", awayStat3="
			+ awayStat3 + "]";
}

}