package com.football.EuroLeague;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class pvCategories {
	
	@JsonProperty("timeMin")
	private String timeMin;

	@JsonProperty("timeMinSec")
	private String timeMinSec;
	
	@JsonProperty("periodId")
	private String periodId;

	@JsonProperty("totalPossessionValue")
	private TotalPossessionValue totalPossessionValue;
	
	@JsonProperty("totalPlusPV")
    private TotalPlusPV totalPlusPV;
	
	@JsonProperty("totalMinusPV")
    private TotalMinusPV totalMinusPV;
	
	@JsonProperty("totalPlusPass")
    private TotalPlusPass totalPlusPass;
	
	@JsonProperty("totalMinusPass")
    private TotalMinusPass totalMinusPass;
	
	@JsonProperty("totalPlusCross")
    private TotalPlusCross totalPlusCross;
	
	@JsonProperty("totalMinusCross")
    private TotalMinusCross totalMinusCross;
	
	@JsonProperty("totalPlusTakeOns")
    private TotalPlusTakeOns totalPlusTakeOns;
	
	@JsonProperty("totalMinusTakeOn")
    private TotalMinusTakeOn totalMinusTakeOn;
	
	@JsonProperty("TotalPlusRegains")
    private TotalPlusRegains totalPlusRegains;
	
	@JsonProperty("totalMinusLosingPossession")
    private TotalMinusLosingPossession totalMinusLosingPossession;

	@JsonProperty("totalPlusSetPlaysTaken")
    private totalPlusSetPlaysTaken totalPlusSetPlaysTaken;
	
	@JsonProperty("totalMinusSetPlayTaken")
    private totalMinusSetPlayTaken totalMinusSetPlayTaken;
	
	@JsonProperty("totalPlusSetPlaysDrawn")
    private totalPlusSetPlaysDrawn totalPlusSetPlaysDrawn;
	
	@JsonProperty("totalMinusSetPlayConceded")
    private totalMinusSetPlayConceded totalMinusSetPlayConceded;

	
	
	
    // Getters and setters for each category
    public TotalPossessionValue getTotalPossessionValue() {
        return totalPossessionValue;
    }

    public void setTotalPossessionValue(TotalPossessionValue totalPossessionValue) {
        this.totalPossessionValue = totalPossessionValue;
    }

    public TotalPlusPV getTotalPlusPV() {
        return totalPlusPV;
    }

    public void setTotalPlusPV(TotalPlusPV totalPlusPV) {
        this.totalPlusPV = totalPlusPV;
    }

    public TotalMinusPV getTotalMinusPV() {
        return totalMinusPV;
    }

    public void setTotalMinusPV(TotalMinusPV totalMinusPV) {
        this.totalMinusPV = totalMinusPV;
    }

    public TotalPlusPass getTotalPlusPass() {
        return totalPlusPass;
    }

    public void setTotalPlusPass(TotalPlusPass totalPlusPass) {
        this.totalPlusPass = totalPlusPass;
    }

    public TotalMinusPass getTotalMinusPass() {
        return totalMinusPass;
    }

    public void setTotalMinusPass(TotalMinusPass totalMinusPass) {
        this.totalMinusPass = totalMinusPass;
    }

    public TotalPlusCross getTotalPlusCross() {
        return totalPlusCross;
    }

    public void setTotalPlusCross(TotalPlusCross totalPlusCross) {
        this.totalPlusCross = totalPlusCross;
    }

    public TotalMinusCross getTotalMinusCross() {
        return totalMinusCross;
    }

    public void setTotalMinusCross(TotalMinusCross totalMinusCross) {
        this.totalMinusCross = totalMinusCross;
    }

    public TotalPlusTakeOns getTotalPlusTakeOns() {
        return totalPlusTakeOns;
    }

    public void setTotalPlusTakeOns(TotalPlusTakeOns totalPlusTakeOns) {
        this.totalPlusTakeOns = totalPlusTakeOns;
    }

    public TotalMinusTakeOn getTotalMinusTakeOn() {
        return totalMinusTakeOn;
    }

    public void setTotalMinusTakeOn(TotalMinusTakeOn totalMinusTakeOn) {
        this.totalMinusTakeOn = totalMinusTakeOn;
    }

    public TotalPlusRegains getTotalPlusRegains() {
        return totalPlusRegains;
    }

    public void setTotalPlusRegains(TotalPlusRegains totalPlusRegains) {
        this.totalPlusRegains = totalPlusRegains;
    }

    public TotalMinusLosingPossession getTotalMinusLosingPossession() {
        return totalMinusLosingPossession;
    }

    public void setTotalMinusLosingPossession(TotalMinusLosingPossession totalMinusLosingPossession) {
        this.totalMinusLosingPossession = totalMinusLosingPossession;
    }
}

class TotalPossessionValue {
    private String totalPossessionValue;

    // Getter and setter for totalPossessionValue
    public String getTotalPossessionValue() {
        return totalPossessionValue;
    }

    public void setTotalPossessionValue(String totalPossessionValue) {
        this.totalPossessionValue = totalPossessionValue;
    }
}

class TotalPlusPV {
    private String totalPlusPV;

    // Getter and setter for totalPlusPV
    public String getTotalPlusPV() {
        return totalPlusPV;
    }

    public void setTotalPlusPV(String totalPlusPV) {
        this.totalPlusPV = totalPlusPV;
    }
	}
   class TotalMinusLosingPossession {
	    private String TotalMinusLosingPossession;

		public String getTotalMinusLosingPossession() {
			return TotalMinusLosingPossession;
		}

		public void setTotalMinusLosingPossession(String totalMinusLosingPossession) {
			TotalMinusLosingPossession = totalMinusLosingPossession;
		}
	}

	 class TotalPlusRegains {
		    private String TotalPlusRegains;

			public String getTotalPlusRegains() {
				return TotalPlusRegains;
			}

			public void setTotalPlusRegains(String totalPlusRegains) {
				TotalPlusRegains = totalPlusRegains;
			}
	}

	 class TotalMinusTakeOn {
		    private String TotalMinusTakeOn;

			public String getTotalMinusTakeOn() {
				return TotalMinusTakeOn;
			}

			public void setTotalMinusTakeOn(String totalMinusTakeOn) {
				TotalMinusTakeOn = totalMinusTakeOn;
			}
	}

	 class TotalPlusTakeOns {
		    private String TotalPlusTakeOns;

			public String getTotalPlusTakeOns() {
				return TotalPlusTakeOns;
			}

			public void setTotalPlusTakeOns(String totalPlusTakeOns) {
				TotalPlusTakeOns = totalPlusTakeOns;
			}
	}

	 class TotalMinusCross {
		    private String TotalMinusCross;

			public String getTotalMinusCross() {
				return TotalMinusCross;
			}

			public void setTotalMinusCross(String totalMinusCross) {
				TotalMinusCross = totalMinusCross;
			}
	}
	
	 class TotalPlusCross {
		    private String TotalPlusCross;

			public String getTotalPlusCross() {
				return TotalPlusCross;
			}

			public void setTotalPlusCross(String totalPlusCross) {
				TotalPlusCross = totalPlusCross;
			}
	}

	 class TotalMinusPass {
		    private String TotalMinusPass;

			public String getTotalMinusPass() {
				return TotalMinusPass;
			}

			public void setTotalMinusPass(String totalMinusPass) {
				TotalMinusPass = totalMinusPass;
			}
	}

	 class TotalPlusPass {
		    private String TotalPlusPass;

			public String getTotalPlusPass() {
				return TotalPlusPass;
			}

			public void setTotalPlusPass(String totalPlusPass) {
				TotalPlusPass = totalPlusPass;
			}
	}

	 class TotalMinusPV {
		    private String TotalMinusPV;

			public String getTotalMinusPV() {
				return TotalMinusPV;
			}

			public void setTotalMinusPV(String totalMinusPV) {
				TotalMinusPV = totalMinusPV;
			}
	}
	 class totalPlusSetPlaysTaken {
		    private String totalPlusSetPlaysTaken;

			public String gettotalPlusSetPlaysTaken() {
				return totalPlusSetPlaysTaken;
			}

			public void settotalPlusSetPlaysTaken(String totalPlusSet) {
				totalPlusSetPlaysTaken = totalPlusSet;
			}
	}

	 class totalMinusSetPlayTaken {
		    private String totalMinusSetPlayTaken;

			public String gettotalMinusSetPlayTaken() {
				return totalMinusSetPlayTaken;
			}

			public void settotalMinusSetPlayTaken(String totalMinusPass) {
				totalMinusSetPlayTaken = totalMinusPass;
			}
	}

	 class totalPlusSetPlaysDrawn {
		    private String totalPlusSetPlaysDrawn;

			public String getTtotalPlusSetPlaysDrawn() {
				return totalPlusSetPlaysDrawn;
			}

			public void settotalPlusSetPlaysDrawn(String totalPlusPass) {
				totalPlusSetPlaysDrawn = totalPlusPass;
			}
	}

	 class totalMinusSetPlayConceded {
		    private String totalMinusSetPlayConceded;

			public String gettotalMinusSetPlayConceded() {
				return totalMinusSetPlayConceded;
			}

			public void settotalMinusSetPlayConceded(String totalMinusPV) {
				totalMinusSetPlayConceded = totalMinusPV;
			}
	}