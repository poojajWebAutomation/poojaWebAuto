package com.bungii.ios.enums;

public enum Status {
    EN_ROUTE_TO_PICKUP("EN ROUTE TO PICKUP"),
    ARRIVED_AT_PICKUP("ARRIVED AT PICKUP"),
    LOADING_ITEMS_AT_PICKUP("LOADING ITEMS AT PICKUP"),
	DRIVING_TO_DROP_OFF("DRIVING TO DROP OFF"),
	UNLOADING_ITEMS_AT_DROP_OFF("UNLOADING ITEMS AT DROP-OFF"),
    LOADING_ITEMS("LOADING ITEMS"),
    DRIVING_TO_DROPOFF("DRIVING TO DROP-OFF"),
    UNLOADING_ITEMS("UNLOADING ITEMS"),
    EN_ROUTE("EN ROUTE"),
    ARRIVED("ARRIVED"),
    UNLOADING_ITEM("UNLOADING ITEM"),
    LOADING_ITEM("LOADING ITEM");

    public final String name;
    Status(String status) {
        this.name = status;
    }
    
    public String toString() {
        return this.name;
     }
}