package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class MatchOfficial {

	@JsonProperty("id")
	private String id;
	  
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("shortFirstName")
	private String shortFirstName;
	
	@JsonProperty("shortLastName")
	private String shortLastName;

	public MatchOfficial() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getShortFirstName() {
		return shortFirstName;
	}

	public void setShortFirstName(String shortFirstName) {
		this.shortFirstName = shortFirstName;
	}

	public String getShortLastName() {
		return shortLastName;
	}

	public void setShortLastName(String shortLastName) {
		this.shortLastName = shortLastName;
	}

	@Override
	public String toString() {
		return "MatchOfficial [id=" + id + ", type=" + type + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", shortFirstName=" + shortFirstName + ", shortLastName=" + shortLastName + "]";
	}

}