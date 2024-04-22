package com.football.EuroLeague;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopPerformerPlayers {

	@JsonProperty("contestantId")
	private String contestantId;
	  
	@JsonProperty("contestantName")
	private String contestantName;
	  
	@JsonProperty("id")	
	private String id;
	  
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("matchName")
	private String matchName;
	  
	@JsonProperty("rank")
	private int rank;
	  
	@JsonProperty("value")
	private int value;

	public TopPerformerPlayers() {
		super();
	}

	public String getContestantId() {
		return contestantId;
	}

	public void setContestantId(String contestantId) {
		this.contestantId = contestantId;
	}

	public String getContestantName() {
		return contestantName;
	}

	public void setContestantName(String contestantName) {
		this.contestantName = contestantName;
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

	public String getMatchName() {
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "TopPerformerPlayers [contestantId=" + contestantId + ", contestantName=" + contestantName + ", id=" + id
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", matchName=" + matchName + ", rank=" + rank
				+ ", value=" + value + "]";
	}
	
}