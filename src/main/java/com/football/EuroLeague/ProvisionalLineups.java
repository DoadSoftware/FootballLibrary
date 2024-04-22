package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvisionalLineups {
	
	@JsonProperty("lineUp")
	private List<CurrentLineup> lineUp;

	public ProvisionalLineups() {
		super();
	}

	public List<CurrentLineup> getLineUp() {
		return lineUp;
	}

	public void setLineUp(List<CurrentLineup> lineUp) {
		this.lineUp = lineUp;
	}

	@Override
	public String toString() {
		return "ProvisionalLineups [lineUp=" + lineUp + "]";
	}
	
}