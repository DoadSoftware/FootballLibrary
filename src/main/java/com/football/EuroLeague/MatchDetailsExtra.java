package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class MatchDetailsExtra {

	@JsonProperty("attendance")
	private String attendance;
	  
	@JsonProperty("matchOfficial")
	private List<MatchOfficial> matchOfficial;

	public MatchDetailsExtra() {
		super();
	}

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}

	public List<MatchOfficial> getMatchOfficial() {
		return matchOfficial;
	}

	public void setMatchOfficial(List<MatchOfficial> matchOfficial) {
		this.matchOfficial = matchOfficial;
	}

	@Override
	public String toString() {
		return "MatchDetailsExtra [attendance=" + attendance + ", matchOfficial=" + matchOfficial + "]";
	}


}
