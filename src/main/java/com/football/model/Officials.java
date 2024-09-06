package com.football.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Column;

@Entity
@Table(name = "Officials")
public class Officials
{
  @Id
  @Column(name = "OfficialsId")
  private int officialsId;

  @Column(name = "Referee")
  private String referee;

  @Column(name = "AssistantReferee1")
  private String assistantReferee1;
  
  @Column(name = "AssistantReferee2")
  private String assistantReferee2;

  @Column(name = "FourthOfficial")
  private String fourthOfficial;
  
  @Column(name = "MatchCommissioner")
  private String matchCommissioner;
  
  @Column(name = "RefereeAssessor")
  private String refereeAssessor;

  public Officials() {
		super();
  }

public Officials(int officialsId) {
	super();
	this.officialsId = officialsId;
}

public int getOfficialsId() {
	return officialsId;
}

public void setOfficialsId(int officialsId) {
	this.officialsId = officialsId;
}

public String getReferee() {
	return referee;
}

public void setReferee(String referee) {
	this.referee = referee;
}

public String getAssistantReferee1() {
	return assistantReferee1;
}

public void setAssistantReferee1(String assistantReferee1) {
	this.assistantReferee1 = assistantReferee1;
}

public String getAssistantReferee2() {
	return assistantReferee2;
}

public void setAssistantReferee2(String assistantReferee2) {
	this.assistantReferee2 = assistantReferee2;
}

public String getFourthOfficial() {
	return fourthOfficial;
}

public void setFourthOfficial(String fourthOfficial) {
	this.fourthOfficial = fourthOfficial;
}

public String getMatchCommissioner() {
	return matchCommissioner;
}

public void setMatchCommissioner(String matchCommissioner) {
	this.matchCommissioner = matchCommissioner;
}

public String getRefereeAssessor() {
	return refereeAssessor;
}

public void setRefereeAssessor(String refereeAssessor) {
	this.refereeAssessor = refereeAssessor;
}

}