package br.com.scoreboard.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.scoreboard.enums.MatchOperationEnum;

import com.google.gson.Gson;

public class MatchDTO {

	private ArrayList<KillDTO> scoreboard;

	private List<ControllMatchDTO> matchControllCommands;

	private int totalOfKills;

	public MatchDTO() {

	}

	public int getTotalOfKills() {
		return totalOfKills;
	}

	public void setTotalOfKills(int totalOfKills) {
		this.totalOfKills = totalOfKills;
	}

	public List<ControllMatchDTO> getMatchControllCommands() {
		return matchControllCommands;
	}

	public void setMatchControllCommands(
			List<ControllMatchDTO> matchControllCommands) {
		this.matchControllCommands = matchControllCommands;
	}

	public ArrayList<KillDTO> getScoreboard() {
		return scoreboard;
	}

	public void setScoreboard(ArrayList<KillDTO> scoreboard) {
		this.scoreboard = scoreboard;
	}

	public void addKillToScoreboard(KillDTO kill) {
		if (this.scoreboard == null) {
			this.scoreboard = new ArrayList<>();
		}
		this.scoreboard.add(kill);
	}

	public void addMatchControllCommandsToScoreboard(
			ControllMatchDTO controllMatch) {
		if (this.matchControllCommands == null) {
			this.matchControllCommands = new ArrayList<ControllMatchDTO>();
		}
		this.matchControllCommands.add(controllMatch);
	}

	public String getDuratrion() {

		String durationMatch = null;

		Date initialDate = null, finalDate = null;
		for (ControllMatchDTO controllMatchDTO : this.matchControllCommands) {

			if (MatchOperationEnum.STARTED.equals(controllMatchDTO
					.getMatchOperation())) {

				initialDate = controllMatchDTO.getDate();

			} else if (MatchOperationEnum.ENDED.equals(controllMatchDTO
					.getMatchOperation())) {

				finalDate = controllMatchDTO.getDate();

			}

		}

		if (finalDate != null && initialDate != null) {

			durationMatch = String.format("%,.2f", (Double.valueOf(finalDate
					.getTime() - initialDate.getTime()) / (1000 * 60)))
					+ "  minuts";

		}

		return durationMatch;

	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
