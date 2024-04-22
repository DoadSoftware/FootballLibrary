package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Division {

	@JsonProperty("type")
	private String type;
	  
	@JsonProperty("ranking")
	private List<PointsTable> ranking;

	public Division() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<PointsTable> getRanking() {
		return ranking;
	}

	public void setRanking(List<PointsTable> ranking) {
		this.ranking = ranking;
	}

	@Override
	public String toString() {
		return "Division [type=" + type + ", ranking=" + ranking + "]";
	}
	
}