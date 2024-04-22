package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Period {

	@JsonProperty("id")
	private int id;
	  
	@JsonProperty("start")
	private String start;
	  
	@JsonProperty("end")	
	private String end;
	  
	@JsonProperty("lengthMin")
	private int lengthMin;
	  
	@JsonProperty("lengthSec")
	private int lengthSec;

	public Period() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getLengthMin() {
		return lengthMin;
	}

	public void setLengthMin(int lengthMin) {
		this.lengthMin = lengthMin;
	}

	public int getLengthSec() {
		return lengthSec;
	}

	public void setLengthSec(int lengthSec) {
		this.lengthSec = lengthSec;
	}

	@Override
	public String toString() {
		return "Period [id=" + id + ", start=" + start + ", end=" + end + ", lengthMin=" + lengthMin + ", lengthSec="
				+ lengthSec + "]";
	}
	
}