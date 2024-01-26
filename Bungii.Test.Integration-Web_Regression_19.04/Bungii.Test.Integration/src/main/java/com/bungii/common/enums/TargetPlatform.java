package com.bungii.common.enums;

public enum TargetPlatform {

	    IOS("IOS"),
	    ANDROID("ANDROID");

	    public final String platformName;
	    TargetPlatform(String platformName) {
	        this.platformName = platformName;
	    }
		public String value() {
			return platformName;
		}
		public String toString() {
		return this.platformName;
	}

	}
