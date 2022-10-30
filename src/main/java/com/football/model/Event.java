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

  @XmlElement(name = "eventLog")
  private String eventLog;
  
  @XmlElement(name = "eventType")
  private String eventType;

  @XmlElement(name = "eventScore")
  private float eventScore;
  
public Event() {
	super();
}

public Event(int eventNumber, int eventPlayerId, String eventMatchHalves, String eventLog, String eventType,
		float eventScore) {
	super();
	this.eventNumber = eventNumber;
	this.eventPlayerId = eventPlayerId;
	this.eventMatchHalves = eventMatchHalves;
	this.eventLog = eventLog;
	this.eventType = eventType;
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

@Override
public int compareTo(Event evnt) {
	return (int) (this.getEventNumber()-evnt.getEventNumber());
}

}