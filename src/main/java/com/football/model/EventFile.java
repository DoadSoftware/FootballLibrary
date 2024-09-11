package com.football.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventFile {

  private ArrayList<Event> events;

public ArrayList<Event> getEvents() {
	return events;
}

public void setEvents(ArrayList<Event> events) {
	this.events = events;
}

}
