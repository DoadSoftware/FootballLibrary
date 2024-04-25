package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Insight {
	@JsonProperty("text")
	private String text;
	
	@JsonProperty("contestantId")
	private String contestantId;
	
	@JsonProperty("timeMin")
	private String timeMin;
	
	@JsonProperty("timeSec")
	private String timeSec;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getContestantId() {
		return contestantId;
	}

	public void setContestantId(String contestantId) {
		this.contestantId = contestantId;
	}

	public String getTimeMin() {
		return timeMin;
	}

	public void setTimeMin(String timeMin) {
		this.timeMin = timeMin;
	}

	public String getTimeSec() {
		return timeSec;
	}

	public void setTimeSec(String timeSec) {
		this.timeSec = timeSec;
	}

	@Override
	public String toString() {
		return "Insight [text=" + text + ", contestantId=" + contestantId + ", timeMin=" + timeMin + ", timeSec="
				+ timeSec + "]";
	}

	public Insight() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
