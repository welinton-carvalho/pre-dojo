package br.com.scoreboard.mapper;

import br.com.scoreboard.dto.KillDTO;
import br.com.scoreboard.dto.PlayerDTO;
import br.com.scoreboard.enums.WeaponEnum;

public class KillRowMapper extends GenericLogRowMapper {

	protected static final String CONTENT_SEPARATOR_PATTERN = " ";

	public KillRowMapper() {

	}

	public KillDTO parse(String line) throws IllegalArgumentException {

		super.preParse(line);

		KillDTO kill = new KillDTO();

		kill.setDate(super.getEntryDate());

		final String entryContent = this.getEntryContent();

		try {

			String[] killInformations = super.lineSpliter(entryContent,
					CONTENT_SEPARATOR_PATTERN);

			final String playerKillerName = killInformations[0];

			kill.setPlayerKiller(new PlayerDTO(playerKillerName));

			final String playerKilledName = killInformations[2];

			kill.setPlayerKilled(new PlayerDTO(playerKilledName));

			final String weaponUsed = killInformations[4];

			kill.setWeapon(WeaponEnum.valueOf(weaponUsed));

		} catch (NullPointerException | IndexOutOfBoundsException exception) {

			throw new IllegalArgumentException(
					"Não foi possível fazer o parse posicional do conteudo do log.",
					exception);

		}

		return kill;

	}

}
