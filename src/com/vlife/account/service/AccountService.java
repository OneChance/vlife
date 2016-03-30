package com.vlife.account.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.vlife.account.entity.Account;
import com.vlife.database.service.DatabaseService;
import com.vlife.gm.services.GameService;

@Service
public class AccountService extends DatabaseService {

	public String accountInputCheck(Account account) {

		if (account.getAccount() == null || account.getAccount().equals("")) {
			return "accountnull";
		}
		if (account.getPassword() == null || account.getPassword().equals("")) {
			return "passwordnull";
		}

		return "";
	}

	public Account checkAccount(Account account) throws Exception {

		String inputRes = accountInputCheck(account);

		if (!inputRes.equals("")) {
			account.setCheckMsg(inputRes);
			return null;
		}

		Account accountDb = this.get(Account.class,
				"select * from account where account=?",
				new String[] { account.getAccount() });

		if (accountDb != null) {
			account.setCheckMsg("accountexist");
			return null;
		}

		return account;
	}

	public void saveAccount(Account account) throws Exception {

		gameService.initAccount(account);

		this.save(account);
	}

	public Account checkLogin(Account account) throws Exception {

		String inputRes = accountInputCheck(account);

		if (!inputRes.equals("")) {
			account.setCheckMsg(inputRes);
			return null;
		}

		Account account_db = this.get(Account.class,
				"select * from account where account=? and password=?",
				new String[] { account.getAccount(), account.getPassword() });

		if (account_db == null) {
			account.setCheckMsg("accounterror");
			return null;
		}

		return account_db;
	}

	public Account getAccount(String id) throws NumberFormatException,
			Exception {
		Account account = this.get(Account.class, Long.parseLong(id));
		return account;
	}

	public Account getLoginAccount(HttpServletRequest request)
			throws NumberFormatException, Exception {
		Account account = (Account) request.getSession().getAttribute(
				"loginAccount");
		return account;
	}

	@Resource
	private GameService gameService;
}
