package com.football.model;

import java.util.ArrayList;
import java.util.List;

public class ApiTeamstats {
    private String name;
    private String code;
    private String id; 
    private int shotOnTarget;
    private int wonCorners;
    private int lostCorners;
    private int cornerTaken;
    private int yellowCards,redCards;
    private double possession;
    private int shots,accuratePass;
    private int onTarget;
    private int corners;
    private int saves;
    private int crosses;
    private int passes;
    private double passingAccuracy;
    private int distanceCovered;
    private int touches;
    private int tackles;
    private int chancesCreated;
    private int foulsWon;
    private int dribbles;
    private int goals;
    private int Offside;
    private int interceptions;
    private int successfulDribble;
    private int duelWon;
    private int left, center, right;
    private int foulLost, totalClearance, effectiveClearance,interceptionWon,
    			ballRecovery, unsuccessfulTouch, turnover,wonTackle,totalFinalThirdPasses,successfulFinalThirdPasses,
    			possWonAtt3rd,possWonDef3rd,touchesInOppBox,duelLost,blockedScoringAtt,ShotOffTarget,goalsConceded,
    			totalThrows,aerialWon,aerialLost;

    List<ApiPlayerStats> Player= new ArrayList<ApiPlayerStats>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getShotOnTarget() {
		return shotOnTarget;
	}
	public void setShotOnTarget(int shotOnTarget) {
		this.shotOnTarget = shotOnTarget;
	}
	public int getWonCorners() {
		return wonCorners;
	}
	public void setWonCorners(int wonCorners) {
		this.wonCorners = wonCorners;
	}
	public int getLostCorners() {
		return lostCorners;
	}
	public void setLostCorners(int lostCorners) {
		this.lostCorners = lostCorners;
	}
	public int getCornerTaken() {
		return cornerTaken;
	}
	public void setCornerTaken(int cornerTaken) {
		this.cornerTaken = cornerTaken;
	}
	public int getYellowCards() {
		return yellowCards;
	}
	public void setYellowCards(int yellowCards) {
		this.yellowCards = yellowCards;
	}
	public double getPossession() {
		return possession;
	}
	public void setPossession(double possession) {
		this.possession = possession;
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
	public int getCorners() {
		return corners;
	}
	public void setCorners(int corners) {
		this.corners = corners;
	}
	public int getSaves() {
		return saves;
	}
	public void setSaves(int saves) {
		this.saves = saves;
	}
	public int getCrosses() {
		return crosses;
	}
	public void setCrosses(int crosses) {
		this.crosses = crosses;
	}
	public int getPasses() {
		return passes;
	}
	public void setPasses(int passes) {
		this.passes = passes;
	}
	public double getPassingAccuracy() {
		return passingAccuracy;
	}
	public void setPassingAccuracy(double passingAccuracy) {
		this.passingAccuracy = passingAccuracy;
	}
	public int getDistanceCovered() {
		return distanceCovered;
	}
	public void setDistanceCovered(int distanceCovered) {
		this.distanceCovered = distanceCovered;
	}
	public int getTouches() {
		return touches;
	}
	public void setTouches(int touches) {
		this.touches = touches;
	}
	public int getTackles() {
		return tackles;
	}
	public void setTackles(int tackles) {
		this.tackles = tackles;
	}
	public int getChancesCreated() {
		return chancesCreated;
	}
	public void setChancesCreated(int chancesCreated) {
		this.chancesCreated = chancesCreated;
	}
	public int getFoulsWon() {
		return foulsWon;
	}
	public void setFoulsWon(int foulsWon) {
		this.foulsWon = foulsWon;
	}
	public int getDribbles() {
		return dribbles;
	}
	public void setDribbles(int dribbles) {
		this.dribbles = dribbles;
	}
	public int getInterceptions() {
		return interceptions;
	}
	public void setInterceptions(int interceptions) {
		this.interceptions = interceptions;
	}
	public int getGoals() {
		return goals;
	}
	public void setGoals(int goals) {
		this.goals = goals;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getCenter() {
		return center;
	}
	public void setCenter(int center) {
		this.center = center;
	}
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}
	public List<ApiPlayerStats> getPlayer() {
		return Player;
	}
	public void setPlayer(List<ApiPlayerStats> player) {
		Player = player;
	}
	public int getRedCards() {
		return redCards;
	}
	public void setRedCards(int redCards) {
		this.redCards = redCards;
	}
	public int getOffside() {
		return Offside;
	}
	public void setOffside(int offside) {
		Offside = offside;
	}
	public int getSuccessfulDribble() {
		return successfulDribble;
	}
	public void setSuccessfulDribble(int successfulDribble) {
		this.successfulDribble = successfulDribble;
	}
	public int getDuelWon() {
		return duelWon;
	}
	public void setDuelWon(int duelWon) {
		this.duelWon = duelWon;
	}
	
	public int getAccuratePass() {
		return accuratePass;
	}
	public void setAccuratePass(int accuratePass) {
		this.accuratePass = accuratePass;
	}
	
	public int getFoulLost() {
		return foulLost;
	}
	public void setFoulLost(int foulLost) {
		this.foulLost = foulLost;
	}
	public int getTotalClearance() {
		return totalClearance;
	}
	public void setTotalClearance(int totalClearance) {
		this.totalClearance = totalClearance;
	}
	public int getEffectiveClearance() {
		return effectiveClearance;
	}
	public void setEffectiveClearance(int effectiveClearance) {
		this.effectiveClearance = effectiveClearance;
	}
	public int getInterceptionWon() {
		return interceptionWon;
	}
	public void setInterceptionWon(int interceptionWon) {
		this.interceptionWon = interceptionWon;
	}
	public int getBallRecovery() {
		return ballRecovery;
	}
	public void setBallRecovery(int ballRecovery) {
		this.ballRecovery = ballRecovery;
	}
	public int getUnsuccessfulTouch() {
		return unsuccessfulTouch;
	}
	public void setUnsuccessfulTouch(int unsuccessfulTouch) {
		this.unsuccessfulTouch = unsuccessfulTouch;
	}
	public int getTurnover() {
		return turnover;
	}
	public void setTurnover(int turnover) {
		this.turnover = turnover;
	}
	public int getWonTackle() {
		return wonTackle;
	}
	public void setWonTackle(int wonTackle) {
		this.wonTackle = wonTackle;
	}
	public int getTotalFinalThirdPasses() {
		return totalFinalThirdPasses;
	}
	public void setTotalFinalThirdPasses(int totalFinalThirdPasses) {
		this.totalFinalThirdPasses = totalFinalThirdPasses;
	}
	public int getSuccessfulFinalThirdPasses() {
		return successfulFinalThirdPasses;
	}
	public void setSuccessfulFinalThirdPasses(int successfulFinalThirdPasses) {
		this.successfulFinalThirdPasses = successfulFinalThirdPasses;
	}
	public int getPossWonAtt3rd() {
		return possWonAtt3rd;
	}
	public void setPossWonAtt3rd(int possWonAtt3rd) {
		this.possWonAtt3rd = possWonAtt3rd;
	}
	public int getPossWonDef3rd() {
		return possWonDef3rd;
	}
	public void setPossWonDef3rd(int possWonDef3rd) {
		this.possWonDef3rd = possWonDef3rd;
	}
	public int getTouchesInOppBox() {
		return touchesInOppBox;
	}
	public void setTouchesInOppBox(int touchesInOppBox) {
		this.touchesInOppBox = touchesInOppBox;
	}
	public int getDuelLost() {
		return duelLost;
	}
	public void setDuelLost(int duelLost) {
		this.duelLost = duelLost;
	}
	public int getBlockedScoringAtt() {
		return blockedScoringAtt;
	}
	public void setBlockedScoringAtt(int blockedScoringAtt) {
		this.blockedScoringAtt = blockedScoringAtt;
	}
	public int getShotOffTarget() {
		return ShotOffTarget;
	}
	public void setShotOffTarget(int shotOffTarget) {
		ShotOffTarget = shotOffTarget;
	}
	public int getGoalsConceded() {
		return goalsConceded;
	}
	public void setGoalsConceded(int goalsConceded) {
		this.goalsConceded = goalsConceded;
	}
	public int getTotalThrows() {
		return totalThrows;
	}
	public void setTotalThrows(int totalThrows) {
		this.totalThrows = totalThrows;
	}
	public int getAerialWon() {
		return aerialWon;
	}
	public void setAerialWon(int aerialWon) {
		this.aerialWon = aerialWon;
	}
	public int getAerialLost() {
		return aerialLost;
	}
	public void setAerialLost(int aerialLost) {
		this.aerialLost = aerialLost;
	}
	@Override
	public String toString() {
		return "ApiTeamstats [name=" + name + ", code=" + code + ", id=" + id + ", shotOnTarget=" + shotOnTarget
				+ ", wonCorners=" + wonCorners + ", lostCorners=" + lostCorners + ", cornerTaken=" + cornerTaken
				+ ", yellowCards=" + yellowCards + ", possession=" + possession + ", shots=" + shots + ", onTarget="
				+ onTarget + ", corners=" + corners + ", saves=" + saves + ", crosses=" + crosses + ", passes=" + passes
				+ ", passingAccuracy=" + passingAccuracy + ", distanceCovered=" + distanceCovered + ", touches="
				+ touches + ", tackles=" + tackles + ", chancesCreated=" + chancesCreated + ", foulsWon=" + foulsWon
				+ ", dribbles=" + dribbles + ", interceptions=" + interceptions + "]";
	}
   
}

