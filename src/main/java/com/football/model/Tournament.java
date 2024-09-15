package com.football.model;

public class Tournament implements Cloneable {

	private int goals;

public Tournament() {
	super();
}


public int getGoals() {
	return goals;
}

public void setGoals(int goals) {
	this.goals = goals;
}


@Override
public Tournament clone() throws CloneNotSupportedException {
    Tournament clone = null;
    try
    {
        clone = (Tournament) super.clone();
    } 
    catch (CloneNotSupportedException e) 
    {
        throw new RuntimeException(e);
    }
    return clone;
}

}