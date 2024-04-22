package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDetails {

	@JsonProperty("periodId")
	private int periodId;
	  
	@JsonProperty("matchStatus")
	private String matchStatus;
	  
	@JsonProperty("winner")	
	private String winner;
	  
	@JsonProperty("matchLengthMin")
	private int matchLengthMin;
	  
	@JsonProperty("matchLengthSec")
	private int matchLengthSec;
	
	@JsonProperty("period")
	private List<Period> period;
	
	@JsonProperty("scores")
	private Scores scores;

	public MatchDetails() {
		super();
	}

	public int getPeriodId() {
		return periodId;
	}

	public void setPeriodId(int periodId) {
		this.periodId = periodId;
	}

	public String getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(String matchStatus) {
		this.matchStatus = matchStatus;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public int getMatchLengthMin() {
		return matchLengthMin;
	}

	public void setMatchLengthMin(int matchLengthMin) {
		this.matchLengthMin = matchLengthMin;
	}

	public int getMatchLengthSec() {
		return matchLengthSec;
	}

	public void setMatchLengthSec(int matchLengthSec) {
		this.matchLengthSec = matchLengthSec;
	}

	public List<Period> getPeriod() {
		return period;
	}

	public void setPeriod(List<Period> period) {
		this.period = period;
	}

	public Scores getScores() {
		return scores;
	}

	public void setScores(Scores scores) {
		this.scores = scores;
	}

	@Override
	public String toString() {
		return "MatchDetails [periodId=" + periodId + ", matchStatus=" + matchStatus + ", winner=" + winner
				+ ", matchLengthMin=" + matchLengthMin + ", matchLengthSec=" + matchLengthSec + ", period=" + period
				+ ", scores=" + scores + "]";
	}
	
}