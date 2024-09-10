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
    private int possession;
    private int shots;
    private int onTarget;
    private int corners;
    private int saves;
    private int crosses;
    private int passes;
    private int passingAccuracy;
    private int distanceCovered;
    private int touches;
    private int tackles;
    private int chancesCreated;
    private int foulsWon;
    private int dribbles;
    private int goals;
    private int Offside;
    private int interceptions;
    private int left, center, right;
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
	public int getPossession() {
		return possession;
	}
	public void setPossession(int possession) {
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
	public int getPassingAccuracy() {
		return passingAccuracy;
	}
	public void setPassingAccuracy(int passingAccuracy) {
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

