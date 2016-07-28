package br.com.scoreboard.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

import br.com.scoreboard.dto.ControllMatchDTO;
import br.com.scoreboard.enums.MatchOperationEnum;

public class ControllMatchRowMapperTest {

	public static final Logger LOGGER = Logger.getAnonymousLogger();

	public static final String EXPECTED_EXCEPTION = "Exceção experada.";

	private ControllMatchRowMapper controllMatchRowMapper;

	public ControllMatchRowMapperTest() {

		this.controllMatchRowMapper = new ControllMatchRowMapper();

	}

	@Test
	public void parseTest() throws ParseException {

		String finalLog1 = "23/04/2013 15:34:22 - New match 11348965 has started";
		String finalLog2 = "23/04/2013 15:34:22 - Match 11348965 has ended";

		String nonParttern1 = "23/04/2013 15:34:22 - New match has started";
		String nonParttern2 = "23/04/2013 15:34:22 - New match 11348965 has";
		String nonParttern3 = "23/04/2013 15:34:22 - New match has";

		try {
			this.controllMatchRowMapper.parse(nonParttern1);
		} catch (IllegalArgumentException e) {
			LOGGER.info(EXPECTED_EXCEPTION);
		}

		try {
			this.controllMatchRowMapper.parse(nonParttern2);
		} catch (IllegalArgumentException e) {
			LOGGER.info(EXPECTED_EXCEPTION);
		}

		try {
			this.controllMatchRowMapper.parse(nonParttern3);
		} catch (IllegalArgumentException e) {
			LOGGER.info(EXPECTED_EXCEPTION);
		}

		ControllMatchDTO controllMatch1 = this.controllMatchRowMapper
				.parse(finalLog1);

		Assert.assertEquals(controllMatch1.getDate(), new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss").parse("23/04/2013 15:34:22"));

		Assert.assertEquals(controllMatch1.getId(), 11348965L);

		Assert.assertEquals(controllMatch1.getMatchOperation(),
				MatchOperationEnum.STARTED);

		ControllMatchDTO controllMatch2 = this.controllMatchRowMapper
				.parse(finalLog2);

		Assert.assertEquals(controllMatch2.getMatchOperation(),
				MatchOperationEnum.ENDED);

	}

}
