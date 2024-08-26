package com.football.model;

public class ApiData {
	ApiTeamstats homeTeam = new ApiTeamstats();
	ApiTeamstats awayTeam = new ApiTeamstats();
	int homeWin,awayWin,draws;
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
	@Override
	public String toString() {
		return "ApiData [homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + "]";
	}
	
}
