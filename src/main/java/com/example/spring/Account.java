package com.example.spring;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "AccountNumber", "AmountDue", "Amount" })
public class Account {
	@JsonProperty("AccountNumber")
	private String AccountNumber;
	@JsonProperty("AmountDue")
	private double AmountDue;
	@JsonProperty("Amount")
	private double Amount;
	
	public Account() {
		
	}
	public Account(String accountNumber, String amountDue) {
		super();
		AccountNumber = accountNumber;
		AmountDue = Double.parseDouble(amountDue);
	}
	@JsonProperty("AccountNumber")
	public String getAccountNumber() {
		return AccountNumber;
	}
	@JsonProperty("AccountNumber")
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public double getAmountDue() {
		return AmountDue;
	}
	@JsonProperty("AmountDue")
	public String getStringAmountDue() {
		return String.format ("%.2f", this.AmountDue);
	}
	@JsonProperty("AmountDue")
	public void setAmountDue(double amountDue) {
		AmountDue = amountDue;
	}
	public double getAmount() {
		return Amount;
	}
	@JsonProperty("Amount")
	public String getStringAmount() {
		return String.format ("%.2f", this.Amount);
	}
	@JsonProperty("Amount")
	public void setAmount(double amount) {
		Amount = amount;
	}

}
