package com.vlife.gm.services;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.vlife.database.service.DatabaseService;

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
		String sql = "select id from vlife.species where ratioStart<"
				+ randomValue + " and ratioEnd>" + randomValue;
		return this.getJdbcTemplate().queryForObject(sql, Integer.class);
	}
}
