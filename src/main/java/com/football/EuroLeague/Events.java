package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Events {

	@JsonProperty("id")
	private long id;
	  
	@JsonProperty("eventId")
	private int eventId;
	
	@JsonProperty("typeId")
	private int typeId;

	@JsonProperty("periodId")
	private int periodId;
	
	@JsonProperty("timeMin")
	private int timeMin;
	
	@JsonProperty("timeSec")
	private int timeSec;
	
	@JsonProperty("contestantId")
	private String contestantId;
	
	@JsonProperty("playerId")
	private String playerId;
	
	@JsonProperty("playerName")
	private String playerName;
	
	@JsonProperty("outcome")
	private int outcome;
	
	@JsonProperty("x")
	private int x;
	
	@JsonProperty("y")
	private int y;
	
	@JsonProperty("timeStamp")
	private String timeStamp;
	
	@JsonProperty("qualifier")
	private List<Qualifier> qualifier;

	public Events() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
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

	public int getTimeSec() {
		return timeSec;
	}

	public void setTimeSec(int timeSec) {
		this.timeSec = timeSec;
	}

	public String getContestantId() {
		return contestantId;
	}

	public void setContestantId(String contestantId) {
		this.contestantId = contestantId;
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

	public int getOutcome() {
		return outcome;
	}

	public void setOutcome(int outcome) {
		this.outcome = outcome;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public List<Qualifier> getQualifier() {
		return qualifier;
	}

	public void setQualifier(List<Qualifier> qualifier) {
		this.qualifier = qualifier;
	}

	@Override
	public String toString() {
		return "Events [id=" + id + ", eventId=" + eventId + ", typeId=" + typeId + ", periodId=" + periodId
				+ ", timeMin=" + timeMin + ", timeSec=" + timeSec + ", contestantId=" + contestantId + ", playerId="
				+ playerId + ", playerName=" + playerName + ", outcome=" + outcome + ", x=" + x + ", y=" + y
				+ ", timeStamp=" + timeStamp + ", qualifier=" + qualifier + "]";
	}

}