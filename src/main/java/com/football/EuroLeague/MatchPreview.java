package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchPreview {
	
	@JsonProperty("matchInfo")
	private MatchInfo matchInfo;	
	
	@JsonProperty("previousMeetings")
	private PreviousMeetings previousMeetings;
	
	@JsonProperty("previousMeetingsAnyComp")
	private PreviousMeetingsAnyComp previousMeetingsAnyComp;
	
	@JsonProperty("form")
	private List<Form> form;
	
	@JsonProperty("formAnyComp")
	private List<FormAnyComp> formAnyComp;
	
	public MatchPreview() {
		super();
	}

	public MatchInfo getMatchInfo() {
		return matchInfo;
	}

	public void setMatchInfo(MatchInfo matchInfo) {
		this.matchInfo = matchInfo;
	}

	public PreviousMeetings getPreviousMeetings() {
		return previousMeetings;
	}

	public void setPreviousMeetings(PreviousMeetings previousMeetings) {
		this.previousMeetings = previousMeetings;
	}

	public PreviousMeetingsAnyComp getPreviousMeetingsAnyComp() {
		return previousMeetingsAnyComp;
	}

	public void setPreviousMeetingsAnyComp(PreviousMeetingsAnyComp previousMeetingsAnyComp) {
		this.previousMeetingsAnyComp = previousMeetingsAnyComp;
	}

	public List<Form> getForm() {
		return form;
	}

	public void setForm(List<Form> form) {
		this.form = form;
	}

	public List<FormAnyComp> getFormAnyComp() {
		return formAnyComp;
	}

	public void setFormAnyComp(List<FormAnyComp> formAnyComp) {
		this.formAnyComp = formAnyComp;
	}

	@Override
	public String toString() {
		return "MatchPreview [matchInfo=" + matchInfo + ", previousMeetings=" + previousMeetings
				+ ", previousMeetingsAnyComp=" + previousMeetingsAnyComp + ", form=" + form + ", formAnyComp="
				+ formAnyComp + "]";
	}

}