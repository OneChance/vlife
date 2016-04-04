package com.vlife.gm.entity;

import java.util.HashMap;
import java.util.Map;

public class RegionInfo {
	private Integer cost;
	private Map<Species,Integer> memberIn;
	
	public RegionInfo(){
		cost = 0;
		memberIn = new HashMap<Species,Integer>(){
			private static final long serialVersionUID = 1L;
		};
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Map<Species, Integer> getMemberIn() {
		return memberIn;
	}

	public void setMemberIn(Map<Species, Integer> memberIn) {
		this.memberIn = memberIn;
	}
	
	
}
