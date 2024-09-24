package com.football.util;

import java.awt.image.BufferedImage;
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
import java.util.Map;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.hibernate.mapping.Collection;
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
import com.football.EuroLeague.TeamStat;
import com.football.EuroLeague.TeamsStanding;
import com.football.EuroLeague.TopPerformerPlayers;
import com.football.EuroLeague.TopPerformers;
import com.football.EuroLeague.rankings;
import com.football.EuroLeague.PassMatrix;
import com.football.EuroLeague.Players;
import com.football.EuroLeague.Qualifier;
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
import com.football.model.HeadToHead;
import com.football.model.HeaderText;
import com.football.model.LeaderBoard;
import com.football.model.LeagueTeam;
import com.football.model.Match;
import com.football.model.MatchStats;
import com.football.model.Player;
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
	    		return Integer.compare(pt2.getGD(), pt1.getGD());
	    	}else {
	    		return Integer.compare(pt2.getPoints(), pt1.getPoints());
	    	}
	    }
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
	
	public static List<TeamStats> getTopStatsDatafromXML(ApiMatch match) throws SAXException, IOException, ParserConfigurationException, FactoryConfigurationError {
		
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
			                        	//match.getApi_LiveMatch().getHomeTeam().setDistanceCovered(distanceValue);

			                        }else if(k==2) {
			                        	//match.getApi_LiveMatch().getAwayTeam().setDistanceCovered(distanceValue);
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
	public static void setXMLDataInMatchApi(ApiMatch match) throws Exception {
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
	    }

	    if (match.getTop_Distance() != null && !match.getTop_Distance().isEmpty()) {
	        Collections.sort(match.getTop_Distance(), new FootballFunctions.PlayerStatsComparator());
	    }

	    if (match.getTop_Sprints() != null && !match.getTop_Sprints().isEmpty()) {
	        Collections.sort(match.getTop_Sprints(), new FootballFunctions.PlayerStatsComparator());
	    }

        readXml(match);
	}

	public static void setJsonDataInMatchApi(ApiMatch match) throws Exception {
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
			            if (card.getType().equalsIgnoreCase("YC")) {
			                if (card.getContestantId().equalsIgnoreCase(liveMatch.getMatchInfo().getContestant().get(0).getId())) {
			                    match.getApi_LiveMatch().getHomeTeam().setYellowCards(
			                        match.getApi_LiveMatch().getHomeTeam().getYellowCards() + 1
			                    );
			                } else if (card.getContestantId().equalsIgnoreCase(liveMatch.getMatchInfo().getContestant().get(1).getId())) {
			                    match.getApi_LiveMatch().getAwayTeam().setYellowCards(
			                        match.getApi_LiveMatch().getAwayTeam().getYellowCards() + 1
			                    );
			                }
			            }else  if (card.getType().equalsIgnoreCase("RC")) {
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

		    //goals
		    if(liveMatch != null && liveMatch.getLiveData() != null && liveMatch.getLiveData().getGoal()!=null) {
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
		    match.getApi_LiveMatch().getHomeTeam().setCode(liveMatch.getMatchInfo().getContestant().get(0).getCode().trim());
		    
		    match.getApi_LiveMatch().getAwayTeam().setName(liveMatch.getMatchInfo().getContestant().get(1).getName().trim());
		    match.getApi_LiveMatch().getAwayTeam().setCode(liveMatch.getMatchInfo().getContestant().get(1).getCode().trim());
	       
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
	}
	public static String RoundValues(String values) {
	    String[] parts = values.split(","); 
	    return (parts[0].endsWith(".5") && parts[1].endsWith(".5")) ? (Integer.parseInt(parts[0].split("\\.")[0]) + 1) + "," + parts[1].split("\\.")[0] 
	        : Math.round(Double.parseDouble(parts[0])) + "," + Math.round(Double.parseDouble(parts[1]));
	}
	
	public static void setApiTournament(ApiMatch match)throws Exception{
		if(new File("C:\\Sports\\Football\\Statistic\\Match_Data\\MatchPreview.json").exists()) {
			MatchPreview mp = new ObjectMapper().readValue(new File("C:\\Sports\\Football\\Statistic\\Match_Data\\MatchPreview.json"), MatchPreview.class);
			if(mp!=null && mp.getPreviousMeetingsAnyComp()!=null) {
				match.getApi_LiveMatch().setHomeWin(mp.getPreviousMeetingsAnyComp().getHomeContestantWins());
				match.getApi_LiveMatch().setAwayWin(mp.getPreviousMeetingsAnyComp().getAwayContestantWins());
				match.getApi_LiveMatch().setDraws(mp.getPreviousMeetingsAnyComp().getDraws());
			}
		}
		if(new File("C:\\Sports\\Football\\Statistic\\Match_Data\\TopPerformers.json").exists()) {
			TopPerformers playerTopPerformers = new ObjectMapper().readValue(new File("C:\\Sports\\Football\\Statistic\\Match_Data\\TopPerformers.json"), TopPerformers.class);
	        
			if (playerTopPerformers != null && playerTopPerformers.getPlayerTopPerformers() != null) {
	        	playerTopPerformers.getPlayerTopPerformers().getRanking().stream()
	            .filter(ply -> ply != null && "Assists".equalsIgnoreCase(HtmlUtils.htmlEscape(ply.getName())))
	            .findAny()
	            .ifPresent(category -> {
	                List<TopPerformerPlayers> topAssists = category.getPlayer() != null ? 
	                    category.getPlayer().stream()
	                    .limit(5)
	                    .map(player -> {
                            player.setMatchName(HtmlUtils.htmlEscape(player.getMatchName()));
                            return player;
                        })
	                    .collect(Collectors.toList()) 
	                    : Collections.emptyList();
	                match.getTopAssists().addAll(topAssists);
	            });
	        	playerTopPerformers.getPlayerTopPerformers().getRanking().stream()
	            .filter(ply -> ply != null && "Goals".equalsIgnoreCase(HtmlUtils.htmlEscape(ply.getName())))
	            .findAny()
	            .ifPresent(category -> {
	                List<TopPerformerPlayers> topGoals = category.getPlayer() != null ? 
	                    category.getPlayer().stream()
	                        .limit(5)
	                        .map(player -> {
	                            player.setMatchName(HtmlUtils.htmlEscape(player.getMatchName()));
	                            return player;
	                        })
	                        .collect(Collectors.toList()) 
	                    : Collections.emptyList(); 
	                match.getTopGoals().addAll(topGoals);
	            });
	        }
	    }
		 File jsonFile = new File("C:\\Sports\\Football\\Statistic\\Match_Data\\TeamStats.json");
	        
		 if (jsonFile.exists()) {
	            List<Map<String, Object>> teamStatsList = new ObjectMapper().readValue(jsonFile,
	            		new ObjectMapper().getTypeFactory().constructCollectionType(List.class, Map.class));

	            Collections.sort(teamStatsList, (p1, p2) -> {
	                int goalsConceded1 = Integer.parseInt((String) p1.get("value"));
	                int goalsConceded2 = Integer.parseInt((String) p2.get("value"));
	                return Integer.compare(goalsConceded2, goalsConceded1); 
	            });

	            List<PlayerStats> top5Players = teamStatsList.stream()
	                    .limit(5) 
	                    .map(map -> {
	                    	PlayerStats player = new PlayerStats("");
	                        player.setFirst_name(HtmlUtils.htmlEscape((String) map.get("matchName")));
	                        player.setLast_name(HtmlUtils.htmlEscape((String) map.get("id")));
	                        player.setTeam_name(HtmlUtils.htmlEscape((String) map.get("teamid")));
	                        player.setValue(HtmlUtils.htmlEscape((String) map.get("value")));
	                        return player;
	                    })
	                    .collect(Collectors.toList());
	            match.getGoalConceded().addAll(top5Players);
	        }
		 setXMLDataInMatchApi(match);
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
	            if (event.getPlayerId().trim().equalsIgnoreCase(ply.getPlayerAPIId())) {
	                event.setJerseyNumber(ply.getJersey_number());
	            } else if (event.getPlayerOffId() != null 
	                    && event.getPlayerOffId().trim().equalsIgnoreCase(ply.getPlayerAPIId())) {
	                event.setOffJerseyNumber(ply.getJersey_number());
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
            case "Tackles_won":
		    	home_value = api_match.getApi_LiveMatch().getHomeTeam().getWonTackle();
		        away_value = api_match.getApi_LiveMatch().getAwayTeam().getWonTackle();
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
            case "Tackles_won":
		    	if(time.equalsIgnoreCase("First Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getHtWonTackle();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getHtWonTackle();
		    	}else if(time.equalsIgnoreCase("Second Half")) {
		    		home_value = api_match.getApi_LiveMatch().getHomeTeam().getFtWonTackle();
			        away_value = api_match.getApi_LiveMatch().getAwayTeam().getFtWonTackle();
		    	}
		        break;
			}
			dataList.add(home_value + "," + WhichStyle.replace("_", " ") + "," + away_value);
		}

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
		    case "interception":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getInterception());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getInterception());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getInterception());
		        break;
		    case "interceptionWon":
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
		    case "possWonAtt3rd":
		        if (i == 0) Stat1 = String.valueOf(apiPlayerStats.getPossWonAtt3rd());
		        else if (i == 1) Stat2 = String.valueOf(apiPlayerStats.getPossWonAtt3rd());
		        else if (i == 2) Stat3 = String.valueOf(apiPlayerStats.getPossWonAtt3rd());
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

}


