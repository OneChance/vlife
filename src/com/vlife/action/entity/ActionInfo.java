package com.vlife.action.entity;

public class ActionInfo {

	private String code;
	private String name;
	private String cost;
	private String earn;
	private long remainTime;
	private String status;
	private String btncss;
	private String fontfa;

	public ActionInfo() {
		btncss = "primary";
		status = "actiongo";
		fontfa = "check";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getEarn() {
		return earn;
	}

	public void setEarn(String earn) {
		this.earn = earn;
	}

	public long getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(long remainTime) {
		this.remainTime = remainTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBtncss() {
		return btncss;
	}

	public void setBtncss(String btncss) {
		this.btncss = btncss;
	}

	public String getFontfa() {
		return fontfa;
	}

	public void setFontfa(String fontfa) {
		this.fontfa = fontfa;
	}

}
