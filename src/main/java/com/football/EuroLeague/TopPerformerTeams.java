package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopPerformerTeams {
	  
	@JsonProperty("id")	
	private String id;
	  
	@JsonProperty("name")
	private String name;
	  
	@JsonProperty("rank")
	private int rank;
	  
	@JsonProperty("value")
	private int value;

	public TopPerformerTeams() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "TopPerformerTeams [id=" + id + ", name=" + name + ", rank=" + rank + ", value=" + value + "]";
	}
	
}