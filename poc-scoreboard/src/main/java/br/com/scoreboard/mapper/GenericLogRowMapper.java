package br.com.scoreboard.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenericLogRowMapper {

	protected static final int ENTRY_DATE_POSITION = 0;

	protected static final int ENTRY_CONTENT_POSITION = 1;

	protected static final String DATE_AND_CONTENT_SEPARATOR_PATTERN = "-";

	protected static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss");

	protected Date entryDate;

	protected String entryContent;

	public GenericLogRowMapper() {

	}

	public void preParse(String line) throws IllegalArgumentException {

		try {

			final String[] lineParts = this.lineSpliter(line,
					DATE_AND_CONTENT_SEPARATOR_PATTERN);

			final Date entryDate = SIMPLE_DATE_FORMAT
					.parse(lineParts[ENTRY_DATE_POSITION].trim());

			this.setEntryDate(entryDate);

			this.setEntryContent(lineParts[ENTRY_CONTENT_POSITION].trim());

		} catch (ParseException | NullPointerException
				| IndexOutOfBoundsException exception) {

			throw new IllegalArgumentException(
					"Não foi possível fazer o parse da data e conteudo do registro.",
					exception);

		}

	}

	protected String[] lineSpliter(final String line, final String pattern) {

		final String[] result = line.split(pattern);

		return result;

	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getEntryContent() {
		return entryContent;
	}

	public void setEntryContent(String entryContent) {
		this.entryContent = entryContent;
	}

}
