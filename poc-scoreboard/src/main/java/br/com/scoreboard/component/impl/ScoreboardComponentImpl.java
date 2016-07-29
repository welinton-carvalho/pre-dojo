package br.com.scoreboard.component.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import br.com.scoreboard.component.ScoreboardComponent;
import br.com.scoreboard.dto.ControllMatchDTO;
import br.com.scoreboard.dto.EntryMatchDTO;
import br.com.scoreboard.dto.KillDTO;
import br.com.scoreboard.dto.MatchDTO;
import br.com.scoreboard.dto.PlayerDTO;
import br.com.scoreboard.reader.MatchReader;
import br.com.scoreboard.validator.MatchValidator;

public class ScoreboardComponentImpl implements ScoreboardComponent {

	private static final String LINE_DELIMITER = "\\||\\n";

	private MatchValidator matchValidator;

	public ScoreboardComponentImpl() {

		this.matchValidator = new MatchValidator();

	}

	@Override
	public MatchDTO loadMatchInteractions(
			final InputStream matchLogFileInputStream)
			throws IllegalArgumentException {

		Scanner scanner = new Scanner(matchLogFileInputStream);

		scanner.useDelimiter(LINE_DELIMITER);

		MatchDTO match = new MatchDTO();

		MatchReader matchReader = new MatchReader();

		int countKillLines = 0;

		while (scanner.hasNextLine()) {

			String line = (String) scanner.nextLine();

			if (line.isEmpty()) {
				continue;
			}

			EntryMatchDTO entryMatch = matchReader.loadLine(line);

			if (entryMatch instanceof ControllMatchDTO) {

				match.addMatchControllCommandsToScoreboard((ControllMatchDTO) entryMatch);

			}

			if (entryMatch instanceof KillDTO) {

				countKillLines++;

				match.addKillToScoreboard((KillDTO) entryMatch);

			}

		}

		match.setTotalOfKills(countKillLines);

		scanner.close();

		if (match.getTotalOfKills() > 0) {

			this.mountScoreboard(match);

		}

		return match;

	}

	private void mountScoreboard(MatchDTO match) {

		Map<String, KillDTO> ranking = new HashMap<String, KillDTO>();

		List<KillDTO> scoreboardEntries = match.getScoreboard();

		if (scoreboardEntries != null) {

			for (KillDTO killerEntry : scoreboardEntries) {

				PlayerDTO playerKiller = killerEntry.getPlayerKiller();

				ranking.put(playerKiller.getName(), killerEntry);

				PlayerDTO playerKilled = killerEntry.getPlayerKilled();

				if (ranking.get(playerKilled.getName()) == null) {

					ranking.put(playerKilled.getName(),
							new KillDTO(playerKilled, killerEntry.getWeapon(),
									killerEntry.getDate()));

				}

			}

			for (KillDTO killerEntry : scoreboardEntries) {

				PlayerDTO playerKiller = ranking.get(
						killerEntry.getPlayerKiller().getName())
						.getPlayerKiller();
				if (playerKiller != null) {
					playerKiller.incrementQuantityOfKills();
				}

				PlayerDTO playerKilled = ranking.get(
						killerEntry.getPlayerKilled().getName())
						.getPlayerKiller();
				if (playerKilled != null) {
					playerKilled.incrementQuantityOfDeaths();
				}

			}

			ranking.remove("<WORLD>");

		}

		ArrayList<KillDTO> finalScoreboard = new ArrayList<KillDTO>(
				ranking.values());

		match.setScoreboard(finalScoreboard);

	}

	@Override
	public void printScoreBoard(MatchDTO match)
			throws IllegalArgumentException, NoSuchFieldException {

		this.matchValidator.validate(match);

		System.out.println("_______________________________");
		System.out.println("| MATCH ID: "
				+ match.getMatchControllCommands().get(0).getId()
				+ "          |");
		System.out.println("_______________________________");
		System.out.println("| DURATION: " + match.getDuratrion() + "      |");
		System.out.println("_______________________________");
		System.out.println("| TOTAL OF KILS: " + match.getTotalOfKills()
				+ "           |");
		System.out.println("_______________________________");
		System.out.println("|   PLAYER   | KILLS | DEATHS |");
		System.out.println("_______________________________");

		for (KillDTO kill : match.getScoreboard()) {

			this.formatOutputLine(kill.getPlayerKiller().getName(), kill
					.getPlayerKiller().getQuantityOfKills(), kill
					.getPlayerKiller().getQuantityOfDeaths());

		}

	}

	private void formatOutputLine(final String name, final long kills,
			final long deaths) {

		String nameSpace = StringUtils.rightPad(name + "", 12, " ");
		String killsSpace = StringUtils.leftPad(kills + "", 4, " ");
		String deathsSpace = StringUtils.leftPad(deaths + "", 11, " ");

		System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
		System.out.println("| " + nameSpace + killsSpace + deathsSpace + " |");
		System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");

	}

}
