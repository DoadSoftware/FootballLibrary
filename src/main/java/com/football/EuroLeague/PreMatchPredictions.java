package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class PreMatchPredictions {
	
	@JsonProperty("type")
	private String type;
	  
	@JsonProperty("timeMin")
	private int timeMin;
	
	@JsonProperty("timeSec")
	private int timeSec;

	@JsonProperty("periodId")
	private int periodId;
	
	@JsonProperty("prediction")
	private List<Prediction> prediction;

	public PreMatchPredictions() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public int getPeriodId() {
		return periodId;
	}

	public void setPeriodId(int periodId) {
		this.periodId = periodId;
	}

	public List<Prediction> getPrediction() {
		return prediction;
	}

	public void setPrediction(List<Prediction> prediction) {
		this.prediction = prediction;
	}

	@Override
	public String toString() {
		return "LivePredictions [type=" + type + ", timeMin=" + timeMin + ", timeSec=" + timeSec + ", periodId="
				+ periodId + ", prediction=" + prediction + "]";
	}

}