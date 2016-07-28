package br.com.scoreboard.enums;

public enum LogControllMatchRegex {

	START_MATCH("New match (?<id>\\d*) has started"),

	END_MATCH("Match (?<id>\\d*) has ended");

	private String regex;

	LogControllMatchRegex(String regex) {
		this.regex = regex;
	}

	public String getRegex() {
		return regex;
	}

}
