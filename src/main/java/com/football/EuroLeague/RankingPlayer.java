package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RankingPlayer {

	@JsonProperty("name")
	private String name;
	  
	@JsonProperty("player")
	private List<TopPerformerPlayers> player;

	public RankingPlayer() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TopPerformerPlayers> getPlayer() {
		return player;
	}

	public void setPlayer(List<TopPerformerPlayers> player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "RankingPlayer [name=" + name + ", player=" + player + "]";
	}
	
}