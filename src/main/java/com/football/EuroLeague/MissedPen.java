package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class MissedPen {
	
	@JsonProperty("contestantId")
	private String contestantId;
	
	@JsonProperty("periodId")
	private String periodId;

	@JsonProperty("timeMin")
	private String timeMin;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("playerId")
	private String playerId;
	
	@JsonProperty("decision")
	private String decision;
	
	@JsonProperty("probability")
	private String probability;
	
	@JsonProperty("outcome")
	private String outcome;
	
	@JsonProperty("playerName")
	private String playerName;
	
	@JsonProperty("optaEventId")
	private String optaEventId;

	public String getContestantId() {
		return contestantId;
	}

	public void setContestantId(String contestantId) {
		this.contestantId = contestantId;
	}

	public String getPeriodId() {
		return periodId;
	}

	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}

	public String getTimeMin() {
		return timeMin;
	}

	public void setTimeMin(String timeMin) {
		this.timeMin = timeMin;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getProbability() {
		return probability;
	}

	public void setProbability(String probability) {
		this.probability = probability;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getOptaEventId() {
		return optaEventId;
	}

	public void setOptaEventId(String optaEventId) {
		this.optaEventId = optaEventId;
	}

	@Override
	public String toString() {
		return "MissedPen [contestantId=" + contestantId + ", periodId=" + periodId + ", timeMin=" + timeMin + ", type="
				+ type + ", playerId=" + playerId + ", decision=" + decision + ", probability=" + probability
				+ ", outcome=" + outcome + ", playerName=" + playerName + ", optaEventId=" + optaEventId + "]";
	}

}
