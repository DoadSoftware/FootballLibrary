package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sport {
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("longName")
	private String longName;
	
	@JsonProperty("shortName")
	private String shortName;
	
	@JsonProperty("knownName")
	private String knownName;
	
	@JsonProperty("competitionFormat")
	private String competitionFormat;

	@JsonProperty("startDate")
	private String startDate;
	
	@JsonProperty("endDate")
	private String endDate;
	
	@JsonProperty("formatId")
	private String formatId;
	
	@JsonProperty("division")
	private List<Division> division;
	
	public Sport() {
		super();
	}

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
	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public String getKnownName() {
		return knownName;
	}

	public void setKnownName(String knownName) {
		this.knownName = knownName;
	}

	public String getCompetitionFormat() {
		return competitionFormat;
	}

	public void setCompetitionFormat(String competitionFormat) {
		this.competitionFormat = competitionFormat;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFormatId() {
		return formatId;
	}

	public void setFormatId(String formatId) {
		this.formatId = formatId;
	}

	public List<Division> getDivision() {
		return division;
	}

	public void setDivision(List<Division> division) {
		this.division = division;
	}

	@Override
	public String toString() {
		return "Sport [id=" + id + ", name=" + name + ", longName=" + longName + ", shortName=" + shortName
				+ ", knownName=" + knownName + ", competitionFormat=" + competitionFormat + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", formatId=" + formatId + ", division=" + division + "]";
	}
	
}
