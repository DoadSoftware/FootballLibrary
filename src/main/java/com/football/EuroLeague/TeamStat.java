package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class TeamStat {

	@JsonProperty("fh")
	private String fh;
	  
	@JsonProperty("sh")
	private String sh;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("value")
	private String value;

	@JsonProperty("ht")
	private HalfTime ht;
	  
	@JsonProperty("ft")
	private HalfTime ft;
	
	@JsonProperty("total")
	private HalfTime total;
	
	public TeamStat() {
		super();
	}

	public String getFh() {
		return fh;
	}

	public void setFh(String fh) {
		this.fh = fh;
	}

	public String getSh() {
		return sh;
	}

	public void setSh(String sh) {
		this.sh = sh;
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

	@Override
	public String toString() {
		return "TeamStat [fh=" + fh + ", sh=" + sh + ", type=" + type + ", value=" + value + "]";
	}

}