package com.football.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.football.EuroLeague.TopPerformerPlayers;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiMatch {
   
  private ApiData Api_LiveMatch = new ApiData();
 
  private List<TopPerformerPlayers> topGoals=new ArrayList<TopPerformerPlayers>();
  
  private List<TopPerformerPlayers> TopAssists=new ArrayList<TopPerformerPlayers>();
  
  List<PlayerStats> top_Sprints = new ArrayList<PlayerStats>();
  
  List<PlayerStats> top_Distance = new ArrayList<PlayerStats>(); 
  
  List<PlayerStats> top_Speed = new ArrayList<PlayerStats>();
  
  List<PlayerStats> top_Passes = new ArrayList<PlayerStats>();
  
  List<PlayerStats> goalConceded = new ArrayList<PlayerStats>();
  
  
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

@Override
public String toString() {
	return "ApiMatch [Api_LiveMatch=" + Api_LiveMatch + ", topGoals=" + topGoals + ", TopAssists=" + TopAssists
			+ ", top_Sprints=" + top_Sprints + ", top_Distance=" + top_Distance + ", top_Speed=" + top_Speed
			+ ", top_Passes=" + top_Passes + ", goalConceded=" + goalConceded + "]";
}

}