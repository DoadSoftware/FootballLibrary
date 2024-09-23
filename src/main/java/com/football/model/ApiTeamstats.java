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
    private int goals,htgoals,ftgoals;
    private int Offside;
    private int interceptions;
    private int successfulDribble,finalThirdEntries,successfulDribblePercent,duelwonPercent,arielwonPercent;
    private int duelWon;
    private int left, center, right,finalThirdPassingAccuracy;
    private int foulLost, totalClearance, effectiveClearance,interceptionWon,
    			ballRecovery, unsuccessfulTouch, turnover,wonTackle,totalFinalThirdPasses,successfulFinalThirdPasses,
    			possWonAtt3rd,possWonDef3rd,touchesInOppBox,duelLost,blockedScoringAtt,ShotOffTarget,goalsConceded,
    			totalThrows,aerialWon,aerialLost;
    private int ftCornerTaken, ftFoulsWon, ftShotOnTarget, ftShots, ftGoalsConceded;
    private int htCornerTaken, htFoulsWon, htShotOnTarget, htShots, htGoalsConceded;
    private int ftSaves, ftCrosses, ftPasses, ftAccuratePass, ftTouches;
    private int htSaves, htCrosses, htPasses, htAccuratePass, htTouches;
    private int  ftChancesCreated, htChancesCreated, ftOffside, htOffside;
    private double ftPossession, htPossession,ftPassingAccuracy, htPassingAccuracy;
    private int ftSuccessfulDribblePercent, htSuccessfulDribblePercent;
    private int ftDuelWonPercent, htDuelWonPercent,HtTackles,FtTackles;
    private int htDribbles, ftDribbles, htInterceptions, ftInterceptions,
	    htSuccessfulDribble, ftSuccessfulDribble, htDuelWon, ftDuelWon, htFoulLost, ftFoulLost, 
	    htTotalClearance, ftTotalClearance, htEffectiveClearance, ftEffectiveClearance,
	    htInterceptionWon, ftInterceptionWon, htBallRecovery, ftBallRecovery, htUnsuccessfulTouch, 
	    ftUnsuccessfulTouch, htTurnover, ftTurnover, htWonTackle, ftWonTackle,
	    htTotalFinalThirdPasses, ftTotalFinalThirdPasses, htSuccessfulFinalThirdPasses, ftSuccessfulFinalThirdPasses,
	    htPossWonAtt3rd, ftPossWonAtt3rd, htPossWonDef3rd, ftPossWonDef3rd, htTouchesInOppBox, ftTouchesInOppBox,
	    htWonCorners, ftWonCorners, htLostCorners, ftLostCorners, htDuelLost, ftDuelLost, htBlockedScoringAtt, 
	    ftBlockedScoringAtt, htShotOffTarget, ftShotOffTarget,HtDuelwonPercent,FtDuelwonPercent,FtArielwonPercent,
	    HtArielwonPercent,htTotalThrows, ftTotalThrows, htAerialWon, ftAerialWon, htAerialLost, ftAerialLost, 
	    htFinalThirdEntries, ftFinalThirdEntries,ftAerialWonPercent,htAerialWonPercent;
    private int ftFinalThirdPassingAccuracy, htFinalThirdPassingAccuracy;

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
	public int getFinalThirdEntries() {
		return finalThirdEntries;
	}
	public void setFinalThirdEntries(int finalThirdEntries) {
		this.finalThirdEntries = finalThirdEntries;
	}
	
	public int getFinalThirdPassingAccuracy() {
		return finalThirdPassingAccuracy;
	}
	public void setFinalThirdPassingAccuracy(int finalThirdPassingAccuracy) {
		this.finalThirdPassingAccuracy = finalThirdPassingAccuracy;
	}
	public int getSuccessfulDribblePercent() {
		return successfulDribblePercent;
	}
	public void setSuccessfulDribblePercent(int successfulDribblePercent) {
		this.successfulDribblePercent = successfulDribblePercent;
	}
	public int getDuelwonPercent() {
		return duelwonPercent;
	}
	public void setDuelwonPercent(int duelwonPercent) {
		this.duelwonPercent = duelwonPercent;
	}
	public int getArielwonPercent() {
		return arielwonPercent;
	}
	public void setArielwonPercent(int arielwonPercent) {
		this.arielwonPercent = arielwonPercent;
	}
	
	public int getFtCornerTaken() {
		return ftCornerTaken;
	}
	public void setFtCornerTaken(int ftCornerTaken) {
		this.ftCornerTaken = ftCornerTaken;
	}
	public int getFtFoulsWon() {
		return ftFoulsWon;
	}
	public void setFtFoulsWon(int ftFoulsWon) {
		this.ftFoulsWon = ftFoulsWon;
	}
	public int getFtShotOnTarget() {
		return ftShotOnTarget;
	}
	public void setFtShotOnTarget(int ftShotOnTarget) {
		this.ftShotOnTarget = ftShotOnTarget;
	}
	public int getFtShots() {
		return ftShots;
	}
	public void setFtShots(int ftShots) {
		this.ftShots = ftShots;
	}
	public int getFtGoalsConceded() {
		return ftGoalsConceded;
	}
	public void setFtGoalsConceded(int ftGoalsConceded) {
		this.ftGoalsConceded = ftGoalsConceded;
	}
	public int getHtCornerTaken() {
		return htCornerTaken;
	}
	public void setHtCornerTaken(int htCornerTaken) {
		this.htCornerTaken = htCornerTaken;
	}
	public int getHtFoulsWon() {
		return htFoulsWon;
	}
	public void setHtFoulsWon(int htFoulsWon) {
		this.htFoulsWon = htFoulsWon;
	}
	public int getHtShotOnTarget() {
		return htShotOnTarget;
	}
	public void setHtShotOnTarget(int htShotOnTarget) {
		this.htShotOnTarget = htShotOnTarget;
	}
	public int getHtShots() {
		return htShots;
	}
	public void setHtShots(int htShots) {
		this.htShots = htShots;
	}
	public int getHtGoalsConceded() {
		return htGoalsConceded;
	}
	public void setHtGoalsConceded(int htGoalsConceded) {
		this.htGoalsConceded = htGoalsConceded;
	}
	public int getFtSaves() {
		return ftSaves;
	}
	public void setFtSaves(int ftSaves) {
		this.ftSaves = ftSaves;
	}
	public int getFtCrosses() {
		return ftCrosses;
	}
	public void setFtCrosses(int ftCrosses) {
		this.ftCrosses = ftCrosses;
	}
	public int getFtPasses() {
		return ftPasses;
	}
	public void setFtPasses(int ftPasses) {
		this.ftPasses = ftPasses;
	}
	public int getFtAccuratePass() {
		return ftAccuratePass;
	}
	public void setFtAccuratePass(int ftAccuratePass) {
		this.ftAccuratePass = ftAccuratePass;
	}
	public int getFtTouches() {
		return ftTouches;
	}
	public void setFtTouches(int ftTouches) {
		this.ftTouches = ftTouches;
	}
	public int getHtSaves() {
		return htSaves;
	}
	public void setHtSaves(int htSaves) {
		this.htSaves = htSaves;
	}
	public int getHtCrosses() {
		return htCrosses;
	}
	public void setHtCrosses(int htCrosses) {
		this.htCrosses = htCrosses;
	}
	public int getHtPasses() {
		return htPasses;
	}
	public void setHtPasses(int htPasses) {
		this.htPasses = htPasses;
	}
	public int getHtAccuratePass() {
		return htAccuratePass;
	}
	public void setHtAccuratePass(int htAccuratePass) {
		this.htAccuratePass = htAccuratePass;
	}
	public int getHtTouches() {
		return htTouches;
	}
	public void setHtTouches(int htTouches) {
		this.htTouches = htTouches;
	}
	public double getFtPossession() {
		return ftPossession;
	}
	public void setFtPossession(double ftPossession) {
		this.ftPossession = ftPossession;
	}
	public double getHtPossession() {
		return htPossession;
	}
	public void setHtPossession(double htPossession) {
		this.htPossession = htPossession;
	}
	public int getFtChancesCreated() {
		return ftChancesCreated;
	}
	public void setFtChancesCreated(int ftChancesCreated) {
		this.ftChancesCreated = ftChancesCreated;
	}
	public int getHtChancesCreated() {
		return htChancesCreated;
	}
	public void setHtChancesCreated(int htChancesCreated) {
		this.htChancesCreated = htChancesCreated;
	}
	public int getFtOffside() {
		return ftOffside;
	}
	public void setFtOffside(int ftOffside) {
		this.ftOffside = ftOffside;
	}
	public int getHtOffside() {
		return htOffside;
	}
	public void setHtOffside(int htOffside) {
		this.htOffside = htOffside;
	}
	public double getFtPassingAccuracy() {
		return ftPassingAccuracy;
	}
	public void setFtPassingAccuracy(double ftPassingAccuracy) {
		this.ftPassingAccuracy = ftPassingAccuracy;
	}
	public double getHtPassingAccuracy() {
		return htPassingAccuracy;
	}
	public void setHtPassingAccuracy(double htPassingAccuracy) {
		this.htPassingAccuracy = htPassingAccuracy;
	}
	public int getFtSuccessfulDribblePercent() {
		return ftSuccessfulDribblePercent;
	}
	public void setFtSuccessfulDribblePercent(int ftSuccessfulDribblePercent) {
		this.ftSuccessfulDribblePercent = ftSuccessfulDribblePercent;
	}
	public int getHtSuccessfulDribblePercent() {
		return htSuccessfulDribblePercent;
	}
	public void setHtSuccessfulDribblePercent(int htSuccessfulDribblePercent) {
		this.htSuccessfulDribblePercent = htSuccessfulDribblePercent;
	}
	public int getFtDuelWonPercent() {
		return ftDuelWonPercent;
	}
	public void setFtDuelWonPercent(int ftDuelWonPercent) {
		this.ftDuelWonPercent = ftDuelWonPercent;
	}
	public int getHtDuelWonPercent() {
		return htDuelWonPercent;
	}
	public void setHtDuelWonPercent(int htDuelWonPercent) {
		this.htDuelWonPercent = htDuelWonPercent;
	}
	public int getFtAerialWonPercent() {
		return ftAerialWonPercent;
	}
	public void setFtAerialWonPercent(int ftAerialWonPercent) {
		this.ftAerialWonPercent = ftAerialWonPercent;
	}
	public int getHtAerialWonPercent() {
		return htAerialWonPercent;
	}
	public void setHtAerialWonPercent(int htAerialWonPercent) {
		this.htAerialWonPercent = htAerialWonPercent;
	}
	public int getFtFinalThirdPassingAccuracy() {
		return ftFinalThirdPassingAccuracy;
	}
	public void setFtFinalThirdPassingAccuracy(int ftFinalThirdPassingAccuracy) {
		this.ftFinalThirdPassingAccuracy = ftFinalThirdPassingAccuracy;
	}
	public int getHtFinalThirdPassingAccuracy() {
		return htFinalThirdPassingAccuracy;
	}
	public void setHtFinalThirdPassingAccuracy(int htFinalThirdPassingAccuracy) {
		this.htFinalThirdPassingAccuracy = htFinalThirdPassingAccuracy;
	}
	
	public int getHtTackles() {
		return HtTackles;
	}
	public void setHtTackles(int htTackles) {
		HtTackles = htTackles;
	}
	public int getFtTackles() {
		return FtTackles;
	}
	public void setFtTackles(int ftTackles) {
		FtTackles = ftTackles;
	}
	
	public int getHtDribbles() {
		return htDribbles;
	}
	public void setHtDribbles(int htDribbles) {
		this.htDribbles = htDribbles;
	}
	public int getFtDribbles() {
		return ftDribbles;
	}
	public void setFtDribbles(int ftDribbles) {
		this.ftDribbles = ftDribbles;
	}
	public int getHtInterceptions() {
		return htInterceptions;
	}
	public void setHtInterceptions(int htInterceptions) {
		this.htInterceptions = htInterceptions;
	}
	public int getFtInterceptions() {
		return ftInterceptions;
	}
	public void setFtInterceptions(int ftInterceptions) {
		this.ftInterceptions = ftInterceptions;
	}
	public int getHtSuccessfulDribble() {
		return htSuccessfulDribble;
	}
	public void setHtSuccessfulDribble(int htSuccessfulDribble) {
		this.htSuccessfulDribble = htSuccessfulDribble;
	}
	public int getFtSuccessfulDribble() {
		return ftSuccessfulDribble;
	}
	public void setFtSuccessfulDribble(int ftSuccessfulDribble) {
		this.ftSuccessfulDribble = ftSuccessfulDribble;
	}
	public int getHtDuelWon() {
		return htDuelWon;
	}
	public void setHtDuelWon(int htDuelWon) {
		this.htDuelWon = htDuelWon;
	}
	public int getFtDuelWon() {
		return ftDuelWon;
	}
	public void setFtDuelWon(int ftDuelWon) {
		this.ftDuelWon = ftDuelWon;
	}
	public int getHtFoulLost() {
		return htFoulLost;
	}
	public void setHtFoulLost(int htFoulLost) {
		this.htFoulLost = htFoulLost;
	}
	public int getFtFoulLost() {
		return ftFoulLost;
	}
	public void setFtFoulLost(int ftFoulLost) {
		this.ftFoulLost = ftFoulLost;
	}
	public int getHtTotalClearance() {
		return htTotalClearance;
	}
	public void setHtTotalClearance(int htTotalClearance) {
		this.htTotalClearance = htTotalClearance;
	}
	public int getFtTotalClearance() {
		return ftTotalClearance;
	}
	public void setFtTotalClearance(int ftTotalClearance) {
		this.ftTotalClearance = ftTotalClearance;
	}
	public int getHtEffectiveClearance() {
		return htEffectiveClearance;
	}
	public void setHtEffectiveClearance(int htEffectiveClearance) {
		this.htEffectiveClearance = htEffectiveClearance;
	}
	public int getFtEffectiveClearance() {
		return ftEffectiveClearance;
	}
	public void setFtEffectiveClearance(int ftEffectiveClearance) {
		this.ftEffectiveClearance = ftEffectiveClearance;
	}
	public int getHtInterceptionWon() {
		return htInterceptionWon;
	}
	public void setHtInterceptionWon(int htInterceptionWon) {
		this.htInterceptionWon = htInterceptionWon;
	}
	public int getFtInterceptionWon() {
		return ftInterceptionWon;
	}
	public void setFtInterceptionWon(int ftInterceptionWon) {
		this.ftInterceptionWon = ftInterceptionWon;
	}
	public int getHtBallRecovery() {
		return htBallRecovery;
	}
	public void setHtBallRecovery(int htBallRecovery) {
		this.htBallRecovery = htBallRecovery;
	}
	public int getFtBallRecovery() {
		return ftBallRecovery;
	}
	public void setFtBallRecovery(int ftBallRecovery) {
		this.ftBallRecovery = ftBallRecovery;
	}
	public int getHtUnsuccessfulTouch() {
		return htUnsuccessfulTouch;
	}
	public void setHtUnsuccessfulTouch(int htUnsuccessfulTouch) {
		this.htUnsuccessfulTouch = htUnsuccessfulTouch;
	}
	public int getFtUnsuccessfulTouch() {
		return ftUnsuccessfulTouch;
	}
	public void setFtUnsuccessfulTouch(int ftUnsuccessfulTouch) {
		this.ftUnsuccessfulTouch = ftUnsuccessfulTouch;
	}
	public int getHtTurnover() {
		return htTurnover;
	}
	public void setHtTurnover(int htTurnover) {
		this.htTurnover = htTurnover;
	}
	public int getFtTurnover() {
		return ftTurnover;
	}
	public void setFtTurnover(int ftTurnover) {
		this.ftTurnover = ftTurnover;
	}
	public int getHtWonTackle() {
		return htWonTackle;
	}
	public void setHtWonTackle(int htWonTackle) {
		this.htWonTackle = htWonTackle;
	}
	public int getFtWonTackle() {
		return ftWonTackle;
	}
	public void setFtWonTackle(int ftWonTackle) {
		this.ftWonTackle = ftWonTackle;
	}
	public int getHtTotalFinalThirdPasses() {
		return htTotalFinalThirdPasses;
	}
	public void setHtTotalFinalThirdPasses(int htTotalFinalThirdPasses) {
		this.htTotalFinalThirdPasses = htTotalFinalThirdPasses;
	}
	public int getFtTotalFinalThirdPasses() {
		return ftTotalFinalThirdPasses;
	}
	public void setFtTotalFinalThirdPasses(int ftTotalFinalThirdPasses) {
		this.ftTotalFinalThirdPasses = ftTotalFinalThirdPasses;
	}
	public int getHtSuccessfulFinalThirdPasses() {
		return htSuccessfulFinalThirdPasses;
	}
	public void setHtSuccessfulFinalThirdPasses(int htSuccessfulFinalThirdPasses) {
		this.htSuccessfulFinalThirdPasses = htSuccessfulFinalThirdPasses;
	}
	public int getFtSuccessfulFinalThirdPasses() {
		return ftSuccessfulFinalThirdPasses;
	}
	public void setFtSuccessfulFinalThirdPasses(int ftSuccessfulFinalThirdPasses) {
		this.ftSuccessfulFinalThirdPasses = ftSuccessfulFinalThirdPasses;
	}
	public int getHtPossWonAtt3rd() {
		return htPossWonAtt3rd;
	}
	public void setHtPossWonAtt3rd(int htPossWonAtt3rd) {
		this.htPossWonAtt3rd = htPossWonAtt3rd;
	}
	public int getFtPossWonAtt3rd() {
		return ftPossWonAtt3rd;
	}
	public void setFtPossWonAtt3rd(int ftPossWonAtt3rd) {
		this.ftPossWonAtt3rd = ftPossWonAtt3rd;
	}
	public int getHtPossWonDef3rd() {
		return htPossWonDef3rd;
	}
	public void setHtPossWonDef3rd(int htPossWonDef3rd) {
		this.htPossWonDef3rd = htPossWonDef3rd;
	}
	public int getFtPossWonDef3rd() {
		return ftPossWonDef3rd;
	}
	public void setFtPossWonDef3rd(int ftPossWonDef3rd) {
		this.ftPossWonDef3rd = ftPossWonDef3rd;
	}
	public int getHtTouchesInOppBox() {
		return htTouchesInOppBox;
	}
	public void setHtTouchesInOppBox(int htTouchesInOppBox) {
		this.htTouchesInOppBox = htTouchesInOppBox;
	}
	public int getFtTouchesInOppBox() {
		return ftTouchesInOppBox;
	}
	public void setFtTouchesInOppBox(int ftTouchesInOppBox) {
		this.ftTouchesInOppBox = ftTouchesInOppBox;
	}
	public int getHtWonCorners() {
		return htWonCorners;
	}
	public void setHtWonCorners(int htWonCorners) {
		this.htWonCorners = htWonCorners;
	}
	public int getFtWonCorners() {
		return ftWonCorners;
	}
	public void setFtWonCorners(int ftWonCorners) {
		this.ftWonCorners = ftWonCorners;
	}
	public int getHtLostCorners() {
		return htLostCorners;
	}
	public void setHtLostCorners(int htLostCorners) {
		this.htLostCorners = htLostCorners;
	}
	public int getFtLostCorners() {
		return ftLostCorners;
	}
	public void setFtLostCorners(int ftLostCorners) {
		this.ftLostCorners = ftLostCorners;
	}
	public int getHtDuelLost() {
		return htDuelLost;
	}
	public void setHtDuelLost(int htDuelLost) {
		this.htDuelLost = htDuelLost;
	}
	public int getFtDuelLost() {
		return ftDuelLost;
	}
	public void setFtDuelLost(int ftDuelLost) {
		this.ftDuelLost = ftDuelLost;
	}
	public int getHtBlockedScoringAtt() {
		return htBlockedScoringAtt;
	}
	public void setHtBlockedScoringAtt(int htBlockedScoringAtt) {
		this.htBlockedScoringAtt = htBlockedScoringAtt;
	}
	public int getFtBlockedScoringAtt() {
		return ftBlockedScoringAtt;
	}
	public void setFtBlockedScoringAtt(int ftBlockedScoringAtt) {
		this.ftBlockedScoringAtt = ftBlockedScoringAtt;
	}
	public int getHtShotOffTarget() {
		return htShotOffTarget;
	}
	public void setHtShotOffTarget(int htShotOffTarget) {
		this.htShotOffTarget = htShotOffTarget;
	}
	public int getFtShotOffTarget() {
		return ftShotOffTarget;
	}
	public void setFtShotOffTarget(int ftShotOffTarget) {
		this.ftShotOffTarget = ftShotOffTarget;
	}
	public int getHtTotalThrows() {
		return htTotalThrows;
	}
	public void setHtTotalThrows(int htTotalThrows) {
		this.htTotalThrows = htTotalThrows;
	}
	public int getFtTotalThrows() {
		return ftTotalThrows;
	}
	public void setFtTotalThrows(int ftTotalThrows) {
		this.ftTotalThrows = ftTotalThrows;
	}
	public int getHtAerialWon() {
		return htAerialWon;
	}
	public void setHtAerialWon(int htAerialWon) {
		this.htAerialWon = htAerialWon;
	}
	public int getFtAerialWon() {
		return ftAerialWon;
	}
	public void setFtAerialWon(int ftAerialWon) {
		this.ftAerialWon = ftAerialWon;
	}
	public int getHtAerialLost() {
		return htAerialLost;
	}
	public void setHtAerialLost(int htAerialLost) {
		this.htAerialLost = htAerialLost;
	}
	public int getFtAerialLost() {
		return ftAerialLost;
	}
	public void setFtAerialLost(int ftAerialLost) {
		this.ftAerialLost = ftAerialLost;
	}
	public int getHtFinalThirdEntries() {
		return htFinalThirdEntries;
	}
	public void setHtFinalThirdEntries(int htFinalThirdEntries) {
		this.htFinalThirdEntries = htFinalThirdEntries;
	}
	public int getFtFinalThirdEntries() {
		return ftFinalThirdEntries;
	}
	public void setFtFinalThirdEntries(int ftFinalThirdEntries) {
		this.ftFinalThirdEntries = ftFinalThirdEntries;
	}
	
	public int getHtDuelwonPercent() {
		return HtDuelwonPercent;
	}
	public void setHtDuelwonPercent(int htDuelwonPercent) {
		HtDuelwonPercent = htDuelwonPercent;
	}
	public int getFtDuelwonPercent() {
		return FtDuelwonPercent;
	}
	public void setFtDuelwonPercent(int ftDuelwonPercent) {
		FtDuelwonPercent = ftDuelwonPercent;
	}
	public int getFtArielwonPercent() {
		return FtArielwonPercent;
	}
	public void setFtArielwonPercent(int ftArielwonPercent) {
		FtArielwonPercent = ftArielwonPercent;
	}
	public int getHtArielwonPercent() {
		return HtArielwonPercent;
	}
	public void setHtArielwonPercent(int htArielwonPercent) {
		HtArielwonPercent = htArielwonPercent;
	}
	
	public int getHtgoals() {
		return htgoals;
	}
	public void setHtgoals(int htgoals) {
		this.htgoals = htgoals;
	}
	public int getFtgoals() {
		return ftgoals;
	}
	public void setFtgoals(int ftgoals) {
		this.ftgoals = ftgoals;
	}
	public void reset() {
	    name = code = id = "";  
	    shotOnTarget = wonCorners = lostCorners = cornerTaken = yellowCards = redCards = 0;
	    possession = passingAccuracy = 0.0;
	    shots = accuratePass = onTarget = corners = saves = crosses = passes = 0;
	    distanceCovered = touches = tackles = chancesCreated = foulsWon = dribbles = 0;
	    goals = Offside = interceptions = successfulDribble = finalThirdEntries = 0;
	    successfulDribblePercent = duelwonPercent = arielwonPercent = duelWon = 0;
	    left = center = right = finalThirdPassingAccuracy = 0;
	    foulLost = totalClearance = effectiveClearance = interceptionWon = 0;
	    ballRecovery = unsuccessfulTouch = turnover = wonTackle = 0;
	    totalFinalThirdPasses = successfulFinalThirdPasses = 0;
	    possWonAtt3rd = possWonDef3rd = touchesInOppBox = duelLost = 0;
	    blockedScoringAtt = ShotOffTarget = goalsConceded = totalThrows = 0;
	    aerialWon = aerialLost = 0;
	    ftCornerTaken= ftFoulsWon= ftShotOnTarget= ftShots= ftGoalsConceded= 0;
	    htCornerTaken= htFoulsWon= htShotOnTarget= htShots= htGoalsConceded= 0;
	    ftSaves= ftCrosses= ftPasses= ftAccuratePass= ftTouches= 0;
	    htSaves= htCrosses= htPasses= htAccuratePass= htTouches= 0;
	    ftPossession= htPossession= ftChancesCreated= htChancesCreated= ftOffside= htOffside= 0;
	    ftPassingAccuracy= htPassingAccuracy= 0;
	    HtTackles = FtTackles = ftSuccessfulDribblePercent= htSuccessfulDribblePercent= 0;
	    ftDuelWonPercent= htDuelWonPercent= 0;
	    ftAerialWonPercent= htAerialWonPercent= 0;
	    
	    ftFinalThirdPassingAccuracy= htFinalThirdPassingAccuracy= 0;
	    HtDuelwonPercent = FtDuelwonPercent = FtArielwonPercent =
	    HtArielwonPercent  = htDribbles= ftDribbles= htInterceptions= ftInterceptions=
	    htSuccessfulDribble= ftSuccessfulDribble= htDuelWon= ftDuelWon= htFoulLost= ftFoulLost= 
	    htTotalClearance= ftTotalClearance= htEffectiveClearance= ftEffectiveClearance=
	    htInterceptionWon= ftInterceptionWon= htBallRecovery= ftBallRecovery= htUnsuccessfulTouch= 
	    ftUnsuccessfulTouch= htTurnover= ftTurnover= htWonTackle= ftWonTackle=
	    htTotalFinalThirdPasses= ftTotalFinalThirdPasses= htSuccessfulFinalThirdPasses= ftSuccessfulFinalThirdPasses=
	    htPossWonAtt3rd= ftPossWonAtt3rd= htPossWonDef3rd= ftPossWonDef3rd= htTouchesInOppBox= ftTouchesInOppBox=
	    htWonCorners= ftWonCorners= htLostCorners= ftLostCorners= htDuelLost= ftDuelLost= htBlockedScoringAtt= 
	    ftBlockedScoringAtt= htShotOffTarget= ftShotOffTarget=
	    htTotalThrows= htgoals= ftgoals = ftTotalThrows= htAerialWon= ftAerialWon= htAerialLost= ftAerialLost= htFinalThirdEntries= 
	    ftFinalThirdEntries=0;
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

