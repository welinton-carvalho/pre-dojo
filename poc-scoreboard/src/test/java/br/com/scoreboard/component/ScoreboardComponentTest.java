package br.com.scoreboard.component;

import java.io.InputStream;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import br.com.scoreboard.ScoreboardLoader;
import br.com.scoreboard.component.impl.ScoreboardComponentImpl;
import br.com.scoreboard.dto.MatchDTO;

public class ScoreboardComponentTest {

	private ScoreboardComponent scoreboardComponent;

	public ScoreboardComponentTest() {

		this.scoreboardComponent = new ScoreboardComponentImpl();

	}

	@Test
	public void loadMatch12KillsTest() throws ParseException,
			IllegalArgumentException, NoSuchFieldException {

		InputStream matchLogInputStream12Kills = ScoreboardLoader.class
				.getClassLoader().getResourceAsStream("log_800_kills.txt");

		MatchDTO match12Kills = this.scoreboardComponent
				.loadMatchInteractions(matchLogInputStream12Kills);

		Assert.assertNotNull(match12Kills);
		
		this.scoreboardComponent.printScoreBoard(match12Kills);


	}

	@Test
	public void loadMatchNoKillsTest() throws ParseException,
			IllegalArgumentException, NoSuchFieldException {

		InputStream matchLogInputStreamNoKills = ScoreboardLoader.class
				.getClassLoader().getResourceAsStream("log_no_kills.txt");

		MatchDTO matchNoKills = this.scoreboardComponent
				.loadMatchInteractions(matchLogInputStreamNoKills);

		Assert.assertNotNull(matchNoKills);

	}

	@Test
	public void loadEmptyMatchLog() throws ParseException {

		InputStream matchEmptyLogInputStream = ScoreboardLoader.class
				.getClassLoader().getResourceAsStream("empty_log.txt");

		this.scoreboardComponent
				.loadMatchInteractions(matchEmptyLogInputStream);

	}

	@Test(expected = NoSuchFieldException.class)
	public void printMatchEmptyLog() throws IllegalArgumentException,
			NoSuchFieldException {

		MatchDTO matchEmptyLog = new MatchDTO();

		this.scoreboardComponent.printScoreBoard(matchEmptyLog);

	}

	@Test(expected = NoSuchFieldException.class)
	public void loadMatchNoEnded() throws IllegalArgumentException,
			NoSuchFieldException {

		InputStream matchInputLogNoEnded = ScoreboardLoader.class
				.getClassLoader().getResourceAsStream("log_no_ended_match.txt");

		MatchDTO matchNoEnded = this.scoreboardComponent
				.loadMatchInteractions(matchInputLogNoEnded);

		this.scoreboardComponent.printScoreBoard(matchNoEnded);

	}

}
