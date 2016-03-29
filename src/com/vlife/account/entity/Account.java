package com.vlife.account.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String account;
	private String name;
	private String password;
	private String sex;
	private Integer specie;
	private Integer soul;
	private Integer addPow;
	private Integer addDef;
	private Integer addHp;
	private Integer addDex;
	private Integer addInt;
	private Integer money;
	private Integer house;
	private Integer spouse;
	private String friends;
	private String profileImg;

	public Account() {
		this.specie = 0;
		this.soul = 0;
		this.addPow = 0;
		this.addDef = 0;
		this.addHp = 0;
		this.addDex = 0;
		this.addInt = 0;
		this.money = 0;
		this.house = 0;
		this.spouse = 0;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getSpecie() {
		return specie;
	}

	public void setSpecie(Integer specie) {
		this.specie = specie;
	}

	public Integer getSoul() {
		return soul;
	}

	public void setSoul(Integer soul) {
		this.soul = soul;
	}

	public Integer getAddPow() {
		return addPow;
	}

	public void setAddPow(Integer addPow) {
		this.addPow = addPow;
	}

	public Integer getAddDef() {
		return addDef;
	}

	public void setAddDef(Integer addDef) {
		this.addDef = addDef;
	}

	public Integer getAddHp() {
		return addHp;
	}

	public void setAddHp(Integer addHp) {
		this.addHp = addHp;
	}

	public Integer getAddDex() {
		return addDex;
	}

	public void setAddDex(Integer addDex) {
		this.addDex = addDex;
	}

	public Integer getAddInt() {
		return addInt;
	}

	public void setAddInt(Integer addInt) {
		this.addInt = addInt;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getHouse() {
		return house;
	}

	public void setHouse(Integer house) {
		this.house = house;
	}

	public Integer getSpouse() {
		return spouse;
	}

	public void setSpouse(Integer spouse) {
		this.spouse = spouse;
	}

	public String getFriends() {
		return friends;
	}

	public void setFriends(String friends) {
		this.friends = friends;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

}
