package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class TeamOfficial {

	@JsonProperty("id")
	private String id;
	  
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("colour1")
	private String colour1;

	public TeamOfficial() {
		super();
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColour1() {
		return colour1;
	}

	public void setColour1(String colour1) {
		this.colour1 = colour1;
	}

	@Override
	public String toString() {
		return "TeamOfficial [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", type=" + type
				+ ", colour1=" + colour1 + "]";
	}

}