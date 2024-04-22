package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown  = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Person {
	
	@JsonProperty("id")
	private String id;
	  
	@JsonProperty("firstName")
	private String firstName;
	  
	@JsonProperty("lastName")	
	private String lastName;
	  
	@JsonProperty("matchName")
	private String matchName;
	  
	@JsonProperty("nationalityId")
	private String nationalityId;
	
	@JsonProperty("position")
	private String position;
	  
	@JsonProperty("type")
	private String type;
	  
	@JsonProperty("foot")	
	private String foot;
	  
	@JsonProperty("shirtNumber")
	private String shirtNumber;
	  
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMatchName() {
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	public String getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(String nationalityId) {
		this.nationalityId = nationalityId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFoot() {
		return foot;
	}

	public void setFoot(String foot) {
		this.foot = foot;
	}

	public String getShirtNumber() {
		return shirtNumber;
	}

	public void setShirtNumber(String shirtNumber) {
		this.shirtNumber = shirtNumber;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", matchName=" + matchName
				+ ", nationalityId=" + nationalityId + ", position=" + position + ", type=" + type + ", foot=" + foot
				+ ", shirtNumber=" + shirtNumber + "]";
	}

}
