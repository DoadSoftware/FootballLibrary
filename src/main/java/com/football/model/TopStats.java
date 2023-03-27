package com.football.model;

import java.util.List;

public class TopStats {
	
	String Header;
	List<PlayerStats> playersStats;
	
	public TopStats(String header, List<PlayerStats> playersStats) {
		super();
		Header = header;
		this.playersStats = playersStats;
	}

	public String getHeader() {
		return Header;
	}

	public void setHeader(String header) {
		Header = header;
	}

	public List<PlayerStats> getPlayersStats() {
		return playersStats;
	}

	public void setPlayersStats(List<PlayerStats> playersStats) {
		this.playersStats = playersStats;
	}

}

