package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Qualifier {

	@JsonProperty("id")
	private long id;
	  
	@JsonProperty("qualifierId")
	private int qualifierId;

	@JsonProperty("value")
	private String value;

	public Qualifier() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQualifierId() {
		return qualifierId;
	}

	public void setQualifierId(int qualifierId) {
		this.qualifierId = qualifierId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Qualifier [id=" + id + ", qualifierId=" + qualifierId + ", value=" + value + "]";
	}
	
}