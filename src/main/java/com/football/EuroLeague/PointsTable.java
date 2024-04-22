package com.football.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PointsTable {

	@JsonProperty("rank")
	private int rank;
	  
	@JsonProperty("contestantId")
	private String contestantId;
	
	@JsonProperty("contestantName")
	private String contestantName;

	@JsonProperty("contestantShortName")
	private String contestantShortName;
	
	@JsonProperty("contestantClubName")
	private String contestantClubName;
	
	@JsonProperty("contestantCode")
	private String contestantCode;
	
	@JsonProperty("points")
	private int points;
	
	@JsonProperty("matchesPlayed")
	private int matchesPlayed;
	
	@JsonProperty("matchesWon")
	private int matchesWon;
	
	@JsonProperty("matchesLost")
	private int matchesLost;
	
	@JsonProperty("matchesDrawn")
	private int matchesDrawn;
	
	@JsonProperty("goalsFor")
	private int goalsFor;
	
	@JsonProperty("goalsAgainst")
	private int goalsAgainst;
	
	@JsonProperty("goaldifference")
	private String goaldifference;
	
	@JsonProperty("lastSix")
	private String lastSix;
	
	@JsonProperty("predicted")
	private List<Prediction> predicted;
	
	@JsonProperty("finishPrediction")
	private List<Prediction> finishPrediction;
	
	@JsonProperty("rankPrediction")
	private List<Prediction> rankPrediction;

	public PointsTable() {
		super();
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
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

	public String getContestantShortName() {
		return contestantShortName;
	}

	public void setContestantShortName(String contestantShortName) {
		this.contestantShortName = contestantShortName;
	}

	public String getContestantClubName() {
		return contestantClubName;
	}

	public void setContestantClubName(String contestantClubName) {
		this.contestantClubName = contestantClubName;
	}

	public String getContestantCode() {
		return contestantCode;
	}

	public void setContestantCode(String contestantCode) {
		this.contestantCode = contestantCode;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getMatchesPlayed() {
		return matchesPlayed;
	}

	public void setMatchesPlayed(int matchesPlayed) {
		this.matchesPlayed = matchesPlayed;
	}

	public int getMatchesWon() {
		return matchesWon;
	}

	public void setMatchesWon(int matchesWon) {
		this.matchesWon = matchesWon;
	}

	public int getMatchesLost() {
		return matchesLost;
	}

	public void setMatchesLost(int matchesLost) {
		this.matchesLost = matchesLost;
	}

	public int getMatchesDrawn() {
		return matchesDrawn;
	}

	public void setMatchesDrawn(int matchesDrawn) {
		this.matchesDrawn = matchesDrawn;
	}

	public int getGoalsFor() {
		return goalsFor;
	}

	public void setGoalsFor(int goalsFor) {
		this.goalsFor = goalsFor;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	public String getGoaldifference() {
		return goaldifference;
	}

	public void setGoaldifference(String goaldifference) {
		this.goaldifference = goaldifference;
	}

	public String getLastSix() {
		return lastSix;
	}

	public void setLastSix(String lastSix) {
		this.lastSix = lastSix;
	}

	public List<Prediction> getPredicted() {
		return predicted;
	}

	public void setPredicted(List<Prediction> predicted) {
		this.predicted = predicted;
	}

	public List<Prediction> getFinishPrediction() {
		return finishPrediction;
	}

	public void setFinishPrediction(List<Prediction> finishPrediction) {
		this.finishPrediction = finishPrediction;
	}

	public List<Prediction> getRankPrediction() {
		return rankPrediction;
	}

	public void setRankPrediction(List<Prediction> rankPrediction) {
		this.rankPrediction = rankPrediction;
	}

	@Override
	public String toString() {
		return "PointsTable [rank=" + rank + ", contestantId=" + contestantId + ", contestantName=" + contestantName
				+ ", contestantShortName=" + contestantShortName + ", contestantClubName=" + contestantClubName
				+ ", contestantCode=" + contestantCode + ", points=" + points + ", matchesPlayed=" + matchesPlayed
				+ ", matchesWon=" + matchesWon + ", matchesLost=" + matchesLost + ", matchesDrawn=" + matchesDrawn
				+ ", goalsFor=" + goalsFor + ", goalsAgainst=" + goalsAgainst + ", goaldifference=" + goaldifference
				+ ", lastSix=" + lastSix + "]";
	}
}
