package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class possessionStats {
	
	@JsonProperty("possessionWave")
	private List<possessionWave> possessionWave;
	
	public List<possessionWave> getPossessionWave() {
		return possessionWave;
	}

	public void setPossessionWave(List<possessionWave> possessionWave) {
		this.possessionWave = possessionWave;
	}

	@Override
	public String toString() {
		return "possessionStats [possessionWave=" + possessionWave + "]";
	}

	public possessionStats() {
		super();
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)

	public static class possessionWave {
		@JsonProperty("type")
		private String type;
		
		@JsonProperty("intervalLength")
		private List<intervalLength> intervalLength;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public List<intervalLength> getIntervalLength() {
			return intervalLength;
		}

		public void setIntervalLength(List<intervalLength> intervalLength) {
			this.intervalLength = intervalLength;
		}

		public possessionWave() {
			super();
		}

		@Override
		public String toString() {
			return "possessionWave [type=" + type + ", intervalLength=" + intervalLength + "]";
		}
		
		
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class intervalLength {
		@JsonProperty("type")
		private String type;
		  
		@JsonProperty("interval")
		private List<interval> interval;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public List<interval> getInterval() {
			return interval;
		}

		public void setInterval(List<interval> interval) {
			this.interval = interval;
		}

		@Override
		public String toString() {
			return "intervalLength [type=" + type + ", interval=" + interval + "]";
		}

		public intervalLength() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		 
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class interval {
		@JsonProperty("type")
		private String type;

		@JsonProperty("away")
		private String away;
		
		@JsonProperty("home")
		private String home;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getAway() {
			return away;
		}

		public void setAway(String away) {
			this.away = away;
		}

		public String getHome() {
			return home;
		}

		public void setHome(String home) {
			this.home = home;
		}

		public interval() {
			super();
		}
		
	}
}
