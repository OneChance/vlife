package com.vlife.action.actions;

import com.vlife.gm.entity.Species;

public class ActionSleep extends ActionClose {

	public static int FULL_SLEEP_HUNGER_COST = 20;
	public static int FULL_SLEEP_VIGOR_EARN = 80;
	public static float FULL_SLEEP_HP_EARN = 0.3f;

	@Override
	public void closeAction() throws Exception {
		// TODO Auto-generated method stub
		Species species = gameService.getSpeice(account);

		Integer pastTime = this.pastTime(species.getSleepTime());

		Integer vigor = pastTime * FULL_SLEEP_VIGOR_EARN
				/ species.getSleepTime();
		Integer hp = (int) (pastTime * FULL_SLEEP_HP_EARN
				* (account.getAddHp() + species.getBaseHp()) / species
				.getSleepTime());
		Integer satiety = pastTime * FULL_SLEEP_HUNGER_COST
				/ species.getSleepTime();

		account.setVigor(Math.min(100, account.getVigor() + vigor));
		account.setHp(Math.min((account.getAddHp() + species.getBaseHp()),
				account.getAddHp() + hp));
		account.setSatiety(Math.max(0, account.getSatiety() - satiety));

		String detail = context.getMessage("actionearn") + ":" + vigor
				+ context.getMessage("vigor") + "," + hp
				+ context.getMessage("hp") + "."
				+ context.getMessage("actioncost") + ":" + satiety
				+ context.getMessage("satiety") + ".";

		running.setDetail(detail);
	}
}
