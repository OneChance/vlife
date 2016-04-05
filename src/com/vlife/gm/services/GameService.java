package com.vlife.gm.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.vlife.account.entity.Account;
import com.vlife.database.service.DatabaseService;
import com.vlife.gm.entity.Region;
import com.vlife.gm.entity.RegionInfo;
import com.vlife.gm.entity.RegionTree;
import com.vlife.gm.entity.Species;

@Service
public class GameService extends DatabaseService {

	public Map<String, String> speciesRegion = new HashMap<String, String>() {

		private static final long serialVersionUID = 1L;

		{
			put("fish", "river,");
			put("bird", "city,forest");
		}
	};

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

		return Math.max(0, totalTime - passTime);
	}

	public Integer calSoulGet(Account account, Species species) {

		Integer specieSoul = species.getSoul();
		Integer specisLevel = account.getLevel();

		return specieSoul * specisLevel;
	}

	public String reincarnate(Account account) throws Exception {

		Species species = this.getSpeice(account);

		if (this.getRemainTime(account, species) > 0) {
			return "stillremaintime";
		}

		Integer soul = this.calSoulGet(account, species);
		Integer sumSoul = account.getSoul() + soul;
		account.setSoul(sumSoul);

		initAccount(account);

		Species newSpecies = this.get(Species.class, account.getSpecie());
		account.setSoul(sumSoul - newSpecies.getSoul());

		assetConvert(account);

		this.merge(account);

		return "";
	}

	public String changeProp(Account account, Account prop) throws Exception {

		Species species = this.getSpeice(account);

		if (this.getRemainTime(account, species) < 24 * 60 * 60 * 1000) {
			return "cannotchangefromtime";
		}

		Integer addPow = prop.getAddPow();
		Integer addDef = prop.getAddDef();
		Integer addDex = prop.getAddDex();
		Integer addInt = prop.getAddInt();
		Integer addHp = prop.getAddHp();

		if (addPow + addDef + addDex + addInt + addHp > account.getSoul()) {
			return "notenoughsoul";
		}

		if (addPow < (0 - account.getAddPow())
				|| addDef < (0 - account.getAddDef())
				|| addDex < (0 - account.getAddDex())
				|| addInt < (0 - account.getAddInt())
				|| addHp < (0 - account.getAddHp())) {
			return "propdataerror";
		}

		account.setAddPow(account.getAddPow() + addPow);
		account.setAddDef(account.getAddDef() + addDef);
		account.setAddDex(account.getAddDex() + addDex);
		account.setAddInt(account.getAddInt() + addInt);
		account.setAddHp(account.getAddHp() + addHp);
		account.setSoul(account.getSoul()
				- (addPow + addDef + addDex + addInt + addHp));

		this.merge(account);

		return "";
	}

	public void assetConvert(Account account) {

	}

	public RegionTree getRegionTree(Species species, Account account) {

		String sql = "select * from region";
		List<Region> rList = this.getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper<Region>(Region.class));

		RegionTree rTree = new RegionTree();

		for (Region r : rList) {
			if (species != null) {
				setAbleBySpecies(r, species);
			}

			if (r.getId().intValue() == account.getRegion().intValue()) {
				r.setColor("#2c3e50");
			}

			rTree.addRegion(r);
		}

		rTree.setDeep(rTree.getRoot(), 1);

		return rTree;
	}

	public void setAbleBySpecies(Region r, Species s) {
		if (r.getType() != null) {
			if (speciesRegion.get(s.getName()).contains(r.getType())) {
				r.setAble(true);
			} else {
				r.setAble(false);
			}
		}
	}

	public RegionInfo getRegionInfo(Account account, Integer regionId) {
		RegionInfo ri = new RegionInfo();

		RegionTree rTree = this.getRegionTree(null, account);

		Integer cost = rTree.getDistance(account.getRegion(), regionId);

		if (cost < 0) {
			return null;
		}

		ri.setCost(cost);

		return ri;
	}
}
