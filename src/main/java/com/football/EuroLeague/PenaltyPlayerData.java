package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PenaltyPlayerData {

	@JsonProperty("id")
	private String id;
	  
	@JsonProperty("position")
	private String position;
	  
	@JsonProperty("matchName")	
	private String matchName;
	 
	@JsonProperty("penalty")	
	private List<Penalty> penalty;

	public PenaltyPlayerData() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMatchName() {
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	public List<Penalty> getPenalty() {
		return penalty;
	}

	public void setPenalty(List<Penalty> penalty) {
		this.penalty = penalty;
	}

	@Override
	public String toString() {
		return "PenaltyPlayerData [id=" + id + ", position=" + position + ", matchName=" + matchName + ", penalty="
				+ penalty + "]";
	}
	
}