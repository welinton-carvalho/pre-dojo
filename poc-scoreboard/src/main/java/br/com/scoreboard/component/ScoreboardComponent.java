package br.com.scoreboard.component;

import java.io.InputStream;

import br.com.scoreboard.dto.MatchDTO;

public interface ScoreboardComponent {

	public MatchDTO loadMatchInteractions(final InputStream matchLogInputStream)
			throws IllegalArgumentException;

	void printScoreBoard(MatchDTO match) throws IllegalArgumentException,
			NoSuchFieldException;

}
