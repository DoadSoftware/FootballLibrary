package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class HalfTime {

	@JsonProperty("home")
	private int home;
	  
	@JsonProperty("away")
	private int away;

	public HalfTime() {
		super();
	}

	public int getHome() {
		return home;
	}

	public void setHome(int home) {
		this.home = home;
	}

	public int getAway() {
		return away;
	}

	public void setAway(int away) {
		this.away = away;
	}

	@Override
	public String toString() {
		return "HalfTime [home=" + home + ", away=" + away + "]";
	}

}