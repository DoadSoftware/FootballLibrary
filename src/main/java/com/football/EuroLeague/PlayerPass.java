package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerPass {
	
	@JsonProperty("playerId")
	private String playerId;
	  
	@JsonProperty("firstName")
	private String firstName;
	  
	@JsonProperty("lastName")	
	private String lastName;
	
	@JsonProperty("value")
	private String value;

	public PlayerPass() {
		super();
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "PlayerPass [playerId=" + playerId + ", firstName=" + firstName + ", lastName=" + lastName + ", value="
				+ value + "]";
	}
	
}
