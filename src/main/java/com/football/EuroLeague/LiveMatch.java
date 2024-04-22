package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LiveMatch {
	
	@JsonProperty("matchInfo")
	private MatchInfo matchInfo;	
	
	@JsonProperty("liveData")
	private LiveData liveData;

	public LiveMatch() {
		super();
	}

	public MatchInfo getMatchInfo() {
		return matchInfo;
	}

	public void setMatchInfo(MatchInfo matchInfo) {
		this.matchInfo = matchInfo;
	}

	public LiveData getLiveData() {
		return liveData;
	}

	public void setLiveData(LiveData liveData) {
		this.liveData = liveData;
	}

	@Override
	public String toString() {
		return "LiveMatch [matchInfo=" + matchInfo + ", liveData=" + liveData + "]";
	}
}