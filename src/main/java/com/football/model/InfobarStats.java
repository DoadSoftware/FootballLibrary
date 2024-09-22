package com.football.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "InfobarStats")
public class InfobarStats {

	@Id
	  @Column(name = "Order")
	  private int Order;
		
	  @Column(name = "Prompt")
	  private String Prompt;

	  @Column(name = "MainHeader")
	  private String MainHeader;
	  
	  @Column(name = "SubHeader1")
	  private String SubHeader1;
	  
	  @Column(name = "SubHeader2")
	  private String SubHeader2;

	  @Column(name = "SH1Value1")
	  private String SH1Value1;
	  
	  @Column(name = "SH1Value2")
	  private String SH1Value2;
	  
	  @Column(name = "SH2Value1")
	  private String SH2Value1;
	  
	  @Column(name = "SH2Value2")
	  private String SH2Value2;

	public int getOrder() {
		return Order;
	}

	public void setOrder(int order) {
		Order = order;
	}

	public String getPrompt() {
		return Prompt;
	}

	public void setPrompt(String prompt) {
		Prompt = prompt;
	}

	public String getMainHeader() {
		return MainHeader;
	}

	public void setMainHeader(String mainHeader) {
		MainHeader = mainHeader;
	}

	public String getSubHeader1() {
		return SubHeader1;
	}

	public void setSubHeader1(String subHeader1) {
		SubHeader1 = subHeader1;
	}

	public String getSubHeader2() {
		return SubHeader2;
	}

	public void setSubHeader2(String subHeader2) {
		SubHeader2 = subHeader2;
	}

	public String getSH1Value1() {
		return SH1Value1;
	}

	public void setSH1Value1(String sH1Value1) {
		SH1Value1 = sH1Value1;
	}

	public String getSH1Value2() {
		return SH1Value2;
	}

	public void setSH1Value2(String sH1Value2) {
		SH1Value2 = sH1Value2;
	}

	public String getSH2Value1() {
		return SH2Value1;
	}

	public void setSH2Value1(String sH2Value1) {
		SH2Value1 = sH2Value1;
	}

	public String getSH2Value2() {
		return SH2Value2;
	}

	public void setSH2Value2(String sH2Value2) {
		SH2Value2 = sH2Value2;
	}

	@Override
	public String toString() {
		return "InfobarStats [Order=" + Order + ", Prompt=" + Prompt + ", MainHeader=" + MainHeader + ", SubHeader1="
				+ SubHeader1 + ", SubHeader2=" + SubHeader2 + ", SH1Value1=" + SH1Value1 + ", SH1Value2=" + SH1Value2
				+ ", SH2Value1=" + SH2Value1 + ", SH2Value2=" + SH2Value2 + "]";
	}

	public InfobarStats() {
		super();
	}
	  
}
