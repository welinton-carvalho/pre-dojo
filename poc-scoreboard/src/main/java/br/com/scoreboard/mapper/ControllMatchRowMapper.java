package br.com.scoreboard.mapper;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.scoreboard.dto.ControllMatchDTO;
import br.com.scoreboard.enums.LogControllMatchRegex;
import br.com.scoreboard.enums.MatchOperationEnum;

public class ControllMatchRowMapper extends GenericLogRowMapper {

	protected static final String CONTENT_SEPARATOR_PATTERN = " ";

	public ControllMatchRowMapper() {

	}

	public ControllMatchDTO parse(String line) throws ParseException {

		super.preParse(line);

		ControllMatchDTO controllMatch = new ControllMatchDTO();

		controllMatch.setDate(super.getEntryDate());

		for (LogControllMatchRegex regex : LogControllMatchRegex.values()) {

			Matcher matcher = Pattern.compile(regex.getRegex()).matcher(
					super.getEntryContent());

			String matchId;

			if (matcher.find()) {

				switch (regex) {

				case START_MATCH:

					controllMatch.setMatchOperation(MatchOperationEnum.STARTED);

					matchId = matcher.group("id");

					controllMatch.setId(Long.valueOf(matchId));

					break;

				case END_MATCH:

					controllMatch.setMatchOperation(MatchOperationEnum.ENDED);

					matchId = matcher.group("id");

					controllMatch.setId(Long.valueOf(matchId));

					break;

				default:
					break;

				}

			}

		}

		if (controllMatch.getId() == 0
				|| controllMatch.getMatchOperation() == null) {

			throw new IllegalArgumentException(
					"Nao foi possivel indentificar o conteudo do log.");

		}

		return controllMatch;

	}

}
