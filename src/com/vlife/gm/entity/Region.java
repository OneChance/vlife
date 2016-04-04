package com.vlife.gm.entity;

import java.util.ArrayList;
import java.util.List;

public class Region {
	private Integer id;
	private Integer pid;
	private String name;
	private String type;
	private List<Region> subRegions;
	private boolean able;
	private Integer deep;

	public Region() {
		subRegions = new ArrayList<Region>();
		id = 0;
		pid = 0;
		name = "";
		deep = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getPid() {
		return pid;
	}

	public List<Region> getSubRegions() {
		return subRegions;
	}

	public void setSubRegions(List<Region> subRegions) {
		this.subRegions = subRegions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isAble() {
		return able;
	}

	public void setAble(boolean able) {
		this.able = able;
	}

	public Integer getDeep() {
		return deep;
	}

	public void setDeep(Integer deep) {
		this.deep = deep;
	}
	
}
