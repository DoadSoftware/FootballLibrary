package com.football.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "TeamColors")
public class TeamColor {

  @Id
  @Column(name = "ColorId")
  private int colorId;
  
  @Column(name = "ColorType")
  private String colorType;
  
  @Column(name = "RGB")
  private String rgb;
  
public TeamColor() {
	super();
}

public int getColorId() {
	return colorId;
}

public void setColorId(int colorId) {
	this.colorId = colorId;
}

public String getColorType() {
	return colorType;
}

public void setColorType(String colorType) {
	this.colorType = colorType;
}

public String getRgb() {
	return rgb;
}

public void setRgb(String rgb) {
	this.rgb = rgb;
}

}