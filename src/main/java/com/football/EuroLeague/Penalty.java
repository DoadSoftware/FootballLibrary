package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Penalty {

	@JsonProperty("eventId")
	private long eventId;
	  
	@JsonProperty("matchId")
	private String matchId;
	
	@JsonProperty("matchDate")
	private String matchDate;

	@JsonProperty("matchTime")
	private String matchTime;
	
	@JsonProperty("competitionId")
	private String competitionId;
	
	@JsonProperty("homeContestantId")
	private String homeContestantId;
	
	@JsonProperty("homeContestantName")
	private String homeContestantName;
	
	@JsonProperty("awayContestantId")
	private String awayContestantName;
	
	@JsonProperty("contestantId")
	private String contestantId;
	
	@JsonProperty("minute")
	private int minute;
	
	@JsonProperty("second")
	private int second;
	
	@JsonProperty("outcome")
	private String outcome;
	
	@JsonProperty("y")
	private float y;
	
	@JsonProperty("z")
	private float z;
	
	@JsonProperty("qualifier")
	private String qualifier;

	public Penalty() {
		super();
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public String getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}

	public String getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(String matchTime) {
		this.matchTime = matchTime;
	}

	public String getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(String competitionId) {
		this.competitionId = competitionId;
	}

	public String getHomeContestantId() {
		return homeContestantId;
	}

	public void setHomeContestantId(String homeContestantId) {
		this.homeContestantId = homeContestantId;
	}

	public String getHomeContestantName() {
		return homeContestantName;
	}

	public void setHomeContestantName(String homeContestantName) {
		this.homeContestantName = homeContestantName;
	}

	public String getAwayContestantName() {
		return awayContestantName;
	}

	public void setAwayContestantName(String awayContestantName) {
		this.awayContestantName = awayContestantName;
	}

	public String getContestantId() {
		return contestantId;
	}

	public void setContestantId(String contestantId) {
		this.contestantId = contestantId;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public String getQualifier() {
		return qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	@Override
	public String toString() {
		return "Penalty [eventId=" + eventId + ", matchId=" + matchId + ", matchDate=" + matchDate + ", matchTime="
				+ matchTime + ", competitionId=" + competitionId + ", homeContestantId=" + homeContestantId
				+ ", homeContestantName=" + homeContestantName + ", awayContestantName=" + awayContestantName
				+ ", contestantId=" + contestantId + ", minute=" + minute + ", second=" + second + ", outcome="
				+ outcome + ", y=" + y + ", z=" + z + ", qualifier=" + qualifier + "]";
	}
	
}