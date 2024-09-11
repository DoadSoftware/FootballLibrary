package com.football.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Clock {

  private String matchHalves;
  
  private String matchTimeStatus;

  private long matchTotalMilliSeconds;

public String getMatchHalves() {
	return matchHalves;
}

public void setMatchHalves(String matchHalves) {
	this.matchHalves = matchHalves;
}

public String getMatchTimeStatus() {
	return matchTimeStatus;
}

public void setMatchTimeStatus(String matchTimeStatus) {
	this.matchTimeStatus = matchTimeStatus;
}

public long getMatchTotalMilliSeconds() {
	return matchTotalMilliSeconds;
}

public void setMatchTotalMilliSeconds(long matchTotalMilliSeconds) {
	this.matchTotalMilliSeconds = matchTotalMilliSeconds;
}

}