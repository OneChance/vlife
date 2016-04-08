package com.vlife.action.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vlife.account.entity.Account;
import com.vlife.account.service.AccountService;
import com.vlife.action.entity.Action;
import com.vlife.action.entity.ActionInfo;
import com.vlife.action.service.ActionService;
import com.vlife.gm.entity.Species;
import com.vlife.gm.service.GameService;
import com.vlife.tool.JsonTool;
import com.vlife.tool.Message;

@Controller
public class BaseAction {

	public static int FULL_SLEEP_HUNGER = 20;
	public static int FULL_SLEEP_VIGOR = 80;
	public static float FULL_SLEEP_HP = 0.3f;

	@RequestMapping("action")
	public String action(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return "action/action";
	}

	@RequestMapping("ableaction")
	public String ableaction(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return "action/ableaction";
	}

	@RequestMapping("myaction")
	public String myaction(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Account account = accountService.getLoginAccount(request);
		Species species = gameService.getSpeice(account);

		request.setAttribute("account", account);
		request.setAttribute("species", species);

		List<String> actionCodes = actionService.getAbleAction(species);
		List<ActionInfo> actionInfoList = new ArrayList<ActionInfo>();
		Action running = actionService.getRunAction(account);

		for (String code : actionCodes) {
			ActionInfo ai = new ActionInfo();
			ai.setCode(code);
			ai.setName(Message.getMessage(request, "action_" + code));
			ai.setCost(Message.getMessage(request, "action_cost_" + code));
			ai.setEarn(Message.getMessage(request, "action_earn_" + code));

			if (running != null && running.getCode().equals(ai.getCode())) {
				ai.setRemainTime(Math.max(0, running.getEndTime().getTime()
						- new Date().getTime()));
				if (ai.getRemainTime() == 0) {
					ai.setStatus(Message.getMessage(request, "actioncomplete"));
					ai.setBtncss("success");
				} else {
					ai.setStatus("");
					ai.setBtncss("danger");
					ai.setFontfa("remove");
				}

			} else {
				ai.setStatus(Message.getMessage(request, ai.getStatus()));
			}

			actionInfoList.add(ai);
		}

		request.setAttribute("actions", actionInfoList);

		return "action/myaction";
	}

	@RequestMapping("othersaction")
	public String othersaction(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return "action/othersaction";
	}

	@RequestMapping("sleep")
	public JsonTool sleep(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		JsonTool jt = JsonTool.getJson("");

		Account account = accountService.getLoginAccount(request);
		Species species = gameService.getSpeice(account);
		Action running = actionService.getRunAction(account);

		if (running == null) {

			Action action = new Action();
			action.setAccount(account.getId());
			action.setCode("sleep");

			Calendar c = Calendar.getInstance();
			Date now = new Date();
			c.setTime(now);
			c.add(Calendar.HOUR, species.getSleepTime());
			Date end = c.getTime();

			action.setStartTime(now);
			action.setEndTime(end);
			action.setStatus(0);

			actionService.addAction(action);

			jt.setMessage(Message.getMessage(request, "start")
					+ Message.getMessage(request, "action_" + action.getCode()));

		} else {
			if (running.getCode() != null && running.getCode().equals("sleep")) {

				Integer sleepHours = Math.min(species.getSleepTime(),
						(int) (new Date().getTime() - running.getStartTime()
								.getTime()) / (1000 * 60 * 60));

				Integer vigor = sleepHours * FULL_SLEEP_VIGOR
						/ species.getSleepTime();
				Integer hp = (int) (sleepHours * FULL_SLEEP_HP
						* (account.getAddHp() + species.getBaseHp()) / species
						.getSleepTime());
				Integer satiety = sleepHours * FULL_SLEEP_HUNGER
						/ species.getSleepTime();

				account.setVigor(Math.min(100, account.getVigor() + vigor));
				account.setHp(Math.min(
						(account.getAddHp() + species.getBaseHp()),
						account.getAddHp() + hp));
				account.setSatiety(Math.max(0, account.getSatiety() - satiety));

				actionService.actionClose(running, account);

				jt.setMessage(Message.getMessage(request, "actionearn") + ":"
						+ vigor + Message.getMessage(request, "vigor") + ","
						+ hp + Message.getMessage(request, "hp") + "."
						+ Message.getMessage(request, "actioncost") + ":"
						+ satiety + Message.getMessage(request, "satiety")
						+ ".");

			} else {
				jt.setMessage(Message.getMessage(request, "actionrunning"));
				jt.setSuccess(false);
			}
		}

		return jt;
	}

	@Resource
	private AccountService accountService;
	@Resource
	private GameService gameService;
	@Resource
	private ActionService actionService;
}
