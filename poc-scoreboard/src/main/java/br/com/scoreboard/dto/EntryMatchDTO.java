package br.com.scoreboard.dto;

import java.util.Date;

import com.google.gson.Gson;

public class EntryMatchDTO {

	private Date date;

	public EntryMatchDTO() {

	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
