package br.com.scoreboard.reader;

import br.com.scoreboard.dto.EntryMatchDTO;
import br.com.scoreboard.dto.KillDTO;
import br.com.scoreboard.mapper.ControllMatchRowMapper;
import br.com.scoreboard.mapper.KillRowMapper;

public class MatchReader {

	public MatchReader() {

	}

	public EntryMatchDTO loadLine(final String line) {

		try {

			if (line.contains("New match") || line.contains("has ended")) {

				ControllMatchRowMapper controllMatchRowMapper = new ControllMatchRowMapper();

				return controllMatchRowMapper.parse(line);

			} else if (line.contains("killed")) {

				KillRowMapper killRowMapper = new KillRowMapper();

				return killRowMapper.parse(line);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new KillDTO();

	}

}
