package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Scores {

	@JsonProperty("ht")
	private HalfTime ht;
	  
	@JsonProperty("ft")
	private HalfTime ft;
	
	@JsonProperty("total")
	private HalfTime total;

	public Scores() {
		super();
	}

	public HalfTime getHt() {
		return ht;
	}

	public void setHt(HalfTime ht) {
		this.ht = ht;
	}

	public HalfTime getFt() {
		return ft;
	}

	public void setFt(HalfTime ft) {
		this.ft = ft;
	}

	public HalfTime getTotal() {
		return total;
	}

	public void setTotal(HalfTime total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Scores [ht=" + ht + ", ft=" + ft + ", total=" + total + "]";
	}
	
}