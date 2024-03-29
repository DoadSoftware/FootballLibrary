package com.football.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.football.model.NameSuper;
import com.football.model.Bugs;
import com.football.dao.FootballDao;
import com.football.model.Officials;
import com.football.model.Fixture;
import com.football.model.Formation;
import com.football.model.Ground;
import com.football.model.Player;
import com.football.model.Playoff;
import com.football.model.Staff;
import com.football.model.Statistics;
import com.football.model.Team;
import com.football.model.TeamColor;
import com.football.model.VariousText;
import com.football.util.FootballUtil;

@Transactional
@Repository("footballDao")
@SuppressWarnings("unchecked")
public class FootballDaoImpl implements FootballDao {

 @Autowired
 private SessionFactory sessionFactory;
 
@Override
public Player getPlayer(String whatToProcess, String valueToProcess) {
	switch (whatToProcess) {
	case FootballUtil.PLAYER:
		return (Player) sessionFactory.getCurrentSession().createQuery("from Player WHERE PlayerId=" + valueToProcess).uniqueResult();  
	default:
		return null;  
	}
}

@Override
public Team getTeam(String whatToProcess, String valueToProcess) {
	switch (whatToProcess) {
	case FootballUtil.TEAM:
		return (Team) sessionFactory.getCurrentSession().createQuery("from Team WHERE TeamId=" + valueToProcess).uniqueResult();  
	default:
		return null;  
	}
}

@Override
public List<NameSuper> getNameSupers() {
	return sessionFactory.getCurrentSession().createQuery("from NameSuper").list();
}

@Override
public List<Team> getTeams() {
	return sessionFactory.getCurrentSession().createQuery("from Team").list();
}

@Override
public List<Player> getPlayers(String whatToProcess, String valueToProcess) {
	switch (whatToProcess) {
	case FootballUtil.TEAM:
		return sessionFactory.getCurrentSession().createQuery("from Player WHERE TeamId=" + valueToProcess).list();  
	default:
		return null;  
	}
}

@Override
public List<Bugs> getBugs() {
	return sessionFactory.getCurrentSession().createQuery("from Bugs").list();
}

@Override
public List<Ground> getGrounds() {
	return sessionFactory.getCurrentSession().createQuery("from Ground").list();  
}

@Override
public List<Playoff> getPlayoffs() {
	return sessionFactory.getCurrentSession().createQuery("from Playoff").list();  
}

@Override
public List<VariousText> getVariousTexts() {
	return sessionFactory.getCurrentSession().createQuery("from VariousText").list();  
}

@Override
public Ground getGround(int ground_id) {
	return (Ground) sessionFactory.getCurrentSession().createQuery("from Ground WHERE GroundId=" + ground_id).uniqueResult();  
}

@Override
public List<Statistics> getAllStats() {
	return sessionFactory.getCurrentSession().createQuery("from Statistics").list();
}

@Override
public List<Player> getAllPlayer() {
	return sessionFactory.getCurrentSession().createQuery("from Player").list();
}

@Override
public List<Fixture> getFixtures() {
	return sessionFactory.getCurrentSession().createQuery("from Fixture").list();
}

@Override
public List<Formation> getFormations() {
	return sessionFactory.getCurrentSession().createQuery("from Formation").list();
}

@Override
public List<TeamColor> getTeamColors() {
	return sessionFactory.getCurrentSession().createQuery("from TeamColor").list();
}

@Override
public List<Staff> getStaff() {
	return sessionFactory.getCurrentSession().createQuery("from Staff").list();
}

@Override
public List<Officials> getOfficials() {
	return sessionFactory.getCurrentSession().createQuery("from Officials").list();
}

}