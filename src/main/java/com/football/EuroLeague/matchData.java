package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class matchData {

	@JsonProperty("id")
	private String id;
	  
	@JsonProperty("teamData")
	private List<teamData> teamData;
	  
	@JsonProperty("stat")	
	private List<Stat> stat;

	public matchData() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<teamData> getTeamData() {
		return teamData;
	}

	public void setTeamData(List<teamData> teamData) {
		this.teamData = teamData;
	}

	public List<Stat> getStat() {
		return stat;
	}

	public void setStat(List<Stat> stat) {
		this.stat = stat;
	}

	@Override
	public String toString() {
		return "matchData [id=" + id + ", teamData=" + teamData + ", stat=" + stat + "]";
	}
}