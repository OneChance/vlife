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
import com.vlife.action.actions.ActionClose;
import com.vlife.action.actions.ActionForage;
import com.vlife.action.actions.ActionSleep;
import com.vlife.action.entity.Action;
import com.vlife.action.entity.ActionInfo;
import com.vlife.action.service.ActionService;
import com.vlife.gm.entity.Species;
import com.vlife.gm.service.GameService;
import com.vlife.tool.JsonTool;
import com.vlife.tool.Message;

@Controller
public class BaseAction {

	@RequestMapping("action")
	public String action(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return "action/action";
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

	@RequestMapping("actionlog")
	public String actionlog(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Account account = accountService.getLoginAccount(request);
		Species species = gameService.getSpeice(account);

		List<Action> actionCompleted = actionService
				.getActionCompleted(account);

		if (actionCompleted != null) {
			request.setAttribute("actions", actionCompleted);
		}

		request.setAttribute("account", account);
		request.setAttribute("species", species);

		return "action/actionlog";
	}

	@RequestMapping("sleep")
	public JsonTool sleep(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Account account = accountService.getLoginAccount(request);
		Species species = gameService.getSpeice(account);
		Integer duration = species.getSleepTime();
		ActionSleep sleep = new ActionSleep();
		return durationActionExe(request, "sleep", duration, account, sleep);
	}

	@RequestMapping("forage")
	public JsonTool forage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Account account = accountService.getLoginAccount(request);
		Species species = gameService.getSpeice(account);
		Integer duration = species.getForageTime();
		ActionForage forage = new ActionForage();
		return durationActionExe(request, "forage", duration, account, forage);
	}

	public JsonTool durationActionExe(HttpServletRequest request,
			String actionCode, Integer duration, Account account,
			ActionClose actionClose) throws Exception {

		JsonTool jt = JsonTool.getJson("");

		Action running = actionService.getRunAction(account);

		if (running == null) {

			Action action = new Action();
			action.setAccount(account.getId());
			action.setCode(actionCode);

			Calendar c = Calendar.getInstance();
			Date now = new Date();
			c.setTime(now);
			c.add(Calendar.MINUTE, duration);
			Date end = c.getTime();

			action.setStartTime(now);
			action.setEndTime(end);
			action.setStatus(0);

			actionService.addAction(action);

			jt.setMessage(Message.getMessage(request, "start")
					+ Message.getMessage(request, "action_" + action.getCode()));

		} else {
			if (running.getCode() != null
					&& running.getCode().equals(actionCode)) {

				actionClose.setRunning(running);
				actionClose.setContext(Message.getContext(request));
				actionClose.setGameService(gameService);
				actionClose.setAccount(account);

				actionService.actionClose(actionClose);

				jt.setMessage(running.getDetail());

			} else {
				jt.setMessage(Message.getMessage(request, "actionrunning"));
				jt.setSuccess(false);
			}
		}

		return jt;
	}

	@RequestMapping("delete")
	public JsonTool delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		JsonTool jt = JsonTool.getJson("");
		boolean success = false;

		String actionId = request.getParameter("actionId");

		if (actionId != null && !actionId.equals("")) {
			Account account = accountService.getLoginAccount(request);
			Action action = actionService.getAction(Long.parseLong(actionId));

			if (action != null
					&& action.getAccount().longValue() == account.getId()
							.longValue()) {

				actionService.deleteAction(action);

				success = true;
			}
		}

		if (!success) {
			jt.setSuccess(false);
			jt.setMessage(Message.getMessage(request, "action_info_error"));
		} else {
			jt.setMessage(Message.getMessage(request, "action_deleted"));
		}

		return jt;
	}

	@Resource
	public AccountService accountService;
	@Resource
	public GameService gameService;
	@Resource
	public ActionService actionService;
}
