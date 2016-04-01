package com.vlife.gm.entity;

import java.util.ArrayList;
import java.util.List;

public class RegionTree {

	private Region root;

	public RegionTree() {
		root = new Region();
		root.setId(-1);
		root.setName("world");
	}

	public void addRegion(Region r) {

		for (int i = 0; i < root.getSubRegions().size(); i++) {
			if (root.getSubRegions().get(i).getPid().intValue() == r.getId()
					.intValue()) {
				r.getSubRegions().add(root.getSubRegions().get(i));
				root.getSubRegions().remove(i);
				i--;
			}
		}

		Region parentR = this.getRegionById(r.getPid());

		if (parentR == null) {
			root.getSubRegions().add(r);
		} else {
			parentR.getSubRegions().add(r);
		}
	}

	public Region getRegionById(Integer id) {
		List<Region> rList = new ArrayList<Region>();
		this.findRegion(root, rList);
		for (Region r : rList) {
			if (r.getId().intValue() == id.intValue()) {
				return r;
			}
		}
		return null;
	}

	public void findRegion(Region r, List<Region> rList) {

		rList.add(r);

		if (r.getSubRegions().size() > 0) {
			for (Region rs : r.getSubRegions()) {
				findRegion(rs, rList);
			}
		}
	}

	public void printTree(Region r, String head) {

		System.out.println(head + r.getName());

		if (r.getSubRegions().size() > 0) {
			for (Region rs : r.getSubRegions()) {
				printTree(rs, head + "--");
			}
		}
	}

	public Region getRoot() {
		return root;
	}

	public void setRoot(Region root) {
		this.root = root;
	}

}
