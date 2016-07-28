package br.com.scoreboard.validator;

import br.com.scoreboard.dto.MatchDTO;

public class MatchValidator {

	public MatchValidator() {

	}

	public void validate(MatchDTO match) throws IllegalArgumentException,
			NoSuchFieldException {

		if (match == null) {
			throw new IllegalArgumentException("Objeto de partida invalido.");
		}

		if (match.getMatchControllCommands() == null
				|| match.getMatchControllCommands().isEmpty()
				|| match.getMatchControllCommands().size() < 2) {

			throw new NoSuchFieldException(
					"Nao foi encontrado os camandos corretos de inicio e fim de partida no log.");

		}

		if (match.getScoreboard() == null || match.getScoreboard().isEmpty()) {

			throw new NoSuchFieldException(
					"Nao foi encontrado nenhuma entrada morte na partida.");

		}

	}

}
