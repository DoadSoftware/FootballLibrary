package com.football.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.football.EuroLeague.Card;
import com.football.EuroLeague.Goal;
import com.football.EuroLeague.LiveMatch;
import com.football.EuroLeague.SeasonalStats;
import com.football.EuroLeague.SeasonalTeams;
import com.football.EuroLeague.Stat;
import com.football.EuroLeague.Substitute;
import com.football.EuroLeague.TeamStat;
import com.football.EuroLeague.TopPerformerPlayers;
import com.football.EuroLeague.TopPerformers;
import com.football.EuroLeague.rankings;
import com.football.EuroLeague.PassMatrix;
import com.football.EuroLeague.Players;
import com.football.EuroLeague.MatchPreview;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.football.model.ApiEventStats;
import com.football.model.ApiTeamstats;
import com.football.model.Configurations;
import com.football.model.Fixture;
import com.football.model.LeaderBoard;
import com.football.model.Match;
import com.football.model.MatchStats;
import com.football.model.Player;
import com.football.model.PlayerStat;
import com.football.model.PlayerStats;
import com.football.model.Team;
import com.football.model.TeamStats;
import com.football.model.TopStats;
import com.football.service.FootballService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Element;
public class FootballFunctions {
	public static LiveMatch LiveMatch;	
	public static SeasonalStats SeasonalStats;
	public static rankings rankings;
	public static PassMatrix PassMatrix;
	public static MatchPreview matchPreview;
	
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
	
	public static void DoadWriteCommandToSelectedViz(int SelectedViz, String SendTextIn, List<PrintWriter> print_writers) 
	{
		if(SelectedViz > 0 && SelectedViz <= print_writers.size()) {
			print_writers.get(SelectedViz-1).println(SendTextIn);
		}
	}	
	public static void DoadWriteCommandToAllViz(String SendTextIn, List<PrintWriter> print_writers) 
	{
		for(int i = 0; i < print_writers.size(); i++) {
			print_writers.get(i).println(SendTextIn);
		}
	}
	
	@SuppressWarnings("resource")
	public static List<PrintWriter> processPrintWriter(Configurations config) throws UnknownHostException, IOException
	{
		List<PrintWriter> print_writer = new ArrayList<PrintWriter>();
		
		if(config.getIpAddress() != null && !config.getIpAddress().isEmpty()) {
			print_writer.add(new PrintWriter(new Socket(config.getIpAddress(), 
					config.getPortNumber()).getOutputStream(), true));
		}
		
		if(config.getSecondaryipAddress() != null && !config.getSecondaryipAddress().isEmpty()) {
			print_writer.add(new PrintWriter(new Socket(config.getSecondaryipAddress(), 
					config.getSecondaryportNumber()).getOutputStream(), true));
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
			return "45'(+" + time + "')" ;
		}else if(half.equalsIgnoreCase("second") && number > 5400) {
			time = ((number - 5400)/60) + 1;
			return "90'(+" + time + "')" ;
		}if(half.equalsIgnoreCase("extra1") && number > 6300) {
			time = ((number - 6300)/60) + 1;
			return "105'(+" + time + "')" ;
		}else if(half.equalsIgnoreCase("extra2") && number > 7200) {
			time = ((number - 7200)/60) + 1;
			return "120'(+" + time + "')" ;
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
	
	public static List<TeamStats> getTopStatsDatafromXML(Match match) throws SAXException, IOException, ParserConfigurationException, FactoryConfigurationError {
		
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
				                                    				teamStats.get(teamStats.size()-1).getTopStats().get(teamStats.get(teamStats.size()-1).getTopStats().size()-1)
		                                    						.getPlayersStats().get(teamStats.get(teamStats.size()-1).getTopStats().get(teamStats.get(teamStats.size()-1)
		                                    								.getTopStats().size()-1).getPlayersStats().size()-1).setJerseyNumber(Integer.valueOf(childNodes.item(i).getChildNodes().item(j)
		                                    										.getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue()));
				                                    				
				                                    				
				                                    			}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes()
				                                                		.item(m).getNodeName().equalsIgnoreCase("Value")) {
				                                    				
				                                    				teamStats.get(teamStats.size()-1).getTopStats().get(teamStats.get(teamStats.size()-1).getTopStats().size()-1)
			                                    						.getPlayersStats().get(teamStats.get(teamStats.size()-1).getTopStats().get(teamStats.get(teamStats.size()-1)
			                                    								.getTopStats().size()-1).getPlayersStats().size()-1).setValue(childNodes.item(i).getChildNodes().item(j)
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
	public static void readXml(Match match) {
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
	public static void setXMLDataInMatchApi(Match match) throws Exception {
		List<TeamStats> topStatsData = getTopStatsDatafromXML(match);
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
        Collections.sort(match.getTop_Speed(),new FootballFunctions.PlayerStatsComparator());
        Collections.sort(match.getTop_Distance(),new FootballFunctions.PlayerStatsComparator());
        Collections.sort(match.getTop_Sprints(),new FootballFunctions.PlayerStatsComparator());
        readXml(match);
	}

	public static void setJsonDataInMatchApi(Match match) throws Exception {
		if(new File(FootballUtil.LIVE_DATA).exists()) {
		    LiveMatch liveMatch = new ObjectMapper().readValue(new File(FootballUtil.LIVE_DATA), LiveMatch.class);
			List<PlayerStats> playerStats = new ArrayList<PlayerStats>();
			if (liveMatch != null && liveMatch.getLiveData() != null && liveMatch.getLiveData().getCard() != null) {
			        for (Card card : liveMatch.getLiveData().getCard()) {
				            match.getApi_LiveMatch().getEvents().add(new ApiEventStats(card.getContestantId(),card.getPlayerId(),card.getPlayerName(),card.getTimeMin(),
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
			            }
			        }
			}

		    //goals
		    if(liveMatch != null && liveMatch.getLiveData() != null && liveMatch.getLiveData().getGoal()!=null) {
		    	for(Goal goal: liveMatch.getLiveData().getGoal()) {
		    		 match.getApi_LiveMatch().getEvents().add(new ApiEventStats(goal.getContestantId(),goal.getScorerId(),goal.getScorerName(),goal.getTimeMin(),
		    				 goal.getType(),goal.getPeriodId(),goal.getTimeMinSec()));
		    	}
		    }
		    //substitutes
		    if(liveMatch != null && liveMatch.getLiveData() != null && liveMatch.getLiveData().getSubstitute()!=null) {
		    	for(Substitute subs: liveMatch.getLiveData().getSubstitute()) {		    		
		    		match.getApi_LiveMatch().getEvents().add(new ApiEventStats(subs.getContestantId(),subs.getPlayerOnId(),subs.getPlayerOffId(),subs.getPlayerOnName(),
		    				subs.getPlayerOffName(),subs.getTimeMin(),"SUB",subs.getPeriodId(),0,subs.getTimeMinSec()));

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

		    for (int teamIndex = 0; teamIndex <= 1; teamIndex++) {
		        ApiTeamstats team = (teamIndex == 0) ? match.getApi_LiveMatch().getHomeTeam() : match.getApi_LiveMatch().getAwayTeam();
		        int accuratePass = 0;
		       
		        if (liveMatch != null && liveMatch.getLiveData() != null &&  liveMatch.getLiveData().getLineUp() != null && liveMatch.getLiveData().getLineUp().get(teamIndex) != null &&
		        	    liveMatch.getLiveData().getLineUp().get(teamIndex).getStat() != null) {
		        	for (TeamStat stat : liveMatch.getLiveData().getLineUp().get(teamIndex).getStat()) {
			            String value = stat.getValue().trim() != null ? stat.getValue().trim() : "0";
			            switch (stat.getType()) {
			                case "cornerTaken":
			                    team.setCornerTaken(Integer.parseInt(value));
			                    break;
			                case "fkFoulWon":
			                    team.setFoulsWon(Integer.parseInt(value));
			                    break;
			                case "ontargetScoringAtt":
			                    team.setShotOnTarget(Integer.parseInt(value));
			                    break;
			                case "totalScoringAtt":
			                    team.setShots(Integer.parseInt(value));
			                    break;
			                case "saves":
			                    team.setSaves(Integer.parseInt(value));
			                    break;
			                case "totalCross":
			                    team.setCrosses(Integer.parseInt(value));
			                    break;
			                case "totalPass":
			                    team.setPasses(Integer.parseInt(value));
			                    break;
			                case "accuratePass":
			                    accuratePass = Integer.parseInt(value);
			                    break;
			                case "touches":
			                    team.setTouches(Integer.parseInt(value));
			                    break;
			                case "totalTackle":
			                    team.setTackles(Integer.parseInt(value));
			                    break;
			                case "totalContest":
			                    team.setDribbles(Integer.parseInt(value));
			                    break;
			                case "interception":
			                    team.setInterceptions(Integer.parseInt(value));
			                    break;
			                case "possessionPercentage":
			                	team.setPossession((int) Math.round(Double.valueOf(value)));
			                    break;
			                case "bigChanceCreated":
			                	team.setChancesCreated(Integer.parseInt(value));
			                	break;
			                case "goals":
			                	team.setGoals(Integer.parseInt(value));
			                	break;
			            }
			        }
		        	 for(Players py : liveMatch.getLiveData().getLineUp().get(teamIndex).getPlayer()) {
						if(py.getStat()!=null) {
							for (Stat stat : py.getStat()) {
								 switch (stat.getType()) {
								 case "totalPass":
									 PlayerStats ply = new PlayerStats("");
									 ply.setFirst_name(py.getMatchName());
									 ply.setLast_name(py.getPlayerId());
									 if(teamIndex==0) {
										 ply.setFirst_name(py.getMatchName()+" <sub>"+liveMatch.getMatchInfo().getContestant().get(0).getCode().trim()+"<sub>");
									 }else {
										 ply.setFirst_name(py.getMatchName()+" <sub>"+liveMatch.getMatchInfo().getContestant().get(1).getCode().trim()+"<sub>"); 
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
		        double passingAccuracy = AccuracyPercentage(team.getPasses(), accuratePass);
		        team.setPassingAccuracy((int) Math.round(passingAccuracy));
		    	} 
		    }
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
		            .filter(ply -> ply != null && "Assists".equalsIgnoreCase(ply.getName()))
		            .findAny()
		            .ifPresent(category -> {
		                List<TopPerformerPlayers> topAssists = category.getPlayer() != null ? 
		                    category.getPlayer().stream()
		                    .limit(5)
		                    .collect(Collectors.toList()) 
		                    : Collections.emptyList();
		                match.getTopAssists().addAll(topAssists);
		            });
		        	playerTopPerformers.getPlayerTopPerformers().getRanking().stream()
		            .filter(ply -> ply != null && "Goals".equalsIgnoreCase(ply.getName()))
		            .findAny()
		            .ifPresent(category -> {
		                List<TopPerformerPlayers> topGoals = category.getPlayer() != null ? 
		                    category.getPlayer().stream()
		                        .limit(5)
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
		                        player.setFirst_name((String) map.get("matchName"));
		                        player.setLast_name((String) map.get("id"));
		                        player.setTeam_name((String) map.get("teamid"));
		                        player.setValue((String) map.get("value"));
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
	public static void setjerseyNumberInMatchApi(Match match, List<Player> allPlayer) {
		
			for(Player ply:allPlayer) {
				for(ApiEventStats event:match.getApi_LiveMatch().getEvents()) {
					if(event.getPlayerId().trim().equalsIgnoreCase(ply.getPlayerAPIId())) {
						event.setJerseyNumber(ply.getJersey_number());
					}else if(event.getPlayerOffId()!=null && event.getPlayerOffId().trim().equalsIgnoreCase(ply.getPlayerAPIId())) {
						event.setOffJerseyNumber(ply.getJersey_number());
					}
				}
			}
			System.out.println(match.getApi_LiveMatch().getEvents().toString());

	}

}
