package com.football.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Column;

@Entity
@Table(name = "HeadToHead")
public class HeadToHead {

  @Id
  @Column(name = "HeadToHeadId")
  private int headToHeadId;
  
  @Column(name = "TeamId")
  private int teamId;
  
  @Column(name = "StatNumber")
  private int statNumber;
  
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
  
  @Column(name = "ValueStats1")
  private String ValueStats1;
  
  @Column(name = "ValueStats2")
  private String valueStats2;
  
  @Column(name = "ValueStats3")
  private String valueStats3;
  
  @Column(name = "ValueStats4")
  private String valueStats4;
  
  @Column(name = "ValueStats5")
  private String valueStats5;
  
  @Column(name = "ValueStats6")
  private String valueStats6;
  
  @Transient
  private Team team;

  
public Team getTeam() {
	return team;
}

public void setTeam(Team team) {
	this.team = team;
}

public int getHeadToHeadId() {
	return headToHeadId;
}

public void setHeadToHeadId(int headToHeadId) {
	this.headToHeadId = headToHeadId;
}

public int getTeamId() {
	return teamId;
}

public void setTeamId(int teamId) {
	this.teamId = teamId;
}

public int getStatNumber() {
	return statNumber;
}

public void setStatNumber(int statNumber) {
	this.statNumber = statNumber;
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

public String getValueStats5() {
	return valueStats5;
}

public void setValueStats5(String valueStats5) {
	this.valueStats5 = valueStats5;
}

public String getValueStats6() {
	return valueStats6;
}

public void setValueStats6(String valueStats6) {
	this.valueStats6 = valueStats6;
}

}