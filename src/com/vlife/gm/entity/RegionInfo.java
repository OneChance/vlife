package com.vlife.gm.entity;

import java.util.HashMap;
import java.util.Map;

public class RegionInfo {
	private Integer cost;
	private Map<String, Integer> memberIn;

	public RegionInfo() {
		cost = 0;
		memberIn = new HashMap<String, Integer>() {
			private static final long serialVersionUID = 1L;
		};
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Map<String, Integer> getMemberIn() {
		return memberIn;
	}

	public void setMemberIn(Map<String, Integer> memberIn) {
		this.memberIn = memberIn;
	}

}
