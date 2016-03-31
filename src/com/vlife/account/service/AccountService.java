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

	public String checkAccount(Account account) throws Exception {

		String inputRes = accountInputCheck(account);

		if (!inputRes.equals("")) {
			return inputRes;
		}

		Account accountDb = this.get(Account.class,
				"select * from account where account=?",
				new String[] { account.getAccount() });

		if (accountDb != null) {
			return "accountexist";
		}

		return "";
	}

	public void saveAccount(Account account) throws Exception {

		gameService.initAccount(account);

		this.save(account);
	}

	public String checkLogin(Account account) throws Exception {

		String inputRes = accountInputCheck(account);

		if (!inputRes.equals("")) {
			return inputRes;
		}

		Account account_db = this.get(Account.class,
				"select * from account where account=? and password=?",
				new String[] { account.getAccount(), account.getPassword() });

		if (account_db == null) {
			return "accounterror";
		}

		return "";
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

		if (account != null) {
			account = this.getAccount(account.getId().toString());
		}

		return account;
	}

	@Resource
	private GameService gameService;
}
