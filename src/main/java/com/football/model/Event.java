package com.football.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="event")
@XmlAccessorType(XmlAccessType.FIELD)
public class Event implements Comparable<Event> {

  @XmlElement(name = "eventNumber")
  private int eventNumber;

  @XmlElement(name = "eventPlayerId")
  private int eventPlayerId;
  
  @XmlElement(name = "eventMatchHalves")
  private String eventMatchHalves;
  
  @XmlElement(name = "statsId")
  private int statsId;

  @XmlElement(name = "eventLog")
  private String eventLog;
  
  @XmlElement(name = "eventType")
  private String eventType;
  
  @XmlElement(name = "offPlayerId")
  private int offPlayerId;
  
  @XmlElement(name = "onPlayerId")
  private int onPlayerId;

  @XmlElement(name = "eventScore")
  private float eventScore;
  
public Event() {
	super();
}

public Event(int eventNumber, int eventPlayerId, String eventMatchHalves, int statsId, String eventLog,
		String eventType, int offPlayerId, int onPlayerId, float eventScore) {
	super();
	this.eventNumber = eventNumber;
	this.eventPlayerId = eventPlayerId;
	this.eventMatchHalves = eventMatchHalves;
	this.statsId = statsId;
	this.eventLog = eventLog;
	this.eventType = eventType;
	this.offPlayerId = offPlayerId;
	this.onPlayerId = onPlayerId;
	this.eventScore = eventScore;
}


public float getEventScore() {
	return eventScore;
}

public void setEventScore(float eventScore) {
	this.eventScore = eventScore;
}


public String getEventLog() {
	return eventLog;
}

public void setEventLog(String eventLog) {
	this.eventLog = eventLog;
}

public int getEventNumber() {
	return eventNumber;
}

public void setEventNumber(int eventNumber) {
	this.eventNumber = eventNumber;
}

public int getEventPlayerId() {
	return eventPlayerId;
}

public void setEventPlayerId(int eventPlayerId) {
	this.eventPlayerId = eventPlayerId;
}

public String getEventType() {
	return eventType;
}

public void setEventType(String eventType) {
	this.eventType = eventType;
}

public String getEventMatchHalves() {
	return eventMatchHalves;
}

public void setEventMatchHalves(String eventMatchHalves) {
	this.eventMatchHalves = eventMatchHalves;
}

public int getStatsId() {
	return statsId;
}

public void setStatsId(int statsId) {
	this.statsId = statsId;
}

public int getOffPlayerId() {
	return offPlayerId;
}

public void setOffPlayerId(int offPlayerId) {
	this.offPlayerId = offPlayerId;
}

public int getOnPlayerId() {
	return onPlayerId;
}

public void setOnPlayerId(int onPlayerId) {
	this.onPlayerId = onPlayerId;
}

@Override
public int compareTo(Event evnt) {
	return (int) (this.getEventNumber()-evnt.getEventNumber());
}

}