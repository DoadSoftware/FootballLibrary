package com.football.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.football.EuroLeague.TopPerformerPlayers;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Match {
   
  private String XmlTimeSpan="";
  
  private int homeSubstitutesPerTeam;

  private int awaySubstitutesPerTeam;
  
  private String xmlTimeSpan;
  
  private String matchFileTimeStamp;

  private String matchResult;

  private String matchStatus;
  
  private String matchFileName;

  private String tournament;

  private String matchIdent;
  
  private int matchId;

  private int homeTeamFormationId;

  private int awayTeamFormationId;
  
  private int homeTeamId;

  private int awayTeamId;

  private int homePenaltiesHits;

  private int awayPenaltiesHits;

  private int homePenaltiesMisses;

  private int awayPenaltiesMisses;
 
  private String homeTeamJerseyColor;

  private String awayTeamJerseyColor;
  
  private String homeTeamGKJerseyColor;

  private String awayTeamGKJerseyColor;

  private int groundId;

  private String venueName;

  private int homeTeamScore;

  private int awayTeamScore;

  private String api_photo;
  
  private Ground ground;
  
  private Team homeTeam;

  private Team awayTeam;
  
  private List<APITeam> ApiData;

  private List<Player> homeSquad;

  private List<Player> homeSubstitutes;
  
  private List<Player> awaySquad;

  private List<Player> awaySubstitutes;
  
  private List<Player> homeOtherSquad;

  private List<Player> awayOtherSquad;

  private List<MatchStats> matchStats;
  
  private Clock clock;

  private List<Event> events;
  
  private ApiData Api_LiveMatch = new ApiData();
 
  private List<TopPerformerPlayers> topGoals=new ArrayList<TopPerformerPlayers>();
  
  private List<TopPerformerPlayers> TopAssists=new ArrayList<TopPerformerPlayers>();
  
  List<PlayerStats> top_Sprints = new ArrayList<PlayerStats>();
  
  List<PlayerStats> top_Distance = new ArrayList<PlayerStats>(); 
  
  List<PlayerStats> top_Speed = new ArrayList<PlayerStats>();
  
  List<PlayerStats> top_Passes = new ArrayList<PlayerStats>();
  
  List<PlayerStats> goalConceded = new ArrayList<PlayerStats>();
  
  
public int getHomePenaltiesHits() {
	return homePenaltiesHits;
}

public void setHomePenaltiesHits(int homePenaltiesHits) {
	this.homePenaltiesHits = homePenaltiesHits;
}

public int getAwayPenaltiesHits() {
	return awayPenaltiesHits;
}

public void setAwayPenaltiesHits(int awayPenaltiesHits) {
	this.awayPenaltiesHits = awayPenaltiesHits;
}

public int getHomePenaltiesMisses() {
	return homePenaltiesMisses;
}

public void setHomePenaltiesMisses(int homePenaltiesMisses) {
	this.homePenaltiesMisses = homePenaltiesMisses;
}

public int getAwayPenaltiesMisses() {
	return awayPenaltiesMisses;
}

public void setAwayPenaltiesMisses(int awayPenaltiesMisses) {
	this.awayPenaltiesMisses = awayPenaltiesMisses;
}

public Clock getClock() {
	return clock;
}

public void setClock(Clock clock) {
	this.clock = clock;
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

public int getHomeSubstitutesPerTeam() {
	return homeSubstitutesPerTeam;
}

public void setHomeSubstitutesPerTeam(int homeSubstitutesPerTeam) {
	this.homeSubstitutesPerTeam = homeSubstitutesPerTeam;
}

public int getAwaySubstitutesPerTeam() {
	return awaySubstitutesPerTeam;
}

public void setAwaySubstitutesPerTeam(int awaySubstitutesPerTeam) {
	this.awaySubstitutesPerTeam = awaySubstitutesPerTeam;
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

public int getMatchId() {
	return matchId;
}

public void setMatchId(int matchId) {
	this.matchId = matchId;
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

public List<APITeam> getApiData() {
	return ApiData;
}

public void setApiData(List<APITeam> apiData) {
	ApiData = apiData;
}

public String getApi_photo() {
	return api_photo;
}

public void setApi_photo(String api_photo) {
	this.api_photo = api_photo;
}

public String getHomeTeamGKJerseyColor() {
	return homeTeamGKJerseyColor;
}

public void setHomeTeamGKJerseyColor(String homeTeamGKJerseyColor) {
	this.homeTeamGKJerseyColor = homeTeamGKJerseyColor;
}

public String getAwayTeamGKJerseyColor() {
	return awayTeamGKJerseyColor;
}

public void setAwayTeamGKJerseyColor(String awayTeamGKJerseyColor) {
	this.awayTeamGKJerseyColor = awayTeamGKJerseyColor;
}


public ApiData getApi_LiveMatch() {
	return Api_LiveMatch;
}

public void setApi_LiveMatch(ApiData api_LiveMatch) {
	Api_LiveMatch = api_LiveMatch;
}


public List<TopPerformerPlayers> getTopGoals() {
	return topGoals;
}

public void setTopGoals(List<TopPerformerPlayers> topGoals) {
	this.topGoals = topGoals;
}

public List<TopPerformerPlayers> getTopAssists() {
	return TopAssists;
}

public void setTopAssists(List<TopPerformerPlayers> TopAssists) {
	this.TopAssists = TopAssists;
}

public List<PlayerStats> getTop_Sprints() {
	return top_Sprints;
}

public void setTop_Sprints(List<PlayerStats> top_Sprints) {
	this.top_Sprints = top_Sprints;
}

public List<PlayerStats> getTop_Distance() {
	return top_Distance;
}

public void setTop_Distance(List<PlayerStats> top_Distance) {
	this.top_Distance = top_Distance;
}

public List<PlayerStats> getTop_Speed() {
	return top_Speed;
}

public void setTop_Speed(List<PlayerStats> top_Speed) {
	this.top_Speed = top_Speed;
}

public List<PlayerStats> getTop_Passes() {
	return top_Passes;
}

public void setTop_Passes(List<PlayerStats> top_Passes) {
	this.top_Passes = top_Passes;
}

public List<PlayerStats> getGoalConceded() {
	return goalConceded;
}

public void setGoalConceded(List<PlayerStats> goalConceded) {
	this.goalConceded = goalConceded;
}
public String getXmlTimeSpan() {
	return xmlTimeSpan;
}

public void setXmlTimeSpan(String xmlTimeSpan) {
	this.xmlTimeSpan = xmlTimeSpan;
}

@Override
public String toString() {
	return "Match [homeSubstitutesPerTeam=" + homeSubstitutesPerTeam + ", awaySubstitutesPerTeam="
			+ awaySubstitutesPerTeam + ", xmlTimeSpan=" + xmlTimeSpan + ", matchFileTimeStamp=" + matchFileTimeStamp
			+ ", matchResult=" + matchResult + ", matchStatus=" + matchStatus + ", matchFileName=" + matchFileName
			+ ", tournament=" + tournament + ", matchIdent=" + matchIdent + ", matchId=" + matchId
			+ ", homeTeamFormationId=" + homeTeamFormationId + ", awayTeamFormationId=" + awayTeamFormationId
			+ ", homeTeamId=" + homeTeamId + ", awayTeamId=" + awayTeamId + ", homePenaltiesHits=" + homePenaltiesHits
			+ ", awayPenaltiesHits=" + awayPenaltiesHits + ", homePenaltiesMisses=" + homePenaltiesMisses
			+ ", awayPenaltiesMisses=" + awayPenaltiesMisses + ", homeTeamJerseyColor=" + homeTeamJerseyColor
			+ ", awayTeamJerseyColor=" + awayTeamJerseyColor + ", homeTeamGKJerseyColor=" + homeTeamGKJerseyColor
			+ ", awayTeamGKJerseyColor=" + awayTeamGKJerseyColor + ", groundId=" + groundId + ", venueName=" + venueName
			+ ", homeTeamScore=" + homeTeamScore + ", awayTeamScore=" + awayTeamScore + ", api_photo=" + api_photo
			+ ", ground=" + ground + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", ApiData=" + ApiData
			+ ", homeSquad=" + homeSquad + ", homeSubstitutes=" + homeSubstitutes + ", awaySquad=" + awaySquad
			+ ", awaySubstitutes=" + awaySubstitutes + ", homeOtherSquad=" + homeOtherSquad + ", awayOtherSquad="
			+ awayOtherSquad + ", matchStats=" + matchStats + ", clock=" + clock + ", events=" + events
			+ ", Api_LiveMatch=" + Api_LiveMatch + ", topGoals=" + topGoals + ", TopAssists=" + TopAssists
			+ ", top_Sprints=" + top_Sprints + ", top_Distance=" + top_Distance + ", top_Speed=" + top_Speed
			+ ", top_Passes=" + top_Passes + ", goalConceded=" + goalConceded + "]";
}

}