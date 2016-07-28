package br.com.scoreboard.dto;

import com.google.gson.Gson;

import br.com.scoreboard.enums.MatchOperationEnum;

public class ControllMatchDTO extends EntryMatchDTO {

	private long id;

	private MatchOperationEnum matchOperation;

	public ControllMatchDTO() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MatchOperationEnum getMatchOperation() {
		return matchOperation;
	}

	public void setMatchOperation(MatchOperationEnum matchOperation) {
		this.matchOperation = matchOperation;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
