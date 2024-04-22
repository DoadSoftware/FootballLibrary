package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class RankPrediction {

	@JsonProperty("rankStatus")
	private String rankStatus;
	
	@JsonProperty("rankId")
	private String rankId;
	
	@JsonProperty("value")
	private String value;

	public RankPrediction() {
		super();
	}

	public String getRankStatus() {
		return rankStatus;
	}

	public void setRankStatus(String rankStatus) {
		this.rankStatus = rankStatus;
	}

	public String getRankId() {
		return rankId;
	}

	public void setRankId(String rankId) {
		this.rankId = rankId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "RankPrediction [rankStatus=" + rankStatus + ", rankId=" + rankId + ", value=" + value + "]";
	}

}