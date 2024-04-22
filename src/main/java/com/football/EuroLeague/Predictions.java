package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Predictions {

	@JsonProperty("type")
	private String type;
	
	@JsonProperty("timeMin")
	private String timeMin;

	@JsonProperty("timeMinSec")
	private String timeMinSec;
	
	@JsonProperty("periodId")
	private String periodId;
	
	@JsonProperty("prediction")
	private List<MissedPen> prediction;

	@Override
	public String toString() {
		return "Predictions [type=" + type + ", timeMin=" + timeMin + ", timeMinSec=" + timeMinSec + ", periodId="
				+ periodId + ", prediction=" + prediction + "]";
	}
	
}

