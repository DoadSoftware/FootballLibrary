package com.football.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.football.model.Fixture;
import com.football.model.Match;
import com.football.model.MatchStats;
import com.football.model.Player;
import com.football.model.Team;
import com.football.service.FootballService;

public class FootballFunctions {
	
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
	
	public static String calExtraTimeGoal(String half,long number) {
		
		String time_value ="";
		long time=0;
		
		if(half.equalsIgnoreCase("first") && number > 2700) {
			time = ((number - 2700)/60) + 1;
			time_value = "45' (+" + time + "')";
		}else if(half.equalsIgnoreCase("second") && number > 5400) {
			time = ((number - 5400)/60) + 1;
			time_value = "90' (+" + time + "')";
		}else {
			time_value = String.valueOf((number/60)+1) + "'" ;
		}
		return time_value;
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
}
