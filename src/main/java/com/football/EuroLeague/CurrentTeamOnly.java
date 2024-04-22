package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentTeamOnly {
	
	@JsonProperty("stat")
	private List<Stat> stat;	
	
	public CurrentTeamOnly() {
		super();
	}

	public List<Stat> getStat() {
		return stat;
	}

	public void setStat(List<Stat> stat) {
		this.stat = stat;
	}

	@Override
	public String toString() {
		return "CurrentTeamOnly [stat=" + stat + "]";
	}

}