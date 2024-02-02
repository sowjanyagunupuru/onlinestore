package com.OnlineStore.onlineStore.model;

public class LoanData {
	private final String loanReferenceNumber;
	private final String customerName;
	private final String currentROI;
	private final String newROI;

	public LoanData(String loanReferenceNumber, String customerName, String currentROI, String newROI) {
		this.loanReferenceNumber = loanReferenceNumber;
		this.customerName = customerName;
		this.currentROI = currentROI;
		this.newROI = newROI;
	}

	public String getLoanReferenceNumber() {
		return loanReferenceNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCurrentROI() {
		return currentROI;
	}

	public String getNewROI() {
		return newROI;
	}
}
