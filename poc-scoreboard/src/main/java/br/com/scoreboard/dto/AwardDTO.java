package br.com.scoreboard.dto;

import com.google.gson.Gson;

import br.com.scoreboard.enums.AwardTypeEnum;

public class AwardDTO {

	private int sequentialKills;

	private AwardTypeEnum awardTypeEnum;

	public AwardDTO() {

	}

	public int getSequentialKills() {
		return sequentialKills;
	}

	public void setSequentialKills(int sequentialKills) {
		this.sequentialKills = sequentialKills;
	}

	public AwardTypeEnum getAwardTypeEnum() {
		return awardTypeEnum;
	}

	public void setAwardTypeEnum(AwardTypeEnum awardTypeEnum) {
		this.awardTypeEnum = awardTypeEnum;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
