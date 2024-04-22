package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDate {

	@JsonProperty("date")
	private String date;
	
	@JsonProperty("match")
	private List<Match> match;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Match> getMatch() {
		return match;
	}

	public void setMatch(List<Match> match) {
		this.match = match;
	}

	@Override
	public String toString() {
		return "MatchDate [date=" + date + ", match=" + match + "]";
	}
}