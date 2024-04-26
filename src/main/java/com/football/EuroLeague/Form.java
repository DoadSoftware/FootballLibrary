package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Form {

	@JsonProperty("contestantId")
	private String contestantId;
	  
	@JsonProperty("lastSix")
	private String lastSix;
	
	@JsonProperty("match")
	private List<PreviewContestant> match;
	

	public Form() {
		super();
	}

	public List<PreviewContestant> getMatch() {
		return match;
	}

	public void setMatch(List<PreviewContestant> match) {
		this.match = match;
	}

	public String getContestantId() {
		return contestantId;
	}

	public void setContestantId(String contestantId) {
		this.contestantId = contestantId;
	}

	public String getLastSix() {
		return lastSix;
	}

	public void setLastSix(String lastSix) {
		this.lastSix = lastSix;
	}

	@Override
	public String toString() {
		return "Form [contestantId=" + contestantId + ", lastSix=" + lastSix + ", match=" + match + "]";
	}

}