package com.football.model;

public class ApiEventStats {
	private String contestantId;
	private String playerId;
	private String playerOffId;
	private String playerName;
	private String playerOffName;
	private int timeMin;
	private String type,timeMinSec;
	private int periodId,jerseyNumber,offJerseyNumber;
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
	public String getPlayerOffId() {
		return playerOffId;
	}
	public void setPlayerOffId(String playerOffId) {
		this.playerOffId = playerOffId;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getPlayerOffName() {
		return playerOffName;
	}
	public ApiEventStats() {
		super();
		contestantId="";
		playerId="";
		playerOffId="";
		playerName="";
		playerOffName="";
		timeMin=0;
		type="";
		periodId=0;
		offJerseyNumber=0;
		jerseyNumber=0;
	}
	public void setPlayerOffName(String playerOffName) {
		this.playerOffName = playerOffName;
	}
	public int getTimeMin() {
		return timeMin;
	}
	public void setTimeMin(int timeMin) {
		this.timeMin = timeMin;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPeriodId() {
		return periodId;
	}
	public void setPeriodId(int periodId) {
		this.periodId = periodId;
	}
	public int getJerseyNumber() {
		return jerseyNumber;
	}
	public void setJerseyNumber(int jerseyNumber) {
		this.jerseyNumber = jerseyNumber;
	}
	
	public int getOffJerseyNumber() {
		return offJerseyNumber;
	}
	public void setOffJerseyNumber(int offJerseyNumber) {
		this.offJerseyNumber = offJerseyNumber;
	}
	
	public String getTimeMinSec() {
		return timeMinSec;
	}
	public void setTimeMinSec(String timeMinSec) {
		this.timeMinSec = timeMinSec;
	}
	public ApiEventStats(String contestantId, String playerId, String playerName, int timeMin, String type,
			int periodId,String timeMinSec) {
		super();
		this.contestantId = contestantId;
		this.playerId = playerId;
		this.playerName = playerName;
		this.timeMin = timeMin;
		this.type = type;
		this.periodId = periodId;
		this.timeMinSec=timeMinSec;
	}
	public ApiEventStats(String contestantId, String playerId, String playerOffId, String playerName,
			String playerOffName, int timeMin, String type, int periodId, int jerseyNumber,String timeMinSec) {
		super();
		this.contestantId = contestantId;
		this.playerId = playerId;
		this.playerOffId = playerOffId;
		this.playerName = playerName;
		this.playerOffName = playerOffName;
		this.timeMin = timeMin;
		this.type = type;
		this.periodId = periodId;
		this.jerseyNumber = jerseyNumber;
		this.timeMinSec=timeMinSec;
	}
	@Override
	public String toString() {
		return "ApiEventStats [contestantId=" + contestantId + ", playerId=" + playerId + ", playerOffId=" + playerOffId
				+ ", playerName=" + playerName + ", playerOffName=" + playerOffName + ", timeMin=" + timeMin + ", type="
				+ type + ", periodId=" + periodId + ", jerseyNumber=" + jerseyNumber + ", offJerseyNumber="
				+ offJerseyNumber + "]";
	}
	
}
