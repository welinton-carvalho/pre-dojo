package br.com.scoreboard;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.scoreboard.component.ScoreboardComponent;
import br.com.scoreboard.component.impl.ScoreboardComponentImpl;
import br.com.scoreboard.dto.MatchDTO;

public class ScoreboardLoader {

	public static final Logger LOGGER = Logger.getAnonymousLogger();

	public static void main(String[] args) {

		try {

			InputStream matchLogInputStream = ScoreboardLoader.class
					.getClassLoader().getResourceAsStream("log.txt");

			ScoreboardComponent scoreboardComponent = new ScoreboardComponentImpl();

			MatchDTO match = scoreboardComponent
					.loadMatchInteractions(matchLogInputStream);

			scoreboardComponent.printScoreBoard(match);

		} catch (Exception exception) {

			LOGGER.log(Level.SEVERE, "Erro ao carregar arquivo de log.",
					exception);

		}

	}

}
