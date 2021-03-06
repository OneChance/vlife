package com.vlife.gm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vlife.account.entity.Account;
import com.vlife.account.service.AccountService;
import com.vlife.gm.entity.Inventory;
import com.vlife.gm.entity.RegionInfo;
import com.vlife.gm.entity.RegionTree;
import com.vlife.gm.entity.Species;
import com.vlife.gm.service.GameService;
import com.vlife.tool.JsonTool;
import com.vlife.tool.Message;
import com.vlife.tool.WebUtil;

@Controller
@RequestMapping("/")
public class GameController {

	@RequestMapping("")
	public String main(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Account account = accountService.getLoginAccount(request);
		boolean login = false;
		String profile = "profile.png";

		if (account != null) {
			Species species = gameService.getSpeice(account);
			Long remainTime = gameService.getRemainTime(account, species);
			request.setAttribute("account", account);
			request.setAttribute("species", species);
			request.setAttribute("remainTime", remainTime);
			profile = species.getName() + "/" + account.getLevel() + ".png";
			login = true;
		}

		request.setAttribute("profile", profile);
		request.setAttribute("login", login);

		return "main";
	}

	@RequestMapping("reg")
	public JsonTool reg(@ModelAttribute("account") Account account, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		JsonTool jt = JsonTool.getJson("");

		String checkRes = accountService.checkAccount(account);

		if (!checkRes.equals("")) {
			jt.setMessage(Message.getMessage(request, checkRes));
		} else {
			accountService.saveAccount(account);
			EnterGame(request, response, account);
		}

		return jt;
	}

	@RequestMapping("unsign")
	public JsonTool unsign(HttpServletRequest request, HttpServletResponse response) throws Exception {

		JsonTool jt = JsonTool.getJson("");
		request.getSession().setAttribute("loginAccount", null);
		WebUtil.cleanCookies(response, "vlife_uinfo");

		return jt;
	}

	public void EnterGame(HttpServletRequest request, HttpServletResponse response, Account account) throws Exception {
		WebUtil.setCookies(response, "vlife_uinfo", account.getId().toString());
		Account accountSession = new Account();
		accountSession.setId(account.getId());
		request.getSession().setAttribute("loginAccount", accountSession);
	}

	@RequestMapping("reincarnateGuide")
	public String reincarnateGuide(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Account account = accountService.getLoginAccount(request);
		Species species = gameService.getSpeice(account);

		request.setAttribute("soulget", gameService.calSoulGet(account, species));
		request.setAttribute("species", species);

		return "reincarnate_guide";
	}

	@RequestMapping("property")
	public String property(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Account account = accountService.getLoginAccount(request);
		Species species = gameService.getSpeice(account);

		request.setAttribute("account", account);
		request.setAttribute("species", species);
		request.setAttribute("remainTime", gameService.getRemainTime(account, species));

		Integer hpPercent = account.getHp() * 100 / (account.getAddHp() + species.getBaseHp());

		request.setAttribute("hpPercent", hpPercent);
		request.setAttribute("vigorPercent", account.getVigor());
		request.setAttribute("satietyPercent", account.getSatiety());

		return "property";
	}

	@RequestMapping("reincarnate")
	public JsonTool reincarnate(HttpServletRequest request, HttpServletResponse response) throws Exception {

		JsonTool jt = JsonTool.getJson("");

		Account account = accountService.getLoginAccount(request);

		String res = gameService.reincarnate(account);

		if (!res.equals("")) {
			jt.setMessage(Message.getMessage(request, res));
		}

		return jt;
	}

	@RequestMapping("changeprop")
	public JsonTool changeprop(@ModelAttribute("account") Account propAccount, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		JsonTool jt = JsonTool.getJson("");

		Account account = accountService.getLoginAccount(request);

		String res = gameService.changeProp(account, propAccount);

		if (!res.equals("")) {
			jt.setMessage(Message.getMessage(request, res));
		}

		return jt;
	}

	@RequestMapping("region")
	public String region(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Account account = accountService.getLoginAccount(request);
		Species species = gameService.getSpeice(account);

		RegionTree rTree = gameService.getRegionTree(species, account, Message.getContext(request));

		String data = JsonTool.getJson(rTree.getRoot()).getData().toString();

		data = "["
				+ data.replaceAll("name", "text").replaceAll("subRegions", "nodes").replaceAll(",\"nodes\":\\[\\]", "")
				+ "]";

		request.setAttribute("treeData", data);
		request.setAttribute("account", account);
		request.setAttribute("species", species);

		return "region";
	}

	@RequestMapping("regionInfo")
	public JsonTool regionInfo(@ModelAttribute("account") Account propAccount, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String regionId = request.getParameter("regionId");

		JsonTool jt = JsonTool.getJson("");

		if (regionId != null && !regionId.equals("")) {
			Account account = accountService.getLoginAccount(request);
			RegionInfo ri = gameService.getRegionInfo(account, Integer.parseInt(regionId), Message.getContext(request));

			if (ri == null) {
				jt.setMessage(Message.getMessage(request, "regioninfoerror"));
			} else {
				jt.setData(ri);
			}
		}

		return jt;
	}

	@RequestMapping("regionMove")
	public JsonTool regionMove(HttpServletRequest request, HttpServletResponse response) throws Exception {

		JsonTool jt = JsonTool.getJson("");

		String regionId = request.getParameter("regionId");

		if (regionId != null && !regionId.equals("")) {
			Account account = accountService.getLoginAccount(request);

			String res = gameService.regionMove(account, Integer.parseInt(regionId));

			if (!res.equals("")) {
				jt.setMessage(Message.getMessage(request, res));
			}
		}

		return jt;
	}

	@RequestMapping("inventory")
	public String inventory(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Account account = accountService.getLoginAccount(request);

		List<Inventory> inventoryList = gameService.getInventoryByAccount(account);

		for (Inventory inventory : inventoryList) {
			gameService.setInventoryDetail(inventory);
		}

		request.setAttribute("inventoryList", inventoryList);

		return "inventory";
	}
	
	@RequestMapping("/login")
	public JsonTool login2(@ModelAttribute("account") Account account, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		JsonTool jt = JsonTool.getJson("");
		String checkRes = accountService.checkLogin(account);
		if (!checkRes.equals("")) {
			jt.setMessage(Message.getMessage(request, checkRes));
		} else {
			EnterGame(request, response, account);
		}

		return jt;
	}
	

	@Resource
	private AccountService accountService;
	@Resource
	private GameService gameService;
}