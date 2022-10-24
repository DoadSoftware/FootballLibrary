package com.football.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.football.dao.FootballDao;
import com.football.model.Fixture;
import com.football.model.Ground;
import com.football.model.Player;
import com.football.model.Statistics;
import com.football.model.Team;
import com.football.service.FootballService;

@Service("footballService")
@Transactional
public class FootballServiceImpl implements FootballService {

 @Autowired
 private FootballDao cricketDao;
 
@Override
public Player getPlayer(String whatToProcess, String valueToProcess) {
	return cricketDao.getPlayer(whatToProcess, valueToProcess);
}

@Override
public Team getTeam(String whatToProcess, String valueToProcess) {
	return cricketDao.getTeam(whatToProcess, valueToProcess);
}

@Override
public List<Team> getTeams() {
	return cricketDao.getTeams();
}

@Override
public List<Player> getPlayers(String whatToProcess, String valueToProcess) {
	return cricketDao.getPlayers(whatToProcess, valueToProcess);
}

@Override
public List<Ground> getGrounds() {
	return cricketDao.getGrounds();
}

@Override
public Ground getGround(int ground_id) {
	return cricketDao.getGround(ground_id);
}

@Override
public List<Statistics> getAllStats() {
	return cricketDao.getAllStats();
}

@Override
public List<Player> getAllPlayer() {
	return cricketDao.getAllPlayer();
}

@Override
public List<Fixture> getFixtures() {
	return cricketDao.getFixtures();
}

}