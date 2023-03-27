package com.football.model;

public class PlayerStats {
	
	int JerseyNumber;
	String Value;
	
	
	public PlayerStats(int jerseyNumber) {
		super();
		JerseyNumber = jerseyNumber;
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
	
}

