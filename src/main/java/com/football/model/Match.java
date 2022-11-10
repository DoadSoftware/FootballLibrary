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

  @XmlElement(name = "matchHalves")
  private String matchHalves;
	
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

  @XmlElement(name = "tournament")
  private String tournament;

  @XmlElement(name = "matchIdent")
  private String matchIdent;

  @XmlElement(name = "homeTeamFormationId")
  private int homeTeamFormationId;

  @XmlElement(name = "awayTeamFormationId")
  private int awayTeamFormationId;
  
  @XmlElement(name = "homeTeamId")
  private int homeTeamId;

  @XmlElement(name = "awayTeamId")
  private int awayTeamId;
  
  @XmlElement(name = "homeTeamJerseyColor")
  private String homeTeamJerseyColor;

  @XmlElement(name = "awayTeamJerseyColor")
  private String awayTeamJerseyColor;

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
  
  @XmlElement(name = "homeYellowCards")
  private int homeYellowCards;

  @XmlElement(name = "awayYellowCards")
  private int awayYellowCards;
  
  @XmlElement(name = "homeRedCards")
  private int homeRedCards;

  @XmlElement(name = "awayRedCards")
  private int awayRedCards;
  
  @XmlElement(name = "homeOffsides")
  private int homeOffsides;

  @XmlElement(name = "awayOffsides")
  private int awayOffsides;
  
  @XmlElement(name = "homeAssists")
  private int homeAssists;

  @XmlElement(name = "awayAssists")
  private int awayAssists;
  
  @XmlElement(name = "homeShots")
  private int homeShots;
  
  @XmlElement(name = "awayShots")
  private int awayShots;
  
  @XmlElement(name = "homeShot_on_target")
  private int homeShot_on_target;
  
  @XmlElement(name = "awayShot_on_target")
  private int awayShot_on_target;
  
  @XmlElement(name = "homeFoul")
  private int homeFoul;
  
  @XmlElement(name = "awayFoul")
  private int awayFoul;
  
  @XmlElement(name = "homeCorner")
  private int homeCorner;
  
  @XmlElement(name = "awayCorner")
  private int awayCorner;
  
  @XmlElement(name = "homeCorner_Converted")
  private int homeCorner_Converted;
  
  @XmlElement(name = "awayCorner_Converted")
  private int awayCorner_Converted;
  
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
  @XmlElement(name = "awaySubstitutes")
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

public String getMatchHalves() {
	return matchHalves;
}

public void setMatchHalves(String matchHalves) {
	this.matchHalves = matchHalves;
}

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

public int getHomeTeamFormationId() {
	return homeTeamFormationId;
}

public void setHomeTeamFormationId(int homeTeamFormationId) {
	this.homeTeamFormationId = homeTeamFormationId;
}

public int getAwayTeamFormationId() {
	return awayTeamFormationId;
}

public void setAwayTeamFormationId(int awayTeamFormationId) {
	this.awayTeamFormationId = awayTeamFormationId;
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

public int getHomeYellowCards() {
	return homeYellowCards;
}

public void setHomeYellowCards(int homeYellowCards) {
	this.homeYellowCards = homeYellowCards;
}

public int getAwayYellowCards() {
	return awayYellowCards;
}

public void setAwayYellowCards(int awayYellowCards) {
	this.awayYellowCards = awayYellowCards;
}

public int getHomeRedCards() {
	return homeRedCards;
}

public void setHomeRedCards(int homeRedCards) {
	this.homeRedCards = homeRedCards;
}

public int getAwayRedCards() {
	return awayRedCards;
}

public void setAwayRedCards(int awayRedCards) {
	this.awayRedCards = awayRedCards;
}

public int getHomeOffsides() {
	return homeOffsides;
}

public void setHomeOffsides(int homeOffsides) {
	this.homeOffsides = homeOffsides;
}

public int getAwayOffsides() {
	return awayOffsides;
}

public void setAwayOffsides(int awayOffsides) {
	this.awayOffsides = awayOffsides;
}

public int getHomeAssists() {
	return homeAssists;
}

public void setHomeAssists(int homeAssists) {
	this.homeAssists = homeAssists;
}

public int getAwayAssists() {
	return awayAssists;
}

public void setAwayAssists(int awayAssists) {
	this.awayAssists = awayAssists;
}

public int getHomeShots() {
	return homeShots;
}

public void setHomeShots(int homeShots) {
	this.homeShots = homeShots;
}

public int getAwayShots() {
	return awayShots;
}

public void setAwayShots(int awayShots) {
	this.awayShots = awayShots;
}

public int getHomeShot_on_target() {
	return homeShot_on_target;
}

public void setHomeShot_on_target(int homeShot_on_target) {
	this.homeShot_on_target = homeShot_on_target;
}

public int getAwayShot_on_target() {
	return awayShot_on_target;
}

public void setAwayShot_on_target(int awayShot_on_target) {
	this.awayShot_on_target = awayShot_on_target;
}

public int getHomeFoul() {
	return homeFoul;
}

public void setHomeFoul(int homeFoul) {
	this.homeFoul = homeFoul;
}

public int getAwayFoul() {
	return awayFoul;
}

public void setAwayFoul(int awayFoul) {
	this.awayFoul = awayFoul;
}

public int getHomeCorner() {
	return homeCorner;
}

public void setHomeCorner(int homeCorner) {
	this.homeCorner = homeCorner;
}

public int getAwayCorner() {
	return awayCorner;
}

public void setAwayCorner(int awayCorner) {
	this.awayCorner = awayCorner;
}

public int getHomeCorner_Converted() {
	return homeCorner_Converted;
}

public void setHomeCorner_Converted(int homeCorner_Converted) {
	this.homeCorner_Converted = homeCorner_Converted;
}

public int getAwayCorner_Converted() {
	return awayCorner_Converted;
}

public void setAwayCorner_Converted(int awayCorner_Converted) {
	this.awayCorner_Converted = awayCorner_Converted;
}

public String getHomeTeamJerseyColor() {
	return homeTeamJerseyColor;
}

public void setHomeTeamJerseyColor(String homeTeamJerseyColor) {
	this.homeTeamJerseyColor = homeTeamJerseyColor;
}

public String getAwayTeamJerseyColor() {
	return awayTeamJerseyColor;
}

public void setAwayTeamJerseyColor(String awayTeamJerseyColor) {
	this.awayTeamJerseyColor = awayTeamJerseyColor;
}

}