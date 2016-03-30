package com.vlife.gm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vlife.account.entity.Account;
import com.vlife.account.service.AccountService;
import com.vlife.gm.entity.Species;
import com.vlife.gm.services.GameService;
import com.vlife.tool.JsonTool;
import com.vlife.tool.Message;
import com.vlife.tool.WebUtil;

@Controller
@RequestMapping("/")
public class GameController {

	@RequestMapping("")
	public String main(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Account account = accountService.getLoginAccount(request);
		boolean login = false;
		String profile = "profile.png";

		if (account != null) {
			Species species = gameService.getSpeice(account);
			Long remainTime = gameService.getRemainTime(account, species);
			request.setAttribute("species", species);
			request.setAttribute("remainTime", remainTime);
			profile = species.getName() + "/" + account.getLevel() + ".png";
			login = true;
		}

		request.setAttribute("profile", profile);
		request.setAttribute("login", login);

		return "main";
	}

	@RequestMapping("login")
	public JsonTool login(@ModelAttribute("account") Account account,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JsonTool jt = JsonTool.getJson("");
		Account checkedAccount = accountService.checkLogin(account);
		if (checkedAccount == null) {
			jt.setMessage(Message.getMessage(request, account.getCheckMsg()));
		} else {
			EnterGame(request, response, checkedAccount);
		}

		return jt;
	}

	@RequestMapping("reg")
	public JsonTool reg(@ModelAttribute("account") Account account,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JsonTool jt = JsonTool.getJson("");

		Account checkedAccount = accountService.checkAccount(account);

		if (checkedAccount == null) {
			jt.setMessage(Message.getMessage(request, account.getCheckMsg()));
		} else {
			accountService.saveAccount(account);
			EnterGame(request, response, account);
		}

		return jt;
	}

	@RequestMapping("unsign")
	public JsonTool unsign(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		JsonTool jt = JsonTool.getJson("");

		request.getSession().setAttribute("loginAccount", null);
		WebUtil.cleanCookies(response, "vlife_uinfo");

		return jt;
	}

	public void EnterGame(HttpServletRequest request,
			HttpServletResponse response, Account account) throws Exception {
		request.getSession().setAttribute("loginAccount", account);
		WebUtil.setCookies(response, "vlife_uinfo", account.getId().toString());
	}

	@RequestMapping("reincarnateGuide")
	public String reincarnateGuide(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Account account = accountService.getLoginAccount(request);
		Species species = gameService.getSpeice(account);

		request.setAttribute("soulget",
				gameService.calSoulGet(account, species));
		request.setAttribute("species", species);

		return "reincarnate_guide";
	}

	@RequestMapping("reincarnate")
	public JsonTool reincarnate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		JsonTool jt = JsonTool.getJson("");

		Account account = accountService.getLoginAccount(request);

		Account reincarnatedAccount = gameService.reincarnate(account);

		if (reincarnatedAccount == null) {
			jt.setMessage(account.getCheckMsg());
		} else {
			request.getSession().setAttribute("loginAccount", account);
		}

		return jt;
	}

	@Resource
	private AccountService accountService;
	@Resource
	private GameService gameService;
}