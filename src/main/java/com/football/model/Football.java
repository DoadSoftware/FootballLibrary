package com.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Football {

    @JsonProperty("Teams")
    private List<Team> teams;

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
	public String toString() {
		return "Football [teams=" + teams + "]";
	}

	public static class Team {
		
        @JsonProperty("TeamID")
        private int teamID;

        @JsonProperty("TeamName")
        private String teamName;

        @JsonProperty("Goals")
        private int goals;

        @JsonProperty("Shots")
        private int shots;

        @JsonProperty("OnTarget")
        private int onTarget;

        @JsonProperty("OffTarget")
        private int offTarget;

        @JsonProperty("Crosses")
        private int crosses;

        @JsonProperty("Chances_Created")
        private int chancesCreated;

        @JsonProperty("Saves")
        private int saves;

        @JsonProperty("Interceptions")
        private int interceptions;

        @JsonProperty("Tackles")
        private int tackles;

        @JsonProperty("Fouls")
        private int fouls;

        @JsonProperty("RedCard")
        private int redCard;

        @JsonProperty("OffSide")
        private int offSide;

        @JsonProperty("Corners")
        private int corners;

        @JsonProperty("Substitutions")
        private int substitutions;

        @JsonProperty("Final_Third_Pass")
        private int finalThirdPass;

        @JsonProperty("Final_Third_Entry")
        private int finalThirdEntry;

        @JsonProperty("Touches_in_Opp_Box")
        private int touchesInOppBox;

        @JsonProperty("Shots_Off_Target")
        private int shotsOffTarget;

        @JsonProperty("Big_Chance_Created")
        private int bigChanceCreated;

        @JsonProperty("Forward_Pass")
        private int forwardPass;
        
        @JsonProperty("Shots_On_Target_Per_Game")
        private int shotsOnTargetPerGame;

        @JsonProperty("Shots_Per_Game")
        private int shotsPerGame;

        @JsonProperty("Chances_Created_Per_Game")
        private int chancesCreatedPerGame;

        @JsonProperty("Touches_in_Opp_Box_Per_Game")
        private int touchesInOppBoxPerGame;

        @JsonProperty("Goal_Contribution")
        private int goalContribution;

        @JsonProperty("Shot_Conversion_Percentage")
        private int shotConversionPercentage;

        @JsonProperty("Most_Shots_Without_Goal")
        private int mostShotsWithoutGoal;

        @JsonProperty("Touches")
        private int touches;
        
        @JsonProperty("Accuracy_Percentage_in_Final_3rd")
        private int accuracyPercentageInFinalThird;
       
        @JsonProperty("Possession_Percentage")
        private int possessionPercentage;
        
        @JsonProperty("Passing_Accuracy_Percentage")
        private int passingAccuracyPercentage;

        @JsonProperty("Crossing_Accuracy_Percentage")
        private int crossingAccuracyPercentage;

        @JsonProperty("Shots_Conceded")
        private int shotsConceded;

        @JsonProperty("Goals_Conceded")
        private int goalsConceded;

        @JsonProperty("Clean_Sheet")
        private int cleanSheet;

        @JsonProperty("Duels")
        private int duels;

        @JsonProperty("Duels_Won")
        private int duelsWon;
        
        @JsonProperty("Player_Distance_Covered")
        private int playerDistanceCovered;
        
        @JsonProperty("Players")
        private List<Player> players;

        public Team() {
			super();
			// TODO Auto-generated constructor stub
		}

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

		public int getGoals() {
			return goals;
		}

		public void setGoals(int goals) {
			this.goals = goals;
		}

		public int getShots() {
			return shots;
		}

		public void setShots(int shots) {
			this.shots = shots;
		}

		public int getOnTarget() {
			return onTarget;
		}

		public void setOnTarget(int onTarget) {
			this.onTarget = onTarget;
		}

		public int getOffTarget() {
			return offTarget;
		}

		public void setOffTarget(int offTarget) {
			this.offTarget = offTarget;
		}

		public int getCrosses() {
			return crosses;
		}

		public void setCrosses(int crosses) {
			this.crosses = crosses;
		}

		public int getChancesCreated() {
			return chancesCreated;
		}

		public void setChancesCreated(int chancesCreated) {
			this.chancesCreated = chancesCreated;
		}

		public int getSaves() {
			return saves;
		}

		public void setSaves(int saves) {
			this.saves = saves;
		}

		public int getInterceptions() {
			return interceptions;
		}

		public void setInterceptions(int interceptions) {
			this.interceptions = interceptions;
		}

		public int getTackles() {
			return tackles;
		}

		public void setTackles(int tackles) {
			this.tackles = tackles;
		}

		public int getFouls() {
			return fouls;
		}

		public void setFouls(int fouls) {
			this.fouls = fouls;
		}

		public int getRedCard() {
			return redCard;
		}

		public void setRedCard(int redCard) {
			this.redCard = redCard;
		}

		public int getOffSide() {
			return offSide;
		}

		public void setOffSide(int offSide) {
			this.offSide = offSide;
		}

		public int getCorners() {
			return corners;
		}

		public void setCorners(int corners) {
			this.corners = corners;
		}

		public int getSubstitutions() {
			return substitutions;
		}

		public void setSubstitutions(int substitutions) {
			this.substitutions = substitutions;
		}

		public int getFinalThirdPass() {
			return finalThirdPass;
		}

		public void setFinalThirdPass(int finalThirdPass) {
			this.finalThirdPass = finalThirdPass;
		}

		public int getFinalThirdEntry() {
			return finalThirdEntry;
		}

		public void setFinalThirdEntry(int finalThirdEntry) {
			this.finalThirdEntry = finalThirdEntry;
		}

		public int getTouchesInOppBox() {
			return touchesInOppBox;
		}

		public void setTouchesInOppBox(int touchesInOppBox) {
			this.touchesInOppBox = touchesInOppBox;
		}

		public int getShotsOffTarget() {
			return shotsOffTarget;
		}

		public void setShotsOffTarget(int shotsOffTarget) {
			this.shotsOffTarget = shotsOffTarget;
		}

		public int getBigChanceCreated() {
			return bigChanceCreated;
		}

		public void setBigChanceCreated(int bigChanceCreated) {
			this.bigChanceCreated = bigChanceCreated;
		}

		public int getForwardPass() {
			return forwardPass;
		}

		public void setForwardPass(int forwardPass) {
			this.forwardPass = forwardPass;
		}

		public int getShotsOnTargetPerGame() {
			return shotsOnTargetPerGame;
		}

		public void setShotsOnTargetPerGame(int shotsOnTargetPerGame) {
			this.shotsOnTargetPerGame = shotsOnTargetPerGame;
		}

		public int getShotsPerGame() {
			return shotsPerGame;
		}

		public void setShotsPerGame(int shotsPerGame) {
			this.shotsPerGame = shotsPerGame;
		}

		public int getChancesCreatedPerGame() {
			return chancesCreatedPerGame;
		}

		public void setChancesCreatedPerGame(int chancesCreatedPerGame) {
			this.chancesCreatedPerGame = chancesCreatedPerGame;
		}

		public int getTouchesInOppBoxPerGame() {
			return touchesInOppBoxPerGame;
		}

		public void setTouchesInOppBoxPerGame(int touchesInOppBoxPerGame) {
			this.touchesInOppBoxPerGame = touchesInOppBoxPerGame;
		}

		public int getGoalContribution() {
			return goalContribution;
		}

		public void setGoalContribution(int goalContribution) {
			this.goalContribution = goalContribution;
		}

		public int getShotConversionPercentage() {
			return shotConversionPercentage;
		}

		public void setShotConversionPercentage(int shotConversionPercentage) {
			this.shotConversionPercentage = shotConversionPercentage;
		}

		public int getMostShotsWithoutGoal() {
			return mostShotsWithoutGoal;
		}

		public void setMostShotsWithoutGoal(int mostShotsWithoutGoal) {
			this.mostShotsWithoutGoal = mostShotsWithoutGoal;
		}

		public int getTouches() {
			return touches;
		}

		public void setTouches(int touches) {
			this.touches = touches;
		}

		public int getAccuracyPercentageInFinalThird() {
			return accuracyPercentageInFinalThird;
		}

		public void setAccuracyPercentageInFinalThird(int accuracyPercentageInFinalThird) {
			this.accuracyPercentageInFinalThird = accuracyPercentageInFinalThird;
		}

		public int getPossessionPercentage() {
			return possessionPercentage;
		}

		public void setPossessionPercentage(int possessionPercentage) {
			this.possessionPercentage = possessionPercentage;
		}

		public int getPassingAccuracyPercentage() {
			return passingAccuracyPercentage;
		}

		public void setPassingAccuracyPercentage(int passingAccuracyPercentage) {
			this.passingAccuracyPercentage = passingAccuracyPercentage;
		}

		public int getCrossingAccuracyPercentage() {
			return crossingAccuracyPercentage;
		}

		public void setCrossingAccuracyPercentage(int crossingAccuracyPercentage) {
			this.crossingAccuracyPercentage = crossingAccuracyPercentage;
		}

		public int getShotsConceded() {
			return shotsConceded;
		}

		public void setShotsConceded(int shotsConceded) {
			this.shotsConceded = shotsConceded;
		}

		public int getGoalsConceded() {
			return goalsConceded;
		}

		public void setGoalsConceded(int goalsConceded) {
			this.goalsConceded = goalsConceded;
		}

		public int getCleanSheet() {
			return cleanSheet;
		}

		public void setCleanSheet(int cleanSheet) {
			this.cleanSheet = cleanSheet;
		}

		public int getDuels() {
			return duels;
		}

		public void setDuels(int duels) {
			this.duels = duels;
		}

		public int getDuelsWon() {
			return duelsWon;
		}

		public void setDuelsWon(int duelsWon) {
			this.duelsWon = duelsWon;
		}

		public int getPlayerDistanceCovered() {
			return playerDistanceCovered;
		}

		public void setPlayerDistanceCovered(int playerDistanceCovered) {
			this.playerDistanceCovered = playerDistanceCovered;
		}

		public List<Player> getPlayers() {
			return players;
		}

		public void setPlayers(List<Player> players) {
			this.players = players;
		}

		@Override
		public String toString() {
			return "Team [teamID=" + teamID + ", teamName=" + teamName + ", goals=" + goals + ", shots=" + shots
					+ ", onTarget=" + onTarget + ", offTarget=" + offTarget + ", crosses=" + crosses
					+ ", chancesCreated=" + chancesCreated + ", saves=" + saves + ", interceptions=" + interceptions
					+ ", tackles=" + tackles + ", fouls=" + fouls + ", redCard=" + redCard + ", offSide=" + offSide
					+ ", corners=" + corners + ", substitutions=" + substitutions + ", finalThirdPass=" + finalThirdPass
					+ ", finalThirdEntry=" + finalThirdEntry + ", touchesInOppBox=" + touchesInOppBox
					+ ", shotsOffTarget=" + shotsOffTarget + ", bigChanceCreated=" + bigChanceCreated + ", forwardPass="
					+ forwardPass + ", shotsOnTargetPerGame=" + shotsOnTargetPerGame + ", shotsPerGame=" + shotsPerGame
					+ ", chancesCreatedPerGame=" + chancesCreatedPerGame + ", touchesInOppBoxPerGame="
					+ touchesInOppBoxPerGame + ", goalContribution=" + goalContribution + ", shotConversionPercentage="
					+ shotConversionPercentage + ", mostShotsWithoutGoal=" + mostShotsWithoutGoal + ", touches="
					+ touches + ", accuracyPercentageInFinalThird=" + accuracyPercentageInFinalThird
					+ ", possessionPercentage=" + possessionPercentage + ", passingAccuracyPercentage="
					+ passingAccuracyPercentage + ", crossingAccuracyPercentage=" + crossingAccuracyPercentage
					+ ", shotsConceded=" + shotsConceded + ", goalsConceded=" + goalsConceded + ", cleanSheet="
					+ cleanSheet + ", duels=" + duels + ", duelsWon=" + duelsWon + ", playerDistanceCovered="
					+ playerDistanceCovered + ", players=" + players + "]";
		}

		public static class Player {
			@JsonProperty("PlayerID")
            private int playerID;

            @JsonProperty("PlayerName")
            private String playerName;

            @JsonProperty("TeamID")
            private int teamID;

            @JsonProperty("TeamName")
            private String teamName;

            @JsonProperty("IsSub")
            private int isSub;

            @JsonProperty("IsStarted")
            private int isStarted;

            @JsonProperty("IsCaptain")
            private int isCaptain;

            @JsonProperty("jerseyNo")
            private int jerseyNo;

            @JsonProperty("IsPosition")
            private int isPosition;

            @JsonProperty("Goals")
            private int goals;

            @JsonProperty("Shots")
            private int shots;

            @JsonProperty("OnTarget")
            private int onTarget;

            @JsonProperty("OffTarget")
            private int offTarget;

            @JsonProperty("Crosses")
            private int crosses;

            @JsonProperty("Chances_Created")
            private int chancesCreated;

            @JsonProperty("Saves")
            private int saves;

            @JsonProperty("Interceptions")
            private int interceptions;

            @JsonProperty("Tackles")
            private int tackles;

            @JsonProperty("Fouls")
            private int fouls;

            @JsonProperty("RedCard")
            private int redCard;

            @JsonProperty("OffSide")
            private int offSide;

            @JsonProperty("Assist")
            private int assist;

            @JsonProperty("Final_Third_Pass")
            private int finalThirdPass;

            @JsonProperty("Final_Third_Entry")
            private int finalThirdEntry;

            @JsonProperty("Touches_in_Opp_Box")
            private int touchesInOppBox;

            @JsonProperty("Shots_Off_Target")
            private int shotsOffTarget;

            @JsonProperty("Big_Chance_Created")
            private int bigChanceCreated;

            @JsonProperty("Forward_Pass")
            private int forwardPass;

            @JsonProperty("Shots_On_Target_Per_Game")
            private int shotsOnTargetPerGame;

            @JsonProperty("Shots_Per_Game")
            private int shotsPerGame;

            @JsonProperty("Chances_Created_Per_Game")
            private int chancesCreatedPerGame;

            @JsonProperty("Touches_in_Opp_Box_Per_Game")
            private int touchesInOppBoxPerGame;

            @JsonProperty("Goal_Contribution")
            private int goalContribution;

            @JsonProperty("Shot_Conversion_Percentage")
            private int shotConversionPercentage;

            @JsonProperty("Most_Shots_Without_Goal")
            private int mostShotsWithoutGoal;

            @JsonProperty("Touches")
            private int touches;

            @JsonProperty("Accuracy_Percentage_in_Final_3rd")
            private int accuracyPercentageInFinalThird;

            @JsonProperty("Possession_Percentage")
            private int possessionPercentage;

            @JsonProperty("Passing_Accuracy_Percentage")
            private int passingAccuracyPercentage;

            @JsonProperty("Crossing_Accuracy_Percentage")
            private int crossingAccuracyPercentage;

            @JsonProperty("Shots_Conceded")
            private int shotsConceded;

            @JsonProperty("Goals_Conceded")
            private int goalsConceded;

            @JsonProperty("Clean_Sheet")
            private int cleanSheet;

            @JsonProperty("Duels")
            private int duels;

            @JsonProperty("Duels_Won")
            private int duelsWon;

            @JsonProperty("Player_Distance_Covered")
            private int playerDistanceCovered;

			public int getPlayerID() {
				return playerID;
			}

			public void setPlayerID(int playerID) {
				this.playerID = playerID;
			}

			public String getPlayerName() {
				return playerName;
			}

			public void setPlayerName(String playerName) {
				this.playerName = playerName;
			}

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

			public int getIsSub() {
				return isSub;
			}

			public void setIsSub(int isSub) {
				this.isSub = isSub;
			}

			public int getIsStarted() {
				return isStarted;
			}

			public void setIsStarted(int isStarted) {
				this.isStarted = isStarted;
			}

			public int getIsCaptain() {
				return isCaptain;
			}

			public void setIsCaptain(int isCaptain) {
				this.isCaptain = isCaptain;
			}

			public int getJerseyNo() {
				return jerseyNo;
			}

			public void setJerseyNo(int jerseyNo) {
				this.jerseyNo = jerseyNo;
			}

			public int getIsPosition() {
				return isPosition;
			}

			public void setIsPosition(int isPosition) {
				this.isPosition = isPosition;
			}

			public int getGoals() {
				return goals;
			}

			public void setGoals(int goals) {
				this.goals = goals;
			}

			public int getShots() {
				return shots;
			}

			public void setShots(int shots) {
				this.shots = shots;
			}

			public int getOnTarget() {
				return onTarget;
			}

			public void setOnTarget(int onTarget) {
				this.onTarget = onTarget;
			}

			public int getOffTarget() {
				return offTarget;
			}

			public void setOffTarget(int offTarget) {
				this.offTarget = offTarget;
			}

			public int getCrosses() {
				return crosses;
			}

			public void setCrosses(int crosses) {
				this.crosses = crosses;
			}

			public int getChancesCreated() {
				return chancesCreated;
			}

			public void setChancesCreated(int chancesCreated) {
				this.chancesCreated = chancesCreated;
			}

			public int getSaves() {
				return saves;
			}

			public void setSaves(int saves) {
				this.saves = saves;
			}

			public int getInterceptions() {
				return interceptions;
			}

			public void setInterceptions(int interceptions) {
				this.interceptions = interceptions;
			}

			public int getTackles() {
				return tackles;
			}

			public void setTackles(int tackles) {
				this.tackles = tackles;
			}

			public int getFouls() {
				return fouls;
			}

			public void setFouls(int fouls) {
				this.fouls = fouls;
			}

			public int getRedCard() {
				return redCard;
			}

			public void setRedCard(int redCard) {
				this.redCard = redCard;
			}

			public int getOffSide() {
				return offSide;
			}

			public void setOffSide(int offSide) {
				this.offSide = offSide;
			}

			public int getAssist() {
				return assist;
			}

			public void setAssist(int assist) {
				this.assist = assist;
			}

			public int getFinalThirdPass() {
				return finalThirdPass;
			}

			public void setFinalThirdPass(int finalThirdPass) {
				this.finalThirdPass = finalThirdPass;
			}

			public int getFinalThirdEntry() {
				return finalThirdEntry;
			}

			public void setFinalThirdEntry(int finalThirdEntry) {
				this.finalThirdEntry = finalThirdEntry;
			}

			public int getTouchesInOppBox() {
				return touchesInOppBox;
			}

			public void setTouchesInOppBox(int touchesInOppBox) {
				this.touchesInOppBox = touchesInOppBox;
			}

			public int getShotsOffTarget() {
				return shotsOffTarget;
			}

			public void setShotsOffTarget(int shotsOffTarget) {
				this.shotsOffTarget = shotsOffTarget;
			}

			public int getBigChanceCreated() {
				return bigChanceCreated;
			}

			public void setBigChanceCreated(int bigChanceCreated) {
				this.bigChanceCreated = bigChanceCreated;
			}

			public int getForwardPass() {
				return forwardPass;
			}

			public void setForwardPass(int forwardPass) {
				this.forwardPass = forwardPass;
			}

			public int getShotsOnTargetPerGame() {
				return shotsOnTargetPerGame;
			}

			public void setShotsOnTargetPerGame(int shotsOnTargetPerGame) {
				this.shotsOnTargetPerGame = shotsOnTargetPerGame;
			}

			public int getShotsPerGame() {
				return shotsPerGame;
			}

			public void setShotsPerGame(int shotsPerGame) {
				this.shotsPerGame = shotsPerGame;
			}

			public int getChancesCreatedPerGame() {
				return chancesCreatedPerGame;
			}

			public void setChancesCreatedPerGame(int chancesCreatedPerGame) {
				this.chancesCreatedPerGame = chancesCreatedPerGame;
			}

			public int getTouchesInOppBoxPerGame() {
				return touchesInOppBoxPerGame;
			}

			public void setTouchesInOppBoxPerGame(int touchesInOppBoxPerGame) {
				this.touchesInOppBoxPerGame = touchesInOppBoxPerGame;
			}

			public int getGoalContribution() {
				return goalContribution;
			}

			public void setGoalContribution(int goalContribution) {
				this.goalContribution = goalContribution;
			}

			public int getShotConversionPercentage() {
				return shotConversionPercentage;
			}

			public void setShotConversionPercentage(int shotConversionPercentage) {
				this.shotConversionPercentage = shotConversionPercentage;
			}

			public int getMostShotsWithoutGoal() {
				return mostShotsWithoutGoal;
			}

			public void setMostShotsWithoutGoal(int mostShotsWithoutGoal) {
				this.mostShotsWithoutGoal = mostShotsWithoutGoal;
			}

			public int getTouches() {
				return touches;
			}

			public void setTouches(int touches) {
				this.touches = touches;
			}

			public int getAccuracyPercentageInFinalThird() {
				return accuracyPercentageInFinalThird;
			}

			public void setAccuracyPercentageInFinalThird(int accuracyPercentageInFinalThird) {
				this.accuracyPercentageInFinalThird = accuracyPercentageInFinalThird;
			}

			public int getPossessionPercentage() {
				return possessionPercentage;
			}

			public void setPossessionPercentage(int possessionPercentage) {
				this.possessionPercentage = possessionPercentage;
			}

			public int getPassingAccuracyPercentage() {
				return passingAccuracyPercentage;
			}

			public void setPassingAccuracyPercentage(int passingAccuracyPercentage) {
				this.passingAccuracyPercentage = passingAccuracyPercentage;
			}

			public int getCrossingAccuracyPercentage() {
				return crossingAccuracyPercentage;
			}

			public void setCrossingAccuracyPercentage(int crossingAccuracyPercentage) {
				this.crossingAccuracyPercentage = crossingAccuracyPercentage;
			}

			public int getShotsConceded() {
				return shotsConceded;
			}

			public void setShotsConceded(int shotsConceded) {
				this.shotsConceded = shotsConceded;
			}

			public int getGoalsConceded() {
				return goalsConceded;
			}

			public void setGoalsConceded(int goalsConceded) {
				this.goalsConceded = goalsConceded;
			}

			public int getCleanSheet() {
				return cleanSheet;
			}

			public void setCleanSheet(int cleanSheet) {
				this.cleanSheet = cleanSheet;
			}

			public int getDuels() {
				return duels;
			}

			public void setDuels(int duels) {
				this.duels = duels;
			}

			public int getDuelsWon() {
				return duelsWon;
			}

			public void setDuelsWon(int duelsWon) {
				this.duelsWon = duelsWon;
			}

			public int getPlayerDistanceCovered() {
				return playerDistanceCovered;
			}

			public void setPlayerDistanceCovered(int playerDistanceCovered) {
				this.playerDistanceCovered = playerDistanceCovered;
			}

			public Player() {
				super();
				// TODO Auto-generated constructor stub
			}
			   @Override
				public String toString() {
					return "Player [playerID=" + playerID + ", playerName=" + playerName + ", teamID=" + teamID
							+ ", teamName=" + teamName + ", isSub=" + isSub + ", isStarted=" + isStarted + ", isCaptain="
							+ isCaptain + ", jerseyNo=" + jerseyNo + ", isPosition=" + isPosition + ", goals=" + goals
							+ ", shots=" + shots + ", onTarget=" + onTarget + ", offTarget=" + offTarget + ", crosses="
							+ crosses + ", chancesCreated=" + chancesCreated + ", saves=" + saves + ", interceptions="
							+ interceptions + ", tackles=" + tackles + ", fouls=" + fouls + ", redCard=" + redCard
							+ ", offSide=" + offSide + ", assist=" + assist + ", finalThirdPass=" + finalThirdPass
							+ ", finalThirdEntry=" + finalThirdEntry + ", touchesInOppBox=" + touchesInOppBox
							+ ", shotsOffTarget=" + shotsOffTarget + ", bigChanceCreated=" + bigChanceCreated
							+ ", forwardPass=" + forwardPass + ", shotsOnTargetPerGame=" + shotsOnTargetPerGame
							+ ", shotsPerGame=" + shotsPerGame + ", chancesCreatedPerGame=" + chancesCreatedPerGame
							+ ", touchesInOppBoxPerGame=" + touchesInOppBoxPerGame + ", goalContribution="
							+ goalContribution + ", shotConversionPercentage=" + shotConversionPercentage
							+ ", mostShotsWithoutGoal=" + mostShotsWithoutGoal + ", touches=" + touches
							+ ", accuracyPercentageInFinalThird=" + accuracyPercentageInFinalThird
							+ ", possessionPercentage=" + possessionPercentage + ", passingAccuracyPercentage="
							+ passingAccuracyPercentage + ", crossingAccuracyPercentage=" + crossingAccuracyPercentage
							+ ", shotsConceded=" + shotsConceded + ", goalsConceded=" + goalsConceded + ", cleanSheet="
							+ cleanSheet + ", duels=" + duels + ", duelsWon=" + duelsWon + ", playerDistanceCovered="
							+ playerDistanceCovered + "]";
				}  
        }
    }
}
