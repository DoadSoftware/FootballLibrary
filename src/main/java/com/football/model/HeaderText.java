package com.football.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HeaderText")
public class HeaderText {

	@Id
	  @Column(name = "Order")
	  private int Order;
		
	  @Column(name = "Header")
	  private String Header;

	  @Column(name = "ChangeHeader")
	  private String ChangeHeader;

	public HeaderText() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getOrder() {
		return Order;
	}

	public void setOrder(int order) {
		Order = order;
	}

	public String getHeader() {
		return Header;
	}

	public void setHeader(String header) {
		Header = header;
	}

	public String getChangeHeader() {
		return ChangeHeader;
	}

	public void setChangeHeader(String changeHeader) {
		ChangeHeader = changeHeader;
	}

	@Override
	public String toString() {
		return "HeaderText [Order=" + Order + ", Header=" + Header + ", ChangeHeader=" + ChangeHeader + "]";
	}
}
