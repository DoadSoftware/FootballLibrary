package com.football.model;

public class PlayerStats {
	
	int JerseyNumber;
	String first_name;
	String last_name;
	String team_name;
	String Value;
	
	public PlayerStats(String team_name) {
		super();
		this.team_name = team_name;
	}
	public int getJerseyNumber() {
		return JerseyNumber;
	}
	public void setJerseyNumber(int jerseyNumber) {
		JerseyNumber = jerseyNumber;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	@Override
	public String toString() {
		return "PlayerStats [JerseyNumber=" + JerseyNumber + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", team_name=" + team_name + ", Value=" + Value + "]";
	}
	
}

