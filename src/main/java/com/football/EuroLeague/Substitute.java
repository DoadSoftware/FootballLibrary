package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Substitute {

	@JsonProperty("contestantId")
	private String contestantId;
	  
	@JsonProperty("periodId")
	private int periodId;
	
	@JsonProperty("timeMin")
	private int timeMin;

	@JsonProperty("timeMinSec")
	private String timeMinSec;
	
	@JsonProperty("playerOnId")
	private String playerOnId;
	
	@JsonProperty("playerOnName")
	private String playerOnName;
	
	@JsonProperty("playerOffId")
	private String playerOffId;
	
	@JsonProperty("playerOffName")
	private String playerOffName;
	
	@JsonProperty("subReason")
	private String subReason;

	public Substitute() {
		super();
	}

	public String getContestantId() {
		return contestantId;
	}

	public void setContestantId(String contestantId) {
		this.contestantId = contestantId;
	}

	public int getPeriodId() {
		return periodId;
	}

	public void setPeriodId(int periodId) {
		this.periodId = periodId;
	}

	public int getTimeMin() {
		return timeMin;
	}

	public void setTimeMin(int timeMin) {
		this.timeMin = timeMin;
	}

	public String getTimeMinSec() {
		return timeMinSec;
	}

	public void setTimeMinSec(String timeMinSec) {
		this.timeMinSec = timeMinSec;
	}

	public String getPlayerOnId() {
		return playerOnId;
	}

	public void setPlayerOnId(String playerOnId) {
		this.playerOnId = playerOnId;
	}

	public String getPlayerOnName() {
		return playerOnName;
	}

	public void setPlayerOnName(String playerOnName) {
		this.playerOnName = playerOnName;
	}

	public String getPlayerOffId() {
		return playerOffId;
	}

	public void setPlayerOffId(String playerOffId) {
		this.playerOffId = playerOffId;
	}

	public String getPlayerOffName() {
		return playerOffName;
	}

	public void setPlayerOffName(String playerOffName) {
		this.playerOffName = playerOffName;
	}

	public String getSubReason() {
		return subReason;
	}

	public void setSubReason(String subReason) {
		this.subReason = subReason;
	}

	@Override
	public String toString() {
		return "Substitute [contestantId=" + contestantId + ", periodId=" + periodId + ", timeMin=" + timeMin
				+ ", timeMinSec=" + timeMinSec + ", playerOnId=" + playerOnId + ", playerOnName=" + playerOnName
				+ ", playerOffId=" + playerOffId + ", playerOffName=" + playerOffName + ", subReason=" + subReason
				+ "]";
	}
	
}