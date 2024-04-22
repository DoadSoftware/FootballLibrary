package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown  = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamPlayerRanking {
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("position")
	private String position;
	
	@JsonProperty("stat")
	private List<Stat> stat;
	
	@JsonProperty("id")
	private String id;

	public TeamPlayerRanking() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<Stat> getStat() {
		return stat;
	}

	public void setStat(List<Stat> stat) {
		this.stat = stat;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TeamPlayerRanking [name=" + name + ", position=" + position + ", stat=" + stat + ", id=" + id + "]";
	}

}
