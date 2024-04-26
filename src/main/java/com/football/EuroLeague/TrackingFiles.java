package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown  = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrackingFiles {
	
	@JsonProperty("fps10")
	private FinishPrediction fps10;
	  
	@JsonProperty("fps25")
	private FinishPrediction fps25;

	public TrackingFiles() {
		super();
	}

	public FinishPrediction getFps10() {
		return fps10;
	}

	public void setFps10(FinishPrediction fps10) {
		this.fps10 = fps10;
	}

	public FinishPrediction getFps25() {
		return fps25;
	}

	public void setFps25(FinishPrediction fps25) {
		this.fps25 = fps25;
	}

	@Override
	public String toString() {
		return "TrackingFiles [fps10=" + fps10 + ", fps25=" + fps25 + "]";
	}

}
