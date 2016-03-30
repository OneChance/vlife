package com.vlife.gm.services;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.vlife.account.entity.Account;
import com.vlife.database.service.DatabaseService;
import com.vlife.gm.entity.Species;

@Service
public class GameService extends DatabaseService {

	public Integer reincarnation(Integer soul) {
		Random random = new Random();
		Integer max = getRandomMax();
		Integer randomValue = Math.min(random.nextInt(max) % (max - 1 + 1) + 1
				+ soul, max);
		return getSpeiceByRandomValue(randomValue);
	}

	public Integer getRandomMax() {
		String sql = "select max(ratioEnd) from species";
		return this.getJdbcTemplate().queryForObject(sql, Integer.class);
	}

	public Integer getSpeiceByRandomValue(Integer randomValue) {
		String sql = "select id from vlife.species where ratioStart<="
				+ randomValue + " and ratioEnd>=" + randomValue;
		return this.getJdbcTemplate().queryForObject(sql, Integer.class);
	}

	public void initAccount(Account account) throws Exception {
		Integer specie = reincarnation(account.getSoul());
		account.setSpecie(specie);
		account.setLevel(1);
		account.setExp(0);
		account.setReincarnateTime(new Date());
	}

	public Species getSpeice(Account account) throws Exception {
		Integer specie = account.getSpecie();
		Species species = this.get(Species.class, specie.longValue());
		return species;
	}

	public Long getRemainTime(Account account, Species species) {
		Long passTime = new Date().getTime()
				- account.getReincarnateTime().getTime();
		Long totalTime = species.getLifetime() * 24 * 60 * 60 * 1000l;

		return totalTime - passTime;
	}

	public Integer calSoulGet(Account account, Species species) {

		Integer specieSoul = species.getSoul();
		Integer specisLevel = account.getLevel();

		return specieSoul * specisLevel;
	}

	public Account reincarnate(Account account) throws Exception {

		Species species = this.getSpeice(account);

		if (this.getRemainTime(account, species) > 0) {
			account.setCheckMsg("stillremaintime");
			return null;
		}

		Integer soul = this.calSoulGet(account, species);
		Integer sumSoul = account.getSoul() + soul;
		account.setSoul(sumSoul);

		initAccount(account);

		Species newSpecies = this.get(Species.class, account.getSpecie());
		account.setSoul(sumSoul - newSpecies.getSoul());

		assetConvert(account);

		this.merge(account);

		return account;
	}

	public void assetConvert(Account account) {

	}
}
