package com.football.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TeamShortMatchStats {

	@JsonProperty("code")
	private String whichTypeOfStats;

	@JsonProperty("match")
	private Object match_id;
	
	@JsonProperty("columns")
	private List<Object> stats_headers;

	@JsonProperty("row")
	private List<APITeam> team_stats_data;

	public String getWhichTypeOfStats() {
		return whichTypeOfStats;
	}

	public void setWhichTypeOfStats(String whichTypeOfStats) {
		this.whichTypeOfStats = whichTypeOfStats;
	}

	public Object getMatch_id() {
		return match_id;
	}

	public void setMatch_id(Object match_id) {
		this.match_id = match_id;
	}

	public List<Object> getStats_headers() {
		return stats_headers;
	}

	public void setStats_headers(List<Object> stats_headers) {
		this.stats_headers = stats_headers;
	}

	public List<APITeam> getTeam_stats_data() {
		return team_stats_data;
	}

	public void setTeam_stats_data(List<APITeam> team_stats_data) {
		this.team_stats_data = team_stats_data;
	}

	@Override
	public String toString() {
		return "TeamShortMatchStats [whichTypeOfStats=" + whichTypeOfStats + ", match_id=" + match_id
				+ ", stats_headers=" + stats_headers + ", team_stats_data=" + team_stats_data + "]";
	}
	
}
