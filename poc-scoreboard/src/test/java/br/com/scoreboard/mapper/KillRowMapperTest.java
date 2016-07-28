package br.com.scoreboard.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

import br.com.scoreboard.dto.KillDTO;
import br.com.scoreboard.enums.WeaponEnum;

public class KillRowMapperTest {

	public static final Logger LOGGER = Logger.getAnonymousLogger();

	public static final String EXPECTED_EXCEPTION = "Exceção experada.";

	private KillRowMapper killRowMapper;

	public KillRowMapperTest() {

		this.killRowMapper = new KillRowMapper();

	}

	@Test
	public void parseTest() throws ParseException {

		String finalLog1 = "23/04/2013 15:36:04 - Roman killed Nick using M16";
		String finalLog2 = "23/04/2013 15:36:33 - <WORLD> killed Nick by DROWN";

		String nonParttern1 = "23/04/2013 15:34:22 - New match has started";
		String nonParttern2 = "23/04/2013 15:34:22 - New match 11348965 has";
		String nonParttern3 = "23/04/2013 15:34:22 - New match has";

		try {
			this.killRowMapper.parse(nonParttern1);
		} catch (IllegalArgumentException e) {
			LOGGER.info(EXPECTED_EXCEPTION);
		}

		try {
			this.killRowMapper.parse(nonParttern2);
		} catch (IllegalArgumentException e) {
			LOGGER.info(EXPECTED_EXCEPTION);
		}

		try {
			this.killRowMapper.parse(nonParttern3);
		} catch (IllegalArgumentException e) {
			LOGGER.info(EXPECTED_EXCEPTION);
		}

		KillDTO kill1 = this.killRowMapper.parse(finalLog1);

		Assert.assertEquals(kill1.getDate(), new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss").parse("23/04/2013 15:36:04"));

		Assert.assertEquals(kill1.getPlayerKiller().getName(), "Roman");

		Assert.assertEquals(kill1.getPlayerKilled().getName(), "Nick");

		Assert.assertEquals(kill1.getWeapon(), WeaponEnum.M16);

		KillDTO kill2 = this.killRowMapper.parse(finalLog2);

		Assert.assertEquals(kill2.getDate(), new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss").parse("23/04/2013 15:36:33"));

		Assert.assertEquals(kill2.getPlayerKiller().getName(), "<WORLD>");

		Assert.assertEquals(kill2.getPlayerKilled().getName(), "Nick");

		Assert.assertEquals(kill2.getWeapon(), WeaponEnum.DROWN);

	}

}
