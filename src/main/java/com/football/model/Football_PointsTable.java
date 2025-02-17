package com.football.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Football_PointsTable {

    @JsonProperty("PointsTable") 
    private List<PointsTable> pointsTable;  

    public List<PointsTable> getPointsTable() {
        return pointsTable;
    }

    public void setPointsTable(List<PointsTable> pointsTable) {
        this.pointsTable = pointsTable;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PointsTable {
        
        @JsonProperty("TeamID")
        private int teamID;
        
        @JsonProperty("TeamName")
        private String teamName;
        
        @JsonProperty("Prefix")
        private String prefix;
        
        @JsonProperty("Matches")
        private int matches;  // Changed from String to int if it's a numeric value
        
        @JsonProperty("Won")
        private int won;
        
        @JsonProperty("Lost")
        private int lost;
        
        @JsonProperty("Drawn")
        private int drawn;
        
        @JsonProperty("Points")
        private int points;
        
        @JsonProperty("GoalDifference")
        private int goalDifference;
        
        @JsonProperty("GoalsFor")
        private int goalsFor;
        
        @JsonProperty("GoalsAgainst")
        private int goalsAgainst;
        
        @JsonProperty("Position")
        private int position;

        // Getters and Setters
        public int getTeamID() {
            return teamID;
        }

        public void setTeamID(int teamID) {
            this.teamID = teamID;
        }

        public String getTeamName() {
            return teamName;
        }

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public int getMatches() {
            return matches;
        }

        public void setMatches(int matches) {
            this.matches = matches;
        }

        public int getWon() {
            return won;
        }

        public void setWon(int won) {
            this.won = won;
        }

        public int getLost() {
            return lost;
        }

        public void setLost(int lost) {
            this.lost = lost;
        }

        public int getDrawn() {
            return drawn;
        }

        public void setDrawn(int drawn) {
            this.drawn = drawn;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public int getGoalDifference() {
            return goalDifference;
        }

        public void setGoalDifference(int goalDifference) {
            this.goalDifference = goalDifference;
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

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        @Override
        public String toString() {
            return "PointsTable [teamID=" + teamID + ", teamName=" + teamName + ", prefix=" + prefix + ", matches=" + matches
                    + ", won=" + won + ", lost=" + lost + ", drawn=" + drawn + ", points=" + points + ", goalDifference=" 
                    + goalDifference + ", goalsFor=" + goalsFor + ", goalsAgainst=" + goalsAgainst + ", position=" + position + "]";
        }

        // Constructor
        public PointsTable() {
            super();
        }
    }
}
