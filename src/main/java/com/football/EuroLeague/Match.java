package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)

	public class Match {

		@JsonProperty("id")
		private String id;
		  
		@JsonProperty("date")
		private String date;
		  
		@JsonProperty("time")	
		private String time;
		  
		@JsonProperty("homeContestantId")
		private String homeContestantId;
		  
		@JsonProperty("awayContestantId")
		private String awayContestantId;
		  
		@JsonProperty("homeContestantName")
		private String homeContestantName;
		  
		@JsonProperty("awayContestantName")
		private String awayContestantName;
		  
		@JsonProperty("homeContestantCode")
		private String homeContestantCode;
		  
		@JsonProperty("awayContestantCode")
		private String awayContestantCode;
		
		
		@JsonProperty("matchInfo")
		private MatchInfo matchInfo;
		
		@JsonProperty("liveData")
		private FixturesLiveMatch liveData;
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getHomeContestantId() {
			return homeContestantId;
		}

		public void setHomeContestantId(String homeContestantId) {
			this.homeContestantId = homeContestantId;
		}

		public String getAwayContestantId() {
			return awayContestantId;
		}

		public void setAwayContestantId(String awayContestantId) {
			this.awayContestantId = awayContestantId;
		}

		public String getHomeContestantName() {
			return homeContestantName;
		}

		public void setHomeContestantName(String homeContestantName) {
			this.homeContestantName = homeContestantName;
		}

		public String getAwayContestantName() {
			return awayContestantName;
		}

		public void setAwayContestantName(String awayContestantName) {
			this.awayContestantName = awayContestantName;
		}

		public String getHomeContestantCode() {
			return homeContestantCode;
		}

		public void setHomeContestantCode(String homeContestantCode) {
			this.homeContestantCode = homeContestantCode;
		}

		public String getAwayContestantCode() {
			return awayContestantCode;
		}

		public void setAwayContestantCode(String awayContestantCode) {
			this.awayContestantCode = awayContestantCode;
		}

		@Override
		public String toString() {
			return "Match [id=" + id + ", date=" + date + ", time=" + time + ", homeContestantId=" + homeContestantId
					+ ", awayContestantId=" + awayContestantId + ", homeContestantName=" + homeContestantName
					+ ", awayContestantName=" + awayContestantName + ", homeContestantCode=" + homeContestantCode
					+ ", awayContestantCode=" + awayContestantCode + "]";
		}
		  
	}