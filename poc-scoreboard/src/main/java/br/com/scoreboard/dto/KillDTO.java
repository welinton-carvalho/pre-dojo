package br.com.scoreboard.dto;

import com.google.gson.Gson;

import br.com.scoreboard.enums.WeaponEnum;

public class KillDTO extends EntryMatchDTO {

	private PlayerDTO playerKiller;

	private PlayerDTO playerKilled;

	private WeaponEnum weapon;

	public KillDTO() {

	}

	public PlayerDTO getPlayerKiller() {
		return playerKiller;
	}

	public void setPlayerKiller(PlayerDTO playerKiller) {
		this.playerKiller = playerKiller;
	}

	public PlayerDTO getPlayerKilled() {
		return playerKilled;
	}

	public void setPlayerKilled(PlayerDTO playerKilled) {
		this.playerKilled = playerKilled;
	}

	public WeaponEnum getWeapon() {
		return weapon;
	}

	public void setWeapon(WeaponEnum weapon) {
		this.weapon = weapon;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
