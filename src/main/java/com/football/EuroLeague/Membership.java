package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Membership {

	@JsonProperty("stat")	
	private List<PlayerStat> stat;
	
	@JsonProperty("contestantId")
	private String contestantId;
	
	@JsonProperty("contestantType")
	private String contestantType;
	
	@JsonProperty("contestantName")
	private String contestantName;
	
	@JsonProperty("active")
	private String active;
	
	@JsonProperty("role")
	private String role;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("transferType")
	private String transferType;

	public Membership() {
		super();
	}

	public String getContestantId() {
		return contestantId;
	}

	public void setContestantId(String contestantId) {
		this.contestantId = contestantId;
	}

	public String getContestantType() {
		return contestantType;
	}

	public void setContestantType(String contestantType) {
		this.contestantType = contestantType;
	}

	public String getContestantName() {
		return contestantName;
	}

	public void setContestantName(String contestantName) {
		this.contestantName = contestantName;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public List<PlayerStat> getStat() {
		return stat;
	}

	public void setStat(List<PlayerStat> stat) {
		this.stat = stat;
	}

	@Override
	public String toString() {
		return "Membership [contestantId=" + contestantId + ", contestantType=" + contestantType + ", contestantName="
				+ contestantName + ", active=" + active + ", role=" + role + ", type=" + type + ", transferType="
				+ transferType + ", stat=" + stat + "]";
	}

}