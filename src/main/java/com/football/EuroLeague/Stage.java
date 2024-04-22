package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Stage {

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("formatId")
	private String formatId;
	
	@JsonProperty("name")
	private String name;
	  
	@JsonProperty("division")
	private List<Division> division;

	public Stage() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFormatId() {
		return formatId;
	}

	public void setFormatId(String formatId) {
		this.formatId = formatId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Division> getDivision() {
		return division;
	}

	public void setDivision(List<Division> division) {
		this.division = division;
	}

	@Override
	public String toString() {
		return "Stage [id=" + id + ", formatId=" + formatId + ", name=" + name + ", division=" + division + "]";
	}
	
}