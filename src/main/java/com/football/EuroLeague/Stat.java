package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Stat {

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("type")
	private String type;
	  
	@JsonProperty("value")
	private String value;

	@JsonProperty("indexScore")
	private Stat indexScore;
	
	@JsonProperty("rawIndexScore")
	private List<Stat> rawIndexScore;
	
	public Stat() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Stat getIndexScore() {
		return indexScore;
	}

	public void setIndexScore(Stat indexScore) {
		this.indexScore = indexScore;
	}

	public List<Stat> getRawIndexScore() {
		return rawIndexScore;
	}

	public void setRawIndexScore(List<Stat> rawIndexScore) {
		this.rawIndexScore = rawIndexScore;
	}
	public Stat(String type, String value) {
		super();
		this.type = type;
		this.value = value;
	}
	@Override
	public String toString() {
		return "Stat [name=" + name + ", type=" + type + ", value=" + value + ", indexScore=" + indexScore
				+ ", rawIndexScore=" + rawIndexScore + "]";
	}

}