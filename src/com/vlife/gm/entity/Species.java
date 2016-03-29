package com.vlife.gm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "species")
public class Species {

	@Id
	private Long id;

	private String name;
	private Integer lifetime;
	private Integer basePow;
	private Integer baseDef;
	private Integer baseDex;
	private Integer baseHp;
	private Integer baseInt;
	private Integer soul;
	private Integer ratioStart;
	private Integer ratioEnd;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLifetime() {
		return lifetime;
	}

	public void setLifetime(Integer lifetime) {
		this.lifetime = lifetime;
	}

	public Integer getBasePow() {
		return basePow;
	}

	public void setBasePow(Integer basePow) {
		this.basePow = basePow;
	}

	public Integer getBaseDef() {
		return baseDef;
	}

	public void setBaseDef(Integer baseDef) {
		this.baseDef = baseDef;
	}

	public Integer getBaseDex() {
		return baseDex;
	}

	public void setBaseDex(Integer baseDex) {
		this.baseDex = baseDex;
	}

	public Integer getBaseHp() {
		return baseHp;
	}

	public void setBaseHp(Integer baseHp) {
		this.baseHp = baseHp;
	}

	public Integer getBaseInt() {
		return baseInt;
	}

	public void setBaseInt(Integer baseInt) {
		this.baseInt = baseInt;
	}

	public Integer getSoul() {
		return soul;
	}

	public void setSoul(Integer soul) {
		this.soul = soul;
	}

	public Integer getRatioStart() {
		return ratioStart;
	}

	public void setRatioStart(Integer ratioStart) {
		this.ratioStart = ratioStart;
	}

	public Integer getRatioEnd() {
		return ratioEnd;
	}

	public void setRatioEnd(Integer ratioEnd) {
		this.ratioEnd = ratioEnd;
	}
}
