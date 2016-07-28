package br.com.scoreboard.enums;

public enum LogKillRegex {

	WORLD_KILL("<WORLD> killed (?<killed>\\w*) by (\\w*)"),

	PLAYER_KILL("(?<killer>\\w*) killed (?<killed>\\w*) using (?<weapon>\\w*)");

	private String regex;

	LogKillRegex(String regex) {
		this.regex = regex;
	}

	public String getRegex() {
		return regex;
	}

}
