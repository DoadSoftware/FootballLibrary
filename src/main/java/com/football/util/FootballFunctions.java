package com.football.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.json.JSONObject;
import org.springframework.web.util.HtmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.football.EuroLeague.Card;
import com.football.EuroLeague.Events;
import com.football.EuroLeague.Goal;
import com.football.EuroLeague.LiveMatch;
import com.football.EuroLeague.SeasonalStats;
import com.football.EuroLeague.Stat;
import com.football.EuroLeague.Substitute;
import com.football.EuroLeague.TeamPlayerRanking;
import com.football.EuroLeague.TeamStat;
import com.football.EuroLeague.TeamsStanding;
import com.football.EuroLeague.rankings;
import com.football.EuroLeague.teamData;
import com.football.EuroLeague.PassMatrix;
import com.football.EuroLeague.PlayerData;
import com.football.EuroLeague.Players;
import com.football.EuroLeague.PointsTable;
import com.football.EuroLeague.MatchPreview;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.football.model.ApiEventStats;
import com.football.model.ApiMatch;
import com.football.model.ApiPlayerStats;
import com.football.model.ApiTeamstats;
import com.football.model.Configurations;
import com.football.model.Fixture;
import com.football.model.Formation;
import com.football.model.HeadToHead;
import com.football.model.HeaderText;
import com.football.model.LeaderBoard;
import com.football.model.LeagueTeam;
import com.football.model.Match;
import com.football.model.MatchStats;
import com.football.model.Player;
import com.football.model.PlayerComparison;
import com.football.model.PlayerStat;
import com.football.model.PlayerStats;
import com.football.model.Team;
import com.football.model.TeamStats;
import com.football.model.TopStats;
import com.football.model.Tournament;
import com.football.service.FootballService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.w3c.dom.Element;

public class FootballFunctions {
	
	public static LiveMatch LiveMatch;	
	public static SeasonalStats SeasonalStats;
	public static rankings rankings;
	public static PassMatrix PassMatrix;
	public static MatchPreview matchPreview;
	
	public static String ordinal(int i) {
	    int mod100 = i % 100;
	    int mod10 = i % 10;
	    if(mod10 == 1 && mod100 != 11) {
	        return i + "st";
	    } else if(mod10 == 2 && mod100 != 12) {
	        return i + "nd";
	    } else if(mod10 == 3 && mod100 != 13) {
	        return i + "rd";
	    } else {
	        return i + "th";
	    }
	}
	
	public static boolean containsAllStats(String mainString, String[] stats) {
        for (String stat : stats) {
            if (mainString.contains(stat)) {
                return true; 
            }
        }
        return false;
    }
	
	public static String[] getMonthNames(int monthNumber) {
        String[] monthNames = new String[2];
        
        // Handle the case for September (9) to return "Sept" for short name
        if (monthNumber == 9) {
            monthNames[0] = "Sept";  // Custom short name for September
        } else {
            // Get the Month enum value for the given month number
            Month month = Month.of(monthNumber);
            
            // Get the short name of the month using TextStyle.SHORT
            monthNames[0] = month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        }
        
        // Get the full name of the month using TextStyle.FULL
        Month month = Month.of(monthNumber);
        monthNames[1] = month.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        
        return monthNames;
    }
	
	public static String hundredsTensUnits(String number) {
		String hundReds ="0",tens="0",units="0";
		
		switch (number.length()) {
		case 1:
			units = String.valueOf(number.charAt(0));
			break;
		case 2:
			tens = String.valueOf(number.charAt(0));
			units = String.valueOf(number.charAt(1));
			break;
		case 3:
			hundReds = String.valueOf(number.charAt(0));
			tens = String.valueOf(number.charAt(1));
			units = String.valueOf(number.charAt(2));
			break;
		}
		
		return hundReds + "," + tens + "," + units;
	}
	
	public static List<LeagueTeam> PointsTableAsStanding(List<LeagueTeam> points_table, Match match) throws IOException {
		
		if(match.getHomeTeamScore() > match.getAwayTeamScore()) {
			for(LeagueTeam table : points_table) {
				if(table.getTeamName().equalsIgnoreCase(match.getHomeTeam().getTeamBadge())) {
					table.setPlayed(table.getPlayed()+1);
					table.setWon(table.getWon()+1);
					table.setGoal_For(table.getGoal_For() + match.getHomeTeamScore());
					table.setGoal_Against(table.getGoal_Against() + match.getAwayTeamScore());
					table.setGD(table.getGoal_For() - table.getGoal_Against());
					table.setPoints(table.getPoints() + 3);
				}
				if(table.getTeamName().equalsIgnoreCase(match.getAwayTeam().getTeamBadge())) {
					table.setPlayed(table.getPlayed()+1);
					table.setLost(table.getLost()+1);
					table.setGoal_For(table.getGoal_For() + match.getAwayTeamScore());
					table.setGoal_Against(table.getGoal_Against() + match.getHomeTeamScore());
					table.setGD(table.getGoal_For() - table.getGoal_Against());
				}
			}
		}else if(match.getHomeTeamScore() < match.getAwayTeamScore()) {
			for(LeagueTeam table : points_table) {
				if(table.getTeamName().equalsIgnoreCase(match.getAwayTeam().getTeamBadge())) {
					table.setPlayed(table.getPlayed()+1);
					table.setWon(table.getWon()+1);
					table.setGoal_For(table.getGoal_For() + match.getAwayTeamScore());
					table.setGoal_Against(table.getGoal_Against()+ match.getHomeTeamScore());
					table.setGD(table.getGoal_For() - table.getGoal_Against());
					table.setPoints(table.getPoints() + 3);
				}
				if(table.getTeamName().equalsIgnoreCase(match.getHomeTeam().getTeamBadge())) {
					table.setPlayed(table.getPlayed()+1);
					table.setLost(table.getLost()+1);
					table.setGoal_For(table.getGoal_For() + match.getHomeTeamScore());
					table.setGoal_Against(table.getGoal_Against() + match.getAwayTeamScore());
					table.setGD(table.getGoal_For() - table.getGoal_Against());
				}
			}
		}else if(match.getHomeTeamScore() == match.getAwayTeamScore()) {
			for(LeagueTeam table : points_table) {
				if(table.getTeamName().equalsIgnoreCase(match.getAwayTeam().getTeamBadge())) {
					table.setPlayed(table.getPlayed()+1);
					table.setDrawn(table.getDrawn() + 1);
					table.setGoal_For(table.getGoal_For() + match.getAwayTeamScore());
					table.setGoal_Against(table.getGoal_Against()+ match.getHomeTeamScore());
					table.setGD(table.getGoal_For() - table.getGoal_Against());
					table.setPoints(table.getPoints() + 1);
				}
				if(table.getTeamName().equalsIgnoreCase(match.getHomeTeam().getTeamBadge())) {
					table.setPlayed(table.getPlayed()+1);
					table.setDrawn(table.getDrawn() + 1);
					table.setGoal_For(table.getGoal_For() + match.getHomeTeamScore());
					table.setGoal_Against(table.getGoal_Against() + match.getAwayTeamScore());
					table.setGD(table.getGoal_For() - table.getGoal_Against());
					table.setPoints(table.getPoints() + 1);
				}
			}
		}
		Collections.sort(points_table,new FootballFunctions.PointsComparator());
		
		return points_table;
	}
	
	public static class PointsComparator implements Comparator<LeagueTeam> {
	    @Override
	    public int compare(LeagueTeam pt1, LeagueTeam pt2) {
	    	if(pt2.getPoints() == pt1.getPoints()) {
	    		if(pt2.getGD() == pt1.getGD()) {
	    			return Integer.compare(pt2.getGoal_For(), pt1.getGoal_For());
	    		}else {
	    			return Integer.compare(pt2.getGD(), pt1.getGD());
	    		}
	    	}else {
	    		return Integer.compare(pt2.getPoints(), pt1.getPoints());
	    	}
	    }
	}
	
	public static List<PointsTable> APIPointsTableAsStanding(TeamsStanding teamStandings , Match match) throws IOException {
		
		List<PointsTable> points_table = new ArrayList<PointsTable>();
		points_table = teamStandings.getStage().get(0).getDivision().get(0).getRanking();
		
		if(match.getHomeTeamScore() > match.getAwayTeamScore() || match.getHomeTeamScore() < match.getAwayTeamScore()) {
			for(PointsTable table : points_table) {
				if(table.getContestantId().equalsIgnoreCase(match.getHomeTeam().getTeamApiId())) {
					table.setMatchesPlayed(table.getMatchesPlayed() + 1);
					table.setMatchesWon(table.getMatchesWon() + 1);
					table.setGoalsFor(table.getGoalsFor() + match.getHomeTeamScore());
					table.setGoalsAgainst(table.getGoalsAgainst() + match.getAwayTeamScore());
					if((table.getGoalsFor() - table.getGoalsAgainst()) > 0) {
						table.setGoaldifference("+" + (table.getGoalsFor() - table.getGoalsAgainst()));
					}else {
						table.setGoaldifference(String.valueOf((table.getGoalsFor() - table.getGoalsAgainst())));
					}
					if(match.getHomeTeamScore() > match.getAwayTeamScore()) {
						table.setPoints(table.getPoints() + 3);
					}
				}
				if(table.getContestantId().equalsIgnoreCase(match.getAwayTeam().getTeamApiId())) {
					table.setMatchesPlayed(table.getMatchesPlayed() + 1);
					table.setMatchesLost(table.getMatchesLost() + 1);
					table.setGoalsFor(table.getGoalsFor() + match.getAwayTeamScore());
					table.setGoalsAgainst(table.getGoalsAgainst() + match.getHomeTeamScore());
					
					if((table.getGoalsFor() - table.getGoalsAgainst()) > 0) {
						table.setGoaldifference("+" + (table.getGoalsFor() - table.getGoalsAgainst()));
					}else {
						table.setGoaldifference(String.valueOf((table.getGoalsFor() - table.getGoalsAgainst())));
					}
					if(match.getHomeTeamScore() < match.getAwayTeamScore()) {
						table.setPoints(table.getPoints() + 3);
					}
				}
			}
		}else if(match.getHomeTeamScore() == match.getAwayTeamScore()) {
			for(PointsTable table : points_table) {
				if(table.getContestantId().equalsIgnoreCase(match.getHomeTeam().getTeamApiId())) {
					table.setMatchesPlayed(table.getMatchesPlayed() + 1);
					table.setMatchesDrawn(table.getMatchesDrawn() + 1);
					table.setGoalsFor(table.getGoalsFor() + match.getHomeTeamScore());
					table.setGoalsAgainst(table.getGoalsAgainst() + match.getAwayTeamScore());
					if((table.getGoalsFor() - table.getGoalsAgainst()) > 0) {
						table.setGoaldifference("+" + (table.getGoalsFor() - table.getGoalsAgainst()));
					}else {
						table.setGoaldifference(String.valueOf((table.getGoalsFor() - table.getGoalsAgainst())));
					}
					table.setPoints(table.getPoints() + 1);
				}
				if(table.getContestantId().equalsIgnoreCase(match.getAwayTeam().getTeamApiId())) {
					table.setMatchesPlayed(table.getMatchesPlayed() + 1);
					table.setMatchesDrawn(table.getMatchesDrawn() + 1);
					table.setGoalsFor(table.getGoalsFor() + match.getAwayTeamScore());
					table.setGoalsAgainst(table.getGoalsAgainst() + match.getHomeTeamScore());
					if((table.getGoalsFor() - table.getGoalsAgainst()) > 0) {
						table.setGoaldifference("+" + (table.getGoalsFor() - table.getGoalsAgainst()));
					}else {
						table.setGoaldifference(String.valueOf((table.getGoalsFor() - table.getGoalsAgainst())));
					}
					table.setPoints(table.getPoints() + 1);
				}
			}
		}
		Collections.sort(points_table,new FootballFunctions.PointsComparatorAPI());
		
		return points_table;
	}
	
	public static class PointsComparatorAPI implements Comparator<PointsTable> {
	    @Override
	    public int compare(PointsTable pt1, PointsTable pt2) {
	    	if(pt2.getPoints() == pt1.getPoints()) {
	    		if(Integer.valueOf(pt2.getGoaldifference()) == Integer.valueOf(pt1.getGoaldifference())) {
	    			return Integer.compare(pt2.getGoalsFor(), pt1.getGoalsFor());
	    		}else {
	    			return Integer.compare(Integer.valueOf(pt2.getGoaldifference()), Integer.valueOf(pt1.getGoaldifference()));
	    		}
	    	}else {
	    		return Integer.compare(pt2.getPoints(), pt1.getPoints());
	    	}
	    }
	}
	
	public static void ApiPlayerStat(LiveMatch liveMatch,ApiMatch match) {
		
		match.getApi_LiveMatch().getAwayTeam().reset();
		match.getApi_LiveMatch().getHomeTeam().reset(); 
		match.getApi_LiveMatch().getHomeTeam().getPlayer().clear();
		match.getApi_LiveMatch().getAwayTeam().getPlayer().clear();
		
		for (int i = 0; i < 2; i++) {
		    match.getApi_LiveMatch().getHomeTeam().setName(liveMatch.getMatchInfo().getContestant().get(i).getName().trim());
		    match.getApi_LiveMatch().getAwayTeam().setId(liveMatch.getMatchInfo().getContestant().get(i).getId().trim());
		}
	    for (int teamIndex = 0; teamIndex <= 1; teamIndex++) {
	        ApiTeamstats team = (teamIndex == 0) ? match.getApi_LiveMatch().getHomeTeam() : match.getApi_LiveMatch().getAwayTeam();
	        for (Players py : liveMatch.getLiveData().getLineUp().get(teamIndex).getPlayer()) {
	            ApiPlayerStats playerStats1 = new ApiPlayerStats();
	            playerStats1.setId(py.getPlayerId().trim());
	            playerStats1.setName(HtmlUtils.htmlEscape(py.getMatchName().trim()));
	            playerStats1.setShirtNumber(py.getShirtNumber());
	            playerStats1.setPosition(py.getPosition());
	            playerStats1.setSubPosition(py.getSubPosition());

	            if (py.getCaptain() != null && py.getCaptain().equalsIgnoreCase(FootballUtil.YES)) {
	                playerStats1.setCaptain(py.getCaptain());
	            }

	            if (py.getStat() != null) {
	                for (Stat stat : py.getStat()) {
	                    switch (stat.getType()) {
	                        case "fouls":
	                            playerStats1.setFoul(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "totalClearance":
	                            playerStats1.setTotalClearance(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "effectiveClearance":
	                            playerStats1.setEffectiveClearance(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "totalTackle":
	                            playerStats1.setTotalTackle(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "accuratePass":
	                            playerStats1.setTotalAccuratePass(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "totalFinalThirdPasses":
	                            playerStats1.setTotalFinalThirdPasses(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "possWonAtt3rd":
	                            playerStats1.setPossWonAtt3rd(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "touches":
	                            playerStats1.setTouches(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "wonCorners":
	                            playerStats1.setWonCorners(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "duelWon":
	                            playerStats1.setDuelWon(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "totalContest":
	                            playerStats1.setDribbles(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "interception":
	                            playerStats1.setInterception(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "interceptionWon":
	                            playerStats1.setInterception(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "ballRecovery":
	                            playerStats1.setBallRecovery(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "unsuccessfulTouch":
	                            playerStats1.setUnsuccessfulTouch(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "turnover":
	                            playerStats1.setTurnover(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "minsPlayed":
	                            playerStats1.setMinsPlayed(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "totalPass":
	                            playerStats1.setTotalPass(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "totalCross":
	                            playerStats1.setTotalCross(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "wonTackle":
	                            playerStats1.setWonTackle(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "wonContest":
	                            playerStats1.setWonContest(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "duelLost":
	                            playerStats1.setDuelLost(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "possLostAll":
	                            playerStats1.setPossLostAll(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "saves":
	                            playerStats1.setSaves(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "goals":
	                            playerStats1.setGoal(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "bigChanceCreated":
	                            playerStats1.setChanceCreated(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "totalAttAssist":
	                            playerStats1.setAssists(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "blockedScoringAtt":
	                            playerStats1.setBlockedScoringAtt(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "ontargetScoringAtt":
	                            playerStats1.setShotOnTarget(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "ShotOffTarget":
	                            playerStats1.setShotOffTarget(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "cornerTaken":
	                            playerStats1.setCornerTaken(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "goalsConceded":
	                            playerStats1.setGoalsConceded(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "totalOffside":
	                            playerStats1.setTotalOffside(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "totalThrows":
	                            playerStats1.setTotalThrows(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "totalScoringAtt":
	                            playerStats1.setTotalShots(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "aerialWon":
	                            playerStats1.setAerialWon(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "aerialLost":
	                            playerStats1.setAerialLost(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "longPassOwnToOpp":
	                            playerStats1.setLongPassOwnToOpp(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "longPassOwnToOppSuccess":
	                            playerStats1.setLongPassOwnToOppSuccess(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "possWonMid3rd":
	                            playerStats1.setPossWonMid3rd(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "possWonDef3rd":
	                            playerStats1.setPossWonDef3rd(Integer.parseInt(stat.getValue()));
	                            break;
	                        case "attemptsIbox":
	                            playerStats1.setAttemptsIbox(Integer.parseInt(stat.getValue()));
	                            break;
	                    }
	                }
	            }
	            team.getPlayer().add(playerStats1);
	        }
	    }
	}
	
	public static void ReverseFormation(Formation Formations,String which_pos) {
		switch(which_pos.toUpperCase()) {
		case "X":
			Formations.setFormOrds1X((100-Formations.getFormOrds1X()));
			Formations.setFormOrds2X((100-Formations.getFormOrds2X()));
			Formations.setFormOrds3X((100-Formations.getFormOrds3X()));
			Formations.setFormOrds4X((100-Formations.getFormOrds4X()));
			Formations.setFormOrds5X((100-Formations.getFormOrds5X()));
			Formations.setFormOrds6X((100-Formations.getFormOrds6X()));
			Formations.setFormOrds7X((100-Formations.getFormOrds7X()));
			Formations.setFormOrds8X((100-Formations.getFormOrds8X()));
			Formations.setFormOrds9X((100-Formations.getFormOrds9X()));
			Formations.setFormOrds10X((100-Formations.getFormOrds10X()));
			Formations.setFormOrds11X((100-Formations.getFormOrds11X()));
			
			break;
		case "Y":
			Formations.setFormOrds1Y((100-Formations.getFormOrds1Y()));
			Formations.setFormOrds2Y((100-Formations.getFormOrds2Y()));
			Formations.setFormOrds3Y((100-Formations.getFormOrds3Y()));
			Formations.setFormOrds4Y((100-Formations.getFormOrds4Y()));
			Formations.setFormOrds5Y((100-Formations.getFormOrds5Y()));
			Formations.setFormOrds6Y((100-Formations.getFormOrds6Y()));
			Formations.setFormOrds7Y((100-Formations.getFormOrds7Y()));
			Formations.setFormOrds8Y((100-Formations.getFormOrds8Y()));
			Formations.setFormOrds9Y((100-Formations.getFormOrds9Y()));
			Formations.setFormOrds10Y((100-Formations.getFormOrds10Y()));
			Formations.setFormOrds11Y((100-Formations.getFormOrds11Y()));
			
			break;
		default:
			 ReverseFormation(Formations,"X");
			 ReverseFormation(Formations,"Y");
			break;
		}
	}
	
	public static void TeamStatApi(LiveMatch liveMatch,ApiMatch match) {
		 match.getApi_LiveMatch().getAwayTeam().reset();
		 match.getApi_LiveMatch().getHomeTeam().reset(); 
		 
		 for (int i = 0; i < 2; i++) {
			    match.getApi_LiveMatch().getHomeTeam().setName(liveMatch.getMatchInfo().getContestant().get(i).getName().trim());
			    match.getApi_LiveMatch().getAwayTeam().setId(liveMatch.getMatchInfo().getContestant().get(i).getId().trim());
			}

			if (liveMatch != null && liveMatch.getLiveData() != null && liveMatch.getLiveData().getCard() != null) {
			    for (Card card : liveMatch.getLiveData().getCard()) {
			     
			    	if (card.getTeamOfficialId() == null) {
			            if ("YC".equalsIgnoreCase(card.getType()) || "Y2C".equalsIgnoreCase(card.getType())) {
			                if (liveMatch.getMatchInfo().getContestant().get(0).getId().equalsIgnoreCase(card.getContestantId())) {
			                    match.getApi_LiveMatch().getHomeTeam().setYellowCards(
			                        match.getApi_LiveMatch().getHomeTeam().getYellowCards() + 
			                        ("Y2C".equalsIgnoreCase(card.getType()) ? -1 : 1));
			                    if ("Y2C".equalsIgnoreCase(card.getType())) {
			                        match.getApi_LiveMatch().getHomeTeam().setRedCards(
			                            match.getApi_LiveMatch().getHomeTeam().getRedCards() + 1);
			                    }
			                } else if (liveMatch.getMatchInfo().getContestant().get(1).getId().equalsIgnoreCase(card.getContestantId())) {
			                    match.getApi_LiveMatch().getAwayTeam().setYellowCards(
			                        match.getApi_LiveMatch().getAwayTeam().getYellowCards() + 
			                        ("Y2C".equalsIgnoreCase(card.getType()) ? -1 : 1));
			                    if ("Y2C".equalsIgnoreCase(card.getType())) {
			                        match.getApi_LiveMatch().getAwayTeam().setRedCards(
			                            match.getApi_LiveMatch().getAwayTeam().getRedCards() + 1);
			                    }
			                }
			            } else if ("RC".equalsIgnoreCase(card.getType())) {
			                if (liveMatch.getMatchInfo().getContestant().get(0).getId().equalsIgnoreCase(card.getContestantId())) {
			                    match.getApi_LiveMatch().getHomeTeam().setRedCards(
			                        match.getApi_LiveMatch().getHomeTeam().getRedCards() + 1);
			                } else if (liveMatch.getMatchInfo().getContestant().get(1).getId().equalsIgnoreCase(card.getContestantId())) {
			                    match.getApi_LiveMatch().getAwayTeam().setRedCards(
			                        match.getApi_LiveMatch().getAwayTeam().getRedCards() + 1);
			                }
			            }
			        }
			    }
			}

			match.getApi_LiveMatch().getHomeTeam().setYellowCards(Math.max(0, match.getApi_LiveMatch().getHomeTeam().getYellowCards()));
			match.getApi_LiveMatch().getAwayTeam().setYellowCards(Math.max(0, match.getApi_LiveMatch().getAwayTeam().getYellowCards()));
      
		  for (int teamIndex = 0; teamIndex <= 1; teamIndex++) {
		        ApiTeamstats team = (teamIndex == 0) ? match.getApi_LiveMatch().getHomeTeam() : match.getApi_LiveMatch().getAwayTeam();
		       
		        if (liveMatch != null && liveMatch.getLiveData() != null &&  liveMatch.getLiveData().getLineUp() != null && liveMatch.getLiveData().getLineUp().get(teamIndex) != null &&
		        	    liveMatch.getLiveData().getLineUp().get(teamIndex).getStat() != null) {
		        	
		        	for (TeamStat stat : liveMatch.getLiveData().getLineUp().get(teamIndex).getStat()) {
			            String value = stat.getValue().trim() != null ? stat.getValue().trim() : "0";
			            String FH = stat.getFh() != null ? stat.getFh().trim() : "0";
			            String SH = stat.getSh() != null ? stat.getSh().trim() : "0";
			            switch (stat.getType()) {
			                case "cornerTaken":
			                    team.setCornerTaken(Integer.parseInt(value));
			                    team.setHtCornerTaken(Integer.parseInt(FH));
			                    team.setFtCornerTaken(Integer.parseInt(SH));
			                    break;
			                case "fkFoulWon":
			                	team.setHtFoulsWon(Integer.parseInt(FH));
			                    team.setFtFoulsWon(Integer.parseInt(SH));
			                    team.setFoulsWon(Integer.parseInt(value));
			                    break;
			                case "ontargetScoringAtt":
			                	team.setHtShotOnTarget(Integer.parseInt(FH));
			                    team.setFtShotOnTarget(Integer.parseInt(SH));
			                    team.setShotOnTarget(Integer.parseInt(value));
			                    break;
			                case "totalScoringAtt":
			                    team.setHtShots(Integer.parseInt(FH));
			                    team.setFtShots(Integer.parseInt(SH));
			                    team.setShots(Integer.parseInt(value));
			                    break;
			                case "saves":
			                	team.setHtSaves(Integer.parseInt(FH));
			                    team.setFtSaves(Integer.parseInt(SH));
			                    team.setSaves(Integer.parseInt(value));
			                    break;
			                case "totalCross":
			                	team.setHtCrosses(Integer.parseInt(FH));
			                    team.setFtCrosses(Integer.parseInt(SH));
			                    team.setCrosses(Integer.parseInt(value));
			                    break;
			                case "totalPass":
			                	team.setHtPasses(Integer.parseInt(FH));
			                	team.setFtPasses(Integer.parseInt(SH));
			                    team.setPasses(Integer.parseInt(value));
			                    break;
			                case "accuratePass":
			                	team.setHtAccuratePass(Integer.parseInt(FH));
			                    team.setFtAccuratePass(Integer.parseInt(SH));
			                    team.setAccuratePass(Integer.parseInt(value));
			                    break;
			                case "touches":
			                	team.setHtTouches(Integer.parseInt(FH));
			                    team.setFtTouches(Integer.parseInt(SH));
			                    team.setTouches(Integer.parseInt(value));
			                    break;
			                case "totalTackle":
			                	team.setHtTackles(Integer.parseInt(FH));
			                    team.setFtTackles(Integer.parseInt(SH));
			                    team.setTackles(Integer.parseInt(value));
			                    break;
			                case "totalContest":
			                	team.setHtDribbles(Integer.parseInt(FH));
			                    team.setFtDribbles(Integer.parseInt(SH));
			                    team.setDribbles(Integer.parseInt(value));
			                    break;
			                case "interception":
			                	team.setHtInterceptions(Integer.parseInt(FH));
			                    team.setFtInterceptions(Integer.parseInt(SH));
			                    team.setInterceptions(Integer.parseInt(value));
			                    break;
			                case "possessionPercentage":
			                	team.setHtPossession(Double.valueOf(FH));
			                    team.setFtPossession(Double.valueOf(SH));
			                    team.setPossession( Double.valueOf(value));
			                    break;
			                case "bigChanceCreated":
			                	team.setHtChancesCreated(Integer.parseInt(FH));
			                    team.setFtChancesCreated(Integer.parseInt(SH));
			                	team.setChancesCreated(Integer.parseInt(value));
			                	break;
			                case "totalOffside":
			                    team.setHtOffside(Integer.parseInt(FH));
			                    team.setFtOffside(Integer.parseInt(SH));
			                	team.setOffside(Integer.parseInt(value));
			                	break;
			                case "wonContest":
			                	team.setHtSuccessfulDribble(Integer.parseInt(FH));
			                    team.setFtSuccessfulDribble(Integer.parseInt(SH));
			                	team.setSuccessfulDribble(Integer.parseInt(value));
		                        break;
			                case "duelWon":
			                	team.setHtDuelWon(Integer.parseInt(FH));
			                    team.setFtDuelWon(Integer.parseInt(SH));
			                	team.setDuelWon(Integer.parseInt(value));
		                        break;
			                case "fkFoulLost":
			                	team.setHtFoulLost(Integer.parseInt(FH));
			                    team.setFtFoulLost(Integer.parseInt(SH));
			                	team.setFoulLost(Integer.parseInt(stat.getValue()));
			                	break;
			                case "totalClearance":
			                    team.setHtTotalClearance(Integer.parseInt(FH));
			                    team.setFtTotalClearance(Integer.parseInt(SH));
			                	team.setTotalClearance(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "effectiveClearance":
		                    	team.setHtEffectiveClearance(Integer.parseInt(FH));
		                        team.setFtEffectiveClearance(Integer.parseInt(SH));
		                    	team.setEffectiveClearance(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "interceptionWon":
		                    	team.setHtInterceptionWon(Integer.parseInt(FH));
		                    	team.setFtInterceptionWon(Integer.parseInt(SH));
		                    	team.setInterceptionWon(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "ballRecovery":
		                    	team.setHtBallRecovery(Integer.parseInt(FH));
		                        team.setFtBallRecovery(Integer.parseInt(SH));
		                    	team.setBallRecovery(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "unsuccessfulTouch":
		                    	team.setHtUnsuccessfulTouch(Integer.parseInt(FH));
		                        team.setFtUnsuccessfulTouch(Integer.parseInt(SH));
		                    	team.setUnsuccessfulTouch(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "turnover":
		                    	team.setHtTurnover(Integer.parseInt(FH));
		                        team.setFtTurnover(Integer.parseInt(SH));
		                    	team.setTurnover(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "wonTackle":
		                    	team.setHtWonTackle(Integer.parseInt(FH));
		                    	team.setFtWonTackle(Integer.parseInt(SH));
								team.setWonTackle(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "longPassOwnToOpp":
		                    	team.setHtlongPassOwnToOpp(Integer.parseInt(FH));
		                    	team.setFtlongPassOwnToOpp(Integer.parseInt(SH));
								team.setLongPassOwnToOpp(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "longPassOwnToOppSuccess":
		                    	team.setHtlongPassOwnToOppSuccess(Integer.parseInt(FH));
		                    	team.setFtlongPassOwnToOppSuccess(Integer.parseInt(SH));
								team.setLongPassOwnToOppSuccess(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "totalFinalThirdPasses":
		                    	team.setHtTotalFinalThirdPasses(Integer.parseInt(FH));
		                        team.setFtTotalFinalThirdPasses(Integer.parseInt(SH));
		                        team.setTotalFinalThirdPasses(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "successfulFinalThirdPasses":
		                        team.setHtSuccessfulFinalThirdPasses(Integer.parseInt(FH));
		                        team.setFtSuccessfulFinalThirdPasses(Integer.parseInt(SH));
		                        team.setSuccessfulFinalThirdPasses(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "possWonAtt3rd":
		                    	team.setHtPossWonAtt3rd(Integer.parseInt(FH));
		                        team.setFtPossWonAtt3rd(Integer.parseInt(SH));
		                        team.setPossWonAtt3rd(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "possWonDef3rd":
		                    	team.setHtPossWonDef3rd(Integer.parseInt(FH));
		                        team.setFtPossWonDef3rd(Integer.parseInt(SH));
		                        team.setPossWonDef3rd(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "touchesInOppBox":
		                    	team.setHtTouchesInOppBox(Integer.parseInt(FH));
		                        team.setFtTouchesInOppBox(Integer.parseInt(SH));
		                        team.setTouchesInOppBox(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "wonCorners":
		                    	team.setHtWonCorners(Integer.parseInt(FH));
		                        team.setFtWonCorners(Integer.parseInt(SH));
		                        team.setWonCorners(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "lostCorners":
		                    	team.setHtLostCorners(Integer.parseInt(FH));
		                        team.setFtLostCorners(Integer.parseInt(SH));
		                        team.setLostCorners(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "duelLost":
		                    	team.setHtDuelLost(Integer.parseInt(FH));
		                        team.setFtDuelLost(Integer.parseInt(SH));
		                        team.setDuelLost(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "blockedScoringAtt":
		                    	team.setHtBlockedScoringAtt(Integer.parseInt(FH));
		                        team.setFtBlockedScoringAtt(Integer.parseInt(SH));
		                    	team.setBlockedScoringAtt(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "ShotOffTarget":
		                    	team.setHtShotOffTarget(Integer.parseInt(FH));
		                        team.setFtShotOffTarget(Integer.parseInt(SH));
		                    	team.setShotOffTarget(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "goalsConceded":
		                    	team.setHtGoalsConceded(Integer.parseInt(FH));
		                        team.setFtGoalsConceded(Integer.parseInt(SH));
		                    	team.setGoalsConceded(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "totalThrows":
		                    	team.setHtTotalThrows(Integer.parseInt(FH));
		                        team.setFtTotalThrows(Integer.parseInt(SH));
		                        team.setTotalThrows(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "attemptsIbox":
		                    	team.setHtshotsInsideBox(Integer.parseInt(FH));
		                        team.setFtshotsInsideBox(Integer.parseInt(SH));
		                        team.setShotsInsideBox(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "aerialWon":
		                    	team.setHtAerialWon(Integer.parseInt(FH));
		                        team.setFtAerialWon(Integer.parseInt(SH));
		                        team.setAerialWon(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "aerialLost":
		                    	team.setHtAerialLost(Integer.parseInt(FH));
		                        team.setFtAerialLost(Integer.parseInt(SH));
		                        team.setAerialLost(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "finalThirdEntries":
		                        team.setHtFinalThirdEntries(Integer.parseInt(FH));
		                        team.setFtFinalThirdEntries(Integer.parseInt(SH));
		                    	team.setFinalThirdEntries(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "possWonMid3rd":
		                    	team.setHtPossWonMid3rd(Integer.parseInt(FH));
		                        team.setFtPossWonMid3rd(Integer.parseInt(SH));
		                        team.setPossWonMid3rd(Integer.parseInt(stat.getValue()));
		                    	break;
		                    
			            }
			            team.setPassingAccuracy(AccuracyPercentage(team.getPasses(), team.getAccuratePass()));
			            team.setSuccessfulDribblePercent(team.getDribbles() > 0 ? (int) Math.round((team.getSuccessfulDribble() * 100.0) / team.getDribbles()) : 0);
			            team.setDuelwonPercent((team.getDuelWon() + team.getDuelLost() > 0) ? (int) Math.round((team.getDuelWon() * 100.0) / (team.getDuelWon() + team.getDuelLost())) : 0);
			            team.setArielwonPercent((team.getAerialWon() + team.getAerialLost() > 0) ? (int) Math.round((team.getAerialWon() * 100.0) / (team.getAerialWon() + team.getAerialLost())) : 0);
			            team.setFinalThirdPassingAccuracy(team.getTotalFinalThirdPasses() > 0 ? (int) Math.round((team.getSuccessfulFinalThirdPasses() * 100.0) / team.getTotalFinalThirdPasses()) : 0);
			            
			            team.setFtPassingAccuracy(AccuracyPercentage(team.getFtPasses(), team.getFtAccuratePass()));
			            team.setFtSuccessfulDribblePercent(team.getFtDribbles() > 0 ? (int) Math.round((team.getFtSuccessfulDribble() * 100.0) / team.getFtDribbles()) : 0);
			            team.setFtDuelwonPercent((team.getFtDuelWon() + team.getFtDuelLost() > 0) ? (int) Math.round((team.getFtDuelWon() * 100.0) / (team.getFtDuelWon() + team.getFtDuelLost())) : 0);
			            team.setFtArielwonPercent((team.getFtAerialWon() + team.getFtAerialLost() > 0) ? (int) Math.round((team.getFtAerialWon() * 100.0) / (team.getFtAerialWon() + team.getFtAerialLost())) : 0);
			            team.setFtFinalThirdPassingAccuracy(team.getFtTotalFinalThirdPasses() > 0 ? (int) Math.round((team.getFtSuccessfulFinalThirdPasses() * 100.0) / team.getFtTotalFinalThirdPasses()) : 0);
			            
			            team.setHtPassingAccuracy(AccuracyPercentage(team.getHtPasses(), team.getHtAccuratePass()));
			            team.setHtSuccessfulDribblePercent(team.getHtDribbles() > 0 ? (int) Math.round((team.getHtSuccessfulDribble() * 100.0) / team.getHtDribbles()) : 0);
			            team.setHtDuelwonPercent((team.getHtDuelWon() + team.getHtDuelLost() > 0) ? (int) Math.round((team.getHtDuelWon() * 100.0) / (team.getHtDuelWon() + team.getHtDuelLost())) : 0);
			            team.setHtArielwonPercent((team.getHtAerialWon() + team.getHtAerialLost() > 0) ? (int) Math.round((team.getHtAerialWon() * 100.0) / (team.getHtAerialWon() + team.getHtAerialLost())) : 0);
			            team.setHtFinalThirdPassingAccuracy(team.getHtTotalFinalThirdPasses() > 0 ? (int) Math.round((team.getHtSuccessfulFinalThirdPasses() * 100.0) / team.getHtTotalFinalThirdPasses()) : 0);

			        }
		        }
		  }
	 }
	
	public static List<ApiPlayerStats> PlayerStats(List<ApiPlayerStats> apiPlayerStats, String which_data) {
		 
		switch (which_data.toUpperCase()) {
		case "TOUCHES":
			Collections.sort(apiPlayerStats, (p1, p2) -> Integer.compare(p2.getTouches(), p1.getTouches()));
			break;
		case "DUEL WON":
			Collections.sort(apiPlayerStats, (p1, p2) -> Integer.compare(p2.getDuelWon(), p1.getDuelWon()));
			break;
		case "SUCCESSFUL DRIBBLES":
			Collections.sort(apiPlayerStats, (p1, p2) -> Integer.compare(p2.getWonContest(), p1.getWonContest()));
			break;
		case "RECOVERIES":
			Collections.sort(apiPlayerStats, (p1, p2) -> Integer.compare(p2.getBallRecovery(), p1.getBallRecovery()));
			break;
		case "AERIAL DUELS WON":
			Collections.sort(apiPlayerStats, (p1, p2) -> Integer.compare(p2.getAerialWon(), p1.getAerialWon()));
			break;
		case "CHANCES CREATED":
			Collections.sort(apiPlayerStats, (p1, p2) -> Integer.compare(p2.getChanceCreated(), p1.getChanceCreated()));
	        break;
	    case "PASSES":
			Collections.sort(apiPlayerStats, (p1, p2) -> Integer.compare(p2.getTotalPass(), p1.getTotalPass()));
	        break;
	    case "FINAL 3RD PASSES":
			Collections.sort(apiPlayerStats, (p1, p2) -> Integer.compare(p2.getTotalFinalThirdPasses(), p1.getTotalFinalThirdPasses()));
	        break;
	    case "CROSSES":
			Collections.sort(apiPlayerStats, (p1, p2) -> Integer.compare(p2.getTotalCross(), p1.getTotalCross()));
	        break;
	    case "DUELS":
	    	Collections.sort(apiPlayerStats, (p1, p2) -> {
	    	    int totalDuels1 = p1.getDuelWon() + p1.getDuelLost();
	    	    int totalDuels2 = p2.getDuelWon() + p2.getDuelLost();
	    	    return Integer.compare(totalDuels2, totalDuels1); 
	    	});
	        break;
	    case "DUELS WON":
			Collections.sort(apiPlayerStats, (p1, p2) -> Integer.compare(p2.getDuelWon(), p1.getDuelWon()));
	        break;
	    case "POSSESSION WON":
	    	Collections.sort(apiPlayerStats, (p1, p2) -> {
	    	    int totalDuels1 = p1.getPossWonAtt3rd() + p1.getPossWonDef3rd() + p1.getPossWonMid3rd();
	    	    int totalDuels2 = p2.getPossWonAtt3rd() + p2.getPossWonDef3rd() + p2.getPossWonMid3rd();
	    	    return Integer.compare(totalDuels2, totalDuels1); 
	    	});
	        break;
	    case "POSSESSION LOST":
			Collections.sort(apiPlayerStats, (p1, p2) -> Integer.compare(p2.getPossLostAll(), p1.getPossLostAll()));
	        break;
	    case "TACKLES":
			Collections.sort(apiPlayerStats, (p1, p2) -> Integer.compare(p2.getTotalTackle(), p1.getTotalTackle()));
	        break;
	    case "TACKLES WON":
			Collections.sort(apiPlayerStats, (p1, p2) -> Integer.compare(p2.getWonTackle(), p1.getWonTackle()));
	        break;
	    case "SHOTS":
			Collections.sort(apiPlayerStats, (p1, p2) -> Integer.compare(p2.getTotalShots(), p1.getTotalShots()));
	        break;
	    case "SHOTS ON TARGET":
			Collections.sort(apiPlayerStats, (p1, p2) -> Integer.compare(p2.getShotOnTarget(), p1.getShotOnTarget()));
	        break;
		}
	    return apiPlayerStats.size() > 5 ? apiPlayerStats.subList(0, 5) : apiPlayerStats;
	}
	
	public static Tournament extracttournamentGoals(String typeOfExtraction, List<Fixture> fixtures, Match currentMatch, 
			Tournament past_tournament_stat) throws CloneNotSupportedException 
	{
		Tournament tournament_stats = new Tournament();	
		switch(typeOfExtraction) {
		case "COMBINED_PAST_CURRENT_MATCH_DATA":
			 return extracttournamentGoals("CURRENT_MATCH_DATA", fixtures, currentMatch, 
					 extracttournamentGoals("PAST_MATCHES_DATA", fixtures, currentMatch, null));
		case "PAST_MATCHES_DATA":
			for(Fixture fix : fixtures) {
				if(!fix.getMatchfilename().equalsIgnoreCase(currentMatch.getMatchFileName().replace(".json", "")) && fix.getMargin() != null) {
					tournament_stats.setGoals(tournament_stats.getGoals() + Integer.valueOf(fix.getMargin().split("-")[0]));
					tournament_stats.setGoals(tournament_stats.getGoals() + Integer.valueOf(fix.getMargin().split("-")[1]));
				}
			}
			return tournament_stats;
		case "CURRENT_MATCH_DATA":
			Tournament past_tournament_stat_clone = new Tournament();
			if(past_tournament_stat  != null) {
				past_tournament_stat_clone = past_tournament_stat.clone(); // create clone of past_tournament_stat
			}
			
			past_tournament_stat_clone.setGoals(past_tournament_stat_clone.getGoals() + currentMatch.getHomeTeamScore());
			past_tournament_stat_clone.setGoals(past_tournament_stat_clone.getGoals() + currentMatch.getAwayTeamScore());
			
			return past_tournament_stat_clone;
		}
		
		return null;
	}
	
	public static int[] roundToSum(double[] values, int targetSum) {
        int n = values.length;
        int[] result = new int[n];
        double[] differences = new double[n];
        int sum = 0;

        // Step 1: Round each value and calculate the sum and differences
        for (int i = 0; i < n; i++) {
            result[i] = (int) Math.floor(values[i]);  // Initial rounding down
            sum += result[i];
            differences[i] = values[i] - result[i];   // Store the difference between actual and rounded
        }

        // Step 2: Adjust the values upwards to reach the target sum
        while (sum < targetSum) {
            int index = 0;
            double maxDiff = -1;

            // Find the value with the largest difference that hasn't been adjusted up yet
            for (int i = 0; i < n; i++) {
                if (differences[i] > maxDiff) {
                    maxDiff = differences[i];
                    index = i;
                }
            }

            // Adjust the value and update sum
            result[index]++;
            sum++;
            differences[index] = 0;  // Set to zero so it won't be adjusted again
        }

        return result;
    }
	
	public static String FTPImageDownload(int port,int match_number,String user,String pass,String player_map_type,Configurations config) {
		
		FTPClient ftpClient = new FTPClient();
		try {
			 
            ftpClient.connect(FootballUtil.FTP_SERVER_LINK, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            String remoteFile1 = player_map_type + ".jpg";
            File downloadFile1 = new File(FootballUtil.FOOTBALL_DIRECTORY + FootballUtil.STATISTIC_DIRECTORY + 
            		FootballUtil.MATCH_DATA_DIRECTORY + remoteFile1);
            
            ftpClient.changeWorkingDirectory("/remote/path");
            FTPFile[] remoteFiles = ftpClient.listFiles(player_map_type + ".jpg");
            if (remoteFiles.length > 0)
            {
            	OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            	boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
            	
            	outputStream1.close();
            	 
                if (success) {
                    System.out.println("File has been downloaded successfully.");
                    return "SUCCESS";
                }
            }
            else
            {
//            	outputStream1.close();
                System.out.println("File does not exists");
                return "UNSUCCESS";
            }
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		return "";
	}

	public static String hashString(String input) {
	    try {
	        // Create a MessageDigest instance for SHA-512
	        MessageDigest digest = MessageDigest.getInstance("SHA-512");
	
	        // Convert the input string to a byte array
	        byte[] hash = digest.digest(input.getBytes());
	
	        // Convert the byte array to a hexadecimal string
	        StringBuilder hexString = new StringBuilder();
	        for (byte b : hash) {
	            // Convert each byte to a 2-digit hexadecimal representation
	            hexString.append(String.format("%02x", b));
	        }
	
	        return hexString.toString(); // Return the SHA-512 hash as a hexadecimal string
	    } catch (NoSuchAlgorithmException e) {
	        throw new RuntimeException("Error creating SHA-512 hash", e);
	    }
	}

	public static String getAccessToken() throws IOException {
	    
		String token_access = "";
		String tokenEndpointUrl = "https://oauth.performgroup.com/oauth/token/26kfa29kdpyu170bzsv5cbuw0?_fmt=json&_rt=b";
	    String OutletKey = "26kfa29kdpyu170bzsv5cbuw0";//"{{OutletApiKey}}";
	    String SecretKey = "1nmlzjsbu0dxz1w4c5yg4m143q";//"{{SecretKey}}";
	    
	    String currentMillis = Long.toString(System.currentTimeMillis());
	    String sigString = OutletKey + currentMillis + SecretKey;
	    
	    String hashedOutput = null;
		try {
			hashedOutput = hashString(sigString);
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
	    HttpResponse<String> userResp;
		try {
			userResp = Unirest.post(tokenEndpointUrl)
					.header("Content-Type", "application/x-www-form-urlencoded")
					.header("Authorization", "Basic " + hashedOutput)
					.header("Timestamp", currentMillis)
					.field("grant_type", "client_credentials")
					.field("scope", "b2b-feeds-auth")
					.asString();
			
			String json_data = userResp.getBody().toString();
			
			JSONObject jsonObject = new JSONObject(json_data);
			System.out.println(jsonObject.toString());
	        // Get the "access_token" value
	        String accessToken = jsonObject.getString("access_token");
	
	        token_access = accessToken;
		} catch (UnirestException e) {
			System.out.println("Error...");
		}
		
	    return token_access;
	}

	public static LiveMatch getFootballLiveDatafromAPI(String token) throws SAXException, IOException, ParserConfigurationException, FactoryConfigurationError
	{
		HttpResponse<String> userResp;
		
		String url = FootballUtil.FOOTBALL_API_PATH + "matchstats" + FootballUtil.FOOTBALL_TOKEN + "/?" + FootballUtil.FOOTBALL_API_MODE + "&" + 
				FootballUtil.FOOTBALL_API_JSON + "&detailed=yes&fx=" + FootballUtil.FOOTBALL_FIXTURE_ID;
		try {
			userResp = Unirest.get(url)
					.header("Content-Type", "application/json;charset=utf-8")
					.header("Authorization", "Bearer " + token)
					.asString();
			

			LiveMatch = new ObjectMapper().readValue(userResp.getBody().toString(), LiveMatch.class);
			
		} catch (UnirestException e) {
			System.out.println("Error...");
		}
		System.out.println(LiveMatch.toString());
		
		return LiveMatch;
	}
	
	public static LiveMatch getExpectedGoals(String token)throws IOException  {
		String url=FootballUtil.FOOTBALL_API_PATH + "matchexpectedgoals" + FootballUtil.FOOTBALL_TOKEN + "?fx=" + FootballUtil.FOOTBALL_FIXTURE_ID + "&" + 
				   FootballUtil.FOOTBALL_API_JSON +"&" + FootballUtil.FOOTBALL_API_MODE;
		HttpResponse<String> userResp;
		try {
			userResp = Unirest.get(url)
					.header("Content-Type", "application/json;charset=utf-8")
					.header("Authorization", "Bearer " + token)
					.asString();
			
			LiveMatch = new ObjectMapper().readValue(userResp.getBody().toString(), LiveMatch.class);
		} catch (UnirestException e) {
			System.out.println("Error...");
		}
		
	       return LiveMatch;
		}
	
	public static LiveMatch getFootballMatchEventfromAPI(String token) throws SAXException, IOException, ParserConfigurationException, FactoryConfigurationError
	{
		String url=FootballUtil.FOOTBALL_API_PATH + "matchevent" + FootballUtil.FOOTBALL_TOKEN +"/"+ FootballUtil.FOOTBALL_FIXTURE_ID + "?" + 
				FootballUtil.FOOTBALL_API_JSON + "&" + FootballUtil.FOOTBALL_API_MODE;
		HttpResponse<String> userResp;
		try {
			userResp = Unirest.get(url)
					.header("Content-Type", "application/json;charset=utf-8")
					.header("Authorization", "Bearer " + token)
					.asString();
			
			LiveMatch = new ObjectMapper().readValue(userResp.getBody().toString(), LiveMatch.class);
	        } catch (UnirestException e) {
			System.out.println("Error...");
		}
		
		return LiveMatch;
	}
	
	public static List<SeasonalStats> getSeasonalStatsfromAPI(String token) throws IOException, SAXException, ParserConfigurationException, FactoryConfigurationError {
		LiveMatch = getFootballLiveDatafromAPI(token);
		List<SeasonalStats>  SeasonalStats = new ArrayList<SeasonalStats>();
	    
	    for (int i = 0; i < 2; i++) {
	        String teamId = LiveMatch.getMatchInfo().getContestant().get(i).getId();

	       String url = FootballUtil.FOOTBALL_API_PATH + "seasonstats" + FootballUtil.FOOTBALL_TOKEN 
	            + "?&tmcl=" + FootballUtil.FOOTBALL_TOURNAMENT_CALENDER_ID + "&ctst=" + teamId 
	            + "&" + FootballUtil.FOOTBALL_API_MODE + "&" + FootballUtil.FOOTBALL_API_JSON;

	        try {
	            HttpResponse<String> userResp = Unirest.get(url)
	                .header("Content-Type", "application/json;charset=utf-8")
	                .header("Authorization", "Bearer " + token)
	                .asString();

	            SeasonalStats.add(new ObjectMapper().readValue(userResp.getBody().toString(), SeasonalStats.class));
	        } catch (UnirestException e) {
	            System.out.println("Error...");
	        }
	    }

	    return SeasonalStats;
	}
	
	public static  rankings getTeamRankingfromAPI(String token) throws IOException {
		String url = FootballUtil.FOOTBALL_API_PATH + "rankings" + FootballUtil.FOOTBALL_TOKEN + "?tmcl=" + FootballUtil.FOOTBALL_TOURNAMENT_CALENDER_ID + "&" + 
				   FootballUtil.FOOTBALL_API_MODE +"&" + FootballUtil.FOOTBALL_API_JSON;
		HttpResponse<String> userResp;
		try {
			userResp = Unirest.get(url)
					.header("Content-Type", "application/json;charset=utf-8")
					.header("Authorization", "Bearer " + token)
					.asString();
			System.out.println(userResp.getBody().toString());
			rankings = new ObjectMapper().readValue(userResp.getBody().toString(), rankings.class);
	        } catch (UnirestException e) {
			System.out.println("Error...");
		}
		
       return rankings;
	}
	
	public static PassMatrix getMatchInsightsfromAPI( String token) throws IOException {
		String url= FootballUtil.FOOTBALL_API_PATH + "matchinsights" + FootballUtil.FOOTBALL_TOKEN + "/" + FootballUtil.FOOTBALL_FIXTURE_ID + "?" + 
				 "&" + FootballUtil.FOOTBALL_API_MODE + "&" + FootballUtil.FOOTBALL_API_JSON;
		HttpResponse<String> userResp;
		try {
			userResp = Unirest.get(url)
					.header("Content-Type", "application/json;charset=utf-8")
					.header("Authorization", "Bearer " + token)
					.asString();
			
			PassMatrix = new ObjectMapper().readValue(userResp.getBody().toString(), PassMatrix.class); 
		} catch (UnirestException e) {
			System.out.println("Error...");
		}
		
	       return PassMatrix;
	}
	
	public static PassMatrix getMatchInsights2fromAPI(String token) throws IOException {
		String url =  FootballUtil.FOOTBALL_API_PATH + "matchplayerratings" + FootballUtil.FOOTBALL_TOKEN + "?fx=" + FootballUtil.FOOTBALL_FIXTURE_ID + "&" + 
				   FootballUtil.FOOTBALL_API_JSON +"&" + FootballUtil.FOOTBALL_API_MODE;
		HttpResponse<String> userResp;
		try {
			userResp = Unirest.get(url)
					.header("Content-Type", "application/json;charset=utf-8")
					.header("Authorization", "Bearer " + token)
					.asString();
			
			PassMatrix = new ObjectMapper().readValue(userResp.getBody().toString(), PassMatrix.class);
		       
		} catch (UnirestException e) {
			System.out.println("Error...");
		}
		
	       return PassMatrix;
		}

	public static MatchPreview getMatchPreview( String token)throws IOException  {
		String url = FootballUtil.FOOTBALL_API_PATH + "matchpreview" + FootballUtil.FOOTBALL_TOKEN + "/?" + 
				FootballUtil.FOOTBALL_API_MODE +"&" + FootballUtil.FOOTBALL_API_JSON + "&fx=" + FootballUtil.FOOTBALL_FIXTURE_ID;
		HttpResponse<String> userResp;
		try {
			userResp = Unirest.get(url)
					.header("Content-Type", "application/json;charset=utf-8")
					.header("Authorization", "Bearer " + token)
					.asString();
			
			matchPreview = new ObjectMapper().readValue(userResp.getBody().toString(), MatchPreview.class);
		} catch (UnirestException e) {
			System.out.println("Error...");
		}
		
	       return matchPreview;
	}
	
	public static PassMatrix getMatchPlayerRatingsfromAPI(String token)throws IOException  {
		String url = FootballUtil.FOOTBALL_API_PATH + "matchfactsall" + FootballUtil.FOOTBALL_TOKEN + "?fx=" + FootballUtil.FOOTBALL_FIXTURE_ID + 
				 "&" + FootballUtil.FOOTBALL_API_MODE + "&" + FootballUtil.FOOTBALL_API_JSON + "&_lcl=en-gb";   
		HttpResponse<String> userResp;
		try {
			userResp = Unirest.get(url)
					.header("Content-Type", "application/json;charset=utf-8")
					.header("Authorization", "Bearer " + token)
					.asString();
			
			PassMatrix = new ObjectMapper().readValue(userResp.getBody().toString(), PassMatrix.class);		       
		} catch (UnirestException e) {
			System.out.println("Error...");
		}
		
       return PassMatrix;
	}
	
	public static LiveMatch getFootballWinProbabilityfromAPI(String token) throws SAXException, IOException, ParserConfigurationException, FactoryConfigurationError
	{
		LiveMatch = getFootballLiveDatafromAPI(token);	    
	    String  url = FootballUtil.FOOTBALL_API_PATH  + "matchlivewinprobability" + FootballUtil.FOOTBALL_TOKEN + "/"+LiveMatch.getMatchInfo().getId()+"?" + 
		FootballUtil.FOOTBALL_API_MODE + "&" + FootballUtil.FOOTBALL_API_JSON;
		HttpResponse<String> userResp;
		try {
			userResp = Unirest.get(url)
					.header("Content-Type", "application/json;charset=utf-8")
					.header("Authorization", "Bearer " + token)
					.asString();
			
			LiveMatch = new ObjectMapper().readValue(userResp.getBody().toString(), LiveMatch.class);
		} catch (UnirestException e) {
			System.out.println("Error...");
		}
		
		return LiveMatch;
	}
	
 	public static void getAttackingZoneDataFromAPI(ApiMatch match) throws StreamReadException, DatabindException, IOException, SAXException, ParserConfigurationException, FactoryConfigurationError {
		
		if (new File("C:\\Sports\\Football\\Statistic\\Match_Data\\MatchEvent.json").exists()) {

		    LiveMatch liveMatch = new ObjectMapper().readValue(new File("C:\\Sports\\Football\\Statistic\\Match_Data\\MatchEvent.json"), LiveMatch.class);
		    match.getApi_LiveMatch().getHomeTeam().setName(liveMatch.getMatchInfo().getContestant().get(0).getName().trim());
	        match.getApi_LiveMatch().getHomeTeam().setCode(liveMatch.getMatchInfo().getContestant().get(0).getCode().trim());
	        match.getApi_LiveMatch().getHomeTeam().setId(liveMatch.getMatchInfo().getContestant().get(0).getId().trim());

	        match.getApi_LiveMatch().getAwayTeam().setName(liveMatch.getMatchInfo().getContestant().get(1).getName().trim());
	        match.getApi_LiveMatch().getAwayTeam().setCode(liveMatch.getMatchInfo().getContestant().get(1).getCode().trim());
	        match.getApi_LiveMatch().getAwayTeam().setId(liveMatch.getMatchInfo().getContestant().get(1).getId().trim());

	        match.getApi_LiveMatch().getHomeTeam().setCenter(0);
	        match.getApi_LiveMatch().getAwayTeam().setCenter(0);
	        match.getApi_LiveMatch().getHomeTeam().setLeft(0);
	        match.getApi_LiveMatch().getAwayTeam().setLeft(0);
	        match.getApi_LiveMatch().getHomeTeam().setRight(0);
	        match.getApi_LiveMatch().getAwayTeam().setRight(0);
	        
		    for (Events event : liveMatch.getLiveData().getEvent()) {
		        ApiTeamstats team = event.getContestantId().equalsIgnoreCase(match.getApi_LiveMatch().getHomeTeam().getId()) ? match.getApi_LiveMatch().getHomeTeam() : 
		                   event.getContestantId().equalsIgnoreCase(match.getApi_LiveMatch().getAwayTeam().getId()) ? match.getApi_LiveMatch().getAwayTeam() : null;
		         if (team == null) continue;
		         
		        if(((event.getTypeId() == 44 && event.getOutcome() == 0) || (event.getTypeId() == 59 && event.getOutcome() == 0)) && event.getX() >= 50) {
		        	if(Double.valueOf(event.getX()) >= 50) {
		        		if(Double.valueOf(event.getY()) >= 66.7) {
		        			team.setLeft(team.getLeft() + 1);
		        		}else if(Double.valueOf(event.getY()) > 33.3 && Double.valueOf(event.getY()) < 66.7) {
		        			team.setCenter(team.getCenter() + 1);
		        		}else if(Double.valueOf(event.getY()) <= 33.3) {
		        			team.setRight(team.getRight() + 1);
		        		}
		        	}
		        	
		        }else if(((event.getTypeId() >= 1 && event.getTypeId() <= 4) || (event.getTypeId() >= 6 && event.getTypeId() <= 8)
	        			|| (event.getTypeId() >= 10 && event.getTypeId() <= 16) || (event.getTypeId() == 41) || (event.getTypeId() == 42)
	        			|| (event.getTypeId() == 45) || (event.getTypeId() >= 49 && event.getTypeId() <= 56) || (event.getTypeId() == 60)
	        			|| (event.getTypeId() == 61) || (event.getTypeId() == 69) || (event.getTypeId() == 72) || (event.getTypeId() == 74)) 
		        		&& event.getX() >= 50) {
		        	
		        	if(Double.valueOf(event.getX()) >= 50) {
		        		if(Double.valueOf(event.getY()) >= 66.7) {
		        			team.setLeft(team.getLeft() + 1);
		        		}else if(Double.valueOf(event.getY()) > 33.3 && Double.valueOf(event.getY()) < 66.7) {
		        			team.setCenter(team.getCenter() + 1);
		        		}else if(Double.valueOf(event.getY()) <= 33.3) {
		        			team.setRight(team.getRight() + 1);
		        		}
		        	}
		        }
		    }
	    }
	}
	
	public static void DoadWriteCommandToSelectedViz(int SelectedViz, String SendTextIn, List<PrintWriter> print_writers) 
	{
		if(SelectedViz > 0 && SelectedViz <= print_writers.size()) {
			print_writers.get(SelectedViz-1).println(SendTextIn);
		}
	}	
	
	public static void DoadWriteCommandToAllViz(String SendTextIn, List<PrintWriter> print_writers) 
	{
		if(print_writers.size() > 0) {
			print_writers.get(0).println(SendTextIn);
		}
//		for(int i = 0; i < print_writers.size(); i++) {
//			print_writers.get(i).println(SendTextIn);
//			
//		}
	}
	
	@SuppressWarnings("resource")
	public static List<PrintWriter> processPrintWriter(Configurations config) throws UnknownHostException, IOException
	{
		List<PrintWriter> print_writer = new ArrayList<PrintWriter>();
		
		if(config.getIpAddress() != null && !config.getIpAddress().isEmpty() && config.getPortNumber() != 0) {
			print_writer.add(new PrintWriter(new Socket(config.getIpAddress(), 
					config.getPortNumber()).getOutputStream(), true));
		}
		
		if(config.getSecondaryipAddress() != null && !config.getSecondaryipAddress().isEmpty() && config.getSecondaryportNumber() != 0) {
			print_writer.add(new PrintWriter(new Socket(config.getSecondaryipAddress(), 
					config.getSecondaryportNumber()).getOutputStream(), true));
		}
		
		try {
			if(config.getVuipAddress() != null && !config.getVuipAddress().isEmpty() && config.getVuportNumber() != 0) {
				print_writer.add(new PrintWriter(new Socket(config.getVuipAddress(), 
					config.getVuportNumber()).getOutputStream(), true));
			}
		} catch (ConnectException e) {
			System.out.println("Unable to create print writer for QT");
		}
	
		return print_writer;
	}
	
	public static class PlayerStatsComparator implements Comparator<PlayerStats> {
	    @Override
	    public int compare(PlayerStats bs1, PlayerStats bs2) {
	       return Float.compare(Float.valueOf(bs2.getValue()), Float.valueOf(bs1.getValue()));
	    }
	}
	
	public static List<Fixture> processAllFixtures(FootballService footballService) {
		List<Fixture> fixtures = footballService.getFixtures();
		for(Team tm : footballService.getTeams()) {
			for(Fixture fix : fixtures) {
				if(fix.getHometeamid() == tm.getTeamId()) {
					fix.setHome_Team(tm);
				}
				if(fix.getAwayteamid() == tm.getTeamId()) {
					fix.setAway_Team(tm);
				}
			}
		}
		return fixtures;
	}
	
	public static List<PlayerStat> processAllPlayerStats(FootballService footballService) {
		
		List<PlayerStat> playerstats = footballService.getPlayerStats();
	
		for(Player plyr : footballService.getAllPlayer()) {
			for(PlayerStat ps : playerstats) {
				if(ps.getPlayerId() == plyr.getPlayerId()) {
					ps.setPlayer(plyr);
					ps.setTeam(footballService.getTeams().get(plyr.getTeamId()-1));
				}
			}
		}
		return playerstats;
	}
	
	public static List<PlayerComparison> processAllPlayerStatsComparion(FootballService footballService) {
		
		List<PlayerComparison> playerstats = footballService.getPlayerComparisons();
	
		for(Player plyr : footballService.getAllPlayer()) {
			for(PlayerComparison ps : playerstats) {
				if(ps.getPlayerId() == plyr.getPlayerId()) {
					ps.setPlayer(plyr);
					ps.setTeam(footballService.getTeams().get(plyr.getTeamId()-1));
				}if(ps.getPlayerId1() == plyr.getPlayerId()) {
					ps.setPlayer1(plyr);
					ps.setTeam1(footballService.getTeams().get(plyr.getTeamId()-1));
				}
			}
		}
		return playerstats;
	}
	
	public static List<LeaderBoard> processAllLeaderBoards(FootballService footballService) {
		List<LeaderBoard> leaderBoards = footballService.getLeaderBoard();
		for(LeaderBoard leader : leaderBoards) {
			leader.setPlayer1(footballService.getPlayer(FootballUtil.PLAYER, String.valueOf(leader.getPlayer1Id())));
			leader.setPlayer2(footballService.getPlayer(FootballUtil.PLAYER, String.valueOf(leader.getPlayer2Id())));
			leader.setPlayer3(footballService.getPlayer(FootballUtil.PLAYER, String.valueOf(leader.getPlayer3Id())));
			leader.setPlayer4(footballService.getPlayer(FootballUtil.PLAYER, String.valueOf(leader.getPlayer4Id())));
			leader.setPlayer5(footballService.getPlayer(FootballUtil.PLAYER, String.valueOf(leader.getPlayer5Id())));
		}
		return leaderBoards;
	}
	
	public static List<HeadToHead> processAllHeadToHead(FootballService footballService){		
		List<HeadToHead> h2h = footballService.getHeadToHeadStats();
		for(HeadToHead h : h2h) {
			h.setTeam(footballService.getTeam(FootballUtil.TEAM, String.valueOf(h.getTeamId())));
		}
		return h2h;
	}
	
	public static String twoDigitString(long number) {
	    if (number == 0) {
	        return "00";
	    }
	    if (number / 10 == 0) {
	        return "0" + number;
	    }
	    return String.valueOf(number);
	}
	
	public static String replace(float number) {
	    return String.valueOf(number).replace(".0", "");
	}
	
	public static String getPlayerSquadType(int player_id,String Goal_Type ,Match match)
	{	
		if(Goal_Type.equalsIgnoreCase(FootballUtil.OWN_GOAL)) {
			for(Player plyr : match.getHomeSquad()) {
				if(plyr.getPlayerId() == player_id) {
					return FootballUtil.AWAY;
				}
			}
			for(Player plyr : match.getHomeSubstitutes()) {
				if(plyr.getPlayerId() == player_id) {
					return FootballUtil.AWAY;
				}
			}
			for(Player plyr : match.getAwaySquad()) {
				if(plyr.getPlayerId() == player_id) {
					return FootballUtil.HOME;
				}
			}
			for(Player plyr : match.getAwaySubstitutes()) {
				if(plyr.getPlayerId() == player_id) {
					return FootballUtil.HOME;
				}
			}
		}else if(Goal_Type.equalsIgnoreCase(FootballUtil.GOAL) || Goal_Type.equalsIgnoreCase(FootballUtil.PENALTY)) {
			for(Player plyr : match.getHomeSquad()) {
				if(plyr.getPlayerId() == player_id) {
					return FootballUtil.HOME;
				}
			}
			for(Player plyr : match.getHomeSubstitutes()) {
				if(plyr.getPlayerId() == player_id) {
					return FootballUtil.HOME;
				}
			}
			for(Player plyr : match.getAwaySquad()) {
				if(plyr.getPlayerId() == player_id) {
					return FootballUtil.AWAY;
				}
			}
			for(Player plyr : match.getAwaySubstitutes()) {
				if(plyr.getPlayerId() == player_id) {
					return FootballUtil.AWAY;
				}
			}
		}
		
		return "";
	}
	
	public static String calExtraTimeGoal(String half,long number_in_milli) {
		
		long time=0,number=0;
		
		number = (number_in_milli/1000);
		
		if(half.equalsIgnoreCase("first") && number > 2700) {
			time = ((number - 2700)/60) + 1;
			return "45' (+" + time + "')" ;
		}else if(half.equalsIgnoreCase("second") && number > 5400) {
			time = ((number - 5400)/60) + 1;
			return "90' (+" + time + "')" ;
		}if(half.equalsIgnoreCase("extra1") && number > 6300) {
			time = ((number - 6300)/60) + 1;
			return "105' (+" + time + "')" ;
		}else if(half.equalsIgnoreCase("extra2") && number > 7200) {
			time = ((number - 7200)/60) + 1;
			return "120' (+" + time + "')" ;
		}else {
			return String.valueOf((number/60)+1) + "'" ;
		}
	}
	
	public static String goal_shortname(String goal_type) {
		if(goal_type.equalsIgnoreCase(FootballUtil.PENALTY)) {
			return " (P) ";
		}else if(goal_type.equalsIgnoreCase(FootballUtil.OWN_GOAL)) {
			return " (OG) ";
		}else if(goal_type.equalsIgnoreCase(FootballUtil.GOAL)) {
			return " ";
		}
		return "";
	}
	
	public static List<TeamStats> getTopStatsDatafromXML(Object match)throws SAXException, IOException, ParserConfigurationException, FactoryConfigurationError {
		
		String team = "";
		ArrayList<TeamStats> teamStats = new ArrayList<TeamStats>();
		
		Document doc = (new File(FootballUtil.FOOTBALL_DIRECTORY + FootballUtil.STATISTIC_DIRECTORY + FootballUtil.MATCH_DATA_DIRECTORY + FootballUtil.SPORTVUSTATISTIC + FootballUtil.XML_EXTENSION).exists()) ? DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(FootballUtil.FOOTBALL_DIRECTORY + FootballUtil.STATISTIC_DIRECTORY + FootballUtil.MATCH_DATA_DIRECTORY + FootballUtil.SPORTVUSTATISTIC + FootballUtil.XML_EXTENSION)) : null;
			if(doc!=null) {
				 doc.getDocumentElement().normalize();
			        
			        NodeList childNodes = doc.getDocumentElement().getChildNodes();
			        for(int i = 0; i < childNodes.getLength(); i++) {
			            if(childNodes.item(i).getNodeType() == Node.ELEMENT_NODE && childNodes.item(i).getNodeName().equals("Teams")) {
			            	for(int j = 0; j < childNodes.item(i).getChildNodes().getLength(); j++) {
			            		if(childNodes.item(i).getChildNodes().item(j).getNodeType() == Node.ELEMENT_NODE 
			            				&& childNodes.item(i).getChildNodes().item(j).getNodeName().equalsIgnoreCase("Team")) {
			                    	for(int k = 0; k < childNodes.item(i).getChildNodes().item(j).getChildNodes().getLength(); k++) {
			                    		
			                    		if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeType() 
			                    				== Node.ELEMENT_NODE && childNodes.item(i).getChildNodes().item(j)
			                    				.getChildNodes().item(k).getNodeName().equalsIgnoreCase("TeamData")) {
			                    			
			                    			for(int t = 0; t < childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().getLength(); t++) {
			                    				
			                    				if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(t).getNodeType() 
					                    				== Node.ELEMENT_NODE && childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(t)
			                    						.getNodeName().equalsIgnoreCase("TeamName")) {
			                    					
//			                    					System.out.println("TEAM : " + childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(t).getFirstChild()
//			                    						.getNodeValue());
			                    					team = childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(t).getFirstChild()
				                    						.getNodeValue();
			                    					teamStats.add(new TeamStats(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(t).getFirstChild()
			                    						.getNodeValue(), new ArrayList<TopStats>()));
			                    					
			                    				}
			                    			} 
			                    		}
			                    		
			                    		if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeType() 
			                    				== Node.ELEMENT_NODE && childNodes.item(i).getChildNodes().item(j)
			                    				.getChildNodes().item(k).getNodeName().equalsIgnoreCase("ResultData")) {
			                    			
			                    			if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getAttributes().getNamedItem("Name").getNodeValue().equalsIgnoreCase("Best Runner")||
			                    					childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getAttributes().getNamedItem("Name").getNodeValue().equalsIgnoreCase("Best Sprinter")||
			                    					childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getAttributes().getNamedItem("Name").getNodeValue().equalsIgnoreCase("Highest Distance")||
			                    					childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getAttributes().getNamedItem("Name").getNodeValue().equalsIgnoreCase("Team Top Speed")) {
			                    				
//			                    				System.out.println("Stat Type = " + childNodes.item(i).getChildNodes().item(j).getChildNodes()
//				                                		.item(k).getAttributes().getNamedItem("Name").getNodeValue());
				                    			
				                    			teamStats.get(teamStats.size()-1).getTopStats().add(new TopStats(childNodes.item(i).getChildNodes().item(j).getChildNodes()
				                                		.item(k).getAttributes().getNamedItem("Name").getNodeValue(), new ArrayList<PlayerStats>()));
			                    				
				                    			for(int l = 0; l < childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().getLength(); l++) {
				                    				
				                    				
				                    				
				                            		if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k)
				                            				.getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE 
				                            				&& childNodes.item(i).getChildNodes().item(j)
				                            				.getChildNodes().item(k).getChildNodes().item(l).getNodeName().equalsIgnoreCase("Result")) {
				                            			
//				                            			System.out.println("TEAM : " + team);
			                                    		teamStats.get(teamStats.size()-1).getTopStats().get(teamStats.get(teamStats.size()-1).getTopStats().size()-1)
			                        					.getPlayersStats().add(new PlayerStats(team));
				                            			
				                                    	for(int m = 0; m < childNodes.item(i).getChildNodes().item(j).getChildNodes()
				                                    			.item(k).getChildNodes().item(l).getChildNodes().getLength(); m++) {
				                                    		
				                                    		if(childNodes.item(i).getChildNodes().item(j).getChildNodes()
				                                    			.item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeType() 
				                                    			== Node.ELEMENT_NODE) {
				                                    			
				                                    			if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes()
					                                            		.item(m).getNodeName().equalsIgnoreCase("PlayerFirstName")) {
				                                    				
//				                                    				System.out.println("PlayerFirstName = " + childNodes.item(i).getChildNodes().item(j).getChildNodes()
//					                                        				.item(k).getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue());
				                                    				
				                                    				teamStats.get(teamStats.size()-1).getTopStats().get(teamStats.get(teamStats.size()-1).getTopStats().size()-1)
		                                    						.getPlayersStats().get(teamStats.get(teamStats.size()-1).getTopStats().get(teamStats.get(teamStats.size()-1)
		                                    								.getTopStats().size()-1).getPlayersStats().size()-1).setFirst_name(childNodes.item(i).getChildNodes().item(j)
		                                    										.getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue());
				                                    			
				                                    				
				                                    			}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes()
				                                            		.item(m).getNodeName().equalsIgnoreCase("PlayerJerseyNumber")) {
				                                    				
//				                                    				System.out.println("PlayerJerseyNumber = " + childNodes.item(i).getChildNodes().item(j).getChildNodes()
//				                                        				.item(k).getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue());
				                                    				
				                                    			    teamStats.get(teamStats.size() - 1).getTopStats().get(teamStats.get(teamStats.size() - 1).getTopStats().size() - 1).getPlayersStats()
				                                    			    .get(teamStats.get(teamStats.size() - 1).getTopStats().get(teamStats.get(teamStats.size() - 1).getTopStats().size() - 1).
				                                    			    		getPlayersStats().size() - 1).setJerseyNumber(Integer.valueOf((childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).
				                                    			    				getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue().isEmpty() || !childNodes.item(i).getChildNodes()
				                                    			    				.item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue().matches("\\d+") ? "0" : 
				                                    			    					childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getFirstChild(
				                                    			    							).getNodeValue())));
				                                    				
				                                    			}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes()
				                                                		.item(m).getNodeName().equalsIgnoreCase("Value")) {

				                                    				teamStats.get(teamStats.size()-1).getTopStats().get(teamStats.get(teamStats.size()-1).getTopStats().size()-1)
			                                    						.getPlayersStats().get(teamStats.get(teamStats.size()-1).getTopStats().get(teamStats.get(teamStats.size()-1)
			                                    								.getTopStats().size()-1).getPlayersStats().size()-1).setValue(childNodes.item(i).getChildNodes().item(j)
			                                    										.getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue().trim().isEmpty()? "0.0":
			                                    											childNodes.item(i).getChildNodes().item(j)
				                                    										.getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue());
			                                    				
//				                                    				System.out.println("Value = " + childNodes.item(i).getChildNodes().item(j).getChildNodes()
//				                                        					.item(k).getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue());
				                                        		}
				                                    		}
				                                    	}
				                            		}
				                            	}
			                    			}
			                    		}
			                    	}
			            		}
			            	}
			            }
			        }
			}
		return teamStats;
	}
	
	public static Player populatePlayer(FootballService footballService, Player player, Match match)
	{
		Player this_plyr = new Player();
		this_plyr = footballService.getPlayer(FootballUtil.PLAYER, String.valueOf(player.getPlayerId()));
		if(this_plyr != null) {
			this_plyr.setPlayerPosition(player.getPlayerPosition()); this_plyr.setCaptainGoalKeeper(player.getCaptainGoalKeeper());
		}
		return this_plyr;
	}
	
	public static Match populateMatchVariables(FootballService footballService,Match match) 
	{
		List<Player> players = new ArrayList<Player>();
		
		for(Player plyr:match.getHomeSquad()) {
			players.add(populatePlayer(footballService, plyr, match));
		}
		match.setHomeSquad(players);

		players = new ArrayList<Player>();
		for(Player plyr:match.getHomeSubstitutes()) {
			players.add(populatePlayer(footballService, plyr, match));
		}
		match.setHomeSubstitutes(players);
		
		players = new ArrayList<Player>();
		if(match.getHomeOtherSquad() != null) {
			for(Player plyr:match.getHomeOtherSquad()) {
				players.add(populatePlayer(footballService, plyr, match));
			}
		}
		match.setHomeOtherSquad(players);
		
		players = new ArrayList<Player>();
		for(Player plyr:match.getAwaySquad()) {
			players.add(populatePlayer(footballService, plyr, match));
		}
		match.setAwaySquad(players);

		players = new ArrayList<Player>();
		for(Player plyr:match.getAwaySubstitutes()) {
			players.add(populatePlayer(footballService, plyr, match));
		}
		match.setAwaySubstitutes(players);
		
		players = new ArrayList<Player>();
		if(match.getAwayOtherSquad() != null) {
			for(Player plyr:match.getAwayOtherSquad()) {
				players.add(populatePlayer(footballService, plyr, match));
			}
		}
		match.setAwayOtherSquad(players);
		
		if(match.getHomeTeamId() > 0)
			match.setHomeTeam(footballService.getTeam(FootballUtil.TEAM, String.valueOf(match.getHomeTeamId())));
		if(match.getAwayTeamId() > 0)
			match.setAwayTeam(footballService.getTeam(FootballUtil.TEAM, String.valueOf(match.getAwayTeamId())));
		if(match.getGroundId() > 0) {
			match.setGround(footballService.getGround(match.getGroundId()));
			match.setVenueName(match.getGround().getFullname());
		}

		if(match.getMatchStats() != null) {
			for(MatchStats ms : match.getMatchStats()) {
				ms.setPlayer(footballService.getPlayer(FootballUtil.PLAYER, String.valueOf(ms.getPlayerId())));
			}
		}
		
		return match;
	}
	
	public static String getOnlineCurrentDate() throws IOException
	{
		HttpURLConnection httpCon = (HttpURLConnection) new URL("https://mail.google.com/").openConnection();
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date(httpCon.getDate()));
	}	
	
	public static List<Player> getPlayersFromDB(FootballService footballService, String whichTeamToProcess, Match match)
	{
		List<Player> players = new ArrayList<Player>(),whichTeamToCheck = new ArrayList<Player>();
		boolean player_found = false; 
		int whichTeamId = 0; 
		
		switch (whichTeamToProcess) {
		case FootballUtil.HOME:
			whichTeamId = match.getHomeTeamId();
			whichTeamToCheck = match.getHomeSquad();
			break;
		case FootballUtil.AWAY:
			whichTeamId = match.getAwayTeamId();
			whichTeamToCheck = match.getAwaySquad();
			break;
		}
		for(Player plyr : footballService.getPlayers(FootballUtil.TEAM,String.valueOf(whichTeamId))) {
			player_found = false;
			for(Player subPlyr : whichTeamToCheck) {
				if (subPlyr.getPlayerId() == plyr.getPlayerId()) {
					player_found = true;
				}
			}
			if(player_found == false) {
				players.add(plyr);
			}
		}
		return players;
	}	
	
	public static void readXml(ApiMatch match) {
		int k=0;
		 try {	            
	           Document document = (new File(FootballUtil.FOOTBALL_DIRECTORY + FootballUtil.STATISTIC_DIRECTORY + FootballUtil.MATCH_DATA_DIRECTORY + FootballUtil.SPORTVUSTATISTIC + FootballUtil.XML_EXTENSION).exists()) ? DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(FootballUtil.FOOTBALL_DIRECTORY + FootballUtil.STATISTIC_DIRECTORY + FootballUtil.MATCH_DATA_DIRECTORY + FootballUtil.SPORTVUSTATISTIC + FootballUtil.XML_EXTENSION)) : null;
				if(document!=null) {
					document.getDocumentElement().normalize();
		            NodeList resultDataList = document.getElementsByTagName("ResultData");
		            for (int i = 0; i < resultDataList.getLength(); i++) {
		                Element resultDataElement = (Element) resultDataList.item(i);

		                if ("Banner Team Data".equals(resultDataElement.getAttribute("Name"))) {
		                	k++;
		                    NodeList resultList = resultDataElement.getElementsByTagName("Result");
		                    for (int j = 0; j < resultList.getLength(); j++) {
		                        Element resultElement = (Element) resultList.item(j);
		                        String label = resultElement.getElementsByTagName("Label").item(0).getFirstChild().getNodeValue().trim();
		                        if (label.equals("Distance")) {
		                            String distanceValue = resultElement.getElementsByTagName("Value").item(0).getFirstChild().getNodeValue().trim();
		                            if(k==1) {
			                        	match.getApi_LiveMatch().getHomeTeam().setDistanceCovered((int) Math.round(Double.valueOf(distanceValue)));

			                        }else if(k==2) {
				                        match.getApi_LiveMatch().getAwayTeam().setDistanceCovered((int) Math.round(Double.valueOf(distanceValue)));
			                        }
		                        } 
		                    }
		                }
		            }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

	public static void setXMLDataInMatchApi(ApiMatch match, Match session_match) throws Exception {
		List<TeamStats> topStatsData = getTopStatsDatafromXML(match);
		
		match.getTop_Distance().clear();match.getTop_Sprints().clear();match.getTop_Speed().clear();
				
	    for (TeamStats statsData : topStatsData) {
	        for (TopStats topStats : statsData.getTopStats()) {
	            String header = topStats.getHeader().toLowerCase();
	            List<PlayerStats> playersStats = topStats.getPlayersStats();

	            switch (header) {
	                case "team top speed":
	                    match.getTop_Speed().addAll(playersStats);
	                    break;
	                case "highest distance":
	                    match.getTop_Distance().addAll(playersStats);
	                    break;
	                case "best sprinter":
	                    match.getTop_Sprints().addAll(playersStats);
	                    break;
	            }
	        }
	    }
	    if (match.getTop_Speed() != null && !match.getTop_Speed().isEmpty()) {
	        Collections.sort(match.getTop_Speed(), new FootballFunctions.PlayerStatsComparator());
	        setTopStatsplayerName(session_match,match.getTop_Speed());
	    }

	    if (match.getTop_Distance() != null && !match.getTop_Distance().isEmpty()) {
	        Collections.sort(match.getTop_Distance(), new FootballFunctions.PlayerStatsComparator());
	        setTopStatsplayerName(session_match,match.getTop_Distance());
	    }

	    if (match.getTop_Sprints() != null && !match.getTop_Sprints().isEmpty()) {
	        Collections.sort(match.getTop_Sprints(), new FootballFunctions.PlayerStatsComparator());
	        setTopStatsplayerName(session_match,match.getTop_Sprints());
	    }
	}

	public static List<String> setTeam(LiveMatch match) throws Exception {
		for (int teamIndex = 0; teamIndex <= 1; teamIndex++) {
			
		}
		return null;
			
	}

	public static void setJsonDataInMatchApi(ApiMatch match) throws Exception {
		try {
		if(new File(FootballUtil.LIVE_DATA).exists()) {
		    LiveMatch liveMatch = new ObjectMapper().readValue(new File(FootballUtil.LIVE_DATA), LiveMatch.class);
		    
		    match.getApi_LiveMatch().getHomeTeam().getPlayer().clear();
		    match.getApi_LiveMatch().getAwayTeam().getPlayer().clear();
		    match.getApi_LiveMatch().getAwayTeam().reset();
		    match.getApi_LiveMatch().getHomeTeam().reset();
			List<PlayerStats> playerStats = new ArrayList<PlayerStats>();
			match.getApi_LiveMatch().getEvents().clear();
			if (liveMatch != null && liveMatch.getLiveData() != null && liveMatch.getLiveData().getCard() != null) {
			        for (Card card : liveMatch.getLiveData().getCard()) {
			        	match.getApi_LiveMatch().getEvents().add(new ApiEventStats(card.getContestantId(),card.getPlayerId(),HtmlUtils.htmlEscape(card.getPlayerName()),card.getTimeMin(),
			            		card.getType(),card.getPeriodId(),card.getTimeMinSec()));
			        	if(card.getPlayerId()== null && card.getPlayerName()==null ) {
			        		match.getApi_LiveMatch().getEvents().get(match.getApi_LiveMatch().getEvents().size()-1).setPlayerName(HtmlUtils.htmlEscape(card.getOfficialName()));
			        	}
			        	  if ((card.getType().equalsIgnoreCase("YC") || card.getType().equalsIgnoreCase("Y2C")) && card.getTeamOfficialId()==null) {
			                if (card.getContestantId().equalsIgnoreCase(liveMatch.getMatchInfo().getContestant().get(0).getId())) {
			                    if(card.getType().equalsIgnoreCase("Y2C")) {
			                    	match.getApi_LiveMatch().getHomeTeam().setYellowCards(
					                        match.getApi_LiveMatch().getHomeTeam().getYellowCards() - 1);
			                    	 match.getApi_LiveMatch().getHomeTeam().setRedCards(
						                        match.getApi_LiveMatch().getHomeTeam().getRedCards() + 1);
			                    }else {
			                    	match.getApi_LiveMatch().getHomeTeam().setYellowCards(
					                        match.getApi_LiveMatch().getHomeTeam().getYellowCards() + 1);
			                    }
			                } else if (card.getContestantId().equalsIgnoreCase(liveMatch.getMatchInfo().getContestant().get(1).getId())) {
			                	if(card.getType().equalsIgnoreCase("Y2C")) {
			                		match.getApi_LiveMatch().getAwayTeam().setYellowCards(
					                        match.getApi_LiveMatch().getAwayTeam().getYellowCards() - 1);
			                		match.getApi_LiveMatch().getAwayTeam().setRedCards(
					                        match.getApi_LiveMatch().getAwayTeam().getRedCards() + 1);
			                    }else {
			                    	match.getApi_LiveMatch().getAwayTeam().setYellowCards(
					                        match.getApi_LiveMatch().getAwayTeam().getYellowCards() + 1);
			                    }
			                }
			            }else  if (card.getType().equalsIgnoreCase("RC") && card.getTeamOfficialId()== null) {
			                if (card.getContestantId().equalsIgnoreCase(liveMatch.getMatchInfo().getContestant().get(0).getId())) {
			                    match.getApi_LiveMatch().getHomeTeam().setRedCards(
			                        match.getApi_LiveMatch().getHomeTeam().getRedCards() + 1
			                    );
			                } else if (card.getContestantId().equalsIgnoreCase(liveMatch.getMatchInfo().getContestant().get(1).getId())) {
			                    match.getApi_LiveMatch().getAwayTeam().setRedCards(
			                        match.getApi_LiveMatch().getAwayTeam().getRedCards() + 1
			                    );
			                }
			            }
			        }
			}
			if( match.getApi_LiveMatch().getHomeTeam().getYellowCards()< 0) {
				match.getApi_LiveMatch().getHomeTeam().setYellowCards(0);
			}if( match.getApi_LiveMatch().getAwayTeam().getYellowCards()< 0) {
				match.getApi_LiveMatch().getAwayTeam().setYellowCards(0);
			}
				
		    //goals
		    if(liveMatch != null && liveMatch.getLiveData() != null && liveMatch.getLiveData().getGoal()!= null) {
		    	for(Goal goal: liveMatch.getLiveData().getGoal()) {
		    		match.getApi_LiveMatch().getEvents().add(new ApiEventStats(goal.getContestantId(),goal.getScorerId(),HtmlUtils.htmlEscape(goal.getScorerName()),goal.getTimeMin(),
		    				 goal.getType(),goal.getPeriodId(),goal.getTimeMinSec()));
		    	}
		    }
		    //substitutes
		    if(liveMatch != null && liveMatch.getLiveData() != null && liveMatch.getLiveData().getSubstitute()!=null) {
		    	for(Substitute subs: liveMatch.getLiveData().getSubstitute()) {		    		
		    		match.getApi_LiveMatch().getEvents().add(new ApiEventStats(subs.getContestantId(),subs.getPlayerOnId(),subs.getPlayerOffId(),HtmlUtils.htmlEscape(subs.getPlayerOnName()),
		    				HtmlUtils.htmlEscape(subs.getPlayerOffName()),subs.getTimeMin(),"SUB",subs.getPeriodId(),0,subs.getTimeMinSec()));
		    	}
		    }
		    match.getApi_LiveMatch().getHomeTeam().setName(liveMatch.getMatchInfo().getContestant().get(0).getName().trim());
		    match.getApi_LiveMatch().getAwayTeam().setName(liveMatch.getMatchInfo().getContestant().get(1).getName().trim());
	       
		    Collections.sort(match.getApi_LiveMatch().getEvents(), (p1, p2) -> Integer.compare(
		    	    Integer.parseInt(p2.getTimeMinSec().split(":")[0]) * 60 + Integer.parseInt(p2.getTimeMinSec().split(":")[1]),
		    	    Integer.parseInt(p1.getTimeMinSec().split(":")[0]) * 60 + Integer.parseInt(p1.getTimeMinSec().split(":")[1])
		    	));
		    //GOALS
		    if(liveMatch != null && liveMatch.getLiveData() != null && liveMatch.getLiveData().getMatchDetails()!=null &&
		    	liveMatch.getLiveData().getMatchDetails().getScores()!=null && liveMatch.getLiveData().getMatchDetails().getScores().getTotal()!=null) {
		    	
		    	match.getApi_LiveMatch().getHomeTeam().setGoals(liveMatch.getLiveData().getMatchDetails().getScores().getTotal().getHome());
		    	match.getApi_LiveMatch().getAwayTeam().setGoals(liveMatch.getLiveData().getMatchDetails().getScores().getTotal().getAway());
		    }
		  //GOALS half
		    if(liveMatch != null && liveMatch.getLiveData() != null && liveMatch.getLiveData().getMatchDetails()!=null &&
			    	liveMatch.getLiveData().getMatchDetails().getScores()!=null && liveMatch.getLiveData().getMatchDetails().getScores().getHt()!=null) {
			    	
			    	match.getApi_LiveMatch().getHomeTeam().setHtgoals(liveMatch.getLiveData().getMatchDetails().getScores().getHt().getHome());
			    	match.getApi_LiveMatch().getAwayTeam().setHtgoals(liveMatch.getLiveData().getMatchDetails().getScores().getHt().getAway());
			    }
		  //GOALS full
		    if(liveMatch != null && liveMatch.getLiveData() != null && liveMatch.getLiveData().getMatchDetails()!=null &&
			    	liveMatch.getLiveData().getMatchDetails().getScores()!=null && liveMatch.getLiveData().getMatchDetails().getScores().getFt()!=null) {
			    	
			    	match.getApi_LiveMatch().getHomeTeam().setFtgoals(liveMatch.getLiveData().getMatchDetails().getScores().getFt().getHome());
			    	match.getApi_LiveMatch().getAwayTeam().setFtgoals(liveMatch.getLiveData().getMatchDetails().getScores().getFt().getAway());
			    }
		    for (int teamIndex = 0; teamIndex <= 1; teamIndex++) {
		        ApiTeamstats team = (teamIndex == 0) ? match.getApi_LiveMatch().getHomeTeam() : match.getApi_LiveMatch().getAwayTeam();
		       
		        if (liveMatch != null && liveMatch.getLiveData() != null &&  liveMatch.getLiveData().getLineUp() != null && liveMatch.getLiveData().getLineUp().get(teamIndex) != null &&
		        	    liveMatch.getLiveData().getLineUp().get(teamIndex).getStat() != null) {
		        	
		        	for (TeamStat stat : liveMatch.getLiveData().getLineUp().get(teamIndex).getStat()) {
			            String value = stat.getValue().trim() != null ? stat.getValue().trim() : "0";
			            String FH = stat.getFh() != null ? stat.getFh().trim() : "0";
			            String SH = stat.getSh() != null ? stat.getSh().trim() : "0";
			            switch (stat.getType()) {
			                case "cornerTaken":
			                    team.setCornerTaken(Integer.parseInt(value));
			                    team.setHtCornerTaken(Integer.parseInt(FH));
			                    team.setFtCornerTaken(Integer.parseInt(SH));
			                    break;
			                case "fkFoulWon":
			                	team.setHtFoulsWon(Integer.parseInt(FH));
			                    team.setFtFoulsWon(Integer.parseInt(SH));
			                    team.setFoulsWon(Integer.parseInt(value));
			                    break;
			                case "ontargetScoringAtt":
			                	team.setHtShotOnTarget(Integer.parseInt(FH));
			                    team.setFtShotOnTarget(Integer.parseInt(SH));
			                    team.setShotOnTarget(Integer.parseInt(value));
			                    break;
			                case "totalScoringAtt":
			                    team.setHtShots(Integer.parseInt(FH));
			                    team.setFtShots(Integer.parseInt(SH));
			                    team.setShots(Integer.parseInt(value));
			                    break;
			                case "saves":
			                	team.setHtSaves(Integer.parseInt(FH));
			                    team.setFtSaves(Integer.parseInt(SH));
			                    team.setSaves(Integer.parseInt(value));
			                    break;
			                case "totalCross":
			                	team.setHtCrosses(Integer.parseInt(FH));
			                    team.setFtCrosses(Integer.parseInt(SH));
			                    team.setCrosses(Integer.parseInt(value));
			                    break;
			                case "totalPass":
			                	team.setHtPasses(Integer.parseInt(FH));
			                	team.setFtPasses(Integer.parseInt(SH));
			                    team.setPasses(Integer.parseInt(value));
			                    break;
			                case "accuratePass":
			                	team.setHtAccuratePass(Integer.parseInt(FH));
			                    team.setFtAccuratePass(Integer.parseInt(SH));
			                    team.setAccuratePass(Integer.parseInt(value));
			                    break;
			                case "touches":
			                	team.setHtTouches(Integer.parseInt(FH));
			                    team.setFtTouches(Integer.parseInt(SH));
			                    team.setTouches(Integer.parseInt(value));
			                    break;
			                case "totalTackle":
			                	team.setHtTackles(Integer.parseInt(FH));
			                    team.setFtTackles(Integer.parseInt(SH));
			                    team.setTackles(Integer.parseInt(value));
			                    break;
			                case "totalContest":
			                	team.setHtDribbles(Integer.parseInt(FH));
			                    team.setFtDribbles(Integer.parseInt(SH));
			                    team.setDribbles(Integer.parseInt(value));
			                    break;
			                case "interception":
			                	team.setHtInterceptions(Integer.parseInt(FH));
			                    team.setFtInterceptions(Integer.parseInt(SH));
			                    team.setInterceptions(Integer.parseInt(value));
			                    break;
			                case "possessionPercentage":
			                	team.setHtPossession(Double.valueOf(FH));
			                    team.setFtPossession(Double.valueOf(SH));
			                    team.setPossession( Double.valueOf(value));
			                    break;
			                case "bigChanceCreated":
			                	team.setHtChancesCreated(Integer.parseInt(FH));
			                    team.setFtChancesCreated(Integer.parseInt(SH));
			                	team.setChancesCreated(Integer.parseInt(value));
			                	break;
//			                case "goals":
//			                	team.setGoals(Integer.parseInt(value));
//			                	break;
			                case "totalOffside":
			                    team.setHtOffside(Integer.parseInt(FH));
			                    team.setFtOffside(Integer.parseInt(SH));
			                	team.setOffside(Integer.parseInt(value));
			                	break;
			                case "wonContest":
			                	team.setHtSuccessfulDribble(Integer.parseInt(FH));
			                    team.setFtSuccessfulDribble(Integer.parseInt(SH));
			                	team.setSuccessfulDribble(Integer.parseInt(value));
		                        break;
			                case "duelWon":
			                	team.setHtDuelWon(Integer.parseInt(FH));
			                    team.setFtDuelWon(Integer.parseInt(SH));
			                	team.setDuelWon(Integer.parseInt(value));
		                        break;
			                case "fkFoulLost":
			                	team.setHtFoulLost(Integer.parseInt(FH));
			                    team.setFtFoulLost(Integer.parseInt(SH));
			                	team.setFoulLost(Integer.parseInt(stat.getValue()));
			                	break;
			                case "totalClearance":
			                    team.setHtTotalClearance(Integer.parseInt(FH));
			                    team.setFtTotalClearance(Integer.parseInt(SH));
			                	team.setTotalClearance(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "effectiveClearance":
		                    	team.setHtEffectiveClearance(Integer.parseInt(FH));
		                        team.setFtEffectiveClearance(Integer.parseInt(SH));
		                    	team.setEffectiveClearance(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "interceptionWon":
		                    	team.setHtInterceptionWon(Integer.parseInt(FH));
		                    	team.setFtInterceptionWon(Integer.parseInt(SH));
		                    	team.setInterceptionWon(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "ballRecovery":
		                    	team.setHtBallRecovery(Integer.parseInt(FH));
		                        team.setFtBallRecovery(Integer.parseInt(SH));
		                    	team.setBallRecovery(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "unsuccessfulTouch":
		                    	team.setHtUnsuccessfulTouch(Integer.parseInt(FH));
		                        team.setFtUnsuccessfulTouch(Integer.parseInt(SH));
		                    	team.setUnsuccessfulTouch(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "turnover":
		                    	team.setHtTurnover(Integer.parseInt(FH));
		                        team.setFtTurnover(Integer.parseInt(SH));
		                    	team.setTurnover(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "wonTackle":
		                    	team.setHtWonTackle(Integer.parseInt(FH));
		                    	team.setFtWonTackle(Integer.parseInt(SH));
								team.setWonTackle(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "longPassOwnToOpp":
		                    	team.setHtlongPassOwnToOpp(Integer.parseInt(FH));
		                    	team.setFtlongPassOwnToOpp(Integer.parseInt(SH));
								team.setLongPassOwnToOpp(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "longPassOwnToOppSuccess":
		                    	team.setHtlongPassOwnToOppSuccess(Integer.parseInt(FH));
		                    	team.setFtlongPassOwnToOppSuccess(Integer.parseInt(SH));
								team.setLongPassOwnToOppSuccess(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "totalFinalThirdPasses":
		                    	team.setHtTotalFinalThirdPasses(Integer.parseInt(FH));
		                        team.setFtTotalFinalThirdPasses(Integer.parseInt(SH));
		                        team.setTotalFinalThirdPasses(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "successfulFinalThirdPasses":
		                        team.setHtSuccessfulFinalThirdPasses(Integer.parseInt(FH));
		                        team.setFtSuccessfulFinalThirdPasses(Integer.parseInt(SH));
		                        team.setSuccessfulFinalThirdPasses(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "possWonAtt3rd":
		                    	team.setHtPossWonAtt3rd(Integer.parseInt(FH));
		                        team.setFtPossWonAtt3rd(Integer.parseInt(SH));
		                        team.setPossWonAtt3rd(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "possWonDef3rd":
		                    	team.setHtPossWonDef3rd(Integer.parseInt(FH));
		                        team.setFtPossWonDef3rd(Integer.parseInt(SH));
		                        team.setPossWonDef3rd(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "touchesInOppBox":
		                    	team.setHtTouchesInOppBox(Integer.parseInt(FH));
		                        team.setFtTouchesInOppBox(Integer.parseInt(SH));
		                        team.setTouchesInOppBox(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "wonCorners":
		                    	team.setHtWonCorners(Integer.parseInt(FH));
		                        team.setFtWonCorners(Integer.parseInt(SH));
		                        team.setWonCorners(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "lostCorners":
		                    	team.setHtLostCorners(Integer.parseInt(FH));
		                        team.setFtLostCorners(Integer.parseInt(SH));
		                        team.setLostCorners(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "duelLost":
		                    	team.setHtDuelLost(Integer.parseInt(FH));
		                        team.setFtDuelLost(Integer.parseInt(SH));
		                        team.setDuelLost(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "blockedScoringAtt":
		                    	team.setHtBlockedScoringAtt(Integer.parseInt(FH));
		                        team.setFtBlockedScoringAtt(Integer.parseInt(SH));
		                    	team.setBlockedScoringAtt(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "ShotOffTarget":
		                    	team.setHtShotOffTarget(Integer.parseInt(FH));
		                        team.setFtShotOffTarget(Integer.parseInt(SH));
		                    	team.setShotOffTarget(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "goalsConceded":
		                    	team.setHtGoalsConceded(Integer.parseInt(FH));
		                        team.setFtGoalsConceded(Integer.parseInt(SH));
		                    	team.setGoalsConceded(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "totalThrows":
		                    	team.setHtTotalThrows(Integer.parseInt(FH));
		                        team.setFtTotalThrows(Integer.parseInt(SH));
		                        team.setTotalThrows(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "attemptsIbox":
		                    	team.setHtshotsInsideBox(Integer.parseInt(FH));
		                        team.setFtshotsInsideBox(Integer.parseInt(SH));
		                        team.setShotsInsideBox(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "aerialWon":
		                    	team.setHtAerialWon(Integer.parseInt(FH));
		                        team.setFtAerialWon(Integer.parseInt(SH));
		                        team.setAerialWon(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "aerialLost":
		                    	team.setHtAerialLost(Integer.parseInt(FH));
		                        team.setFtAerialLost(Integer.parseInt(SH));
		                        team.setAerialLost(Integer.parseInt(stat.getValue()));
		                        break;
		                    case "finalThirdEntries":
		                        team.setHtFinalThirdEntries(Integer.parseInt(FH));
		                        team.setFtFinalThirdEntries(Integer.parseInt(SH));
		                    	team.setFinalThirdEntries(Integer.parseInt(stat.getValue()));
		                    	break;
		                    case "possWonMid3rd":
		                    	team.setHtPossWonMid3rd(Integer.parseInt(FH));
		                        team.setFtPossWonMid3rd(Integer.parseInt(SH));
		                        team.setPossWonMid3rd(Integer.parseInt(stat.getValue()));
		                    	break;
		                    
			            }
			        }	
		            for(Players py : liveMatch.getLiveData().getLineUp().get(teamIndex).getPlayer()) {
						 ApiPlayerStats playerStats1 = new ApiPlayerStats();
				            playerStats1.setId(py.getPlayerId().trim());
							playerStats1.setName(HtmlUtils.htmlEscape(py.getMatchName().trim()));
							playerStats1.setShirtNumber(py.getShirtNumber());
							playerStats1.setPosition(py.getPosition());
				            playerStats1.setSubPosition(py.getSubPosition());
				            if(py.getCaptain() != null && py.getCaptain().equalsIgnoreCase(FootballUtil.YES)) {
				            	playerStats1.setCaptain(py.getCaptain());
				            }
				            if(py.getStat() != null) {
				            	for (Stat stat : py.getStat()) {
					                switch (stat.getType()) {
					                    case "fouls":
					                        playerStats1.setFoul(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "totalClearance":
					                    	playerStats1.setTotalClearance(Integer.parseInt(stat.getValue()));
					                    	break;
					                    case "effectiveClearance":
					                    	playerStats1.setEffectiveClearance(Integer.parseInt(stat.getValue()));
					                    	break;
					                    case "totalTackle":
					                        playerStats1.setTotalTackle(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "accuratePass":
					                        playerStats1.setTotalAccuratePass(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "totalFinalThirdPasses":
					                        playerStats1.setTotalFinalThirdPasses(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "possWonAtt3rd":
					                        playerStats1.setPossWonAtt3rd(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "touches":
					                        playerStats1.setTouches(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "wonCorners":
					                        playerStats1.setWonCorners(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "duelWon":
					                        playerStats1.setDuelWon(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "totalContest":
					                        playerStats1.setDribbles(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "interception":
					                    	playerStats1.setInterception(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "interceptionWon":
					                    	playerStats1.setInterception(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "ballRecovery":
					                        playerStats1.setBallRecovery(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "unsuccessfulTouch":
					                    	playerStats1.setUnsuccessfulTouch(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "turnover":
					                    	playerStats1.setTurnover(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "minsPlayed":
					                    	playerStats1.setMinsPlayed(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "totalPass":
					                    	playerStats1.setTotalPass(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "totalCross":
					                    	playerStats1.setTotalCross(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "wonTackle":
					                    	playerStats1.setWonTackle(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "wonContest":
					                    	playerStats1.setWonContest(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "duelLost":
					                        playerStats1.setDuelLost(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "possLostAll":
					                    	  playerStats1.setPossLostAll(Integer.parseInt(stat.getValue()));
					                    	break;
					                    case "saves":
					                        playerStats1.setSaves(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "goals":
					                    	playerStats1.setGoal(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "bigChanceCreated":
					                    	playerStats1.setChanceCreated(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "totalAttAssist":
					                    	playerStats1.setAssists(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "blockedScoringAtt":
					                    	playerStats1.setBlockedScoringAtt(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "ontargetScoringAtt":
					                    	playerStats1.setShotOnTarget(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "ShotOffTarget":
					                    	playerStats1.setShotOffTarget(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "cornerTaken":
					                    	playerStats1.setCornerTaken(Integer.parseInt(stat.getValue()));
					                    	break;
					                    case "goalsConceded":
					                    	playerStats1.setGoalsConceded(Integer.parseInt(stat.getValue()));
					                    	break;
					                    case "totalOffside":
					                    	playerStats1.setTotalOffside(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "totalThrows":
					                    	playerStats1.setTotalThrows(Integer.parseInt(stat.getValue()));
					                    	break;
					                    case "totalScoringAtt":
					                    	playerStats1.setTotalShots(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "aerialWon":
					                    	playerStats1.setAerialWon(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "aerialLost":
					                    	playerStats1.setAerialLost(Integer.parseInt(stat.getValue()));
					                        break;
					                    case "longPassOwnToOpp":
					                    	playerStats1.setLongPassOwnToOpp(Integer.parseInt(stat.getValue()));
					                    	break;
					                    case "longPassOwnToOppSuccess":
					                    	playerStats1.setLongPassOwnToOppSuccess(Integer.parseInt(stat.getValue()));
					                    	break;
					                    case "possWonMid3rd":
					                    	playerStats1.setPossWonMid3rd(Integer.parseInt(stat.getValue()));
					                    	break;
					                    case "possWonDef3rd":
					                    	playerStats1.setPossWonDef3rd(Integer.parseInt(stat.getValue()));
					                    	break;
					                    case "attemptsIbox":
					                    	playerStats1.setAttemptsIbox(Integer.parseInt(stat.getValue()));
					                    	break;
					                }
					            }
				            }
				            team.getPlayer().add(playerStats1);
					}
		        	 for(Players py : liveMatch.getLiveData().getLineUp().get(teamIndex).getPlayer()) {
						if(py.getStat()!=null) {
							for (Stat stat : py.getStat()) {
								 switch (stat.getType()) {
								 case "totalPass":
									 PlayerStats ply = new PlayerStats("");
									 ply.setLast_name(py.getPlayerId());
									 if(teamIndex==0) {
										 ply.setFirst_name(HtmlUtils.htmlEscape(py.getMatchName())+" <sub>"+liveMatch.getMatchInfo().getContestant().get(0).getCode().trim()+"<sub>");
									 }else {
										 ply.setFirst_name(HtmlUtils.htmlEscape(py.getMatchName())+" <sub>"+liveMatch.getMatchInfo().getContestant().get(1).getCode().trim()+"<sub>"); 
									 }
									 ply.setTeam_name(liveMatch.getLiveData().getLineUp().get(teamIndex).getContestantId());
				                     ply.setJerseyNumber(py.getShirtNumber());
				                     ply.setValue(stat.getValue());
									 playerStats.add(ply);
									break;
								 }
							}
						}
		        	 }
		        }
		        Collections.sort(playerStats, (p1, p2) -> Integer.parseInt(p2.getValue()) - Integer.parseInt(p1.getValue()));
		        match.setTop_Passes(playerStats.subList(0, Math.min(3, playerStats.size())));
		        
		        team.setPassingAccuracy(AccuracyPercentage(team.getPasses(), team.getAccuratePass()));
	            team.setSuccessfulDribblePercent(team.getDribbles() > 0 ? (int) Math.round((team.getSuccessfulDribble() * 100.0) / team.getDribbles()) : 0);
	            team.setDuelwonPercent((team.getDuelWon() + team.getDuelLost() > 0) ? (int) Math.round((team.getDuelWon() * 100.0) / (team.getDuelWon() + team.getDuelLost())) : 0);
	            team.setArielwonPercent((team.getAerialWon() + team.getAerialLost() > 0) ? (int) Math.round((team.getAerialWon() * 100.0) / (team.getAerialWon() + team.getAerialLost())) : 0);
	            team.setFinalThirdPassingAccuracy(team.getTotalFinalThirdPasses() > 0 ? (int) Math.round((team.getSuccessfulFinalThirdPasses() * 100.0) / team.getTotalFinalThirdPasses()) : 0);
	            
	            team.setFtPassingAccuracy(AccuracyPercentage(team.getFtPasses(), team.getFtAccuratePass()));
	            team.setFtSuccessfulDribblePercent(team.getFtDribbles() > 0 ? (int) Math.round((team.getFtSuccessfulDribble() * 100.0) / team.getFtDribbles()) : 0);
	            team.setFtDuelwonPercent((team.getFtDuelWon() + team.getFtDuelLost() > 0) ? (int) Math.round((team.getFtDuelWon() * 100.0) / (team.getFtDuelWon() + team.getFtDuelLost())) : 0);
	            team.setFtArielwonPercent((team.getFtAerialWon() + team.getFtAerialLost() > 0) ? (int) Math.round((team.getFtAerialWon() * 100.0) / (team.getFtAerialWon() + team.getFtAerialLost())) : 0);
	            team.setFtFinalThirdPassingAccuracy(team.getFtTotalFinalThirdPasses() > 0 ? (int) Math.round((team.getFtSuccessfulFinalThirdPasses() * 100.0) / team.getFtTotalFinalThirdPasses()) : 0);
	            
	            team.setHtPassingAccuracy(AccuracyPercentage(team.getHtPasses(), team.getHtAccuratePass()));
	            team.setHtSuccessfulDribblePercent(team.getHtDribbles() > 0 ? (int) Math.round((team.getHtSuccessfulDribble() * 100.0) / team.getHtDribbles()) : 0);
	            team.setHtDuelwonPercent((team.getHtDuelWon() + team.getHtDuelLost() > 0) ? (int) Math.round((team.getHtDuelWon() * 100.0) / (team.getHtDuelWon() + team.getHtDuelLost())) : 0);
	            team.setHtArielwonPercent((team.getHtAerialWon() + team.getHtAerialLost() > 0) ? (int) Math.round((team.getHtAerialWon() * 100.0) / (team.getHtAerialWon() + team.getHtAerialLost())) : 0);
	            team.setHtFinalThirdPassingAccuracy(team.getHtTotalFinalThirdPasses() > 0 ? (int) Math.round((team.getHtSuccessfulFinalThirdPasses() * 100.0) / team.getHtTotalFinalThirdPasses()) : 0);

		    	} 
		    }
		}catch (Exception e) {
            System.err.println("Error reading live match data: " + e.getMessage());
        }
	}

	public static String RoundValues(String values) {
	    String[] parts = values.split(","); 
	    return (parts[0].endsWith(".5") && parts[1].endsWith(".5")) ? (Integer.parseInt(parts[0].split("\\.")[0]) + 1) + "," + parts[1].split("\\.")[0] 
	        : Math.round(Double.parseDouble(parts[0])) + "," + Math.round(Double.parseDouble(parts[1]));
	}	

	public static void setApiTournament(ApiMatch match)throws Exception{
		match.getTopAssists().clear();
		match.getTopGoals().clear();
		match.getGoalConceded().clear();
		if(new File("C:\\Sports\\Football\\Statistic\\Match_Data\\MatchPreview.json").exists()) {
			MatchPreview mp = new ObjectMapper().readValue(new File("C:\\Sports\\Football\\Statistic\\Match_Data\\MatchPreview.json"), MatchPreview.class);
			if(mp!=null && mp.getPreviousMeetingsAnyComp()!=null) {
				match.getApi_LiveMatch().setHomeWin(mp.getPreviousMeetingsAnyComp().getHomeContestantWins());
				match.getApi_LiveMatch().setAwayWin(mp.getPreviousMeetingsAnyComp().getAwayContestantWins());
				match.getApi_LiveMatch().setDraws(mp.getPreviousMeetingsAnyComp().getDraws());
			}
		}
		match.getTopGoals().addAll(SeasonalData("MOST GOALS"));
		match.getTopAssists().addAll(SeasonalData("MOST ASSISTS"));
		match.getGoalConceded().addAll(SeasonalData("Clean Sheets"));
	}

	public static double AccuracyPercentage(int totalPassesAttempted, int accuratePasses) {
	    if (totalPassesAttempted <= 0) {
	        return 0.00;
	    }
	    accuratePasses = Math.max(0, accuratePasses);
	    return Double.parseDouble(new DecimalFormat("0.00").format((double) accuratePasses / totalPassesAttempted * 100));
	}	

	public static void setjerseyNumberInMatchApi(ApiMatch match, List<Player> allPlayer) {
	    for (Player ply : allPlayer) {
	        for (ApiEventStats event : match.getApi_LiveMatch().getEvents()) {
	            if (event.getPlayerId()!= null && event.getPlayerId().trim().equalsIgnoreCase(ply.getPlayerAPIId())) {
	                event.setJerseyNumber(ply.getJersey_number());
	            } else if (event.getPlayerOffId() != null 
	                    && event.getPlayerOffId().trim().equalsIgnoreCase(ply.getPlayerAPIId())) {
	                event.setOffJerseyNumber(ply.getJersey_number());
	            }
	        }
	        for (PlayerData goal : match.getGoalConceded()) {
	            if (goal.getId().equalsIgnoreCase(ply.getPlayerAPIId())) {
	                goal.setJerseyNumber(ply.getJersey_number());
	            }
	        }
	        for (PlayerData performer : match.getTopGoals()) {
	            if (performer.getId().equalsIgnoreCase(ply.getPlayerAPIId())) {
	            	 if (!performer.getName().contains(String.valueOf(ply.getJersey_number()))) {
	                     performer.setName(ply.getJersey_number() + ".  " + performer.getName());
	                 }
	            }
	        }

	        for (PlayerData performer : match.getTopAssists()) {
	            if (performer.getId().equalsIgnoreCase(ply.getPlayerAPIId())) {
	            	 if (!performer.getName().contains(String.valueOf(ply.getJersey_number()))) {
	                     performer.setName(ply.getJersey_number() + ".  " + performer.getName());
	                 }
	            }
	        }
	    }
	}

	public static List<String> MatchStatsSingle(ApiMatch api_match,String values) {
		int home_value=0,away_value=0;
		String WhichStyle="";
        List<String> dataList = new ArrayList<>();
		
		for(int i=0;i<values.split(",").length;i++) {
			 WhichStyle = values.split(",")[i];
			switch (values.split(",")[i]) {
		    case "Possession":
		    	String Value = FootballFunctions.RoundValues(api_match.getApi_LiveMatch().getHomeTeam().getPossession()+","
		    		+ api_match.getApi_LiveMatch().getAwayTeam().getPossession());
				home_value = Integer.valueOf(Value.split(",")[0]);
		        away_value =Integer.valueOf(Value.split(",")[1]);
		        WhichStyle="Possession (%)";
		        break;
		    case "Shots":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getShots();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getShots();
		        break;
		    case "Shots_on_Target":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getShotOnTarget();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getShotOnTarget();
		        WhichStyle="Shots on Target";
		        break;
		    case "Corners":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getCornerTaken();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getCornerTaken();
		        break;
		    case "Corners_Won":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getWonCorners();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getWonCorners();
		        break;
		    case "Yellow_Cards":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getYellowCards();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getYellowCards();
		        break;
		    case "Saves":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getSaves();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getSaves();
		        break;
		    case "Crosses":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getCrosses();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getCrosses();
		        break;
		    case "Passes":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getPasses();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getPasses();
		        break;
		    case "Passing_Accuracy":
		    	 Value = RoundValues(api_match.getApi_LiveMatch().getHomeTeam().getPassingAccuracy()+","
		    			+api_match.getApi_LiveMatch().getAwayTeam().getPassingAccuracy());
				home_value = Integer.valueOf(Value.split(",")[0]);
		        away_value =Integer.valueOf(Value.split(",")[1]);
		        WhichStyle = "Passing Accuracy(%)";
		        break;
		    case "Touches":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getTouches();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getTouches();
		        break;
		    case "Tackles":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getTackles();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getTackles();
		        break;
		    case "Offside":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getOffside();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getOffside();
		        break;
		    case "Fouls_Won":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getFoulsWon();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFoulsWon();
		        break;
		    case "Fouls":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getFoulLost();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFoulLost();
		        break;
		    case "Dribbles":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getDribbles();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getDribbles();
		        break;
		    case "Interceptions":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getInterceptions();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getInterceptions();
		        break;
		    case "InterceptionsWon":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getInterceptionWon();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getInterceptionWon();
		    	break;
		    case "Chance_Created":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getChancesCreated();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getChancesCreated();
		    	break;
		    case "goalsConceded":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getGoalsConceded();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getGoalsConceded();
		    	break;
		    case "dribbleWon":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getSuccessfulDribble();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getSuccessfulDribble();
		    	break;
		    case "duelWon":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getDuelWon();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getDuelWon();
		    	break;
		    case "Red_Cards":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getRedCards();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getRedCards();
		    	break;
		    case "Aerial":
		    	home_value = (api_match.getApi_LiveMatch().getHomeTeam().getAerialWon()+api_match.getApi_LiveMatch().getHomeTeam().getAerialLost());
		        away_value = (api_match.getApi_LiveMatch().getAwayTeam().getAerialWon()+api_match.getApi_LiveMatch().getAwayTeam().getAerialLost());
                break;
		    case "Aerial_Won":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getAerialWon();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getAerialWon();
                break;
            case "Successful_Dribbles":
            	home_value = api_match.getApi_LiveMatch().getHomeTeam().getSuccessfulDribblePercent();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getSuccessfulDribblePercent();
		        WhichStyle="Successful Dribbles(%)";
                break;
            case "Duel_won":
            	home_value = api_match.getApi_LiveMatch().getHomeTeam().getDuelwonPercent();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getDuelwonPercent();
		        WhichStyle= "Duel won (%)";
                break;
            case "Duel":
            	home_value = (api_match.getApi_LiveMatch().getHomeTeam().getDuelWon()+api_match.getApi_LiveMatch().getHomeTeam().getDuelLost());
		        away_value = (api_match.getApi_LiveMatch().getAwayTeam().getDuelWon()+api_match.getApi_LiveMatch().getAwayTeam().getDuelLost());
               break;
            case "passes_final_3rd_Accuracy":
            	home_value = api_match.getApi_LiveMatch().getHomeTeam().getFinalThirdPassingAccuracy();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFinalThirdPassingAccuracy();
                break;
            case "Final_3rd_Entries":
            	home_value = api_match.getApi_LiveMatch().getHomeTeam().getFinalThirdEntries();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFinalThirdEntries();
                break;
            case "Touches_In_OppBox":
            	home_value = api_match.getApi_LiveMatch().getHomeTeam().getTouchesInOppBox();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getTouchesInOppBox();
               break;
            case "Final_Third_Passes":
            	home_value = api_match.getApi_LiveMatch().getHomeTeam().getTotalFinalThirdPasses();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getTotalFinalThirdPasses();
                break;
            case "Accurate_Pass":
            	home_value = api_match.getApi_LiveMatch().getHomeTeam().getAccuratePass();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getAccuratePass();
            	break;
            case "Tackles_Won":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getWonTackle();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getWonTackle();
		        break;
            case "long_Pass":
            	home_value = (api_match.getApi_LiveMatch().getHomeTeam().getLongPassOwnToOpp());
		        away_value = (api_match.getApi_LiveMatch().getAwayTeam().getLongPassOwnToOpp());
            	break;
            case "Goals":
            	home_value = (api_match.getApi_LiveMatch().getHomeTeam().getGoals());
		        away_value = (api_match.getApi_LiveMatch().getAwayTeam().getGoals());
            	break;
            case "long_Pass_Success":
            	home_value = (api_match.getApi_LiveMatch().getHomeTeam().getLongPassOwnToOppSuccess());
		        away_value = (api_match.getApi_LiveMatch().getAwayTeam().getLongPassOwnToOppSuccess());
            	break;
            case "Shots_Inside_Box":
            	home_value = (api_match.getApi_LiveMatch().getHomeTeam().getShotsInsideBox());
		        away_value = (api_match.getApi_LiveMatch().getAwayTeam().getShotsInsideBox());
		        System.out.println("home:- "+home_value+" away:- "+away_value);
            	break;
            case "Possession_Won_in_the_Final_Third":
            	home_value = (api_match.getApi_LiveMatch().getHomeTeam().getPossWonAtt3rd());
		        away_value = (api_match.getApi_LiveMatch().getAwayTeam().getPossWonAtt3rd());
            	break;
            case "Possession_Won":
            	home_value = (api_match.getApi_LiveMatch().getHomeTeam().getPossWonAtt3rd() + 
            			api_match.getApi_LiveMatch().getHomeTeam().getPossWonMid3rd() + 
            			api_match.getApi_LiveMatch().getHomeTeam().getPossWonDef3rd());
		        away_value = (api_match.getApi_LiveMatch().getAwayTeam().getPossWonAtt3rd() + 
		        		api_match.getApi_LiveMatch().getAwayTeam().getPossWonMid3rd() + 
		        		api_match.getApi_LiveMatch().getAwayTeam().getPossWonDef3rd() );
            	break;
			}
			dataList.add(home_value + "," + WhichStyle.replace("_", " ") + "," + away_value);
		}

		return dataList;
		
	}

	public static List<String> MatchStatsSingle(ApiMatch api_match,String values,String time) {
		int home_value=0,away_value=0;
		String WhichStyle="";
        List<String> dataList = new ArrayList<>();
		
		for(int i=0;i<values.split(",").length;i++) {
			 WhichStyle = values.split(",")[i];
			switch (values.split(",")[i]) {
		    case "Possession":
		    	String Value = "";
		    	if(time.equalsIgnoreCase("First Half")) {
		    		Value =FootballFunctions.RoundValues(api_match.getApi_LiveMatch().getHomeTeam().getHtPossession()+","
			    		+ api_match.getApi_LiveMatch().getAwayTeam().getHtPossession());
					home_value = Integer.valueOf(Value.split(",")[0]);
			        away_value =Integer.valueOf(Value.split(",")[1]);
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		Value =FootballFunctions.RoundValues(api_match.getApi_LiveMatch().getHomeTeam().getFtPossession()+","
			    		+ api_match.getApi_LiveMatch().getAwayTeam().getFtPossession());
					home_value = Integer.valueOf(Value.split(",")[0]);
			        away_value =Integer.valueOf(Value.split(",")[1]);	
		    	}
		        WhichStyle="Possession (%)";
		        break;
		    case "Shots":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtShots();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtShots();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtShots();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtShots();
		    	}
		        break;
		    case "Shots_on_Target":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtShotOnTarget();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtShotOnTarget();	
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtShotOnTarget();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtShotOnTarget();	
		    	}
		        WhichStyle="Shots on Target";
		        break;
		    case "Corners":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtCornerTaken();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtCornerTaken();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtCornerTaken();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtCornerTaken();
		    	}
		        break;
		    case "Corners_Won":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtWonCorners();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtWonCorners();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtWonCorners();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtWonCorners();
		    	}
		        break;
		    case "Saves":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtSaves();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtSaves();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtSaves();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtSaves();
		    	}
		        break;
		    case "Crosses":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtCrosses();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtCrosses();	
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtCrosses();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtCrosses();
		    	}
		        break;
		    case "Passes":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtPasses();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtPasses();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtPasses();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtPasses();
		    	}
		        break;
		    case "Passing_Accuracy":
		    	if(time.equalsIgnoreCase("First Half")) {
			    	 Value = RoundValues(api_match.getApi_LiveMatch().getHomeTeam().getHtPassingAccuracy()+","
			    			+api_match.getApi_LiveMatch().getAwayTeam().getHtPassingAccuracy());
			    	 home_value = Integer.valueOf(Value.split(",")[0]);
				     away_value =Integer.valueOf(Value.split(",")[1]);
		    	}else if(time.equalsIgnoreCase("Second Half")) {
			    	 Value = RoundValues(api_match.getApi_LiveMatch().getHomeTeam().getFtPassingAccuracy()+","
			    			+api_match.getApi_LiveMatch().getAwayTeam().getFtPassingAccuracy());
			    	 home_value = Integer.valueOf(Value.split(",")[0]);
				     away_value =Integer.valueOf(Value.split(",")[1]);
		    	}
		        WhichStyle = "Passing Accuracy(%)";
		        break;
		    case "Touches":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtTouches();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtTouches();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtTouches();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtTouches();
		    	}
		        break;
		    case "Tackles":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtTackles();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtTackles();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtTackles();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtTackles();
		    	}
		        break;
		    case "Offside":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtOffside();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtOffside();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtOffside();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtOffside();	
		    	}
		        break;
		    case "Fouls_Won":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtFoulsWon();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtFoulsWon();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtFoulsWon();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtFoulsWon();
		    	}
		        break;
		    case "Fouls":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtFoulLost();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtFoulLost();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtFoulLost();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtFoulLost();
		    	}
		        break;
		    case "Dribbles":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtDribbles();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtDribbles();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtDribbles();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtDribbles();
		    	}
		        break;
		    case "Interceptions":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtInterceptions();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtInterceptions();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtInterceptions();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtInterceptions();
		    	}
		        break;
		    case "InterceptionsWon":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtInterceptionWon();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtInterceptionWon();	
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtInterceptionWon();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtInterceptionWon();
		    	}
		    	break;
		    case "Chance_Created":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtChancesCreated();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtChancesCreated();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtChancesCreated();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtChancesCreated();
		    	}
		    	break;
		    case "goalsConceded":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtGoalsConceded();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtGoalsConceded();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtGoalsConceded();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtGoalsConceded();
		    	}
		    	break;
		    case "dribbleWon":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtSuccessfulDribble();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtSuccessfulDribble();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtSuccessfulDribble();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtSuccessfulDribble();
		    	}
		    	break;
		    case "duelWon":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtDuelWon();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtDuelWon();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtDuelWon();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtDuelWon();
		    	}
		    	break;
		    case "Aerial":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = (api_match.getApi_LiveMatch().getHomeTeam().getHtAerialWon()+api_match.getApi_LiveMatch().getHomeTeam().getHtAerialLost());
			        away_value = (api_match.getApi_LiveMatch().getAwayTeam().getHtAerialWon()+api_match.getApi_LiveMatch().getAwayTeam().getHtAerialLost());
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = (api_match.getApi_LiveMatch().getHomeTeam().getFtAerialWon()+api_match.getApi_LiveMatch().getHomeTeam().getFtAerialLost());
			        away_value = (api_match.getApi_LiveMatch().getAwayTeam().getFtAerialWon()+api_match.getApi_LiveMatch().getAwayTeam().getFtAerialLost());
		    	}
                break;
            case "Successful_Dribbles":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtSuccessfulDribblePercent();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtSuccessfulDribblePercent();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtSuccessfulDribblePercent();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtSuccessfulDribblePercent();
		    	}
		        WhichStyle="Successful Dribbles(%)";
                break;
            case "Duel_won":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtDuelwonPercent();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtDuelwonPercent();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtDuelwonPercent();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtDuelwonPercent();
		    	}
		        WhichStyle= "Duel won (%)";
                break;
            case "Duel":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = (api_match.getApi_LiveMatch().getHomeTeam().getHtDuelWon()+api_match.getApi_LiveMatch().getHomeTeam().getHtDuelLost());
			        away_value = (api_match.getApi_LiveMatch().getAwayTeam().getHtDuelWon()+api_match.getApi_LiveMatch().getAwayTeam().getHtDuelLost());
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = (api_match.getApi_LiveMatch().getHomeTeam().getFtDuelWon()+api_match.getApi_LiveMatch().getHomeTeam().getFtDuelLost());
			        away_value = (api_match.getApi_LiveMatch().getAwayTeam().getFtDuelWon()+api_match.getApi_LiveMatch().getAwayTeam().getFtDuelLost());
		    	}
               break;
            case "passes_final_3rd_Accuracy":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtFinalThirdPassingAccuracy();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtFinalThirdPassingAccuracy();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtFinalThirdPassingAccuracy();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtFinalThirdPassingAccuracy();	
		    	}
                break;
            case "Final_3rd_Entries":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtFinalThirdEntries();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtFinalThirdEntries();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtFinalThirdEntries();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtFinalThirdEntries();
		    	}
                break;
            case "Touches_In_OppBox":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtTouchesInOppBox();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtTouchesInOppBox();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtTouchesInOppBox();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtTouchesInOppBox();
		    	}
               break;
            case "Final_Third_Passes":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtTotalFinalThirdPasses();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtTotalFinalThirdPasses();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtTotalFinalThirdPasses();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtTotalFinalThirdPasses();
		    	}
                break;
            case "Accurate_Pass":
		    	if(time.equalsIgnoreCase("First Half")) {
	            	home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtAccuratePass();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtAccuratePass();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
	            	home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtAccuratePass();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtAccuratePass();
		    	}
            	break;
            case "Tackles_Won":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtWonTackle();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtWonTackle();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtWonTackle();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtWonTackle();
		    	}
		        break;
            case "long_Pass":
            	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtlongPassOwnToOpp();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtlongPassOwnToOpp();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtlongPassOwnToOpp();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtlongPassOwnToOpp();
		    	}
            	break;
            case "long_Pass_Success":
            	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtlongPassOwnToOppSuccess();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtlongPassOwnToOppSuccess();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtlongPassOwnToOppSuccess();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtlongPassOwnToOppSuccess();
		    	}
            	break;
            case "Shots_Inside_Box":
            	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtshotsInsideBox();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtshotsInsideBox();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtshotsInsideBox();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtshotsInsideBox();
		    	}
            	break;
            case "Possession_Won_in_the_Final_Third":
            	if(time.equalsIgnoreCase("First Half")) {
            		home_value = (api_match.getApi_LiveMatch().getHomeTeam().getHtPossWonAtt3rd());
    		        away_value = (api_match.getApi_LiveMatch().getAwayTeam().getHtPossWonAtt3rd());
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = (api_match.getApi_LiveMatch().getHomeTeam().getFtPossWonAtt3rd());
			        away_value = (api_match.getApi_LiveMatch().getAwayTeam().getFtPossWonAtt3rd());
		    	}
            	break;
            case "Possession_Won":
            	if(time.equalsIgnoreCase("First Half")) {
            		home_value = (api_match.getApi_LiveMatch().getHomeTeam().getHtPossWonAtt3rd() + 
                			api_match.getApi_LiveMatch().getHomeTeam().getHtPossWonMid3rd() + 
                			api_match.getApi_LiveMatch().getHomeTeam().getHtPossWonDef3rd());
    		        away_value = (api_match.getApi_LiveMatch().getAwayTeam().getHtPossWonAtt3rd() + 
    		        		api_match.getApi_LiveMatch().getAwayTeam().getHtPossWonMid3rd() + 
    		        		api_match.getApi_LiveMatch().getAwayTeam().getHtPossWonDef3rd() );
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = (api_match.getApi_LiveMatch().getHomeTeam().getFtPossWonAtt3rd() + 
	            			api_match.getApi_LiveMatch().getHomeTeam().getFtPossWonMid3rd() + 
	            			api_match.getApi_LiveMatch().getHomeTeam().getFtPossWonDef3rd());
			        away_value = (api_match.getApi_LiveMatch().getAwayTeam().getFtPossWonAtt3rd() + 
			        		api_match.getApi_LiveMatch().getAwayTeam().getFtPossWonMid3rd() + 
			        		api_match.getApi_LiveMatch().getAwayTeam().getFtPossWonDef3rd() );
		    	}
            	break;
			}
			dataList.add(home_value + "," + WhichStyle.replace("_", " ") + "," + away_value);
		}

		return dataList;
		
	}	

	public static List<String> MatchStatsBothHalfs(ApiMatch api_match,String values,String time) {
		int homeHtPossession = 0,homeFtPossession =  0,awayHtPossession = 0,awayFtPossession = 0;
		String WhichStyle="";
        List<String> dataList = new ArrayList<>();
        
		System.out.println("values:- "+values);	
			 WhichStyle = values;
			switch (WhichStyle) {
		    case "Possession":
		    	String Value = "";
		    	Value =FootballFunctions.RoundValues(api_match.getApi_LiveMatch().getHomeTeam().getHtPossession()+","
			    		+ api_match.getApi_LiveMatch().getAwayTeam().getHtPossession());
	    		homeHtPossession =  Integer.valueOf(Value.split(",")[0]);
                homeFtPossession =  Integer.valueOf(Value.split(",")[1]);
	    		Value =FootballFunctions.RoundValues(api_match.getApi_LiveMatch().getHomeTeam().getFtPossession()+","
			    		+ api_match.getApi_LiveMatch().getAwayTeam().getPossession());		    		
                awayHtPossession =  Integer.valueOf(Value.split(",")[0]);
                awayFtPossession = Integer.valueOf(Value.split(",")[1]);
		        WhichStyle="Possession (%)";
		        break;
		    case "Shots":
		    	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtShots();
	            homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtShots();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtShots();
  	            awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtShots();

		        break;
		    case "Shots_on_Target":
		    	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtShotOnTarget();
                homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtShotOnTarget();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtShotOnTarget();
                awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtShotOnTarget();
		        WhichStyle="Shots on Target";
		        break;
		    case "Corners":
		    	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtCornerTaken();
                homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtCornerTaken();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtCornerTaken();
                awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtCornerTaken();

		        break;
		    case "Corners_Won":
		    	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtWonCorners();
                homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtWonCorners();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtWonCorners();
                awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtWonCorners();
		        break;
		    case "Saves":
		    	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtSaves();
                homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtSaves();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtSaves();
                awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtSaves();
		        break;
		    case "Crosses":
		    	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtCrosses();
                homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtCrosses();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtCrosses();
                awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtCrosses();
		        break;
		    case "Passes":
		    	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtPasses();
                homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtPasses();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtPasses();
                awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtPasses();
		        break;
		    case "Passing_Accuracy":
		    	Value =FootballFunctions.RoundValues(api_match.getApi_LiveMatch().getHomeTeam().getHtPassingAccuracy()+","
			    		+ api_match.getApi_LiveMatch().getAwayTeam().getHtPassingAccuracy());
	    		homeHtPossession =  Integer.valueOf(Value.split(",")[0]);
                homeFtPossession =  Integer.valueOf(Value.split(",")[1]);
	    		Value =FootballFunctions.RoundValues(api_match.getApi_LiveMatch().getHomeTeam().getFtPassingAccuracy()+","
			    		+ api_match.getApi_LiveMatch().getAwayTeam().getFtPassingAccuracy());		    		
                awayHtPossession =  Integer.valueOf(Value.split(",")[0]);
                awayFtPossession = Integer.valueOf(Value.split(",")[1]);
		        WhichStyle = "Passing Accuracy(%)";
		        break;
		    case "Touches":
		    	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtTouches();
                homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtTouches();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtTouches();
                awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtTouches();
		        break;
		    case "Tackles":
		    	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtTackles();
                homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtTackles();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtTackles();
                awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtTackles();
		        break;
		    case "Offside":
		    	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtOffside();
                homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtOffside();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtOffside();
                awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtOffside();
		        break;
		    case "Fouls_Won":
		    	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtFoulsWon();
                homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtFoulsWon();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtFoulsWon();
                awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtFoulsWon();
		        break;
		    case "Fouls":
		    	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtFoulLost();
                homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtFoulLost();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtFoulLost();
                awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtFoulLost();
		        break;
		    case "Dribbles":
		    	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtDribbles();
                homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtDribbles();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtDribbles();
                awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtDribbles();
		        break;
		    case "Interceptions":
	    		homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtInterceptions();
                homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtInterceptions();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtInterceptions();
                awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtInterceptions();
		        break;
		    case "InterceptionsWon":
		    	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtInterceptionWon();
		    	homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtInterceptionWon();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtInterceptionWon();
	    		awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtInterceptionWon();
		    	break;
		    case "Chance_Created":
		    	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtChancesCreated();
		    	homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtChancesCreated();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtChancesCreated();
	    		awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtChancesCreated();

		    	break;
		    case "goalsConceded":
		    	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtGoalsConceded();
                homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtGoalsConceded();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtGoalsConceded();
                awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtGoalsConceded();
		    	break;
		    case "dribbleWon":
		    	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtSuccessfulDribble();
                homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtSuccessfulDribble();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtSuccessfulDribble();
                awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtSuccessfulDribble();
		    	break;
		    case "duelWon":
		    	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtDuelWon();
                homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtDuelWon();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtDuelWon();
                awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtDuelWon();

		    	break;
		    case "Aerial":
		    	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtAerialLost();
		    	homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtAerialLost();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtAerialLost();
	    		awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtAerialLost();

                break;
            case "Successful_Dribbles":
            	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtSuccessfulDribblePercent();
            	homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtSuccessfulDribblePercent();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtSuccessfulDribblePercent();
	    		awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtSuccessfulDribblePercent();

                break;
            case "Duel_won":
            	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtDuelwonPercent();
            	homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtDuelwonPercent();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtDuelwonPercent();
	    		awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtDuelwonPercent();
               break;
            case "Duel":
            	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtDuelLost();
                homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtDuelLost();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtDuelLost();
                awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtDuelLost();

               break;
            case "passes_final_3rd_Accuracy":
            	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtFinalThirdPassingAccuracy();
            	homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtFinalThirdPassingAccuracy();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtFinalThirdPassingAccuracy();
	    		awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtFinalThirdPassingAccuracy();
               break;
            case "Final_3rd_Entries":
            	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtFinalThirdEntries();
                homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtFinalThirdEntries();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtFinalThirdEntries();
                awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtFinalThirdEntries();

                break;
            case "Touches_In_OppBox":
            	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtTouchesInOppBox();
            	homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtTouchesInOppBox();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtTouchesInOppBox();
	    		awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtTouchesInOppBox();
	    		
               break;
            case "Final_Third_Passes":
            	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtTotalFinalThirdPasses();
            	homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtTotalFinalThirdPasses();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtTotalFinalThirdPasses();
	    		awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtTotalFinalThirdPasses();

                break;
            case "Accurate_Pass":
            	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtAccuratePass();
            	homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtAccuratePass();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtAccuratePass();
	    		awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtAccuratePass();

            	break;
            case "Tackles_Won":
            	homeHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getHtWonTackle();
                homeFtPossession =  api_match.getApi_LiveMatch().getAwayTeam().getHtWonTackle();
	    		awayHtPossession =  api_match.getApi_LiveMatch().getHomeTeam().getFtWonTackle();
	    		awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtWonTackle();              
		        break;
            case "long_Pass":
        	    homeHtPossession = api_match.getApi_LiveMatch().getHomeTeam().getHtlongPassOwnToOpp();
	    		homeFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getHtlongPassOwnToOpp();
		        awayHtPossession = api_match.getApi_LiveMatch().getHomeTeam().getFtlongPassOwnToOpp();
	    		awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtlongPassOwnToOpp();
            	break;
            case "long_Pass_Success":
        	    homeHtPossession = api_match.getApi_LiveMatch().getHomeTeam().getHtlongPassOwnToOppSuccess();
	    		homeFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getHtlongPassOwnToOppSuccess();
		        awayHtPossession = api_match.getApi_LiveMatch().getHomeTeam().getFtlongPassOwnToOppSuccess();
	    		awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtlongPassOwnToOppSuccess();
            	break;
            case "Shots_Inside_Box":
            	homeHtPossession = api_match.getApi_LiveMatch().getHomeTeam().getHtshotsInsideBox();
            	homeFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getHtshotsInsideBox();
            	awayHtPossession = api_match.getApi_LiveMatch().getHomeTeam().getFtshotsInsideBox();
            	awayFtPossession = api_match.getApi_LiveMatch().getAwayTeam().getFtshotsInsideBox();
            	break;
            case "Possession_Won_in_the_Final_Third":
            	homeHtPossession = (api_match.getApi_LiveMatch().getHomeTeam().getHtPossWonAtt3rd());
            	homeFtPossession = (api_match.getApi_LiveMatch().getAwayTeam().getHtPossWonAtt3rd());
            	awayHtPossession = (api_match.getApi_LiveMatch().getHomeTeam().getFtPossWonAtt3rd());
            	awayFtPossession = (api_match.getApi_LiveMatch().getAwayTeam().getFtPossWonAtt3rd());
            	break;
            case "Possession_Won":
        		homeHtPossession = (api_match.getApi_LiveMatch().getHomeTeam().getHtPossWonAtt3rd() + 
            			api_match.getApi_LiveMatch().getHomeTeam().getHtPossWonMid3rd() + 
            			api_match.getApi_LiveMatch().getHomeTeam().getHtPossWonDef3rd());
        		homeFtPossession = (api_match.getApi_LiveMatch().getAwayTeam().getHtPossWonAtt3rd() + 
		        		api_match.getApi_LiveMatch().getAwayTeam().getHtPossWonMid3rd() + 
		        		api_match.getApi_LiveMatch().getAwayTeam().getHtPossWonDef3rd() );
        		awayHtPossession = (api_match.getApi_LiveMatch().getHomeTeam().getFtPossWonAtt3rd() + 
            			api_match.getApi_LiveMatch().getHomeTeam().getFtPossWonMid3rd() + 
            			api_match.getApi_LiveMatch().getHomeTeam().getFtPossWonDef3rd());
        		awayFtPossession = (api_match.getApi_LiveMatch().getAwayTeam().getFtPossWonAtt3rd() + 
		        		api_match.getApi_LiveMatch().getAwayTeam().getFtPossWonMid3rd() + 
		        		api_match.getApi_LiveMatch().getAwayTeam().getFtPossWonDef3rd() );
            	break;
			}
			dataList.add(homeHtPossession + "," + "FIRST" + "," + homeFtPossession);
			dataList.add(awayHtPossession + "," +"SECOND" + "," + awayFtPossession);
		return dataList;
		
	}	

	public static List<String> MatchStatsPlayer(ApiPlayerStats apiPlayerStats,String values) {
        List<String> dataList = new ArrayList<>();
        String Stat1="",Stat2="",Stat3="";
        
		for (int i = 0; i < values.split(",").length; i++) {
			switch ( values.split(",")[i]) {
		    case "touches":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getTouches());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getTouches());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getTouches());
		        break;
		    case "accuratePass":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getTotalAccuratePass());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getTotalAccuratePass());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getTotalAccuratePass());
		        break;
		    case "saves":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getSaves());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getSaves());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getSaves());
		        break;
		    case "totalPass":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getTotalPass());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getTotalPass());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getTotalPass());
		        break;
		    case "ballRecovery":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getBallRecovery());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getBallRecovery());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getBallRecovery());
		        break;
		    case "duelWon":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getDuelWon());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getDuelWon());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getDuelWon());
		        break;
		    case "fouls":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getFoul());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getFoul());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getFoul());
		        break;
		    case "aerialWon":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getAerialWon());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getAerialWon());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getAerialWon());
		        break;
		    case "aerialLost":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getAerialLost());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getAerialLost());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getAerialLost());
		        break;
		    case "interceptions":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getInterception());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getInterception());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getInterception());
		        break;
		    case "interceptionsWon":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getInterceptionWon());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getInterceptionWon());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getInterceptionWon());
		        break;
		    case "totalClearance":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getTotalClearance());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getTotalClearance());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getTotalClearance());
		        break;
		    case "dribbleWon":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getWonContest());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getWonContest());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getWonContest());
		        break;
		    case "wonTackle":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getWonTackle());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getWonTackle());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getWonTackle());
		        break;
		    case "cornerTaken":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getCornerTaken());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getCornerTaken());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getCornerTaken());
		        break;
		    case "wonCorners":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getWonCorners());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getWonCorners());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getWonCorners());
		        break;
		    case "dribbles":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getDribbles());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getDribbles());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getDribbles());
		        break;
		    case "dribblesWon":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getSuccessfulDribbles());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getSuccessfulDribbles());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getSuccessfulDribbles());
		        break;
		    case "totalCross":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getTotalCross());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getTotalCross());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getTotalCross());
		        break;
		    case "Offside":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getTotalOffside());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getTotalOffside());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getTotalOffside());
		        break;
		    case "turnover":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getTurnover());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getTurnover());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getTurnover());
		        break;
		    case "minsPlayed":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getMinsPlayed());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getMinsPlayed());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getMinsPlayed());
		        break;
		    case "totalThrows":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getTotalThrows());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getTotalThrows());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getTotalThrows());
		        break;
		    case "Shots":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getTotalShots());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getTotalShots());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getTotalShots());
		        break;
		    case "ShotOnTarget":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getShotOnTarget());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getShotOnTarget());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getShotOnTarget());
		        break;
		    case "goals":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getGoal());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getGoal());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getGoal());
		        break;
		    case "duelLost":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getDuelLost());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getDuelLost());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getDuelLost());
		        break;
		    case "effectiveClearance":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getEffectiveClearance());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getEffectiveClearance());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getEffectiveClearance());
		        break;
		    case "totalTackle":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getTotalTackle());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getTotalTackle());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getTotalTackle());
		        break;
		    case "totalFinalThirdPasses":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getTotalFinalThirdPasses());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getTotalFinalThirdPasses());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getTotalFinalThirdPasses());
		        break;
		    case "possWonAtt3rd":case "possWonInFinalThird":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getPossWonAtt3rd());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getPossWonAtt3rd());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getPossWonAtt3rd());
		        break;
		    case "possWonMid3rd":
		    	if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getPossWonMid3rd());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getPossWonMid3rd());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getPossWonMid3rd());		    	
		    	break;
		    case "possWonDef3rd":
		    	if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getPossWonDef3rd());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getPossWonDef3rd());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getPossWonDef3rd());
		    	break;
		    case "LongPass": 
		    	if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getLongPassOwnToOpp());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getLongPassOwnToOpp());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getLongPassOwnToOpp());
		    	break;
		    case "LongPassSucess":
		    	if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getLongPassOwnToOppSuccess());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getLongPassOwnToOppSuccess());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getLongPassOwnToOppSuccess());
		    	break;
		    case "unsuccessfulTouches":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getUnsuccessfulTouch());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getUnsuccessfulTouch());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getUnsuccessfulTouch());
		        break;
		    case "ChanceCreated":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getChanceCreated());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getChanceCreated());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getChanceCreated());
		        break;
		    case "Assist":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getAssists());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getAssists());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getAssists());
		        break;
		    case "blockedScoringAtt":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getBlockedScoringAtt());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getBlockedScoringAtt());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getBlockedScoringAtt());
		        break;
		    case "goalsConceded":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getGoalsConceded());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getGoalsConceded());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getGoalsConceded());
		        break;
			}
			
		}
		dataList.add(values.split(",")[0]+","+Stat1);
		dataList.add(values.split(",")[1]+","+Stat2);
		dataList.add(values.split(",")[2]+","+Stat3);
		
		return dataList;

	}

	public static String ChangedHeader(List<HeaderText> headerText, String header) {
	    List<String> updatedHeaders = new ArrayList<>();
	    
	    for (String str : header.split(",")) {
	        boolean found = false;
	        for (HeaderText txt : headerText) {
	            if (str.equalsIgnoreCase(txt.getHeader()) && txt.getChangeHeader() != null) {
	                updatedHeaders.add(txt.getChangeHeader());
	                found = true;
	                break;
	            }
	        }
	        if (!found) {
	            updatedHeaders.add(str);
	        }
	    }
	    
	    return String.join(",", updatedHeaders);
	}

	public static List<Stat> GoalTally() throws Exception{
		List<Stat> plyer = new ArrayList<Stat>();
		if(new File("C:\\Sports\\Football\\Statistic\\Match_Data\\seasonalRanking.json").exists()) {
			rankings ranking = new ObjectMapper().readValue(new File("C:\\Sports\\Football\\Statistic\\Match_Data\\seasonalRanking.json"), rankings.class);
			if (ranking != null && ranking.getTeam()!= null) {
	        	for(teamData team :ranking.getTeam() ) {
	        		if(team.getStat()!= null) {
        				for(Stat st:team.getStat()) {
        					if (st.getType().equalsIgnoreCase("total goals")) {
	        					plyer.add(new Stat(HtmlUtils.htmlEscape(team.getName()),team.getId(),st.getValue()));
	        				}
        				}
	        		}
	        	}
			}
		}
        plyer.sort((st1, st2) -> Integer.compare(Integer.valueOf(st2.getValue()), Integer.valueOf(st1.getValue())));
		return plyer;
	}

	public static List<Stat> SeasonalDataTeam(String Stats ,String Type, String TeamId) throws Exception {
		List<Stat> plyer = new ArrayList<Stat>();
		if(new File("C:\\Sports\\Football\\Statistic\\Match_Data\\seasonalRanking.json").exists()) {
			rankings ranking = new ObjectMapper().readValue(new File("C:\\Sports\\Football\\Statistic\\Match_Data\\seasonalRanking.json"), rankings.class);
			if (ranking != null && ranking.getTeam()!= null) {
	        	for(teamData team :ranking.getTeam() ) {
	        		if(team.getId().equalsIgnoreCase(TeamId)){
	        			if(team.getStat()!=null) {
	        				for(Stat st:team.getStat()) {
	        					for(String stats : Stats.split(",")) {
	        						switch(stats) {
				        			case "Passes":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total pass")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total pass ranking")) {
				        						 plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Successful Passes":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total accurate pass")) {
				        						plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total accurate pass ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Crosses":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total crosses")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total crosses ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Successful Crosses":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total accurate cross")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total accurate cross ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Duels Won":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total duels won")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total duels won ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Duels Lost":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total duels lost")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total duels lost ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Shots":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total scoring att")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total scoring att ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Shots on Target":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total ontarget scoring att")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total ontarget scoring att ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Tackles":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total tackle")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total tackle ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Tackles Won":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total won tackle")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total won tackle ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Dribble":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total contest")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total contest ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Dribble Won":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total won contest")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total won contest ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Clean Sheet":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total goals conceded")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total goals conceded ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Won Corners":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total won corners card")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total won corners ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Lost Corners":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total lost corners")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total lost corners ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Touches in Opp. Box":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total touches in opposition box")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total touches in opposition box ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Clearance":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total clearance")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total clearance ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Fouls":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total fouls")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total fouls ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Offsides":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total offside")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total offside ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Attempts InsideBox":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total attempts ibox")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total attempts ibox ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Goals":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total goals")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total goals ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Yellow Card":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total yellow card")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total yellow card ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			case "Red Card":
				        				switch(Type) {
				        				case "Total":
				        					if (st.getType().equalsIgnoreCase("total red card")) {
					        					plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				case "Rank":
				        					 if(st.getType().equalsIgnoreCase("total red card ranking")) {
						        				plyer.add(new Stat(stats ,st.getValue()));
					        				}
				        					break;
				        				}
				        				break;
				        			}
	        					}
		        			}
	        			 }
	        		  }	
	        	   }
	        	}
	    	}
		return plyer;		
	}

	public static List<Stat> SeasonalDataPlayer(String Stats ,String Type, String TeamId,String PlayerId) throws Exception {
		List<Stat> plyer = new ArrayList<Stat>();
		if(new File("C:\\Sports\\Football\\Statistic\\Match_Data\\seasonalRanking.json").exists()) {
			rankings ranking = new ObjectMapper().readValue(new File("C:\\Sports\\Football\\Statistic\\Match_Data\\seasonalRanking.json"), rankings.class);
			if (ranking != null && ranking.getTeam()!= null) {
	        	for(teamData team :ranking.getTeam() ) {
	        		if(team.getId().equalsIgnoreCase(TeamId)){
	        			for(TeamPlayerRanking ply : team.getPlayer()) {
		        			if(ply.getStat()!= null && ply.getId().equalsIgnoreCase(PlayerId)) {
			        			if(ply.getStat()!= null) {
			        				for(Stat st:ply.getStat()) {
			        					for(String stats : Stats.split(",")) {
			        						switch(stats) {
						        			case "Passes":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total pass")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total pass ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Successful Passes":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total accurate pass")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total accurate pass ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Crosses":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total crosses")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total crosses ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Successful Crosses":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total accurate cross")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total accurate cross ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Duels Won":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total duels won")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total duels won ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Duels Lost":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total duels lost")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total duels lost ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Shots":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total scoring att")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total scoring att ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Shots on Target":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total ontarget scoring att")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total ontarget scoring att ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Tackles":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total tackle")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total tackle ranking")) {
									        			plyer.add(new Stat(stats ,st.getValue()));
						        					 }
						        					break;
						        				}
						        				break;
						        			case "Tackles Won":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total won tackle")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total won tackle ranking")) {
									        			plyer.add(new Stat(stats ,st.getValue()));
								        			}
						        					break;
						        				}
						        				break;
						        			case "Dribble":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total contest")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total contest ranking")) {
									        			plyer.add(new Stat(stats ,st.getValue()));
								        			 }
						        					break;
						        				}
						        				break;
						        			case "Dribble Won":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total won contest")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total won contest ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Red card":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total red card")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total red card ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Yellow card":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total yellow card")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total yellow card ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Clean Sheet":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total goals conceded")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total goals conceded ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Won Corners":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total won corners")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total won corners ranking")) {
									        			plyer.add(new Stat(stats ,st.getValue()));
								        			}
						        					break;
						        				}
						        				break;
						        			case "Lost Corners":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total lost corners")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total lost corners ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				 }
						        					break;
						        				}
						        				break;
						        			case "Touches in Opp. Box":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total touches in opposition box")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total touches in opposition box ranking")) {
									        			plyer.add(new Stat(stats ,st.getValue()));
								        			  }
						        					break;
						        				}
						        				break;
						        			case "Clearance":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total clearance")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total clearance ranking")) {
									        			plyer.add(new Stat(stats ,st.getValue()));
								        			  }
						        					break;
						        				}
						        				break;
						        			case "Fouls":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total fouls")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total fouls ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				 }
						        					break;
						        				}
						        				break;
						        			case "Offsides":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total offside")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total offside ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Attempts InsideBox":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total attempts ibox")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total attempts ibox ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Assists":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total assists")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total assists ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Interception":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total interception")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total interception ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Saves":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total saves")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total saves ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Aerial Won":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total aerial won")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total aerial won ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Aerial Lost":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total aerial lost")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total aerial lost ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Long Balls":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total long balls")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total long balls ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Effective Clearance":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total effective clearance")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total effective clearance ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Keeper Throws":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total keeper throws")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total keeper throws ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Accurate Keeper Throws":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total accurate keeper throws")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total accurate keeper throws ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			case "Goals":
						        				switch(Type) {
						        				case "Total":
						        					if (st.getType().equalsIgnoreCase("total goals")) {
							        					plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				case "Rank":
						        					 if(st.getType().equalsIgnoreCase("total goals ranking")) {
								        				plyer.add(new Stat(stats ,st.getValue()));
							        				}
						        					break;
						        				}
						        				break;
						        			}
			        					}
				        			}
			        			}
		        		    }
	        			 }
	        		  }	
	        	   }
	        	}
	    	}
		return plyer;		
	}

	public static List<PlayerData> SeasonalData(String Type) throws Exception {
		List<PlayerData>  Player = new ArrayList<PlayerData>();
		if(new File("C:\\Sports\\Football\\Statistic\\Match_Data\\seasonalRanking.json").exists()) {
			rankings ranking = new ObjectMapper().readValue(new File("C:\\Sports\\Football\\Statistic\\Match_Data\\seasonalRanking.json"), rankings.class);
			if (ranking != null && ranking.getTeam()!= null) {
	        	for(teamData team :ranking.getTeam() ) {
	        		for(TeamPlayerRanking ply : team.getPlayer()) {
	        			PlayerData  plyer = new PlayerData();
	        			if(ply.getStat()!=null) {
	        				for(Stat st:ply.getStat()) {
	        					plyer.setTeamId(team.getId());
	        					plyer.setTeamName(team.getName());
	        					plyer.setId(ply.getId());
		        				plyer.setName(HtmlUtils.htmlEscape(ply.getName()));
		        				
	        					if (st.getType().equalsIgnoreCase("first name")) {
		        					plyer.setFirstName(HtmlUtils.htmlEscape(st.getValue()));
		                        }
	        					if (st.getType().equalsIgnoreCase("last name")) {
	        						plyer.setLastName(HtmlUtils.htmlEscape(st.getValue()));
		                        }
	        					if (st.getType().equalsIgnoreCase("total games")) {
	        						plyer.setMatch(Integer.valueOf(st.getValue()));
		                        }
	        					if (st.getType().equalsIgnoreCase("total mins played")) {
	        						plyer.setMinsPlayed(Integer.valueOf(st.getValue()));
		                        }
	        					if (st.getType().equalsIgnoreCase("total goals conceded")) {
	        						plyer.setGoalconceded(Integer.valueOf(st.getValue()));
		                        }
	        					if (st.getType().equalsIgnoreCase("total saves")) {
	        						plyer.setSaves(Integer.valueOf(st.getValue()));
		                        }
		        				switch(Type) {
			        			case "Most Passes":
			        				if (st.getType().equalsIgnoreCase("total pass")) {
			        					plyer.setValue(Integer.valueOf(st.getValue()));
			        				}else if(st.getType().equalsIgnoreCase("total pass ranking")) {
				        				plyer.setRank(Integer.valueOf(st.getValue()));
			        				}
			        				break;
			        			case "Successful Passes":
			        				if (st.getType().equalsIgnoreCase("total accurate pass")) {
			        					plyer.setValue(Integer.valueOf(st.getValue()));
			        				}else if(st.getType().equalsIgnoreCase("total accurate pass ranking")) {
				        				plyer.setRank(Integer.valueOf(st.getValue()));
			        				}
			        				break;
			        			case "Most crosses":
			        				if (st.getType().equalsIgnoreCase("total crosses")) {
			        					plyer.setValue(Integer.valueOf(st.getValue()));
			        				}else if(st.getType().equalsIgnoreCase("total crosses ranking")) {				        				
				        				plyer.setRank(Integer.valueOf(st.getValue()));
			        				}
			        				break;
			        			case "Most Duels Won":
			        				if (st.getType().equalsIgnoreCase("total duels won")) {
			        					plyer.setValue(Integer.valueOf(st.getValue()));
			        				}else if(st.getType().equalsIgnoreCase("total duels won ranking")) {				        				
				        				plyer.setRank(Integer.valueOf(st.getValue()));
			        				}
			        				break;
			        			case "Most Shots":
			        				if (st.getType().equalsIgnoreCase("total scoring att")) {
			        					plyer.setValue(Integer.valueOf(st.getValue()));
			        				}else if(st.getType().equalsIgnoreCase("total scoring att ranking")) {
				        				plyer.setRank(Integer.valueOf(st.getValue()));
			        				}
			        				break;
			        			case "Most Shots on Target":
			        				if (st.getType().equalsIgnoreCase("total ontarget scoring att")) {
			        					plyer.setValue(Integer.valueOf(st.getValue()));
			        				}else if(st.getType().equalsIgnoreCase( "total ontarget scoring att ranking")) {
				        				plyer.setRank(Integer.valueOf(st.getValue()));
			        				}
			        				break;
			        			case "Most Tackles":
			        				if (st.getType().equalsIgnoreCase("total tackle")) {
			        					plyer.setValue(Integer.valueOf(st.getValue()));
			                        }else if(st.getType().equalsIgnoreCase("total tackle ranking")) {
				        				plyer.setRank(Integer.valueOf(st.getValue()));
			        				}
			        				break;
			        			case "Most Tackles Won":
			        				if (st.getType().equalsIgnoreCase("total won tackle")) {
			        					plyer.setValue(Integer.valueOf(st.getValue()));
			                        }else if(st.getType().equalsIgnoreCase("total won tackle ranking")) {
				        				plyer.setRank(Integer.valueOf(st.getValue()));
			        				}
			        				break;
			        			case "Chance Created":
			        				if (st.getType().equalsIgnoreCase("total att assist")) {
			        					plyer.setValue(Integer.valueOf(st.getValue()));
			                        }else if(st.getType().equalsIgnoreCase("total att assist ranking")) {
				        				plyer.setRank(Integer.valueOf(st.getValue()));
			        				}
			        				break;
			        			case "Clean Sheets": 
			        				if (ply.getPosition()!=null && ply.getPosition().equalsIgnoreCase("Goalkeeper")
			        						&& st.getType().equalsIgnoreCase("total clean sheet")) {
			        					plyer.setValue(Integer.valueOf(st.getValue()));
			                        }else if(ply.getPosition()!=null && ply.getPosition().equalsIgnoreCase("Goalkeeper") && 
			                        		st.getType().equalsIgnoreCase("total clean sheet ranking")) {
				        				plyer.setRank(Integer.valueOf(st.getValue()));
			        				}
			        				break;
			        			case "MOST GOALS":
			        				if (st.getType().equalsIgnoreCase("total goals")) {
			        					plyer.setValue(Integer.valueOf(st.getValue()));
			                        }else if(st.getType().equalsIgnoreCase("total goals ranking")) {
				        				plyer.setRank(Integer.valueOf(st.getValue()));
			        				}
			        				break;
			        			case "MOST ASSISTS":
			        				if (st.getType().equalsIgnoreCase("total assists")) {
			        					plyer.setValue(Integer.valueOf(st.getValue()));
			                        }else if(st.getType().equalsIgnoreCase("total assists ranking")) {
				        				plyer.setRank(Integer.valueOf(st.getValue()));
			        				}
			        				break;
			        			}
		        			}
	        			}
	        			if (Type.equalsIgnoreCase("Clean Sheets")&& ply.getPosition().equalsIgnoreCase("Goalkeeper")) {
                             Player.add(plyer);
	        			}else {
	        				if(plyer.getRank() != 0 && plyer.getRank() >= 1 && plyer.getRank() <= 5) {
	        					 Player.add(plyer);
	        				}
	        			}
	        		}
	        	}
	        }
	    }
		//.thenComparing(Comparator.comparing(PlayerData::getSaves).reversed())
		return Player.stream().sorted(Comparator.comparing(PlayerData::getRank)
        		.thenComparing(PlayerData::getMatch)
                .thenComparing(PlayerData::getMinsPlayed)).limit(5).collect(Collectors.toList());
	}

	public static void setDBNames(List<PlayerData> players, List<Player> allPlayer) {
		 for (Player ply : allPlayer) {
			  for (PlayerData pl : players) {
				  if(ply.getPlayerAPIId().equalsIgnoreCase(pl.getId())) {
					  pl.setName(ply.getFull_name());
					  pl.setFirstName(ply.getFirstname());
					  pl.setLastName(ply.getSurname());
				  }
			  }
		 }
	}

	public static void SetTeam(ApiMatch mtch,Match match) {
	   
	    for(PlayerStats pl: mtch.getTop_Sprints()) {
	    	if (match.getHomeTeam().getTeamName1().equalsIgnoreCase(pl.getTeam_name())) {
	    	    pl.setFirst_name(pl.getFirst_name() + "<sub> " + match.getHomeTeam().getTeamName4() + "</sub>");
	    	} else if (match.getAwayTeam().getTeamName1().equalsIgnoreCase(pl.getTeam_name())) {
	    	    pl.setFirst_name(pl.getFirst_name() + "<sub> " + match.getAwayTeam().getTeamName4() + "</sub>");
	    	}

		  }
	    for(PlayerStats pl: mtch.getTop_Distance()) {
	    	if (match.getHomeTeam().getTeamName1().equalsIgnoreCase(pl.getTeam_name())) {
	    	    pl.setFirst_name(pl.getFirst_name() + "<sub> " + match.getHomeTeam().getTeamName4() + "</sub>");
	    	} else if (match.getAwayTeam().getTeamName1().equalsIgnoreCase(pl.getTeam_name())) {
	    	    pl.setFirst_name(pl.getFirst_name() + "<sub> " + match.getAwayTeam().getTeamName4() + "</sub>");
	    	}

		  }
	    for(PlayerStats pl: mtch.getTop_Speed()) {	    	
	    	if (match.getHomeTeam().getTeamName1().equalsIgnoreCase(pl.getTeam_name())) {
	    	    pl.setFirst_name(pl.getFirst_name() + "<sub> " + match.getHomeTeam().getTeamName4() + "</sub>");
	    	} else if (match.getAwayTeam().getTeamName1().equalsIgnoreCase(pl.getTeam_name())) {
	    	    pl.setFirst_name(pl.getFirst_name() + "<sub> " + match.getAwayTeam().getTeamName4() + "</sub>");
	    	}

		}
	}

	public static void setTopStatsDataplayerName(Match session_match, List<TeamStats> teamStats) {
		for(TeamStats ts: teamStats) {
			for(TopStats tp: ts.getTopStats()) {
				for(PlayerStats plyr:tp.getPlayersStats()) {
					if(plyr.getTeam_name().equalsIgnoreCase(session_match.getHomeTeam().getTeamName5())) {
						plyr.setFirst_name(getPlayerName(plyr.getJerseyNumber(), session_match.getHomeSquad(), session_match.getHomeSubstitutes()));
					}else if(plyr.getTeam_name().equalsIgnoreCase(session_match.getAwayTeam().getTeamName5())) {
						plyr.setFirst_name(getPlayerName(plyr.getJerseyNumber(), session_match.getAwaySquad(), session_match.getAwaySubstitutes()));
					}
				}
			}
		}
	}

	public static void setTopStatsplayerName(Match session_match, List<PlayerStats> teamStats) {
		for(PlayerStats plyr:teamStats) {
			if(plyr.getTeam_name().equalsIgnoreCase(session_match.getHomeTeam().getTeamName5())) {
				plyr.setFirst_name(getPlayerName(plyr.getJerseyNumber(), session_match.getHomeSquad(), session_match.getHomeSubstitutes()));
			}else if(plyr.getTeam_name().equalsIgnoreCase(session_match.getAwayTeam().getTeamName5())) {
				plyr.setFirst_name(getPlayerName(plyr.getJerseyNumber(), session_match.getAwaySquad(), session_match.getAwaySubstitutes()));
			}
		}
	}

	public static String getPlayerName(int playerId, List<Player> squad, List<Player> substitutes) {
        for (Player player : squad) {
            if (player.getJersey_number() == playerId) {
                return player.getTicker_name();
            }
        }
        for (Player player : substitutes) {
            if (player.getJersey_number() == playerId) {
                return player.getTicker_name();
            }
        }
        return "";
    }
}