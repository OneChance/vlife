package com.vlife.gm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vlife.account.entity.Account;
import com.vlife.account.service.AccountService;
import com.vlife.tool.JsonTool;
import com.vlife.tool.Message;
import com.vlife.tool.WebUtil;

@Controller
@RequestMapping("/")
public class GameController {

	@RequestMapping("")
	public String main(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return "main";
	}

	@RequestMapping("login")
	public JsonTool login(@ModelAttribute("account") Account account,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JsonTool jt = JsonTool.getJson("");
		String resCode = accountService.checkLogin(account);
		if (!resCode.equals("")) {
			jt.setMessage(Message.getMessage(request, resCode));
		} else {
			EnterGame(request, response, account);
		}

		return jt;
	}

	@RequestMapping("reg")
	public JsonTool reg(@ModelAttribute("account") Account account,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JsonTool jt = JsonTool.getJson("");

		String resCode = accountService.checkAccount(account);

		if (!resCode.equals("")) {
			jt.setMessage(Message.getMessage(request, resCode));
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

	@Resource
	private AccountService accountService;
}