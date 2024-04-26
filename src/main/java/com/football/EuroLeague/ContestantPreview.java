package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ContestantPreview {

	@JsonProperty("homeContestantId")
	private String homeContestantId;
	  
	@JsonProperty("homeContestantName")
	private String homeContestantName;
	
	@JsonProperty("homeContestantOfficialName")
	private String homeContestantOfficialName;

	@JsonProperty("homeContestantShortName")
	private String homeContestantShortName;
	
	@JsonProperty("homeContestantCode")
	private String homeContestantCode;
	
	@JsonProperty("awayContestantId")
	private String awayContestantId;
	
	@JsonProperty("awayContestantName")
	private String awayContestantName;
	
	@JsonProperty("awayContestantOfficialName")
	private String awayContestantOfficialName;
	
	@JsonProperty("awayContestantShortName")
	private String awayContestantShortName;
	
	@JsonProperty("awayContestantCode")
	private String awayContestantCode;
	
	@JsonProperty("homeScore")
	private String homeScore;
	
	@JsonProperty("awayScore")
	private String awayScore;

	public ContestantPreview() {
		super();
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

	public String getHomeContestantOfficialName() {
		return homeContestantOfficialName;
	}

	public void setHomeContestantOfficialName(String homeContestantOfficialName) {
		this.homeContestantOfficialName = homeContestantOfficialName;
	}

	public String getHomeContestantShortName() {
		return homeContestantShortName;
	}

	public void setHomeContestantShortName(String homeContestantShortName) {
		this.homeContestantShortName = homeContestantShortName;
	}

	public String getHomeContestantCode() {
		return homeContestantCode;
	}

	public void setHomeContestantCode(String homeContestantCode) {
		this.homeContestantCode = homeContestantCode;
	}

	public String getAwayContestantId() {
		return awayContestantId;
	}

	public void setAwayContestantId(String awayContestantId) {
		this.awayContestantId = awayContestantId;
	}

	public String getAwayContestantName() {
		return awayContestantName;
	}

	public void setAwayContestantName(String awayContestantName) {
		this.awayContestantName = awayContestantName;
	}

	public String getAwayContestantOfficialName() {
		return awayContestantOfficialName;
	}

	public void setAwayContestantOfficialName(String awayContestantOfficialName) {
		this.awayContestantOfficialName = awayContestantOfficialName;
	}

	public String getAwayContestantShortName() {
		return awayContestantShortName;
	}

	public void setAwayContestantShortName(String awayContestantShortName) {
		this.awayContestantShortName = awayContestantShortName;
	}

	public String getAwayContestantCode() {
		return awayContestantCode;
	}

	public void setAwayContestantCode(String awayContestantCode) {
		this.awayContestantCode = awayContestantCode;
	}

	public String getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(String homeScore) {
		this.homeScore = homeScore;
	}

	public String getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(String awayScore) {
		this.awayScore = awayScore;
	}

	@Override
	public String toString() {
		return "ContestantPreview [homeContestantId=" + homeContestantId + ", homeContestantName=" + homeContestantName
				+ ", homeContestantOfficialName=" + homeContestantOfficialName + ", homeContestantShortName="
				+ homeContestantShortName + ", homeContestantCode=" + homeContestantCode + ", awayContestantId="
				+ awayContestantId + ", awayContestantName=" + awayContestantName + ", awayContestantOfficialName="
				+ awayContestantOfficialName + ", awayContestantShortName=" + awayContestantShortName
				+ ", awayContestantCode=" + awayContestantCode + ", homeScore=" + homeScore + ", awayScore=" + awayScore
				+ "]";
	}
}