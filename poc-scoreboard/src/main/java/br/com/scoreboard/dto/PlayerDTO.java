package br.com.scoreboard.dto;

import java.util.List;

import com.google.gson.Gson;

import br.com.scoreboard.enums.WeaponEnum;

public class PlayerDTO {

	private String name;

	private WeaponEnum favoriteWeapon;

	private long quantityOfKills = 0;

	private long quantityOfDeaths = 0;

	private List<AwardDTO> awards;

	public PlayerDTO() {

	}

	public void incrementQuantityOfKills() {
		this.quantityOfKills++;
	}

	public void incrementQuantityOfDeaths() {
		this.quantityOfDeaths++;
	}

	public long getQuantityOfKills() {
		return quantityOfKills;
	}

	public long getQuantityOfDeaths() {
		return quantityOfDeaths;
	}

	public void setQuantityOfDeaths(long quantityOfDeaths) {
		this.quantityOfDeaths = quantityOfDeaths;
	}

	public void setQuantityOfKills(long quantityOfKills) {
		this.quantityOfKills = quantityOfKills;
	}

	public PlayerDTO(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WeaponEnum getFavoriteWeapon() {
		return favoriteWeapon;
	}

	public void setFavoriteWeapon(WeaponEnum favoriteWeapon) {
		this.favoriteWeapon = favoriteWeapon;
	}

	public List<AwardDTO> getAwards() {
		return awards;
	}

	public void setAwards(List<AwardDTO> awards) {
		this.awards = awards;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((awards == null) ? 0 : awards.hashCode());
		result = prime * result
				+ ((favoriteWeapon == null) ? 0 : favoriteWeapon.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ (int) (quantityOfDeaths ^ (quantityOfDeaths >>> 32));
		result = prime * result
				+ (int) (quantityOfKills ^ (quantityOfKills >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerDTO other = (PlayerDTO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
