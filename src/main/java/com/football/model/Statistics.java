package com.football.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Statistics")
public class Statistics
{
  @Id
  @Column(name="STATISTICSID")
  private Integer statistics_id;
  
  @Column(name="PLAYERID")
  private Integer player_id;
  
  @Column(name="MATCHES")
  private Integer matches;

public Integer getStatistics_id() {
	return statistics_id;
}

public void setStatistics_id(Integer statistics_id) {
	this.statistics_id = statistics_id;
}

public Integer getPlayer_id() {
	return player_id;
}

public void setPlayer_id(Integer player_id) {
	this.player_id = player_id;
}

public Integer getMatches() {
	return matches;
}

public void setMatches(Integer matches) {
	this.matches = matches;
}
  
}