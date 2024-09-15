package com.football.model;

import java.util.ArrayList;
import java.util.List;

import com.football.EuroLeague.Possession;


public class ApiData {
	
	ApiTeamstats homeTeam = new ApiTeamstats();
	
	ApiTeamstats awayTeam = new ApiTeamstats();
	
	List<ApiEventStats> events= new ArrayList<ApiEventStats>(); 
	int homeWin,awayWin,draws;
	
	Possession  Possession =new Possession();
	
	public ApiTeamstats getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(ApiTeamstats homeTeam) {
		this.homeTeam = homeTeam;
	}
	public ApiTeamstats getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(ApiTeamstats awayTeam) {
		this.awayTeam = awayTeam;
	}
	public int getHomeWin() {
		return homeWin;
	}
	public void setHomeWin(int homeWin) {
		this.homeWin = homeWin;
	}
	public int getAwayWin() {
		return awayWin;
	}
	public void setAwayWin(int awayWin) {
		this.awayWin = awayWin;
	}
	public int getDraws() {
		return draws;
	}
	public void setDraws(int draws) {
		this.draws = draws;
	}
	
	public List<ApiEventStats> getEvents() {
		return events;
	}
	public void setEvents(List<ApiEventStats> events) {
		this.events = events;
	}
	
	public Possession getPossession() {
		return Possession;
	}
	public void setPossession(Possession possession) {
		Possession = possession;
	}
	@Override
	public String toString() {
		return "ApiData [homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + "]";
	}
	
}
