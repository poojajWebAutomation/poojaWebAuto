package com.bungii.common.enums;


public  enum ResultType {
	PASSED("PASSED", 1), FAILED("FAILED", 3), WARNING("WARNING", 2), ERROR(
			"ERROR", 4), DONE("PASSED", 0);

	public final String name;

	/**
	 * Returns ResultType name
	 */
	public String toString() {
		return name;
	}

	public final int level;

	/**
	 * Returns true/false depending on TestCase passed or failed
	 * @return true/false
	 */
	public boolean isPassed() {
		return level <= WARNING.level;
	}

	private ResultType(String n, int l) {
		name = n;
		level = l;
	}
}