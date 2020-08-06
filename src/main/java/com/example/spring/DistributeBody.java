package com.example.spring;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "DistributionAmount", "Accounts" })
public class DistributeBody {

    @JsonProperty("DistributionAmount")
    private String DistributionAmount;
    
    @JsonProperty("Accounts")
    private List<Account> Accounts;
    
    @JsonProperty("DistributionType")
    private String DistributionType;

    @JsonProperty("Accounts")
    public List<Account> getAccounts() {
		return Accounts;
	}
    
    @JsonProperty("Accounts")
	public void setAccounts(List<Account> Accounts) {
		this.Accounts = Accounts;
	}

    @JsonProperty("DistributionType")
	public String getDistributionType() {
		return DistributionType;
	}

    @JsonProperty("DistributionType")
	public void setDistributionType(String distributionType) {
		DistributionType = distributionType;
	}

	@JsonProperty("DistributionAmount")
    public String getDistributionAmount() {
        return DistributionAmount;
    }

    @JsonProperty("DistributionAmount")
    public void setDistributionAmount(String DistributionAmount) {
        this.DistributionAmount = DistributionAmount;
    }

}