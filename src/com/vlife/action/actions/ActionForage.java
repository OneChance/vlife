package com.vlife.action.actions;

import java.util.List;
import java.util.Random;

import com.vlife.gm.entity.Inventory;
import com.vlife.gm.entity.Species;

public class ActionForage extends ActionClose {

	public static int FULL_FORAGE_VIGOR_COST = 10;
	public static int FULL_FORAGE_FOOD_EARN = 5;

	@Override
	public void closeAction() throws Exception {
		// TODO Auto-generated method stub
		Species species = gameService.getSpeice(account);

		Integer pastTime = this.pastTime(species.getForageTime());

		String[] foods = species.getFoods().split(",");
		String foodId = foods[new Random().nextInt(foods.length)];
		Species food = gameService.getSpeciesInfo().get(
				Integer.parseInt(foodId));

		boolean isNPCFood = food.getLifetime() == 0;

		if (!isNPCFood) {

		}

		Integer getNum = (new Random().nextInt(FULL_FORAGE_FOOD_EARN) + 1)
				* pastTime / species.getForageTime();
		Integer vigorCost = FULL_FORAGE_VIGOR_COST * pastTime
				/ species.getForageTime();

		String detail = "";

		if (getNum > 0) {
			Inventory i = new Inventory();
			i.setAccount(account.getId());
			i.setType("food");
			i.setTypeId(food.getId());
			i.setName(food.getName());
			i.setNum(getNum);
			i.setHpRecover(food.getHpRecover());
			i.setVigorRecover(food.getVigorRecover());
			i.setSatietyRecover(food.getSatietyRecover());

			boolean haveSameType = false;

			List<Inventory> inventoryList = gameService
					.getInventoryByAccount(account);

			for (Inventory inventory : inventoryList) {
				if ((inventory.getType() + inventory.getTypeId()).equals((i
						.getType() + i.getTypeId()))) {
					haveSameType = true;
					inventory.setNum(inventory.getNum() + i.getNum());
					break;
				}
			}

			if (!haveSameType) {
				inventoryList.add(i);
			}

			detail = context.getMessage("actionearn") + ":"
					+ context.getMessage(food.getName()) + "X" + getNum + ".";

			gameService.merge(inventoryList);
		} else {
			detail = context.getMessage("actionearn") + "无.";
		}

		detail = detail + context.getMessage("actioncost") + ":" + vigorCost
				+ context.getMessage("vigor") + ".";

		running.setDetail(detail);
	}
}
