package com.football.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.football.model.NameSuper;
import com.football.model.Bugs;
import com.football.model.ExtraData;
import com.football.dao.FootballDao;
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
import com.football.model.Officials;
import com.football.service.FootballService;

@Service("footballService")
@Transactional
public class FootballServiceImpl implements FootballService {

 @Autowired
 private FootballDao footballDao;
 
@Override
public Player getPlayer(String whatToProcess, String valueToProcess) {
	return footballDao.getPlayer(whatToProcess, valueToProcess);
}

@Override
public Team getTeam(String whatToProcess, String valueToProcess) {
	return footballDao.getTeam(whatToProcess, valueToProcess);
}

@Override
public List<Team> getTeams() {
	return footballDao.getTeams();
}

@Override
public List<NameSuper> getNameSupers() {
	return footballDao.getNameSupers();
}

@Override
public List<Player> getPlayers(String whatToProcess, String valueToProcess) {
	return footballDao.getPlayers(whatToProcess, valueToProcess);
}

@Override
public List<Ground> getGrounds() {
	return footballDao.getGrounds();
}

@Override
public List<Playoff> getPlayoffs() {
	return footballDao.getPlayoffs();
}

@Override
public List<VariousText> getVariousTexts() {
	return footballDao.getVariousTexts();
}

@Override
public Ground getGround(int ground_id) {
	return footballDao.getGround(ground_id);
}

@Override
public List<Bugs> getBugs() {
	return footballDao.getBugs();
}

@Override
public List<Statistics> getAllStats() {
	return footballDao.getAllStats();
}

@Override
public List<Player> getAllPlayer() {
	return footballDao.getAllPlayer();
}

@Override
public List<Fixture> getFixtures() {
	return footballDao.getFixtures();
}

@Override
public List<Formation> getFormations() {
	return footballDao.getFormations();
}

@Override
public List<TeamColor> getTeamColors() {
	return footballDao.getTeamColors();
}

@Override
public List<Staff> getStaffs() {
	return footballDao.getStaff();
}

@Override
public List<Officials> getOfficials() {
	return footballDao.getOfficials();
}

@Override
public List<ExtraData> getExtraData() {
	return footballDao.getExtraData();
}

@Override
public List<LeaderBoard> getLeaderBoard() {
	return footballDao.getLeaderBoard();
}

@Override
public List<TeamStat> getTeamStats() {
	return footballDao.getTeamStats();
}

@Override
public List<HeadToHead> getHeadToHeadStats() {
	return footballDao.getHeadToHeadStats();
}

@Override
public List<PlayerStat> getPlayerStats() {
	return footballDao.getPlayerStats();
}

@Override
public List<InfobarStats> getInfobarStats() {
	return footballDao.getInfobarStats();
}

@Override
public List<HeaderText> getHeaderText() {
	return footballDao.getHeaderText();
}

}