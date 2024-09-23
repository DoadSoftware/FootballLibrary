package com.football.dao;

import java.util.List;

import com.football.model.NameSuper;
import com.football.model.Officials;
import com.football.model.Bugs;
import com.football.model.ExtraData;
import com.football.model.Fixture;
import com.football.model.Formation;
import com.football.model.Ground;
import com.football.model.HeadToHead;
import com.football.model.HeaderText;
import com.football.model.InfobarStats;
import com.football.model.LeaderBoard;
import com.football.model.Player;
import com.football.model.PlayerStat;
import com.football.model.Playoff;
import com.football.model.Staff;
import com.football.model.Statistics;
import com.football.model.Team;
import com.football.model.TeamColor;
import com.football.model.TeamStat;
import com.football.model.VariousText;

public interface FootballDao {
  Player getPlayer(String whatToProcess, String valueToProcess);
  Team getTeam(String whatToProcess, String valueToProcess);
  Ground getGround(int ground_id);
  List<Player> getPlayers(String whatToProcess, String valueToProcess);
  List<Team> getTeams();
  List<Ground> getGrounds();
  List<Playoff> getPlayoffs();
  List<VariousText> getVariousTexts();
  List<NameSuper> getNameSupers();
  List<Statistics> getAllStats();
  List<Player> getAllPlayer();
  List<Bugs> getBugs();
  List<Fixture> getFixtures();
  List<Formation> getFormations();
  List<TeamColor> getTeamColors();
  List<Staff> getStaff();
  List<Officials> getOfficials();
  List<ExtraData> getExtraData();
  List<LeaderBoard> getLeaderBoard();
  List<TeamStat> getTeamStats();
  List<HeadToHead> getHeadToHeadStats();
  List<PlayerStat> getPlayerStats();
  List<InfobarStats> getInfobarStats();
  List<HeaderText> getHeaderText();
}