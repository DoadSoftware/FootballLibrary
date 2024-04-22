package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class LineUp {

	@JsonProperty("contestantId")
	private String contestantId;
	  
	@JsonProperty("formationUsed")
	private String formationUsed;
	
	@JsonProperty("player")
	private List<Players> player;
	
	@JsonProperty("teamOfficial")
	private TeamOfficial teamOfficial;
	
	@JsonProperty("stat")
	private List<TeamStat> stat;
	
	@JsonProperty("kit")
	private TeamOfficial kit;

	public LineUp() {
		super();
	}

	public String getContestantId() {
		return contestantId;
	}

	public void setContestantId(String contestantId) {
		this.contestantId = contestantId;
	}

	public String getFormationUsed() {
		return formationUsed;
	}

	public void setFormationUsed(String formationUsed) {
		this.formationUsed = formationUsed;
	}

	public List<Players> getPlayer() {
		return player;
	}

	public void setPlayer(List<Players> player) {
		this.player = player;
	}

	public TeamOfficial getTeamOfficial() {
		return teamOfficial;
	}

	public void setTeamOfficial(TeamOfficial teamOfficial) {
		this.teamOfficial = teamOfficial;
	}

	public List<TeamStat> getStat() {
		return stat;
	}

	public void setStat(List<TeamStat> stat) {
		this.stat = stat;
	}

	public TeamOfficial getKit() {
		return kit;
	}

	public void setKit(TeamOfficial kit) {
		this.kit = kit;
	}

	@Override
	public String toString() {
		return "LineUp [contestantId=" + contestantId + ", formationUsed=" + formationUsed + ", player=" + player
				+ ", teamOfficial=" + teamOfficial + ", stat=" + stat + ", kit=" + kit + "]";
	}
	
}