package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PassMatrix {

	@JsonProperty("matchInfo")
	private MatchInfo matchInfo;
	
	@JsonProperty("liveData")
	private LiveData liveData;
	
	@JsonProperty("playerRatings")
	private MatchInfo playerRatings;


	@JsonProperty("lineUp")
	private List<LineUp> lineUp;
	
	@JsonProperty("possessionValue")
	private List<possessionValue> possessionValue;
	@JsonProperty("insight")
	private List<Insight> insight;
	
	public PassMatrix() {
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

	public List<possessionValue> getPossessionValue() {
		return possessionValue;
	}

	public void setPossessionValue(List<possessionValue> possessionValue) {
		this.possessionValue = possessionValue;
	}

	@Override
	public String toString() {
		return "PassMatrix [matchInfo=" + matchInfo + ", liveData=" + liveData + "]";
	}

	public MatchInfo getPlayerRatings() {
		return playerRatings;
	}

	public void setPlayerRatings(MatchInfo playerRatings) {
		this.playerRatings = playerRatings;
	}

	public List<LineUp> getLineUp() {
		return lineUp;
	}

	public void setLineUp(List<LineUp> lineUp) {
		this.lineUp = lineUp;
	}

	public List<Insight> getInsight() {
		return insight;
	}

	public void setInsight(List<Insight> insight) {
		this.insight = insight;
	}
	
}
