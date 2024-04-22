package com.football.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.football.model.Configurations;
import com.football.model.Fixture;
import com.football.model.Match;
import com.football.model.MatchStats;
import com.football.model.Player;
import com.football.model.PlayerStats;
import com.football.model.Team;
import com.football.model.TeamStats;
import com.football.model.TopStats;
import com.football.service.FootballService;

public class FootballFunctions {
	
public static String FTPImageDownload(int port,int match_number,String user,String pass,String player_map_type,Configurations config) {
		
		FTPClient ftpClient = new FTPClient();
		try {
			 
            ftpClient.connect(FootballUtil.FTP_SERVER_LINK, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: using retrieveFile(String, OutputStream)
            //String remoteFile1 = FileExtractionUtil.SPORTVUSTATISTIC + FileExtractionUtil.ZIP;
            //File downloadFile1 = new File(FileExtractionUtil.FOOTBALL_SPORTS_DIRECTORY + FileExtractionUtil.STATISTIC_DIRECTORY + remoteFile1);
            String remoteFile1 = player_map_type + ".jpg";
            File downloadFile1 = new File(FootballUtil.FOOTBALL_DIRECTORY + FootballUtil.STATISTIC_DIRECTORY + 
            		FootballUtil.MATCH_DATA_DIRECTORY + remoteFile1);
//            File downloadFile1 = new File("\\\\"+config.getIpAddress() + "\\c\\Sports\\Football\\" + FootballUtil.STATISTIC_DIRECTORY + 
//            		FootballUtil.MATCH_DATA_DIRECTORY + remoteFile1);
            
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
		
		long time=0;
		
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
		
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
				new File(FootballUtil.FOOTBALL_DIRECTORY + FootballUtil.STATISTIC_DIRECTORY + FootballUtil.MATCH_DATA_DIRECTORY + 
						FootballUtil.SPORTVUSTATISTIC + FootballUtil.XML_EXTENSION));
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
	                    					
//	                    					System.out.println("TEAM : " + childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(t).getFirstChild()
//	                    						.getNodeValue());
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
	                    				
//	                    				System.out.println("Stat Type = " + childNodes.item(i).getChildNodes().item(j).getChildNodes()
//		                                		.item(k).getAttributes().getNamedItem("Name").getNodeValue());
		                    			
		                    			teamStats.get(teamStats.size()-1).getTopStats().add(new TopStats(childNodes.item(i).getChildNodes().item(j).getChildNodes()
		                                		.item(k).getAttributes().getNamedItem("Name").getNodeValue(), new ArrayList<PlayerStats>()));
	                    				
		                    			for(int l = 0; l < childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().getLength(); l++) {
		                    				
		                    				
		                    				
		                            		if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k)
		                            				.getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE 
		                            				&& childNodes.item(i).getChildNodes().item(j)
		                            				.getChildNodes().item(k).getChildNodes().item(l).getNodeName().equalsIgnoreCase("Result")) {
		                            			
//		                            			System.out.println("TEAM : " + team);
	                                    		teamStats.get(teamStats.size()-1).getTopStats().get(teamStats.get(teamStats.size()-1).getTopStats().size()-1)
	                        					.getPlayersStats().add(new PlayerStats(team));
		                            			
		                                    	for(int m = 0; m < childNodes.item(i).getChildNodes().item(j).getChildNodes()
		                                    			.item(k).getChildNodes().item(l).getChildNodes().getLength(); m++) {
		                                    		
		                                    		if(childNodes.item(i).getChildNodes().item(j).getChildNodes()
		                                    			.item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeType() 
		                                    			== Node.ELEMENT_NODE) {
		                                    			
		                                    			if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes()
			                                            		.item(m).getNodeName().equalsIgnoreCase("PlayerFirstName")) {
		                                    				
//		                                    				System.out.println("PlayerFirstName = " + childNodes.item(i).getChildNodes().item(j).getChildNodes()
//			                                        				.item(k).getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue());
		                                    				
		                                    				teamStats.get(teamStats.size()-1).getTopStats().get(teamStats.get(teamStats.size()-1).getTopStats().size()-1)
                                    						.getPlayersStats().get(teamStats.get(teamStats.size()-1).getTopStats().get(teamStats.get(teamStats.size()-1)
                                    								.getTopStats().size()-1).getPlayersStats().size()-1).setFirst_name(childNodes.item(i).getChildNodes().item(j)
                                    										.getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue());
		                                    				
		                                    			}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes()
		                                            		.item(m).getNodeName().equalsIgnoreCase("PlayerJerseyNumber")) {
		                                    				
//		                                    				System.out.println("PlayerJerseyNumber = " + childNodes.item(i).getChildNodes().item(j).getChildNodes()
//		                                        				.item(k).getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue());
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
		                                    				
//		                                    				System.out.println("Value = " + childNodes.item(i).getChildNodes().item(j).getChildNodes()
//		                                        					.item(k).getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue());
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
}
