package com.football.EuroLeague;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Possession {

	@JsonProperty("matchInfo")
	private MatchInfo matchInfo;	
	
	@JsonProperty("liveData")
	private PossesionData liveData;

	public MatchInfo getMatchInfo() {
		return matchInfo;
	}

	public void setMatchInfo(MatchInfo matchInfo) {
		this.matchInfo = matchInfo;
	}

	public PossesionData getLiveData() {
		return liveData;
	}

	public void setLiveData(PossesionData liveData) {
		this.liveData = liveData;
	}

	public Possession() {
		super();
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class PossesionData {

		@JsonProperty("matchDetails")
		private MatchDetails matchDetails;
		 
		@JsonProperty("possession")
		private possessionStats possession;

		public MatchDetails getMatchDetails() {
			return matchDetails;
		}

		public void setMatchDetails(MatchDetails matchDetails) {
			this.matchDetails = matchDetails;
		}

		public possessionStats getPossession() {
			return possession;
		}

		public void setPossession(possessionStats possession) {
			this.possession = possession;
		}

		public PossesionData(MatchDetails matchDetails, possessionStats possession) {
			super();
			this.matchDetails = matchDetails;
			this.possession = possession;
		}

		public PossesionData() {
			super();
			// TODO Auto-generated constructor stub
		}
		
	}
}
