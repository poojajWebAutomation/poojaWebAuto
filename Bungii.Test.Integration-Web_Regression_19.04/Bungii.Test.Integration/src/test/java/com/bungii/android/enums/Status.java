package com.bungii.android.enums;

public enum Status {
	EN_ROUTE("EN ROUTE"),
	ARRIVED("ARRIVED"),
	LOADING_ITEM("LOADING ITEM"),
	DRIVING_TO_DROP_OFF("DRIVING TO DROP OFF"),
	UNLOADING_ITEM("UNLOADING ITEM"),
    LOADING_ITEMS("LOADING ITEMS"),
    DRIVING_TO_DROPOFF("DRIVING TO DROP-OFF"),
    UNLOADING_ITEMS("UNLOADING ITEMS");

    public final String name;
    Status(String status) {
        this.name = status;
    }
    
    public String toString() {
        return this.name;
     }
}