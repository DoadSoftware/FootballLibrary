package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class  TournamentContestants {
		@JsonProperty("id")
		private String id;
		
		@JsonProperty("name")
	    private String name;
		
		@JsonProperty("shortName")
	    private String shortName;
		
		@JsonProperty("officialName")
	    private String officialName;
		
		@JsonProperty("code")
	    private String code;
		
		@JsonProperty("type")
	    private String type;
		
		@JsonProperty("teamType")
	    private String teamType;
		
		@JsonProperty("countryId")
	    private String countryId;
		
		@JsonProperty("country")
	    private String country;
		
		@JsonProperty("status")
	    private String status;
		
		@JsonProperty("city")
	    private String city;
		
		@JsonProperty("postalAddress")
	    private String postalAddress;
		
		@JsonProperty("addressZip")
	    private String addressZip;
		
		@JsonProperty("founded")
	    private String founded;
		
		@JsonProperty("lastUpdated")
	    private String lastUpdated;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getShortName() {
			return shortName;
		}
		public void setShortName(String shortName) {
			this.shortName = shortName;
		}
		public String getOfficialName() {
			return officialName;
		}
		public void setOfficialName(String officialName) {
			this.officialName = officialName;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getTeamType() {
			return teamType;
		}
		public void setTeamType(String teamType) {
			this.teamType = teamType;
		}
		public String getCountryId() {
			return countryId;
		}
		public void setCountryId(String countryId) {
			this.countryId = countryId;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getPostalAddress() {
			return postalAddress;
		}
		public void setPostalAddress(String postalAddress) {
			this.postalAddress = postalAddress;
		}
		public String getAddressZip() {
			return addressZip;
		}
		public void setAddressZip(String addressZip) {
			this.addressZip = addressZip;
		}
		public String getFounded() {
			return founded;
		}
		public void setFounded(String founded) {
			this.founded = founded;
		}
		public String getLastUpdated() {
			return lastUpdated;
		}
		public void setLastUpdated(String lastUpdated) {
			this.lastUpdated = lastUpdated;
		}
		@Override
		public String toString() {
			return "contestant [id=" + id + ", name=" + name + ", shortName=" + shortName + ", officialName="
					+ officialName + ", code=" + code + ", type=" + type + ", teamType=" + teamType + ", countryId="
					+ countryId + ", country=" + country + ", status=" + status + ", city=" + city + ", postalAddress="
					+ postalAddress + ", addressZip=" + addressZip + ", founded=" + founded + ", lastUpdated="
					+ lastUpdated + "]";
		}
		public  TournamentContestants() {
			super();
			// TODO Auto-generated constructor stub
		}
		
}
