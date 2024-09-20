package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

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
	@JsonInclude(JsonInclude.Include.NON_NULL)

	public static class possessionWave {
		@JsonProperty("type")
		private String type;
		
		@JsonProperty("intervalLength")
		private List<intervalLength> intervalLength;
		
		@JsonProperty("last")
		private List<interval> last;
		
		@JsonProperty("overall")
		private interval overall;

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

		public List<interval> getLast() {
			return last;
		}

		public void setLast(List<interval> last) {
			this.last = last;
		}

		public interval getOverall() {
			return overall;
		}

		public void setOverall(interval overall) {
			this.overall = overall;
		}

		public possessionWave() {
			super();
		}

		@Override
		public String toString() {
			return "possessionWave [type=" + type + ", intervalLength=" + intervalLength + ", last=" + last
					+ ", overall=" + overall + "]";
		}
		
		
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)

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
		}
		
		 
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)

	public static class interval {
		@JsonProperty("type")
		private String type;

		@JsonProperty("away")
		private String away;
		
		@JsonProperty("home")
		private String home;
		
		@JsonProperty("middle")
		private String middle;

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

		public String getMiddle() {
			return middle;
		}

		public void setMiddle(String middle) {
			this.middle = middle;
		}

		public interval() {
			super();
		}

		@Override
		public String toString() {
			return "interval [type=" + type + ", away=" + away + ", home=" + home + "]";
		}
		
	}
}
