package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Prediction {

	@JsonProperty("type")
	private String type;
	
	@JsonProperty("value")
	private String value;
	  
	@JsonProperty("probability")
	private String probability;
	
	@JsonProperty("position")
	private int position;
	
	@JsonProperty("rankStatus")
	private String rankStatus;
	
	@JsonProperty("rankId")
	private String rankId;
	
	public Prediction() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProbability() {
		return probability;
	}

	public void setProbability(String probability) {
		this.probability = probability;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
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

	@Override
	public String toString() {
		return "Prediction [type=" + type + ", value=" + value + ", probability=" + probability + ", position="
				+ position + ", rankStatus=" + rankStatus + ", rankId=" + rankId + "]";
	}

}