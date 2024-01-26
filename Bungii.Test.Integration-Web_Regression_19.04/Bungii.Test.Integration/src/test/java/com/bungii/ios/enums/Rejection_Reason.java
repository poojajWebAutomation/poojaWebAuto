package com.bungii.ios.enums;

public enum Rejection_Reason {

	TOO_FAR_AWAY("Too far away"),
	EARNINGS("Earnings"),
	LABOR_REQUIREMENTS("Labor requirements"),
	TYPE_OF_ITEM("Type of item(s)"),
	NOT_ENOUGH_INFORMATION("Not enough information"),
    NOT_AVAILABLE("Not available");

    public final String name;
    Rejection_Reason(String status) {
        this.name = status;
    }
    
    public String toString() {
        return this.name;
     }
}