package com.football.service;

import java.util.List;

import com.football.model.Fixture;
import com.football.model.Formation;
import com.football.model.Ground;
import com.football.model.Player;
import com.football.model.Statistics;
import com.football.model.Team;
import com.football.model.TeamColor;

public interface FootballService {
  Player getPlayer(String whatToProcess, String valueToProcess);
  Team getTeam(String whatToProcess, String valueToProcess);
  Ground getGround(int ground_id);
  List<Player> getPlayers(String whatToProcess, String valueToProcess);
  List<Team> getTeams();
  List<Ground> getGrounds();
  List<Statistics> getAllStats();
  List<Player> getAllPlayer();
  List<Fixture> getFixtures();
  List<Formation> getFormations();
  List<TeamColor> getTeamColors();
}