package com.football.model;

public class APITeam {
	
	private Integer team_id;
	private String team_name;
	private Integer param_id;
	private String param_name;
	private float value;
	private String ts;

	public Integer getTeam_id() {
		return team_id;
	}
	public void setTeam_id(Integer team_id) {
		this.team_id = team_id;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public Integer getParam_id() {
		return param_id;
	}
	public void setParam_id(Integer param_id) {
		this.param_id = param_id;
	}
	public String getParam_name() {
		return param_name;
	}
	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	@Override
	public String toString() {
		return "Team [team_id=" + team_id + ", team_name=" + team_name + ", param_id=" + param_id + ", param_name="
				+ param_name + ", value=" + value + ", ts=" + ts + "]";
	}
	
}
