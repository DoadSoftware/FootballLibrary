package com.football.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.DiffBuilder;
import org.apache.commons.lang3.builder.DiffResult;
import org.apache.commons.lang3.builder.Diffable;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;


@SuppressWarnings("unused")
@Entity
@Table(name = "Staff")
public class Staff
{
  @Id
  @Column(name = "StaffId")
  private int staffId;

  @Column(name = "NAME")
  private String name;

  @Column(name = "ClubId")
  private int clubId;
  
  @Column(name = "ROLE")
  private String role;

  @Column(name = "NATIONALITY")
  private String nationality;

  public Staff() {
		super();
  }

  public Staff(int staffId) {
	super();
	this.staffId = staffId;
  }

public int getStaffId() {
	return staffId;
}

public void setStaffId(int staffId) {
	this.staffId = staffId;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getClubId() {
	return clubId;
}

public void setClubId(int clubId) {
	this.clubId = clubId;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public String getNationality() {
	return nationality;
}

public void setNationality(String nationality) {
	this.nationality = nationality;
}
  


}