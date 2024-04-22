package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Card {

	@JsonProperty("contestantId")
	private String contestantId;
	  
	@JsonProperty("periodId")
	private int periodId;
	
	@JsonProperty("timeMin")
	private int timeMin;

	@JsonProperty("timeMinSec")
	private String timeMinSec;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("playerId")
	private String playerId;
	
	@JsonProperty("playerName")
	private String playerName;
	
	@JsonProperty("teamOfficialId")
	private String teamOfficialId;
	
	@JsonProperty("officialName")
	private String officialName;
	
	@JsonProperty("optaEventId")
	private String optaEventId;
	
	@JsonProperty("cardReason")
	private String cardReason;

	public Card() {
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

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getTeamOfficialId() {
		return teamOfficialId;
	}

	public void setTeamOfficialId(String teamOfficialId) {
		this.teamOfficialId = teamOfficialId;
	}

	public String getOfficialName() {
		return officialName;
	}

	public void setOfficialName(String officialName) {
		this.officialName = officialName;
	}

	public String getOptaEventId() {
		return optaEventId;
	}

	public void setOptaEventId(String optaEventId) {
		this.optaEventId = optaEventId;
	}

	public String getCardReason() {
		return cardReason;
	}

	public void setCardReason(String cardReason) {
		this.cardReason = cardReason;
	}

	@Override
	public String toString() {
		return "Card [contestantId=" + contestantId + ", periodId=" + periodId + ", timeMin=" + timeMin
				+ ", timeMinSec=" + timeMinSec + ", type=" + type + ", playerId=" + playerId + ", playerName="
				+ playerName + ", teamOfficialId=" + teamOfficialId + ", officialName=" + officialName
				+ ", optaEventId=" + optaEventId + ", cardReason=" + cardReason + "]";
	}
}