package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contestant {

	@JsonProperty("id")
	private String id;
	  
	@JsonProperty("name")
	private String name;
	  
	@JsonProperty("code")	
	private String code;
	  
	@JsonProperty("position")
	private String position;
	  
	@JsonProperty("matchDataScore")
	private Players matchDataScore;
	
	@JsonProperty("country")
	private Country country;

	@JsonProperty("stat")	
	private List<Stat> stat;
	
	@JsonProperty("lineUp")
	private List<LineUp> lineUp;
	
	@JsonProperty("player")
	private List<Players> player;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Stat> getStat() {
		return stat;
	}

	public void setStat(List<Stat> stat) {
		this.stat = stat;
	}

	
	public List<LineUp> getLineUp() {
		return lineUp;
	}

	public void setLineUp(List<LineUp> lineUp) {
		this.lineUp = lineUp;
	}

	public List<Players> getPlayer() {
		return player;
	}

	public void setPlayer(List<Players> player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "Contestant [id=" + id + ", name=" + name + ", code=" + code + ", position=" + position + ", country="
				+ country + ", stat=" + stat + "]";
	}
 

}
