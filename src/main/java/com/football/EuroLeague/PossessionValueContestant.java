package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PossessionValueContestant {
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("pvCategories")
	private pvCategories pvCategories;
	
	@JsonProperty("lineUp")
	private LineUp LineUp;
	

}
