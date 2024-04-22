package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PenaltyContestant {

	@JsonProperty("id")
	private String id;
	  
	@JsonProperty("name")
	private String name;
	  
	@JsonProperty("player")
	private List<PenaltyPlayerData> player;

	public PenaltyContestant() {
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

	public List<PenaltyPlayerData> getPlayer() {
		return player;
	}

	public void setPlayer(List<PenaltyPlayerData> player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "PenaltyContestant [id=" + id + ", name=" + name + ", player=" + player + "]";
	}
	
}