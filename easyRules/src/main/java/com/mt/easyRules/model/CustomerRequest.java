package com.mt.easyRules.model;

public class CustomerRequest {
	private String channel;
	private int eipAmount;
	private int fraudScore;
	private int invalidPayments;
	private String risk;

	public CustomerRequest(String channel, int eipAmount, int fraudScore, int invalidPayments) {
		super();
		this.channel = channel;
		this.eipAmount = eipAmount;
		this.fraudScore = fraudScore;
		this.invalidPayments = invalidPayments;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public int getEipAmount() {
		return eipAmount;
	}
	public void setEipAmount(int eipAmount) {
		this.eipAmount = eipAmount;
	}
	public int getFraudScore() {
		return fraudScore;
	}
	public void setFraudScore(int fraudScore) {
		this.fraudScore = fraudScore;
	}
	public int getInvalidPayments() {
		return invalidPayments;
	}
	public void setInvalidPayments(int invalidPayments) {
		this.invalidPayments = invalidPayments;
	}
	public String getRisk() {
		return risk;
	}
	public void setRisk(String risk) {
		this.risk = risk;
	}
	
	@Override
	public String toString() {
		return "CustomerRequest [channel=" + channel + ", eipAmount=" + eipAmount + ", fraudScore=" + fraudScore
				+ ", invalidPayments=" + invalidPayments + ", risk=" + risk + "]";
	}
	
	
}
