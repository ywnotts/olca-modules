package org.openlca.simapro.csv.model.pedigreetypes;

public enum GeographicalCorrelation {
	/**
	 * na
	 */
	NA("na", "Unspecified", 1),

	/**
	 * one
	 */
	ONE("1", "Data from area under study", 1),

	/**
	 * two
	 */
	TWO(
			"2",
			"Average data from larger area in which the area under study is included",
			1.01),

	/**
	 * three
	 */
	THREE("3", "Data from area with similar production conditions", 1.02),
	/**
	 * four
	 */
	FOUR("4", "Data from are with slightly similar production conditions", 1),
	/**
	 * five
	 */
	FIVE(
			"5",
			"Data from unknown OR distinctly different area (north america instead of middle east, OECD-Europe instead of Russia)",
			1.1);

	private String key;
	private String value;
	private double indicator;

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public double getIndicator() {
		return indicator;
	}

	private GeographicalCorrelation(String key, String value, double indicator) {
		this.key = key;
		this.value = value;
		this.indicator = indicator;
	}
}
