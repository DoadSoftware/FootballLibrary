package com.football.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="Match")
@XmlAccessorType(XmlAccessType.FIELD)
public class Match {

  @XmlElement(name = "substitutesPerTeam")
  private int substitutesPerTeam;
	
  @XmlElement(name = "matchFileTimeStamp")
  private String matchFileTimeStamp;

  @XmlElement(name = "matchResult")
  private String matchResult;

  @XmlElement(name = "matchStatus")
  private String matchStatus;
  
  @XmlElement(name = "matchFileName")
  private String matchFileName;

  @XmlElement(name = "tossResult")
  private String tossResult;

  @XmlElement(name = "tossWinningDecision")
  private String tossWinningDecision;

  @XmlElement(name = "tournament")
  private String tournament;

  @XmlElement(name = "matchIdent")
  private String matchIdent;

  @XmlElement(name = "tossWinningTeam")
  private int tossWinningTeam;

  @XmlElement(name = "homeTeamId")
  private int homeTeamId;

  @XmlElement(name = "awayTeamId")
  private int awayTeamId;

  @XmlElement(name = "groundId")
  private int groundId;

  @XmlElement(name = "venueName")
  private String venueName;

  @XmlElement(name = "matchStartTime")
  private String matchStartTime;

  @XmlElement(name = "matchTimeStatus")
  private String matchTimeStatus;

  @XmlElement(name = "matchTotalSeconds")
  private long matchTotalSeconds;

  @XmlElement(name = "homeTeamScore")
  private int homeTeamScore;

  @XmlElement(name = "awayTeamScore")
  private int awayTeamScore;
  
  @XmlElement(name = "homeTeamYellowCard")
  private int homeTeamYellowCard;

  @XmlElement(name = "awayTeamYellowCard")
  private int awayTeamYellowCard;
  
  @XmlElement(name = "homeTeamRedCard")
  private int homeTeamRedCard;

  @XmlElement(name = "awayTeamRedCard")
  private int awayTeamRedCard;
  
  @XmlTransient
  private Ground ground;
  
  @XmlTransient
  private Team homeTeam;

  @XmlTransient
  private Team awayTeam;

  @XmlElementWrapper(name = "homeSquad")
  @XmlElement(name = "homeSquad")
  private List<Player> homeSquad;

  @XmlElementWrapper(name = "homeSubstitutes")
  @XmlElement(name = "homeSubstitutes")
  private List<Player> homeSubstitutes;
  
  @XmlElementWrapper(name = "awaySquad")
  @XmlElement(name = "awaySquad")
  private List<Player> awaySquad;

  @XmlElementWrapper(name = "awaySubstitutes")
  @XmlElement(name = "awaySubstit/utes")
  private List<Player> awaySubstitutes;
  
  @XmlElementWrapper(name = "homeOtherSquad")
  @XmlElement(name = "homeOtherSquad")
  private List<Player> homeOtherSquad;

  @XmlElementWrapper(name = "awayOtherSquad")
  @XmlElement(name = "awayOtherSquad")
  private List<Player> awayOtherSquad;

  @XmlElementWrapper(name = "matchStats")
  @XmlElement(name = "matchStat")
  private List<MatchStats> matchStats;
  
  @XmlTransient
  private List<Event> events;

public int getHomeTeamScore() {
	return homeTeamScore;
}

public void setHomeTeamScore(int homeTeamScore) {
	this.homeTeamScore = homeTeamScore;
}

public int getAwayTeamScore() {
	return awayTeamScore;
}

public void setAwayTeamScore(int awayTeamScore) {
	this.awayTeamScore = awayTeamScore;
}

public List<MatchStats> getMatchStats() {
	return matchStats;
}

public void setMatchStats(List<MatchStats> matchStats) {
	this.matchStats = matchStats;
}

public int getSubstitutesPerTeam() {
	return substitutesPerTeam;
}

public void setSubstitutesPerTeam(int substitutesPerTeam) {
	this.substitutesPerTeam = substitutesPerTeam;
}

public String getMatchFileTimeStamp() {
	return matchFileTimeStamp;
}

public void setMatchFileTimeStamp(String matchFileTimeStamp) {
	this.matchFileTimeStamp = matchFileTimeStamp;
}

public String getMatchResult() {
	return matchResult;
}

public void setMatchResult(String matchResult) {
	this.matchResult = matchResult;
}

public String getMatchStatus() {
	return matchStatus;
}

public void setMatchStatus(String matchStatus) {
	this.matchStatus = matchStatus;
}

public String getMatchFileName() {
	return matchFileName;
}

public void setMatchFileName(String matchFileName) {
	this.matchFileName = matchFileName;
}

public String getTossResult() {
	return tossResult;
}

public void setTossResult(String tossResult) {
	this.tossResult = tossResult;
}

public String getTossWinningDecision() {
	return tossWinningDecision;
}

public void setTossWinningDecision(String tossWinningDecision) {
	this.tossWinningDecision = tossWinningDecision;
}

public String getTournament() {
	return tournament;
}

public void setTournament(String tournament) {
	this.tournament = tournament;
}

public String getMatchIdent() {
	return matchIdent;
}

public void setMatchIdent(String matchIdent) {
	this.matchIdent = matchIdent;
}

public int getTossWinningTeam() {
	return tossWinningTeam;
}

public void setTossWinningTeam(int tossWinningTeam) {
	this.tossWinningTeam = tossWinningTeam;
}

public int getHomeTeamId() {
	return homeTeamId;
}

public void setHomeTeamId(int homeTeamId) {
	this.homeTeamId = homeTeamId;
}

public int getAwayTeamId() {
	return awayTeamId;
}

public void setAwayTeamId(int awayTeamId) {
	this.awayTeamId = awayTeamId;
}

public int getGroundId() {
	return groundId;
}

public void setGroundId(int groundId) {
	this.groundId = groundId;
}

public String getVenueName() {
	return venueName;
}

public void setVenueName(String venueName) {
	this.venueName = venueName;
}

public String getMatchStartTime() {
	return matchStartTime;
}

public void setMatchStartTime(String matchStartTime) {
	this.matchStartTime = matchStartTime;
}

public String getMatchTimeStatus() {
	return matchTimeStatus;
}

public void setMatchTimeStatus(String matchTimeStatus) {
	this.matchTimeStatus = matchTimeStatus;
}

public long getMatchTotalSeconds() {
	return matchTotalSeconds;
}

public void setMatchTotalSeconds(long matchTotalSeconds) {
	this.matchTotalSeconds = matchTotalSeconds;
}

public Ground getGround() {
	return ground;
}

public void setGround(Ground ground) {
	this.ground = ground;
}

public Team getHomeTeam() {
	return homeTeam;
}

public void setHomeTeam(Team homeTeam) {
	this.homeTeam = homeTeam;
}

public Team getAwayTeam() {
	return awayTeam;
}

public void setAwayTeam(Team awayTeam) {
	this.awayTeam = awayTeam;
}

public List<Player> getHomeSquad() {
	return homeSquad;
}

public void setHomeSquad(List<Player> homeSquad) {
	this.homeSquad = homeSquad;
}

public List<Player> getHomeSubstitutes() {
	return homeSubstitutes;
}

public void setHomeSubstitutes(List<Player> homeSubstitutes) {
	this.homeSubstitutes = homeSubstitutes;
}

public List<Player> getAwaySquad() {
	return awaySquad;
}

public void setAwaySquad(List<Player> awaySquad) {
	this.awaySquad = awaySquad;
}

public List<Player> getAwaySubstitutes() {
	return awaySubstitutes;
}

public void setAwaySubstitutes(List<Player> awaySubstitutes) {
	this.awaySubstitutes = awaySubstitutes;
}

public List<Player> getHomeOtherSquad() {
	return homeOtherSquad;
}

public void setHomeOtherSquad(List<Player> homeOtherSquad) {
	this.homeOtherSquad = homeOtherSquad;
}

public List<Player> getAwayOtherSquad() {
	return awayOtherSquad;
}

public void setAwayOtherSquad(List<Player> awayOtherSquad) {
	this.awayOtherSquad = awayOtherSquad;
}

public List<Event> getEvents() {
	return events;
}

public void setEvents(List<Event> events) {
	this.events = events;
}

public int getHomeTeamYellowCard() {
	return homeTeamYellowCard;
}

public void setHomeTeamYellowCard(int homeTeamYellowCard) {
	this.homeTeamYellowCard = homeTeamYellowCard;
}

public int getAwayTeamYellowCard() {
	return awayTeamYellowCard;
}

public void setAwayTeamYellowCard(int awayTeamYellowCard) {
	this.awayTeamYellowCard = awayTeamYellowCard;
}

public int getHomeTeamRedCard() {
	return homeTeamRedCard;
}

public void setHomeTeamRedCard(int homeTeamRedCard) {
	this.homeTeamRedCard = homeTeamRedCard;
}

public int getAwayTeamRedCard() {
	return awayTeamRedCard;
}

public void setAwayTeamRedCard(int awayTeamRedCard) {
	this.awayTeamRedCard = awayTeamRedCard;
}

}