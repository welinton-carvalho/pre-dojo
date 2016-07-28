package br.com.scoreboard.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

public class GenericRowMapperTest {

	public static final Logger LOGGER = Logger.getAnonymousLogger();

	public static final String EXPECTED_EXCEPTION = "Exceção experada.";

	private GenericLogRowMapper genericLogRowMapper;

	public GenericRowMapperTest() {

		this.genericLogRowMapper = new GenericLogRowMapper();

	}

	@Test
	public void preParseTest() throws ParseException {

		String finalLog = "23/04/2013 15:34:22 - New match 11348965 has started";

		String nonParttern1 = "sfsdgdsg sdgsdgdg";
		String nonParttern2 = "23/04/2013 15:34:22  sdgsdgdg";

		try {
			this.genericLogRowMapper.preParse(nonParttern1);
		} catch (IllegalArgumentException e) {
			LOGGER.info(EXPECTED_EXCEPTION);
		}

		try {
			this.genericLogRowMapper.preParse(nonParttern2);
		} catch (IllegalArgumentException e) {
			LOGGER.info(EXPECTED_EXCEPTION);
		}

		this.genericLogRowMapper.preParse(finalLog);

		Assert.assertEquals(this.genericLogRowMapper.getEntryDate(),
				new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
						.parse("23/04/2013 15:34:22"));

		Assert.assertEquals(this.genericLogRowMapper.getEntryContent(),
				"New match 11348965 has started");

	}

}
