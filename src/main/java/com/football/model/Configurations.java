package com.football.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Configurations")
@XmlAccessorType(XmlAccessType.FIELD)
public class Configurations {
	
	@XmlElement(name="filename")
	private String filename;
	
	@XmlElement(name="broadcaster")
	private String broadcaster;
	
	@XmlElement(name="sponsor")
	private String sponsor;
	
	@XmlElement(name="ipAddress")
	private String ipAddress;
	
	@XmlElement(name="portNumber")
	private int portNumber;
	
	@XmlElement(name="secondaryipAddress")
	private String secondaryipAddress;
	
	@XmlElement(name="secondaryportNumber")
	private int secondaryportNumber;
	
	@XmlElement(name="vuipAddress")
	private String vuipAddress;
	
	@XmlElement(name="vuportNumber")
	private int vuportNumber;
	
	@XmlElement(name="vizscene")
	private String vizscene;

	public Configurations() {
		super();
	}

	public Configurations(String broadcaster, String ipAddress, int portNumber, String secondaryipAddress,
			int secondaryportNumber,String vuipAddress, int vuportNumber) {
		super();
		this.broadcaster = broadcaster;
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
		this.secondaryipAddress = secondaryipAddress;
		this.secondaryportNumber = secondaryportNumber;
		this.vuipAddress = vuipAddress;
		this.vuportNumber = vuportNumber;
	}
	
	public Configurations(String broadcaster, String ipAddress, int portNumber, String secondaryipAddress,
			int secondaryportNumber) {
		super();
		this.broadcaster = broadcaster;
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
		this.secondaryipAddress = secondaryipAddress;
		this.secondaryportNumber = secondaryportNumber;
	}

	public String getVizscene() {
		return vizscene;
	}
	public void setVizscene(String vizscene) {
		this.vizscene = vizscene;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getBroadcaster() {
		return broadcaster;
	}
	public void setBroadcaster(String broadcaster) {
		this.broadcaster = broadcaster;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	public String getSecondaryipAddress() {
		return secondaryipAddress;
	}

	public void setSecondaryipAddress(String secondaryipAddress) {
		this.secondaryipAddress = secondaryipAddress;
	}

	public int getSecondaryportNumber() {
		return secondaryportNumber;
	}

	public void setSecondaryportNumber(int secondaryportNumber) {
		this.secondaryportNumber = secondaryportNumber;
	}

	public String getVuipAddress() {
		return vuipAddress;
	}

	public void setVuipAddress(String vuipAddress) {
		this.vuipAddress = vuipAddress;
	}

	public int getVuportNumber() {
		return vuportNumber;
	}

	public void setVuportNumber(int vuportNumber) {
		this.vuportNumber = vuportNumber;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "Configurations [broadcaster=" + broadcaster + ", ipAddress=" + ipAddress + ", portNumber=" + portNumber
				+ ", secondaryipAddress=" + secondaryipAddress + ", secondaryportNumber=" + secondaryportNumber + "]";
	}
	
}
