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
  
  @Column(name = "FormType")
  private String formType;
  
  @Column(name = "FormDescription")
  private String formDescription;
  
  @Column(name = "FormStyle")
  private String formStyle;
  
  @Column(name = "FormOrds1X")
  private int formOrds1X;
  
  @Column(name = "FormOrds1Y")
  private int formOrds1Y;
  
  @Column(name = "FormOrds2X")
  private int formOrds2X;
  
  @Column(name = "FormOrds2Y")
  private int formOrds2Y;
  
  @Column(name = "FormOrds3X")
  private int formOrds3X;
  
  @Column(name = "FormOrds3Y")
  private int formOrds3Y;
  
  @Column(name = "FormOrds4X")
  private int formOrds4X;
  
  @Column(name = "FormOrds4Y")
  private int formOrds4Y;
  
  @Column(name = "FormOrds5X")
  private int formOrds5X;
  
  @Column(name = "FormOrds5Y")
  private int formOrds5Y;
  
  @Column(name = "FormOrds6X")
  private int formOrds6X;
  
  @Column(name = "FormOrds6Y")
  private int formOrds6Y;
  
  @Column(name = "FormOrds7X")
  private int formOrds7X;
  
  @Column(name = "FormOrds7Y")
  private int formOrds7Y;
  
  @Column(name = "FormOrds8X")
  private int formOrds8X;
  
  @Column(name = "FormOrds8Y")
  private int formOrds8Y;
  
  @Column(name = "FormOrds9X")
  private int formOrds9X;
  
  @Column(name = "FormOrds9Y")
  private int formOrds9Y;
  
  @Column(name = "FormOrds10X")
  private int formOrds10X;
  
  @Column(name = "FormOrds10Y")
  private int formOrds10Y;
  
  @Column(name = "FormOrds11X")
  private int formOrds11X;
  
  @Column(name = "FormOrds11Y")
  private int formOrds11Y;

public Formation() {
	super();
}

public int getFormId() {
	return formId;
}

public void setFormId(int formId) {
	this.formId = formId;
}

public String getFormType() {
	return formType;
}

public void setFormType(String formType) {
	this.formType = formType;
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

public int getFormOrds1X() {
	return formOrds1X;
}

public void setFormOrds1X(int formOrds1X) {
	this.formOrds1X = formOrds1X;
}

public int getFormOrds1Y() {
	return formOrds1Y;
}

public void setFormOrds1Y(int formOrds1Y) {
	this.formOrds1Y = formOrds1Y;
}

public int getFormOrds2X() {
	return formOrds2X;
}

public void setFormOrds2X(int formOrds2X) {
	this.formOrds2X = formOrds2X;
}

public int getFormOrds2Y() {
	return formOrds2Y;
}

public void setFormOrds2Y(int formOrds2Y) {
	this.formOrds2Y = formOrds2Y;
}

public int getFormOrds3X() {
	return formOrds3X;
}

public void setFormOrds3X(int formOrds3X) {
	this.formOrds3X = formOrds3X;
}

public int getFormOrds3Y() {
	return formOrds3Y;
}

public void setFormOrds3Y(int formOrds3Y) {
	this.formOrds3Y = formOrds3Y;
}

public int getFormOrds4X() {
	return formOrds4X;
}

public void setFormOrds4X(int formOrds4X) {
	this.formOrds4X = formOrds4X;
}

public int getFormOrds4Y() {
	return formOrds4Y;
}

public void setFormOrds4Y(int formOrds4Y) {
	this.formOrds4Y = formOrds4Y;
}

public int getFormOrds5X() {
	return formOrds5X;
}

public void setFormOrds5X(int formOrds5X) {
	this.formOrds5X = formOrds5X;
}

public int getFormOrds5Y() {
	return formOrds5Y;
}

public void setFormOrds5Y(int formOrds5Y) {
	this.formOrds5Y = formOrds5Y;
}

public int getFormOrds6X() {
	return formOrds6X;
}

public void setFormOrds6X(int formOrds6X) {
	this.formOrds6X = formOrds6X;
}

public int getFormOrds6Y() {
	return formOrds6Y;
}

public void setFormOrds6Y(int formOrds6Y) {
	this.formOrds6Y = formOrds6Y;
}

public int getFormOrds7X() {
	return formOrds7X;
}

public void setFormOrds7X(int formOrds7X) {
	this.formOrds7X = formOrds7X;
}

public int getFormOrds7Y() {
	return formOrds7Y;
}

public void setFormOrds7Y(int formOrds7Y) {
	this.formOrds7Y = formOrds7Y;
}

public int getFormOrds8X() {
	return formOrds8X;
}

public void setFormOrds8X(int formOrds8X) {
	this.formOrds8X = formOrds8X;
}

public int getFormOrds8Y() {
	return formOrds8Y;
}

public void setFormOrds8Y(int formOrds8Y) {
	this.formOrds8Y = formOrds8Y;
}

public int getFormOrds9X() {
	return formOrds9X;
}

public void setFormOrds9X(int formOrds9X) {
	this.formOrds9X = formOrds9X;
}

public int getFormOrds9Y() {
	return formOrds9Y;
}

public void setFormOrds9Y(int formOrds9Y) {
	this.formOrds9Y = formOrds9Y;
}

public int getFormOrds10X() {
	return formOrds10X;
}

public void setFormOrds10X(int formOrds10X) {
	this.formOrds10X = formOrds10X;
}

public int getFormOrds10Y() {
	return formOrds10Y;
}

public void setFormOrds10Y(int formOrds10Y) {
	this.formOrds10Y = formOrds10Y;
}

public int getFormOrds11X() {
	return formOrds11X;
}

public void setFormOrds11X(int formOrds11X) {
	this.formOrds11X = formOrds11X;
}

public int getFormOrds11Y() {
	return formOrds11Y;
}

public void setFormOrds11Y(int formOrds11Y) {
	this.formOrds11Y = formOrds11Y;
}

}