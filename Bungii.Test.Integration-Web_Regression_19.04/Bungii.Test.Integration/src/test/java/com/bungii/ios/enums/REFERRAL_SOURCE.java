package com.bungii.ios.enums;

public enum REFERRAL_SOURCE {
	NEWS_STORY("News Story"), WORD_OF_MOUTH("Word Of Mouth"), CRAIGSLIST("Craigslist"), FACEBOOK("Facebook"), GOOGLE(
			"Google"), STORE("Store"), ESTATE_SALE("Estate Sale"), APT_COMPLEX("Apt Complex"), OTHER("Other");

	public final String name;

	REFERRAL_SOURCE(String status) {
		this.name = status;
	}

	public String toString() {
		return this.name;
	}
}
