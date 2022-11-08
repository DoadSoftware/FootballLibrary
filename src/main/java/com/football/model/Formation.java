package com.football.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "Formations")
public class Formation {

  @Id
  @Column(name = "FORMID")
  private int formId;
  
  @Column(name = "FormDescription")
  private String formDescription;
  
  @Column(name = "FormStyle")
  private String formStyle;

public Formation() {
	super();
}

public int getFormId() {
	return formId;
}

public void setFormId(int formId) {
	this.formId = formId;
}

public String getFormDescription() {
	return formDescription;
}

public void setFormDescription(String formDescription) {
	this.formDescription = formDescription;
}

public String getFormStyle() {
	return formStyle;
}

public void setFormStyle(String formStyle) {
	this.formStyle = formStyle;
}

}