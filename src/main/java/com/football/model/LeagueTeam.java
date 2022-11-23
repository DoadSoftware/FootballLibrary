package com.football.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="LeagueTeam")
@XmlAccessorType(XmlAccessType.FIELD)
public class LeagueTeam {
	
	@XmlElement(name="QualifiedStatus")
	private String qualifiedStatus;
	
	@XmlElement(name="TeamsName")
	private String TeamName;
	
	@XmlElement(name="Played")
	private int Played;
	
	@XmlElement(name="Won")
	private int Won;
	
	@XmlElement(name="Lost")
	private int Lost;
	
	@XmlElement(name="Drawn")
	private int drawn;
	
	@XmlElement(name="For")
	private int goal_For;
	
	@XmlElement(name="Against")
	private int goal_Against;
	
	@XmlElement(name="GD")
	private int GD;
	
	@XmlElement(name="Points")
	private int Points;

	public String getQualifiedStatus() {
		return qualifiedStatus;
	}

	public void setQualifiedStatus(String qualifiedStatus) {
		this.qualifiedStatus = qualifiedStatus;
	}

	public String getTeamName() {
		return TeamName;
	}

	public void setTeamName(String teamName) {
		TeamName = teamName;
	}

	public int getPlayed() {
		return Played;
	}

	public void setPlayed(int played) {
		Played = played;
	}

	public int getWon() {
		return Won;
	}

	public void setWon(int won) {
		Won = won;
	}

	public int getLost() {
		return Lost;
	}

	public void setLost(int lost) {
		Lost = lost;
	}

	public int getDrawn() {
		return drawn;
	}

	public void setDrawn(int drawn) {
		this.drawn = drawn;
	}

	public int getGoal_For() {
		return goal_For;
	}

	public void setGoal_For(int goal_For) {
		this.goal_For = goal_For;
	}

	public int getGoal_Against() {
		return goal_Against;
	}

	public void setGoal_Against(int goal_Against) {
		this.goal_Against = goal_Against;
	}

	public int getGD() {
		return GD;
	}

	public void setGD(int gD) {
		GD = gD;
	}

	public int getPoints() {
		return Points;
	}

	public void setPoints(int points) {
		Points = points;
	}
	
	
}
